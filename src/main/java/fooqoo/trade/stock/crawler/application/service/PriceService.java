package fooqoo.trade.stock.crawler.application.service;

import fooqoo.trade.stock.crawler.domain.model.PriceConverter;
import fooqoo.trade.stock.crawler.domain.model.write.Price;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriceService {

  private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";
  private static final String DATE_OF_PRICE_FORMAT = "yyyy/M/d";

  public Price getPrice(final String[] price) {
    return convertResponseToPrice(price);
  }

  private Price convertResponseToPrice(final String[] row) {
    if (row.length == PriceConverter.values().length) {
      return Price.builder()
          .code(row[PriceConverter.CODE.getIndex()])
          .codeName(row[PriceConverter.NAME.getIndex()])
          .market(row[PriceConverter.MARKET.getIndex()])
          .bizType(row[PriceConverter.TYPE.getIndex()])
          .crawledDate(getFormattedDate(row[PriceConverter.DATE.getIndex()], DATE_FORMAT))
          .price(getIntegerFormat(row[PriceConverter.PRICE.getIndex()]))
          .priceDayOverDay(getIntegerFormat(row[PriceConverter.PRICE_DAY_OVER_DAY.getIndex()]))
          .priceDayOverDayPercentage(
              getBigDecimalFormat(row[PriceConverter.PRICE_DAY_OVER_DAY_PERCENTAGE.getIndex()]))
          .closed(getIntegerFormat(row[PriceConverter.CLOSED.getIndex()]))
          .opening(getIntegerFormat(row[PriceConverter.START.getIndex()]))
          .high(getIntegerFormat(row[PriceConverter.HIGH.getIndex()]))
          .low(getIntegerFormat(row[PriceConverter.LOW.getIndex()]))
          .vwap(getBigDecimalFormat(row[PriceConverter.VWAP.getIndex()]))
          .volume(getIntegerFormat(row[PriceConverter.VOLUME.getIndex()]))
          .volumePercentage(getBigDecimalFormat(row[PriceConverter.VOLUME_PERCENTAGE.getIndex()]))
          .tradingValue(getIntegerFormat(row[PriceConverter.TRADING_VALUE.getIndex()]))
          .marketCapitalization(
              getIntegerFormat(row[PriceConverter.MARKET_CAPITALIZATION.getIndex()]))
          .priceLowLimit(getIntegerFormat(row[PriceConverter.PRICE_LOW_LIMIT.getIndex()]))
          .priceHighLimit(getIntegerFormat(row[PriceConverter.PRICE_HIGH_LIMIT.getIndex()]))
          .dateOfHighPrice(
              getFormattedDate(
                  row[PriceConverter.DATE_OF_HIGH_PRICE.getIndex()], DATE_OF_PRICE_FORMAT))
          .yearlyHigh(getIntegerFormat(row[PriceConverter.YEARLY_HIGH.getIndex()]))
          .rateOfDeviationOfYearlyHigh(
              getBigDecimalFormat(row[PriceConverter.RATE_OF_DEVIATION_OF_YEARLY_HIGH.getIndex()]))
          .dateOfLowPrice(
              getFormattedDate(
                  row[PriceConverter.DATE_OF_LOW_PRICE.getIndex()], DATE_OF_PRICE_FORMAT))
          .yearlyLow(getIntegerFormat(row[PriceConverter.YEARLY_LOW.getIndex()]))
          .rateOfDeviationOfYearlyLow(
              getBigDecimalFormat(row[PriceConverter.RATE_OF_DEVIATION_OF_YEARLY_LOW.getIndex()]))
          .build();
    }
    return Price.builder().build();
  }

  private LocalDate getFormattedDate(final String date, final String format) {
    try {
      return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
    } catch (DateTimeParseException e) {
      return null;
    }
  }

  private Integer getIntegerFormat(final String number) {
    return isInteger(number) ? Integer.parseInt(number) : null;
  }

  private BigDecimal getBigDecimalFormat(final String number) {
    return isNumber(number) ? new BigDecimal(number) : null;
  }

  private boolean isInteger(final String number) {
    try {
      Integer.parseInt(number);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private boolean isNumber(final String number) {
    try {
      Float.parseFloat(number);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
