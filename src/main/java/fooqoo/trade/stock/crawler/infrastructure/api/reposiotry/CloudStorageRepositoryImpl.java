package fooqoo.trade.stock.crawler.infrastructure.api.reposiotry;

import com.fasterxml.jackson.databind.ObjectMapper;
import fooqoo.trade.stock.crawler.domain.repository.CloudStorageRepository;
import fooqoo.trade.stock.crawler.infrastructure.api.response.KabuPlusApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/** CloudStorageRepository実装クラス */
@Repository
@RequiredArgsConstructor
public class CloudStorageRepositoryImpl implements CloudStorageRepository {

  @Value("gs://stock-crawler-kabu-plus-backet/stock.json")
  private Resource gcsFile;

  private final ObjectMapper mapper;

  /**
   * GCSからJSON形式のデータ取得
   *
   * @return json形式のデータ
   * @throws IOException IOException
   */
  @Override
  public KabuPlusApiResponse getCloudResource() throws IOException {
    return mapper.readValue(
        StreamUtils.copyToString(this.gcsFile.getInputStream(), Charset.defaultCharset()),
        KabuPlusApiResponse.class);
  }
}
