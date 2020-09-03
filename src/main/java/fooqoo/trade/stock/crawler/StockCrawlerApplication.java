package fooqoo.trade.stock.crawler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import java.util.function.Consumer;

/** SpringBootApplicationクラス */
@SpringBootApplication
@ConfigurationPropertiesScan
public class StockCrawlerApplication {

  /**
   * Spring Boot main
   *
   * @param args args
   */
  public static void main(final String[] args) {
    SpringApplication application = new SpringApplication(StockCrawlerApplication.class);

    application.setWebApplicationType(WebApplicationType.NONE);
    application.run(args);
  }

  /**
   * Cloud Functionの実行関数
   *
   * @return 関数
   */
  @Bean
  public Consumer<PubSubMessage> pubSubFunction() {
    return message -> {
      // The PubSubMessage data field arrives as a base-64 encoded string and must be decoded.
      // See: https://cloud.google.com/functions/docs/calling/pubsub#event_structure
      String[] args = {getDecodedMessage(message)};
      StockCrawlerApplication.main(args);
    };
  }

  /**
   * メッセージをデコードする
   *
   * @param message pubsubメッセージ
   * @return デコードされたdataパラメータ
   */
  private String getDecodedMessage(PubSubMessage message) {
    if (Objects.nonNull(message)) {
      if (StringUtils.isNoneBlank(message.getData())) {
        return new String(Base64.getDecoder().decode(message.getData()), StandardCharsets.UTF_8);
      }
    }
    return StringUtils.EMPTY;
  }
}
