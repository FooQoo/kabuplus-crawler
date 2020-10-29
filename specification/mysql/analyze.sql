WITH MOVING_AVG AS (
	SELECT
		code,
		crawled_date,
		closed,
		AVG(closed) OVER (PARTITION BY code ORDER BY crawled_date ROWS BETWEEN 5 PRECEDING AND CURRENT ROW) AS avg_price_five_days,
		AVG(closed) OVER (PARTITION BY code ORDER BY crawled_date ROWS BETWEEN 20 PRECEDING AND CURRENT ROW) AS avg_price_twenty_days,
		AVG(closed) OVER (PARTITION BY code ORDER BY crawled_date ROWS BETWEEN 40 PRECEDING AND CURRENT ROW) AS avg_price_fourty_days
	FROM
		price
),
LATEST_THREE_DAYS AS (
	SELECT DISTINCT(crawled_date) AS crawled_date FROM price ORDER BY crawled_date DESC LIMIT 3
),
PRICE_STAGE AS (
	SELECT
		A.code,
		A.closed,
		A.avg_price_five_days,
		A.avg_price_twenty_days,
		A.avg_price_fourty_days,
		CASE
			WHEN A.avg_price_five_days >= A.avg_price_twenty_days AND A.avg_price_five_days >= A.avg_price_fourty_days AND A.avg_price_twenty_days >= A.avg_price_fourty_days THEN 1
			WHEN A.avg_price_five_days < A.avg_price_twenty_days AND A.avg_price_five_days >= A.avg_price_fourty_days AND A.avg_price_twenty_days >= A.avg_price_fourty_days THEN 2
			WHEN A.avg_price_five_days < A.avg_price_twenty_days AND A.avg_price_five_days < A.avg_price_fourty_days AND A.avg_price_twenty_days >= A.avg_price_fourty_days THEN 3
			WHEN A.avg_price_five_days < A.avg_price_twenty_days AND A.avg_price_five_days < A.avg_price_fourty_days AND A.avg_price_twenty_days < A.avg_price_fourty_days THEN 4
			WHEN A.avg_price_five_days >= A.avg_price_twenty_days AND A.avg_price_five_days < A.avg_price_fourty_days AND A.avg_price_twenty_days < A.avg_price_fourty_days THEN 5
			WHEN A.avg_price_five_days >= A.avg_price_twenty_days AND A.avg_price_five_days >= A.avg_price_fourty_days AND A.avg_price_twenty_days < A.avg_price_fourty_days THEN 6
			ELSE null
		END AS stage,
		A.crawled_date
	FROM
		MOVING_AVG A INNER JOIN LATEST_THREE_DAYS B ON A.crawled_date = B.crawled_date
),
PURCHACE_SIGNED_CODE AS (
	SELECT
		code,
		GROUP_CONCAT(stage ORDER BY crawled_date DESC SEPARATOR '/') AS stage_before_three_days,
		MAX(crawled_date) AS latest_date
	FROM
		PRICE_STAGE
	GROUP BY
		code
)
SELECT
	P.*
FROM
	price P INNER JOIN PURCHACE_SIGNED_CODE S ON P.code = S.code AND P.crawled_date = S.latest_date
WHERE
	stage_before_three_days = '4/5/5'

;