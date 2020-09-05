-- データベース作成
CREATE DATABASE kabuplus;

-- ユーザ作成
CREATE USER 'fooqoo'@'%' IDENTIFIED BY 'passw0rd';
GRANT ALL PRIVILEGES on kabuplus.* TO 'fooqoo'@'%';

-- テーブル作成
USE kabuplus;
CREATE TABLE price IF NOT EXISTS (
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
    rate_of_deviation_of_yearly_low decimal(5, 2)
);

DROP TABLE price;