package fooqoo.trade.stock.crawler.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** 株+の配列のindexと要素の対応 */
@RequiredArgsConstructor
@Getter
public enum PriceConverter {
  CODE(0),
  NAME(1),
  MARKET(2),
  TYPE(3),
  DATE(4),
  PRICE(5),
  PRICE_DAY_OVER_DAY(6),
  PRICE_DAY_OVER_DAY_PERCENTAGE(7),
  CLOSED(8),
  START(9),
  HIGH(10),
  LOW(11),
  VWAP(12),
  VOLUME(13),
  VOLUME_PERCENTAGE(14),
  TRADING_VALUE(15),
  MARKET_CAPITALIZATION(16),
  PRICE_LOW_LIMIT(17),
  PRICE_HIGH_LIMIT(18),
  DATE_OF_HIGH_PRICE(19),
  YEARLY_HIGH(20),
  RATE_OF_DEVIATION_OF_YEARLY_HIGH(21),
  DATE_OF_LOW_PRICE(22),
  YEARLY_LOW(23),
  RATE_OF_DEVIATION_OF_YEARLY_LOW(24);

  public final Integer index;
}
