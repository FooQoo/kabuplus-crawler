package fooqoo.trade.stock.crawler.application.service;

import fooqoo.trade.stock.crawler.domain.model.Index;
import fooqoo.trade.stock.crawler.domain.model.IndexConverter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Priceに変換するServiceクラス.
 */
@Service
@RequiredArgsConstructor
public class IndexConverterService {

    private final ZonedDateTime nowZonedDt = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));

    private static final int EXPECTED_ROW_LENGTH = 18;

    /**
     * 株+ -> DB要素に変換.
     *
     * @param price 株+
     * @return DB
     */
    public Index getIndex(final String[] price) {
        return convertResponseToIndex(price);
    }

    /**
     * 行ごとの変換処理.
     *
     * @param row 株+の銘柄要素
     * @return DB行要素
     */
    private Index convertResponseToIndex(final String[] row) {
        if (row.length == EXPECTED_ROW_LENGTH) {
            return Index.builder()
                    .code(row[IndexConverter.CODE.getIndex()])
                    .sector(row[IndexConverter.SECTOR.getIndex()])
                    .crawledDate(getToday())
                    .share(getIntegerFormat(row[IndexConverter.SHARE.getIndex()]))
                    .yield(getBigDecimalFormat(row[IndexConverter.YIELD.getIndex()]))
                    .forwardPer(getBigDecimalFormat(row[IndexConverter.FORWARD_PER.getIndex()]))
                    .trailingPer(getBigDecimalFormat(row[IndexConverter.TRAILING_PER.getIndex()]))
                    .forwardEps(getBigDecimalFormat(row[IndexConverter.FORWARD_PER.getIndex()]))
                    .trailingBps(getBigDecimalFormat(row[IndexConverter.TRAILING_BPS.getIndex()]))
                    .build();
        }
        return Index.builder().build();
    }

    /**
     * 当日の日付を獲得する.
     *
     * @return 当日の日付
     */
    public LocalDate getToday() {
        return nowZonedDt.toLocalDate();
    }

    /**
     * 文字列 -> Integer変換.
     *
     * @param number 数値の文字列
     * @return Integer型の数値
     */
    private Integer getIntegerFormat(final String number) {
        return isNumber(number) ? (new BigDecimal(number)).intValue() : 0;
    }

    /**
     * 文字列 -> 小数点型.
     *
     * @param number 数値の文字列
     * @return BigDecimal型の数値
     */
    private BigDecimal getBigDecimalFormat(final String number) {
        return isNumber(number) ? new BigDecimal(number) : new BigDecimal("0.00");
    }

    /**
     * 数値に変換できるか.
     *
     * @param number 数値の文字列
     * @return Float型に変換できる
     */
    private boolean isNumber(final String number) {
        try {
            Float.parseFloat(number);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }
}
