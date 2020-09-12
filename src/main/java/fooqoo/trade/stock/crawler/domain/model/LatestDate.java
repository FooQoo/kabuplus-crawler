package fooqoo.trade.stock.crawler.domain.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDate;

/** DB保存用の銘柄クラス */
@Value
@Builder
public class LatestDate {

  /** 日時 */
  @NonNull
  LocalDate crawledDate;
}
