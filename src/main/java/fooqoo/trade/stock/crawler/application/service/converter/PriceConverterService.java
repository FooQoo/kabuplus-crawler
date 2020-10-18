package fooqoo.trade.stock.crawler.application.service.converter;

import fooqoo.trade.stock.crawler.domain.model.Price;
import fooqoo.trade.stock.crawler.domain.model.converter.PriceConverter;
import fooqoo.trade.stock.crawler.domain.service.ConverterUtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Priceに変換するServiceクラス.
 */
@Service
@RequiredArgsConstructor
public class PriceConverterService {

    private final ConverterUtilService converterUtilService;

    /**
     * 株+ -> DB要素に変換.
     *
     * @param price 株+
     * @return DB
     */
    public Price getPrice(final String[] price) {
        return convertResponseToPrice(price);
    }

    /**
     * 行ごとの変換処理.
     *
     * @param row 株+の銘柄要素
     * @return DB行要素
     */
    private Price convertResponseToPrice(final String[] row) {
        if (row.length == PriceConverter.values().length) {
            return Price.builder()
                    .code(row[PriceConverter.CODE.getIndex()])
                    .codeName(row[PriceConverter.NAME.getIndex()])
                    .market(row[PriceConverter.MARKET.getIndex()])
                    .sector(row[PriceConverter.TYPE.getIndex()])
                    .crawledDate(converterUtilService
                            .getFormattedDate(row[PriceConverter.DATE.getIndex()],
                                    ConverterUtilService.DATE_FORMAT))
                    .price(converterUtilService
                            .getIntegerFormat(row[PriceConverter.PRICE.getIndex()]))
                    .priceDayOverDay(
                            converterUtilService.getIntegerFormat(
                                    row[PriceConverter.PRICE_DAY_OVER_DAY.getIndex()]))
                    .priceDayOverDayPercentage(
                            converterUtilService.getBigDecimalFormat(
                                    row[PriceConverter.PRICE_DAY_OVER_DAY_PERCENTAGE.getIndex()]))
                    .closed(converterUtilService
                            .getIntegerFormat(row[PriceConverter.CLOSED.getIndex()]))
                    .opening(converterUtilService
                            .getIntegerFormat(row[PriceConverter.START.getIndex()]))
                    .high(converterUtilService
                            .getIntegerFormat(row[PriceConverter.HIGH.getIndex()]))
                    .low(converterUtilService.getIntegerFormat(row[PriceConverter.LOW.getIndex()]))
                    .vwap(converterUtilService
                            .getBigDecimalFormat(row[PriceConverter.VWAP.getIndex()]))
                    .volume(converterUtilService
                            .getIntegerFormat(row[PriceConverter.VOLUME.getIndex()]))
                    .volumePercentage(
                            converterUtilService.getBigDecimalFormat(
                                    row[PriceConverter.VOLUME_PERCENTAGE.getIndex()]))
                    .tradingValue(converterUtilService
                            .getIntegerFormat(row[PriceConverter.TRADING_VALUE.getIndex()]))
                    .marketCapitalization(
                            converterUtilService.getIntegerFormat(
                                    row[PriceConverter.MARKET_CAPITALIZATION.getIndex()]))
                    .priceLowLimit(converterUtilService
                            .getIntegerFormat(row[PriceConverter.PRICE_LOW_LIMIT.getIndex()]))
                    .priceHighLimit(
                            converterUtilService.getIntegerFormat(
                                    row[PriceConverter.PRICE_HIGH_LIMIT.getIndex()]))
                    .dateOfHighPrice(
                            converterUtilService.getFormattedDate(
                                    row[PriceConverter.DATE_OF_HIGH_PRICE.getIndex()],
                                    ConverterUtilService.DATE_OF_PRICE_FORMAT))
                    .yearlyHigh(converterUtilService
                            .getIntegerFormat(row[PriceConverter.YEARLY_HIGH.getIndex()]))
                    .rateOfDeviationOfYearlyHigh(
                            converterUtilService.getBigDecimalFormat(
                                    row[PriceConverter.RATE_OF_DEVIATION_OF_YEARLY_HIGH
                                            .getIndex()]))
                    .dateOfLowPrice(
                            converterUtilService.getFormattedDate(
                                    row[PriceConverter.DATE_OF_LOW_PRICE.getIndex()],
                                    ConverterUtilService.DATE_OF_PRICE_FORMAT))
                    .yearlyLow(converterUtilService
                            .getIntegerFormat(row[PriceConverter.YEARLY_LOW.getIndex()]))
                    .rateOfDeviationOfYearlyLow(
                            converterUtilService.getBigDecimalFormat(
                                    row[PriceConverter.RATE_OF_DEVIATION_OF_YEARLY_LOW.getIndex()]))
                    .build();
        }
        return Price.builder().build();
    }
}
