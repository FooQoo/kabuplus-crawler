package fooqoo.trade.stock.crawler.domain.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConverterUtilService {

    public static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";
    public static final String DATE_OF_PRICE_FORMAT = "yyyy/M/d";
    private final ZonedDateTime nowZonedDt = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));


    /**
     * 文字列 -> 日付変換.
     *
     * @param date   文字列の日付
     * @param format yyyy/MM/dd HH:mm
     * @return LocalDateTimeの日付
     */
    public LocalDate getFormattedDate(final String date, final String format) {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
        } catch (final DateTimeParseException e) {
            return null;
        }
    }

    /**
     * 文字列 -> Integer変換.
     *
     * @param number 数値の文字列
     * @return Integer型の数値
     */
    public Integer getIntegerFormat(final String number) {
        return isNumber(number) ? (new BigDecimal(number)).intValue() : null;
    }

    /**
     * 文字列 -> 小数点型.
     *
     * @param number 数値の文字列
     * @return BigDecimal型の数値
     */
    public BigDecimal getBigDecimalFormat(final String number) {
        return isNumber(number) ? new BigDecimal(number) : null;
    }

    /**
     * 数値に変換できるか.
     *
     * @param number 数値の文字列
     * @return Float型に変換できる
     */
    public boolean isNumber(final String number) {
        try {
            Float.parseFloat(number);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    /**
     * 当日の日付を獲得する.
     *
     * @return 当日の日付
     */
    public LocalDate getToday() {
        return nowZonedDt.toLocalDate();
    }
}
