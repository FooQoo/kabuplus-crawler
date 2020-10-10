-- データベース作成
CREATE DATABASE kabuplus;

-- ユーザ作成
CREATE USER 'fooqoo'@'%' IDENTIFIED BY 'passw0rd';
GRANT ALL PRIVILEGES on kabuplus.* TO 'fooqoo'@'%';

-- テーブル作成
USE kabuplus;
CREATE TABLE IF NOT EXISTS price  (
    code int,
    code_name varchar(255),
    market varchar(255),
    sector varchar(255),
    crawled_date date,
    price int,
    price_day_over_day int,
    price_day_over_day_percentage decimal(5, 2),
    closed int,
    opening int,
    high int,
    low int,
    vwap decimal(11, 2),
    volume int,
    volume_percentage decimal(5, 2),
    trading_value int,
    market_capitalization int,
    price_low_limit int,
    price_high_limit int,
    date_of_high_price date,
    yearly_high int,
    rate_of_deviation_of_yearly_high decimal(5, 2),
    date_of_low_price date,
    yearly_low int,
    rate_of_deviation_of_yearly_low decimal(5, 2),
    PRIMARY KEY(code, crawled_date)
);

CREATE TABLE IF NOT EXISTS idx (
    code int,
    sector varchar(255),
    crawled_date date,
    share int,
    yield decimal(11, 2) DEFAULT 0.00,
    forward_per decimal(11, 2),
    trailing_per decimal(11, 2),
    forward_eps decimal(11, 2),
    trailing_bps decimal(11, 2),
    PRIMARY KEY(code, crawled_date)
);

DROP TABLE index;
DROP TABLE price;