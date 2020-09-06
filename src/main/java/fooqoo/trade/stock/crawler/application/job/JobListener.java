package fooqoo.trade.stock.crawler.application.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JobListener extends JobExecutionListenerSupport {

  @Override
  public void beforeJob(final JobExecution jobExecution) {
    super.beforeJob(jobExecution);
    log.info("ジョブ開始");
  }

  @Override
  public void afterJob(final JobExecution jobExecution) {
    super.afterJob(jobExecution);
    log.info("ジョブ終了");
  }
}
