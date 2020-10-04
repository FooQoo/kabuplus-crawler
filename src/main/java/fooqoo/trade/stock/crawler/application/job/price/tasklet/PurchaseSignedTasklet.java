package fooqoo.trade.stock.crawler.application.job.price.tasklet;

import fooqoo.trade.stock.crawler.application.job.price.PriceReader;
import fooqoo.trade.stock.crawler.application.service.CloudStorageService;
import fooqoo.trade.stock.crawler.domain.model.Price;
import java.util.ArrayList;
import java.util.List;
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
public class PurchaseSignedTasklet implements Tasklet {

    private final PriceReader reader;
    private final CloudStorageService cloudStorageService;

    /**
     * タスクレットの実行メソッド.
     *
     * @param stepContribution StepContributionインスタンス
     * @param chunkContext     ChunkContextインスタンス
     * @return 実行ステータス
     * @throws Exception 例外
     */
    @Override
    public RepeatStatus execute(
            @NonNull final StepContribution stepContribution, final ChunkContext chunkContext)
            throws Exception {

        Price price;
        List<Price> prices = new ArrayList<>();

        reader.open(chunkContext.getStepContext().getStepExecution().getExecutionContext());

        while ((price = reader.read()) != null) {
            prices.add(price);
        }

        cloudStorageService.writeCloudResource(prices);

        log.info("complete write file");

        return RepeatStatus.FINISHED;
    }
}
