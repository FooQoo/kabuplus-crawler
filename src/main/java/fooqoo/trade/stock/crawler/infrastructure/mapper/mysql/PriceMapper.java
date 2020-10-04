package fooqoo.trade.stock.crawler.infrastructure.mapper.mysql;

import fooqoo.trade.stock.crawler.domain.model.Price;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * Priceのmapperクラス.
 */
@Mapper
public interface PriceMapper {

    /**
     * 価格の挿入.
     *
     * @param price DB保存用の銘柄情報
     */
    void insertPrice(final Price price);

    /**
     * 買いサイン銘柄の取得.
     *
     * @return 銘柄のリスト
     */
    List<Price> findPurchaseSignedPrice();
}
