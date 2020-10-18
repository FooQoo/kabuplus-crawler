package fooqoo.trade.stock.crawler.infrastructure.mapper.mysql;

import fooqoo.trade.stock.crawler.domain.model.Balance;
import fooqoo.trade.stock.crawler.domain.model.Index;
import org.apache.ibatis.annotations.Mapper;

/**
 * Priceのmapperクラス.
 */
@Mapper
public interface BalanceMapper {

    /**
     * 価格の挿入.
     *
     * @param balance DB保存用の銘柄情報
     */
    void insertBalance(final Balance balance);
}
