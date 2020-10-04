package fooqoo.trade.stock.crawler.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Value;
import org.springframework.lang.NonNull;

/**
 * DB保存用の銘柄クラス.
 */
@Value
@Builder
public class Price implements Serializable {

    /**
     * 銘柄コード.
     */
    @NonNull
    String code;

    /**
     * 銘柄名.
     */
    String codeName;

    /**
     * 市場.
     */
    String market;

    /**
     * 業種.
     */
    String sector;

    /**
     * 日時.
     */
    @NonNull
    LocalDate crawledDate;

    /**
     * 株価.
     */
    Integer price;

    /**
     * 前日比.
     */
    Integer priceDayOverDay;

    /**
     * 前日比（%）.
     */
    BigDecimal priceDayOverDayPercentage;

    /**
     * 前日終値.
     */
    Integer closed;

    /**
     * 始値.
     */
    Integer opening;

    /**
     * 高値.
     */
    Integer high;

    /**
     * 安値.
     */
    Integer low;

    /**
     * VWAP.
     */
    BigDecimal vwap;

    /**
     * 出来高.
     */
    Integer volume;

    /**
     * 出来高率.
     */
    BigDecimal volumePercentage;

    /**
     * 売買代金（千円）.
     */
    Integer tradingValue;

    /**
     * 時価総額（百万円）.
     */
    Integer marketCapitalization;

    /**
     * 値幅下限.
     */
    Integer priceLowLimit;

    /**
     * 値幅上限.
     */
    Integer priceHighLimit;

    /**
     * 高値日付.
     */
    LocalDate dateOfHighPrice;

    /**
     * 年初来高値.
     */
    Integer yearlyHigh;

    /**
     * 年初来高値乖離率.
     */
    BigDecimal rateOfDeviationOfYearlyHigh;

    /**
     * 安値日付.
     */
    LocalDate dateOfLowPrice;

    /**
     * 年初来安値.
     */
    Integer yearlyLow;

    /**
     * 年初来安値乖離率.
     */
    BigDecimal rateOfDeviationOfYearlyLow;
}
