package fooqoo.trade.stock.crawler.application.job.kabuplus;

import fooqoo.trade.stock.crawler.domain.model.write.Price;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class PriceWriter extends MyBatisBatchItemWriter<Price> {

  public PriceWriter(final SqlSessionFactory sqlSessionFactory) {
    setSqlSessionFactory(sqlSessionFactory);
    setStatementId("fooqoo.trade.stock.crawler.infrastructure.mapper.mysql.PriceMapper.insertPrice");
  }
}
