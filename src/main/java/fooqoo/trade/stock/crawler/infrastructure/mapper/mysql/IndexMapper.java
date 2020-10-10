package fooqoo.trade.stock.crawler.infrastructure.mapper.mysql;

import fooqoo.trade.stock.crawler.domain.model.Index;
import org.apache.ibatis.annotations.Mapper;

/**
 * Priceのmapperクラス.
 */
@Mapper
public interface IndexMapper {

    /**
     * 価格の挿入.
     *
     * @param index DB保存用の銘柄情報
     */
    void insertIndex(final Index index);
}
