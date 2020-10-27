package fooqoo.trade.stock.crawler.application.job.macd.tasklet;

import fooqoo.trade.stock.crawler.application.service.MacdService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Spring Batch タスクレット.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MacdTasklet implements Tasklet {

    private final MacdService macdService;

    /**
     * タスクレットの実行メソッド.
     *
     * @param contribution StepContributionインスタンス
     * @param chunkContext ChunkContextインスタンス
     * @return 実行ステータス
     */
    @Override
    public RepeatStatus execute(
            @NonNull final StepContribution contribution,
            @NonNull final ChunkContext chunkContext) {

        final LocalDate today = macdService.getToday();
        final LocalDate latestDate = macdService.getLatestDate();

        // フィルタリングされた銘柄を保存
        try {
            macdService.insertMacd(today, latestDate);
        } catch (final Exception e) {
            log.error("書き込み処理に失敗しました - {}", e.getMessage());
            contribution.setExitStatus(ExitStatus.FAILED);
        }

        log.info("complete insert chunk");

        return RepeatStatus.FINISHED;
    }
}
