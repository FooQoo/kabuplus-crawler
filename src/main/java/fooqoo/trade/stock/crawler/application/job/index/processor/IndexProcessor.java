package fooqoo.trade.stock.crawler.application.job.index.processor;

import fooqoo.trade.stock.crawler.application.service.converter.IndexConverterService;
import fooqoo.trade.stock.crawler.domain.model.Index;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Spring Batch中間処理.
 */
@Component
@StepScope
@RequiredArgsConstructor
@Slf4j
public class IndexProcessor implements ItemProcessor<String[], Index> {

    private final IndexConverterService indexConverterService;

    /**
     * 中間処理.
     *
     * @param index 株+の価格
     * @return DB保存用インスタンス
     */
    @Override
    public Index process(@NonNull final String[] index) {
        return indexConverterService.getIndex(index);
    }
}
