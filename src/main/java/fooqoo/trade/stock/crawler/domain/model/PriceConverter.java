package fooqoo.trade.stock.crawler.domain.model;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 株+の配列のindexと要素の対応.
 */
@RequiredArgsConstructor
@Getter
public enum PriceConverter {
    CODE(0, "SC"),
    NAME(1, "名所"),
    MARKET(2, "市場"),
    TYPE(3, "業種"),
    DATE(4, "日時"),
    PRICE(5, "株価"),
    PRICE_DAY_OVER_DAY(6, "前日比"),
    PRICE_DAY_OVER_DAY_PERCENTAGE(7, "前日比（％）"),
    CLOSED(8, "前日終値"),
    START(9, "始値"),
    HIGH(10, "高値"),
    LOW(11, "安値"),
    VWAP(12, "VWAP"),
    VOLUME(13, "出来高"),
    VOLUME_PERCENTAGE(14, "出来高率"),
    TRADING_VALUE(15, "売買代金（千円）"),
    MARKET_CAPITALIZATION(16, "時価総額（百万円）"),
    PRICE_LOW_LIMIT(17, "値幅下限"),
    PRICE_HIGH_LIMIT(18, "値幅上限"),
    DATE_OF_HIGH_PRICE(19, "高値日付"),
    YEARLY_HIGH(20, "年初来高値"),
    RATE_OF_DEVIATION_OF_YEARLY_HIGH(21, "年初来高値乖離率"),
    DATE_OF_LOW_PRICE(22, "安値日付"),
    YEARLY_LOW(23, "年初来安値"),
    RATE_OF_DEVIATION_OF_YEARLY_LOW(24, "年初来安値乖離率");

    public final Integer index;
    public final String columnName;

    /**
     * カラム一覧を取得する.
     *
     * @return カラムが保存された文字列の配列
     */
    public static String[] getColumnNames() {
        return Arrays.stream(PriceConverter.values())
                .map(PriceConverter::getColumnName)
                .toArray(String[]::new);
    }
}
