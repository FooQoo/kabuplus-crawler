package fooqoo.trade.stock.crawler.infrastructure.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 株+のレスポンス.
 */
@Data
@RequiredArgsConstructor
public class KabuPlusPriceApiResponse implements Serializable {

    @JsonProperty("japan-all-stock-prices-2")
    private final List<String[]> prices;

    /**
     * デフォルトコンストラクタ.
     */
    public KabuPlusPriceApiResponse() {
        this.prices = new ArrayList<>();
    }
}
