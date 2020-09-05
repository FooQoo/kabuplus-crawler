package fooqoo.trade.stock.crawler.domain.model.read;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/** DB保存用の銘柄クラス */
@Data
public class CsvPrice implements Serializable {

  /** 銘柄コード */
  String code;

  /** 銘柄名 */
  String codeName;


}
