package fooqoo.trade.stock.crawler.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Value;
import org.springframework.lang.NonNull;

/**
 * DB保存用の銘柄クラス.
 */
@Value
@Builder
public class LatestDate implements Serializable {

    private static final long serialVersionUID = 971323531335560353L;

    /**
     * 日時.
     */
    @NonNull
    LocalDate crawledDate;
}
