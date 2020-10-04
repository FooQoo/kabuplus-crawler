package fooqoo.trade.stock.crawler.domain.repository;

import fooqoo.trade.stock.crawler.infrastructure.api.response.KabuPlusApiResponse;
import java.io.IOException;

/**
 * CloudStorageRepository.
 */
public interface CloudStorageRepository {

    /**
     * GCSからJSON形式のデータ取得.
     *
     * @return json形式のデータ
     * @throws IOException IOException
     */
    KabuPlusApiResponse getCloudResource() throws IOException;

    /**
     * GCSにファイルを書き込む.
     *
     * @param csv csv形式の文字列
     * @throws IOException IOException
     */
    void writeCloudResource(final String csv) throws IOException;
}
