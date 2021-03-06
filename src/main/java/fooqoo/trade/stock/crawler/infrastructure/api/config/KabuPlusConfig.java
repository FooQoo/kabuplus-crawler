package fooqoo.trade.stock.crawler.infrastructure.api.config;

import java.time.Duration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 株+の設定.
 */
@ConstructorBinding
@ConfigurationProperties(prefix = "extension.api.kabu-plus")
@RequiredArgsConstructor
@Slf4j
@Getter
public class KabuPlusConfig {

    private final String baseUrl;

    private final String path;

    private final Duration connectTimeout;

    private final Duration readTimeout;

    private final String username;

    private final String password;

    /**
     * 株+のRestTemplate取得.
     *
     * @return RestTemplate
     */
    @Bean
    public RestTemplate kabuPlusRestTemplate() {
        return getRestTemplateBuilder()
                .additionalInterceptors(
                        (httpRequest, bytes, clientHttpRequestExecution) -> {
                            log.info(
                                    "kabu plus request - {}: {}", httpRequest.getMethodValue(),
                                    httpRequest.getURI());
                            final ClientHttpResponse response =
                                    clientHttpRequestExecution.execute(httpRequest, bytes);
                            log.info(
                                    "kabu plus response - {}: {}",
                                    response.getRawStatusCode(),
                                    response.getStatusText());
                            return response;
                        })
                .setConnectTimeout(connectTimeout)
                .setReadTimeout(readTimeout)
                .basicAuthentication(username, password)
                .build();
    }

    /**
     * RestTemplateBuilderを取得.
     *
     * @return RestTemplateBuilder
     */
    private RestTemplateBuilder getRestTemplateBuilder() {
        final BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory =
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());

        return new RestTemplateBuilder().requestFactory(() -> bufferingClientHttpRequestFactory);
    }

    /**
     * 株価取得用のパスを取得.
     *
     * @return 株価取得用パス
     */
    public String getPricePath() {
        return path + "japan-all-stock-prices-2/daily/japan-all-stock-prices-2.json";
    }

    /**
     * 銘柄指数のパスを取得.
     *
     * @return 銘柄指数のパス
     */
    public String getIndexPath() {
        return path + "japan-all-stock-data/daily/japan-all-stock-data.json";
    }

    /**
     * 日証金 融資・貸株残高データ.
     *
     * @return 日証金 融資・貸株残高データのパス
     */
    public String getJsfBalancePath() {
        return path + "jsf-balance-data/daily/jsf-balance-data.json";
    }
}
