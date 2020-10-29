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

    private static final long serialVersionUID = -8972214619717307713L;

    @JsonProperty("japan-all-stock-prices-2")
    private final List<String[]> prices;

    /**
     * デフォルトコンストラクタ.
     */
    public KabuPlusPriceApiResponse() {
        prices = new ArrayList<>();
    }
}
