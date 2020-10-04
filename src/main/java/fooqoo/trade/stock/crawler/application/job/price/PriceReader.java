package fooqoo.trade.stock.crawler.application.job.price;

import fooqoo.trade.stock.crawler.domain.model.Price;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

/**
 * Spring Batch Writerクラス.
 */
@Component
@StepScope
public class PriceReader extends MyBatisCursorItemReader<Price> {

    private static final String STATEMENT_ID =
            "fooqoo.trade.stock.crawler.infrastructure.mapper.mysql.1PriceMapper."
                    + "findPurchaseSignedPrice";

    /**
     * コンストラクタ.
     *
     * @param sqlSessionFactory SqlSessionFactoryインスタンス
     */
    public PriceReader(final SqlSessionFactory sqlSessionFactory) {
        setSqlSessionFactory(sqlSessionFactory);
        setQueryId(STATEMENT_ID);
    }
}
