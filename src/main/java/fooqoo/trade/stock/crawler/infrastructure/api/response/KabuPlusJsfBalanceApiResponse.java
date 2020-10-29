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
public class KabuPlusJsfBalanceApiResponse implements Serializable {

    private static final long serialVersionUID = -7306585849260260402L;

    @JsonProperty("jsf-balance-data")
    private final List<String[]> balances;

    /**
     * デフォルトコンストラクタ.
     */
    public KabuPlusJsfBalanceApiResponse() {
        balances = new ArrayList<>();
    }
}
