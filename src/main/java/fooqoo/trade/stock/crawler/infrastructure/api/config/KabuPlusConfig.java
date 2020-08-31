package fooqoo.trade.stock.crawler.infrastructure.api.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

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

  @Bean
  public RestTemplate kabuPlusRestTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder
        .additionalInterceptors(
            (httpRequest, bytes, clientHttpRequestExecution) -> {
              log.info(
                  "kabu plus request - {}: {}", httpRequest.getMethodValue(), httpRequest.getURI());
              ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, bytes);
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
}
