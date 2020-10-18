package fooqoo.trade.stock.crawler.application.job.balance.writer;

import fooqoo.trade.stock.crawler.domain.model.Balance;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

/**
 * Spring Batch Writerクラス.
 */
@Component
@StepScope
public class BalanceWriter extends MyBatisBatchItemWriter<Balance> {

    private static final String STATEMENT_ID =
            "fooqoo.trade.stock.crawler.infrastructure.mapper.mysql.BalanceMapper.insertBalance";

    /**
     * コンストラクタ.
     *
     * @param sqlSessionFactory SqlSessionFactoryインスタンス
     */
    public BalanceWriter(final SqlSessionFactory sqlSessionFactory) {
        setSqlSessionFactory(sqlSessionFactory);
        setStatementId(STATEMENT_ID);
    }
}
