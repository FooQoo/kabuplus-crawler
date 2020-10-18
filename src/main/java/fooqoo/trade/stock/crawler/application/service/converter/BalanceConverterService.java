package fooqoo.trade.stock.crawler.application.service.converter;

import fooqoo.trade.stock.crawler.domain.model.Balance;
import fooqoo.trade.stock.crawler.domain.model.converter.BalanceConverter;
import fooqoo.trade.stock.crawler.domain.service.ConverterUtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Priceに変換するServiceクラス.
 */
@Service
@RequiredArgsConstructor
public class BalanceConverterService {

    private static final int EXPECTED_ROW_LENGTH = 14;

    private final ConverterUtilService converterUtilService;

    /**
     * 株+ -> DB要素に変換.
     *
     * @param balance 株+
     * @return DB
     */
    public Balance getBalance(final String[] balance) {
        return convertResponseToBalance(balance);
    }

    /**
     * 行ごとの変換処理.
     *
     * @param row 株+の銘柄要素
     * @return DB行要素
     */
    private Balance convertResponseToBalance(final String[] row) {
        if (row.length == EXPECTED_ROW_LENGTH) {
            return Balance.builder()
                    .code(row[BalanceConverter.CODE.getIndex()])
                    .crawledDate(converterUtilService
                            .getFormattedDate(row[BalanceConverter.CRAWLED_DATE.getIndex()],
                                    ConverterUtilService.DATE_OF_PRICE_FORMAT))
                    .newLoan(converterUtilService
                            .getIntegerFormat(row[BalanceConverter.NEW_LOAN.getIndex()]))
                    .loanRepayment(
                            converterUtilService.getIntegerFormat(
                                    row[BalanceConverter.LOAN_REPAYMENT.getIndex()]))
                    .loanBalance(converterUtilService
                            .getIntegerFormat(row[BalanceConverter.LOAN_BALANCE.getIndex()]))
                    .newLending(converterUtilService
                            .getIntegerFormat(row[BalanceConverter.NEW_LENDING.getIndex()]))
                    .lendingRepayment(
                            converterUtilService.getIntegerFormat(
                                    row[BalanceConverter.LENDING_REPAYMENT.getIndex()]))
                    .lendingBalance(
                            converterUtilService.getIntegerFormat(
                                    row[BalanceConverter.LENDING_BALANCE.getIndex()]))
                    .balance(converterUtilService
                            .getIntegerFormat(row[BalanceConverter.BALANCE.getIndex()]))
                    .balanceDayOverDay(
                            converterUtilService.getIntegerFormat(
                                    row[BalanceConverter.BALANCE_DAY_OVER_DAY.getIndex()]))
                    .build();
        }
        return Balance.builder().build();
    }
}
