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
public class KabuPlusIndexApiResponse implements Serializable {

    private static final long serialVersionUID = -2982507908416579534L;

    @JsonProperty("japan-all-stock-data")
    private final List<String[]> indexes;

    /**
     * デフォルトコンストラクタ.
     */
    public KabuPlusIndexApiResponse() {
        indexes = new ArrayList<>();
    }
}
