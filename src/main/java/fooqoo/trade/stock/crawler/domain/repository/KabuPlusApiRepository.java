package fooqoo.trade.stock.crawler.domain.repository;

import fooqoo.trade.stock.crawler.infrastructure.api.response.KabuPlusApiResponse;

/** 株+のエンドポイント */
public interface KabuPlusApiRepository {

  /**
   * 最新の株価を取得
   *
   * @return 株プラスのAPIレスポンス
   */
  KabuPlusApiResponse getLatestPrices();
}
