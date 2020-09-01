package fooqoo.trade.stock.crawler.infrastructure.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** 株+のレスポンス */
@Data
@RequiredArgsConstructor
public class KabuPlusApiResponse implements Serializable {

  @JsonProperty("japan-all-stock-prices-2")
  private final List<String[]> prices;

  /** デフォルトコンストラクタ */
  public KabuPlusApiResponse() {
    this.prices = new ArrayList<>();
  }
}
