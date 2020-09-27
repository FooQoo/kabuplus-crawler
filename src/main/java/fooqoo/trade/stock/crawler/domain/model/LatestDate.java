package fooqoo.trade.stock.crawler.domain.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

/** DB保存用の銘柄クラス */
@Value
@Builder
public class LatestDate {

  /** 日時 */
  @NonNull
  LocalDate crawledDate;
}
