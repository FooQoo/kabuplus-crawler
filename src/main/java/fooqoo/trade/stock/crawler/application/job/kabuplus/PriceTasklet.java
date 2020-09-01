package fooqoo.trade.stock.crawler.application.job.kabuplus;

import fooqoo.trade.stock.crawler.domain.model.write.Price;
import fooqoo.trade.stock.crawler.domain.repository.KabuPlusApiRepository;
import fooqoo.trade.stock.crawler.infrastructure.api.response.KabuPlusApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/** Spring Batch タスクレット */
@Component
@RequiredArgsConstructor
public class PriceTasklet implements Tasklet {

  private final KabuPlusApiRepository kabuPlusApiRepository;
  private final PriceProcessor processor;
  private final PriceWriter writer;

  /**
   * タスクレットの実行メソッド
   *
   * @param stepContribution StepContributionインスタンス
   * @param chunkContext ChunkContextインスタンス
   * @return 実行ステータス
   * @throws Exception 例外
   */
  @Override
  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
      throws Exception {

    KabuPlusApiResponse response = kabuPlusApiRepository.getLatestPrices();

    List<Price> priceList =
        response.getPrices().stream().map(processor::process).collect(Collectors.toList());

    writer.write(priceList);

    return RepeatStatus.FINISHED;
  }
}
