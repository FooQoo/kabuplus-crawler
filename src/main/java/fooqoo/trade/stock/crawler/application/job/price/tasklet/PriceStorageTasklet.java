package fooqoo.trade.stock.crawler.application.job.price.tasklet;

import fooqoo.trade.stock.crawler.application.job.price.PriceProcessor;
import fooqoo.trade.stock.crawler.application.job.price.PriceWriter;
import fooqoo.trade.stock.crawler.application.service.CloudStorageService;
import fooqoo.trade.stock.crawler.application.service.PriceFilterService;
import fooqoo.trade.stock.crawler.domain.model.Price;
import fooqoo.trade.stock.crawler.infrastructure.api.response.KabuPlusApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/** Spring Batch タスクレット */
@Slf4j
@Component
@RequiredArgsConstructor
public class PriceStorageTasklet implements Tasklet {

  private final CloudStorageService cloudStorageService;
  private final PriceProcessor processor;
  private final PriceWriter writer;
  private final PriceFilterService priceFilterService;

  /**
   * タスクレットの実行メソッド
   *
   * @param stepContribution StepContributionインスタンス
   * @param chunkContext ChunkContextインスタンス
   * @return 実行ステータス
   * @throws Exception 例外
   */
  @Override
  public RepeatStatus execute(
      final StepContribution stepContribution, final ChunkContext chunkContext) throws Exception {

    KabuPlusApiResponse response = cloudStorageService.getPrice();

    List<Price> priceList =
        response.getPrices().stream().map(processor::process).collect(Collectors.toList());

    // フィルタリングされた銘柄を保存
    writer.write(priceList);

    log.info("complete insert chunk");

    return RepeatStatus.FINISHED;
  }
}
