package fooqoo.trade.stock.crawler.application.job.price;

import fooqoo.trade.stock.crawler.application.service.PriceConverterService;
import fooqoo.trade.stock.crawler.domain.model.Price;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/** Spring Batch中間処理 */
@Component
@StepScope
@RequiredArgsConstructor
@Slf4j
public class PriceProcessor implements ItemProcessor<String[], Price> {

  private final PriceConverterService priceConverterService;

  /**
   * 中間処理
   *
   * @param price 株+の価格
   * @return DB保存用インスタンス
   */
  @Override
  public Price process(final String[] price) {
    return priceConverterService.getPrice(price);
  }
}
