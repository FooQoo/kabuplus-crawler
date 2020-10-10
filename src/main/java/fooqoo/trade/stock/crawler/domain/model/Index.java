package fooqoo.trade.stock.crawler.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Value;
import org.springframework.lang.NonNull;

/**
 * DB保存用の銘柄指標クラス.
 */
@Value
@Builder
public class Index implements Serializable {

    /**
     * 銘柄コード.
     */
    @NonNull
    String code;

    /**
     * 日時.
     */
    @NonNull
    LocalDate crawledDate;

    /**
     * 業種.
     */
    String sector;

    /**
     * 発行済株式数.
     */
    Integer share;

    /**
     * 配当利回り.
     */
    BigDecimal yield;

    /**
     * PER（予想）.
     */
    BigDecimal forwardPer;

    /**
     * PBR（実績）.
     */
    BigDecimal trailingPer;

    /**
     * EPS（予想）.
     */
    BigDecimal forwardEps;

    /**
     * BPS（実績）.
     */
    BigDecimal trailingBps;
}
