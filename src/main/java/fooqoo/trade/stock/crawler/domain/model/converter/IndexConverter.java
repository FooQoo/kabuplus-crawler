package fooqoo.trade.stock.crawler.domain.model.converter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 株+の配列のindexと要素の対応.
 */
@RequiredArgsConstructor
@Getter
public enum IndexConverter {

    CODE(0, "code"),
    SECTOR(3, "sector"),
    SHARE(5, "share"),
    YIELD(6, "yield"),
    FORWARD_PER(8, "forward_per"),
    TRAILING_PER(9, "trailing_per"),
    FORWARD_EPS(10, "forward_eps"),
    TRAILING_BPS(11, "trailing_bps");

    public final Integer index;
    public final String columnName;
}