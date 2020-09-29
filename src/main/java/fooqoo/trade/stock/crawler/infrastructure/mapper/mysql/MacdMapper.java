package fooqoo.trade.stock.crawler.infrastructure.mapper.mysql;

import fooqoo.trade.stock.crawler.domain.model.LatestDate;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;

/** Priceのmapperクラス */
@Mapper
public interface MacdMapper {

  /** MACDの更新 */
  void insertMacd(final String priceCrawledDate, final String macdCrawledDate);

  /** 最新の日付の更新 */
  LatestDate getLatestDate();
}
