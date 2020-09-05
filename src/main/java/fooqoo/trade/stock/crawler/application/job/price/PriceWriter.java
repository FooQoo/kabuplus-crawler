package fooqoo.trade.stock.crawler.application.job.price;

import fooqoo.trade.stock.crawler.domain.model.write.Price;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

/** Spring Batch Writerクラス */
@Component
@StepScope
public class PriceWriter extends MyBatisBatchItemWriter<Price> {

  private static final String STATEMENT_ID =
      "fooqoo.trade.stock.crawler.infrastructure.mapper.mysql.PriceMapper.insertPrice";

  /**
   * コンストラクタ
   *
   * @param sqlSessionFactory SqlSessionFactoryインスタンス
   */
  public PriceWriter(final SqlSessionFactory sqlSessionFactory) {
    setSqlSessionFactory(sqlSessionFactory);
    setStatementId(STATEMENT_ID);
  }
}
