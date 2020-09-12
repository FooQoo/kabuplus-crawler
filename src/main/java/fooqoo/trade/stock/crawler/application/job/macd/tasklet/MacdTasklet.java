package fooqoo.trade.stock.crawler.application.job.macd.tasklet;

import fooqoo.trade.stock.crawler.application.service.MacdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/** Spring Batch タスクレット */
@Slf4j
@Component
@RequiredArgsConstructor
public class MacdTasklet implements Tasklet {

  private final MacdService macdService;

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

    LocalDate today = macdService.getToday();
    LocalDate latestDate = macdService.getLatestDate();

    // フィルタリングされた銘柄を保存
    try {
      macdService.insertMacd(today, latestDate);
    } catch (Exception e) {
      log.error("書き込み処理に失敗しました - {}", e.getMessage());
    }

    log.info("complete insert chunk");

    return RepeatStatus.FINISHED;
  }
}
