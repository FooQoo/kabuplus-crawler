package fooqoo.trade.stock.crawler.application.job.kabuplus;

import fooqoo.trade.stock.crawler.application.service.PriceService;
import fooqoo.trade.stock.crawler.domain.model.write.Price;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@StepScope
@RequiredArgsConstructor
@Slf4j
public class PriceProcessor implements ItemProcessor<String[], Price> {

  private final PriceService priceService;

  @Override
  public Price process(final String[] price) {
    return priceService.getPrice(price);
  }
}
