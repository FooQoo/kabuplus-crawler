package fooqoo.trade.stock.crawler.domain.model.converter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 株+の配列のindexと要素の対応.
 */
@RequiredArgsConstructor
@Getter
public enum BalanceConverter {

    CODE(0, "code"),
    CRAWLED_DATE(3, "crawled_date"),
    NEW_LOAN(6, "new_loan"),
    LOAN_REPAYMENT(7, "loan_repayment"),
    LOAN_BALANCE(8, "loan_balance"),
    NEW_LENDING(9, "new_lending"),
    LENDING_REPAYMENT(10, "lending_repayment"),
    LENDING_BALANCE(11, "lending_balance"),
    BALANCE(12, "balance"),
    BALANCE_DAY_OVER_DAY(13, "balance_day_over_day");

    private final Integer index;
    private final String columnName;
}