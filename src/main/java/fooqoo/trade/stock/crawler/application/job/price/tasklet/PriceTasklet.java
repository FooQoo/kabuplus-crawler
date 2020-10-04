package fooqoo.trade.stock.crawler.application.job.price.tasklet;

import fooqoo.trade.stock.crawler.application.job.price.PriceProcessor;
import fooqoo.trade.stock.crawler.application.job.price.PriceWriter;
import fooqoo.trade.stock.crawler.application.service.PriceFilterService;
import fooqoo.trade.stock.crawler.domain.model.Price;
import fooqoo.trade.stock.crawler.domain.repository.KabuPlusApiRepository;
import fooqoo.trade.stock.crawler.infrastructure.api.response.KabuPlusApiResponse;
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
    private final PriceFilterService priceFilterService;

    /**
     * タスクレットの実行メソッド.
     *
     * @param stepContribution StepContributionインスタンス
     * @param chunkContext     ChunkContextインスタンス
     * @return 実行ステータス
     */
    @Override
    public RepeatStatus execute(
            @NonNull final StepContribution stepContribution,
            @NonNull final ChunkContext chunkContext) {

        KabuPlusApiResponse response = kabuPlusApiRepository.getLatestPrices();

        List<Price> priceList =
                response.getPrices().stream().map(processor::process).collect(Collectors.toList());

        // フィルタリングされた銘柄を保存
        try {
            writer.write(priceFilterService.getFilteredPrice(priceList));
        } catch (Exception e) {
            log.error("書き込み処理に失敗しました - {}", e.getMessage());
        }

        log.info("complete insert chunk");

        return RepeatStatus.FINISHED;
    }
}
