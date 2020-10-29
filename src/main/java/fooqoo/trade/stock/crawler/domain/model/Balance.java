package fooqoo.trade.stock.crawler.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Value;
import org.springframework.lang.NonNull;

/**
 * 日証金 融資・貸株残高データ.
 */
@Value
@Builder
public class Balance implements Serializable {

    private static final long serialVersionUID = -2123918320409938781L;

    /**
     * 銘柄コード.
     */
    @NonNull
    String code;

    /**
     * 日時.
     */
    @NonNull
    LocalDate crawledDate;

    /**
     * "融資新規（株・口）信用買い.
     */
    Integer newLoan;

    /**
     * 融資返済（株・口）信用買い.
     */
    Integer loanRepayment;

    /**
     * 融資残高（株・口）信用買い.
     */
    Integer loanBalance;

    /**
     * 貸株新規（株・口）信用売り.
     */
    Integer newLending;

    /**
     * 貸株返済（株・口）信用売り.
     */
    Integer lendingRepayment;

    /**
     * 貸株残高（株・口）信用売り.
     */
    Integer lendingBalance;

    /**
     * 差引残高（株・口）.
     */
    Integer balance;

    /**
     * 差引前日比（株・口）.
     */
    Integer balanceDayOverDay;
}
