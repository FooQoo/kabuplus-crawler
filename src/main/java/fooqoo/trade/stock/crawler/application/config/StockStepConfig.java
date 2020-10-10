package fooqoo.trade.stock.crawler.application.config;

import fooqoo.trade.stock.crawler.application.job.JobListener;
import fooqoo.trade.stock.crawler.application.job.index.tasklet.IndexTasklet;
import fooqoo.trade.stock.crawler.application.job.macd.tasklet.MacdTasklet;
import fooqoo.trade.stock.crawler.application.job.price.reader.PriceFileReader;
import fooqoo.trade.stock.crawler.application.job.price.tasklet.PriceStorageTasklet;
import fooqoo.trade.stock.crawler.application.job.price.tasklet.PriceTasklet;
import fooqoo.trade.stock.crawler.application.job.price.tasklet.PurchaseSignedTasklet;
import fooqoo.trade.stock.crawler.application.job.price.writer.PriceWriter;
import fooqoo.trade.stock.crawler.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * PriceStep設定クラス.
 */
@Component
@RequiredArgsConstructor
@EnableBatchProcessing
public class StockStepConfig {
    private static final String PRICE_STEP = "price";
    private static final String PRICE_CHUNK_STEP = "price_chunk";
    private static final String PRICE_STEP_GCS = "price_gcs";
    private static final String UPLOAD_STEP = "upload_gcs";
    private static final String MACD_STEP = "macd_step";
    private static final String INDEX_STEP = "index_step";

    private static final int CHUNK_SIZE = 10000;

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final PriceWriter writer;

    private final PriceFileReader reader;

    private final PriceTasklet tasklet;

    private final PriceStorageTasklet storageTasklet;

    private final PurchaseSignedTasklet purchaseSignedTasklet;

    private final JobListener jobListener;

    private final MacdTasklet macdTasklet;

    private final IndexTasklet indexTasklet;

    /**
     * priceChunkStep.
     *
     * @return Stepインスタンス
     */
    @Bean(name = PRICE_CHUNK_STEP)
    public Step priceChunkStep() {
        return stepBuilderFactory
                .get(PRICE_CHUNK_STEP)
                .<Price, Price>chunk(CHUNK_SIZE)
                .reader(reader)
                .writer(writer)
                .build();
    }

    /**
     * ステップのbean.
     *
     * @return Stepインスタンス
     */
    @Bean(name = PRICE_STEP)
    public Step priceStep() {
        return stepBuilderFactory.get(PRICE_STEP).tasklet(tasklet).build();
    }

    /**
     * ステップのbean.
     *
     * @return Stepインスタンス
     */
    @Bean(name = UPLOAD_STEP)
    public Step uploadStep() {
        return stepBuilderFactory.get(UPLOAD_STEP).tasklet(purchaseSignedTasklet).build();
    }

    /**
     * ステップのbean.
     *
     * @return Stepインスタンス
     */
    @Bean(name = PRICE_STEP_GCS)
    public Step priceStorageStep() {
        return stepBuilderFactory.get(PRICE_STEP_GCS).tasklet(storageTasklet).build();
    }

    /**
     * ステップのbean.
     *
     * @return Stepインスタンス
     */
    @Bean(name = MACD_STEP)
    public Step macdStep() {
        return stepBuilderFactory.get(MACD_STEP).tasklet(macdTasklet).build();
    }

    /**
     * ステップのbean.
     *
     * @return Stepインスタンス
     */
    @Bean(name = INDEX_STEP)
    public Step indexStep() {
        return stepBuilderFactory.get(INDEX_STEP).tasklet(indexTasklet).build();
    }

    /**
     * Jobのbean.
     *
     * @param priceStep Stepインスタンス
     * @return Jobインスタンス
     */
    @Bean
    public Job job(
            @Qualifier(PRICE_STEP) final Step priceStep,
            @Qualifier(UPLOAD_STEP) final Step uploadStep,
            @Qualifier(MACD_STEP) final Step macdStep,
            @Qualifier(INDEX_STEP) final Step indexStep) {
        return jobBuilderFactory
                .get("job")
                .incrementer(new RunIdIncrementer())
                .listener(jobListener)
                .start(priceStep)
                .next(indexStep)
                //.next(macdStep)
                //.next(uploadStep)
                .build();
    }
}
