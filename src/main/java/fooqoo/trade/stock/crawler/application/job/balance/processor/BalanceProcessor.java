package fooqoo.trade.stock.crawler.application.job.balance.processor;

import fooqoo.trade.stock.crawler.application.service.converter.BalanceConverterService;
import fooqoo.trade.stock.crawler.domain.model.Balance;
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
public class BalanceProcessor implements ItemProcessor<String[], Balance> {

    private final BalanceConverterService balanceConverterService;

    /**
     * 中間処理.
     *
     * @param balance 株+の価格
     * @return DB保存用インスタンス
     */
    @Override
    public Balance process(@NonNull final String[] balance) {
        return balanceConverterService.getBalance(balance);
    }
}
