package fooqoo.trade.stock.crawler.infrastructure.api.reposiotryimpl;

import fooqoo.trade.stock.crawler.domain.repository.KabuPlusApiRepository;
import fooqoo.trade.stock.crawler.infrastructure.api.config.KabuPlusConfig;
import fooqoo.trade.stock.crawler.infrastructure.api.response.KabuPlusIndexApiResponse;
import fooqoo.trade.stock.crawler.infrastructure.api.response.KabuPlusJsfBalanceApiResponse;
import fooqoo.trade.stock.crawler.infrastructure.api.response.KabuPlusPriceApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * KabuPlusApiRepository実装クラス.
 */
@Repository
@RequiredArgsConstructor
public class KabuPlusApiRepositoryImpl implements KabuPlusApiRepository {

    private final KabuPlusConfig config;
    private final RestTemplate restTemplate;

    /**
     * {@inheritDoc}
     */
    @Override
    public KabuPlusPriceApiResponse getLatestPrices() {

        final HttpHeaders headers = new HttpHeaders();

        final String url =
                UriComponentsBuilder.fromHttpUrl(config.getBaseUrl())
                        .path(config.getPricePath())
                        .build()
                        .toString();

        return restTemplate
                .exchange(url, HttpMethod.GET, new HttpEntity<String>(headers),
                        KabuPlusPriceApiResponse.class)
                .getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KabuPlusIndexApiResponse getLatestIndexes() {
        final HttpHeaders headers = new HttpHeaders();

        final String url =
                UriComponentsBuilder.fromHttpUrl(config.getBaseUrl())
                        .path(config.getIndexPath())
                        .build()
                        .toString();

        return restTemplate
                .exchange(url, HttpMethod.GET, new HttpEntity<String>(headers),
                        KabuPlusIndexApiResponse.class)
                .getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KabuPlusJsfBalanceApiResponse getJsfBalance() {
        final HttpHeaders headers = new HttpHeaders();

        final String url =
                UriComponentsBuilder.fromHttpUrl(config.getBaseUrl())
                        .path(config.getJsfBalancePath())
                        .build()
                        .toString();

        return restTemplate
                .exchange(url, HttpMethod.GET, new HttpEntity<String>(headers),
                        KabuPlusJsfBalanceApiResponse.class)
                .getBody();
    }
}
