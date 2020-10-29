package fooqoo.trade.stock.crawler.application.job.price.tasklet;

import fooqoo.trade.stock.crawler.application.job.price.processor.PriceProcessor;
import fooqoo.trade.stock.crawler.application.job.price.writer.PriceWriter;
import fooqoo.trade.stock.crawler.application.service.SectorFilterService;
import fooqoo.trade.stock.crawler.domain.model.Price;
import fooqoo.trade.stock.crawler.domain.repository.KabuPlusApiRepository;
import fooqoo.trade.stock.crawler.infrastructure.api.response.KabuPlusPriceApiResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class PriceTasklet implements Tasklet {

    private final KabuPlusApiRepository kabuPlusApiRepository;
    private final PriceProcessor processor;
    private final PriceWriter writer;
    private final SectorFilterService sectorFilterService;

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

        final KabuPlusPriceApiResponse response = kabuPlusApiRepository.getLatestPrices();

        final List<Price> priceList =
                response.getPrices().stream().map(processor::process).collect(Collectors.toList());

        // フィルタリングされた銘柄を保存
        try {
            writer.write(sectorFilterService.filterSectorPrice(priceList));
        } catch (final Exception e) {
            log.error("書き込み処理に失敗しました - {}", e.getMessage());
            //contribution.setExitStatus(ExitStatus.FAILED);
        }

        log.info("complete insert chunk");

        return RepeatStatus.FINISHED;
    }
}
