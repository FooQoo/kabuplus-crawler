package fooqoo.trade.stock.crawler.application.service.converter;

import fooqoo.trade.stock.crawler.domain.model.Index;
import fooqoo.trade.stock.crawler.domain.model.converter.IndexConverter;
import fooqoo.trade.stock.crawler.domain.service.ConverterUtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Priceに変換するServiceクラス.
 */
@Service
@RequiredArgsConstructor
public class IndexConverterService {


    private static final int EXPECTED_ROW_LENGTH = 18;

    private final ConverterUtilService converterUtilService;

    /**
     * 株+ -> DB要素に変換.
     *
     * @param price 株+
     * @return DB
     */
    public Index getIndex(final String[] price) {
        return convertResponseToIndex(price);
    }

    /**
     * 行ごとの変換処理.
     *
     * @param row 株+の銘柄要素
     * @return DB行要素
     */
    private Index convertResponseToIndex(final String[] row) {
        if (row.length == EXPECTED_ROW_LENGTH) {
            return Index.builder()
                    .code(row[IndexConverter.CODE.getIndex()])
                    .sector(row[IndexConverter.SECTOR.getIndex()])
                    .crawledDate(converterUtilService.getToday())
                    .share(converterUtilService
                            .getIntegerFormat(row[IndexConverter.SHARE.getIndex()]))
                    .yield(converterUtilService
                            .getBigDecimalFormat(row[IndexConverter.YIELD.getIndex()]))
                    .forwardPer(converterUtilService
                            .getBigDecimalFormat(row[IndexConverter.FORWARD_PER.getIndex()]))
                    .trailingPer(converterUtilService
                            .getBigDecimalFormat(row[IndexConverter.TRAILING_PER.getIndex()]))
                    .forwardEps(converterUtilService
                            .getBigDecimalFormat(row[IndexConverter.FORWARD_PER.getIndex()]))
                    .trailingBps(converterUtilService
                            .getBigDecimalFormat(row[IndexConverter.TRAILING_BPS.getIndex()]))
                    .build();
        }
        return Index.builder().build();
    }
}
