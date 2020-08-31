package fooqoo.trade.stock.crawler.application.config;

import fooqoo.trade.stock.crawler.application.job.kabuplus.PriceTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableBatchProcessing
public class PriceStepConfig {

  private static final String PRICE_STEP = "price";

  private final JobBuilderFactory jobBuilderFactory;

  private final StepBuilderFactory stepBuilderFactory;

  private final PriceTasklet tasklet;

  @Bean(name = PRICE_STEP)
  public Step priceStep() {
    return stepBuilderFactory.get(PRICE_STEP).tasklet(tasklet).build();
  }

  @Bean
  public Job job(Step step) throws Exception {
    return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer()).start(step).build();
  }
}
