package fooqoo.trade.stock.crawler.domain.repository;

import fooqoo.trade.stock.crawler.infrastructure.api.response.KabuPlusIndexApiResponse;
import fooqoo.trade.stock.crawler.infrastructure.api.response.KabuPlusJsfBalanceApiResponse;
import fooqoo.trade.stock.crawler.infrastructure.api.response.KabuPlusPriceApiResponse;

/**
 * 株+のエンドポイント.
 */
public interface KabuPlusApiRepository {

    /**
     * 最新の株価を取得.
     *
     * @return 株プラスの株価情報APIレスポンス
     */
    KabuPlusPriceApiResponse getLatestPrices();

    /**
     * 最新の株価指数を取得.
     *
     * @return 株プラスの株価指数APIレスポンス
     */
    KabuPlusIndexApiResponse getLatestIndexes();

    /**
     * 日証金 融資・貸株残高データを取得.
     *
     * @return 日証金 融資・貸株残高データAPIのレスポンス
     */
    KabuPlusJsfBalanceApiResponse getJsfBalance();
}
