CREATE TABLE IF NOT EXISTS price (
    code int NOT NULL,
    code_name varchar(255),
    market varchar(255),
    sector varchar(255),
    crawled_date date NOT NULL,
    price int,
    price_day_over_day int,
    price_day_over_day_percentage decimal(11, 2),
    closed int,
    opening int,
    high int,
    low int,
    vwap decimal(11, 2),
    volume int,
    volume_percentage decimal(11, 2),
    trading_value int,
    market_capitalization int,
    price_low_limit int,
    price_high_limit int,
    date_of_high_price date,
    yearly_high int,
    rate_of_deviation_of_yearly_high decimal(11, 2),
    date_of_low_price date,
    yearly_low int,
    rate_of_deviation_of_yearly_low decimal(11, 2),
    PRIMARY KEY(code, crawled_date)
);

CREATE TABLE IF NOT EXISTS idx (
    code int,
    sector varchar(255),
    crawled_date date,
    share int,
    yield decimal(11, 2),
    forward_per decimal(11, 2),
    trailing_per decimal(11, 2),
    forward_eps decimal(11, 2),
    trailing_bps decimal(11, 2),
    PRIMARY KEY(code, crawled_date)
);

CREATE TABLE IF NOT EXISTS macd (
    code int NOT NULL,
    crawled_date date,
    short_ema decimal(11, 2),
    long_ema decimal(11, 2),
    macd_value decimal(11, 2),
    PRIMARY KEY(code, crawled_date)
);