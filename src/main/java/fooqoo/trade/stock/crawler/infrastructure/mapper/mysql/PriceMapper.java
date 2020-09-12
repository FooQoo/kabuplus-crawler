package fooqoo.trade.stock.crawler.infrastructure.mapper.mysql;

import fooqoo.trade.stock.crawler.domain.model.Price;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** Priceのmapperクラス */
@Mapper
public interface PriceMapper {

  /**
   * 価格の挿入
   *
   * @param price DB保存用の銘柄情報
   */
  public void insertPrice(final Price price);

  /**
   * 買いサイン銘柄の取得
   *
   * @return
   */
  public List<Price> findPurchaseSignedPrice();
}
