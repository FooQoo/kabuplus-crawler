package fooqoo.trade.stock.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockCrawlerApplication {

  public static void main(final String[] args) {
    SpringApplication application = new SpringApplication(StockCrawlerApplication.class);

    application.setWebApplicationType(WebApplicationType.NONE);
    System.exit(SpringApplication.exit(application.run(args)));
  }

}
