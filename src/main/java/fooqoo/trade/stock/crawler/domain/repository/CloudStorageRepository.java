package fooqoo.trade.stock.crawler.domain.repository;

import fooqoo.trade.stock.crawler.infrastructure.api.response.KabuPlusApiResponse;

import java.io.IOException;

/** CloudStorageRepository */
public interface CloudStorageRepository {

  /**
   * GCSからJSON形式のデータ取得
   *
   * @return json形式のデータ
   * @throws IOException IOException
   */
  public KabuPlusApiResponse getCloudResource() throws IOException;
}
