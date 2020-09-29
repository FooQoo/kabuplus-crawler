package fooqoo.trade.stock.crawler.infrastructure.api.reposiotryimpl;

import fooqoo.trade.stock.crawler.domain.repository.KabuPlusApiRepository;
import fooqoo.trade.stock.crawler.infrastructure.api.config.KabuPlusConfig;
import fooqoo.trade.stock.crawler.infrastructure.api.response.KabuPlusApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/** KabuPlusApiRepository実装クラス */
@Repository
@RequiredArgsConstructor
public class KabuPlusApiRepositoryImpl implements KabuPlusApiRepository {

  private final KabuPlusConfig config;
  private final RestTemplate restTemplate;

  /**
   * 最新の銘柄の価格を取得
   *
   * @return 株プラスのレスポンス
   */
  @Override
  public KabuPlusApiResponse getLatestPrices() {

    final HttpHeaders headers = new HttpHeaders();

    final String url =
        UriComponentsBuilder.fromHttpUrl(config.getBaseUrl())
            .path(config.getPath())
            .build()
            .toString();

    return restTemplate
        .exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), KabuPlusApiResponse.class)
        .getBody();
  }
}
