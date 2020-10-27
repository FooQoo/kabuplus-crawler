package fooqoo.trade.stock.crawler.application.job.balance.tasklet;

import fooqoo.trade.stock.crawler.application.job.balance.processor.BalanceProcessor;
import fooqoo.trade.stock.crawler.application.job.balance.writer.BalanceWriter;
import fooqoo.trade.stock.crawler.domain.model.Balance;
import fooqoo.trade.stock.crawler.domain.repository.KabuPlusApiRepository;
import fooqoo.trade.stock.crawler.infrastructure.api.response.KabuPlusJsfBalanceApiResponse;
import java.util.List;
import java.util.stream.Collectors;
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
 * 銘柄指数用タスクレット.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BalanceTasklet implements Tasklet {

    private final KabuPlusApiRepository kabuPlusApiRepository;
    private final BalanceProcessor processor;
    private final BalanceWriter writer;


    /**
     * タスクレットの実行メソッド.
     *
     * @param contribution StepContributionインスタンス
     * @param chunkContext ChunkContextインスタンス
     * @return 実行ステータス
     */
    @Override
    public RepeatStatus execute(@NonNull final StepContribution contribution,
                                @NonNull final ChunkContext chunkContext) {

        final KabuPlusJsfBalanceApiResponse response = kabuPlusApiRepository.getJsfBalance();

        final List<Balance> balances =
                response.getBalances().stream().map(processor::process)
                        .collect(Collectors.toList());

        // フィルタリングされた銘柄を保存
        try {
            writer.write(balances);
        } catch (final Exception e) {
            log.error("書き込み処理に失敗しました - {}", e.getMessage());
            contribution.setExitStatus(ExitStatus.FAILED);
        }

        log.info("complete insert chunk");

        return RepeatStatus.FINISHED;
    }
}
