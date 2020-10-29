package fooqoo.trade.stock.crawler.application.service;

import fooqoo.trade.stock.crawler.domain.model.Index;
import fooqoo.trade.stock.crawler.domain.model.Price;
import fooqoo.trade.stock.crawler.domain.model.Sector;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Priceを絞り込むServiceクラス.
 */
@Service
@RequiredArgsConstructor
public class SectorFilterService {

    /**
     * 大循環分析が当てはまりやすい業種.
     */
    private static final List<Sector> MY_FILTER =
            List.of(
                    Sector.INFORMATION_AND_COMMUNICATION,
                    Sector.WAREHOUSING_AND_HARBOR_TRANSPORTATION,
                    Sector.OTHER_PRODUCTS,
                    Sector.PHARMACEUTICALS,
                    Sector.PRECISION_INSTRUMENTS,
                    Sector.CONSTRUCTION,
                    Sector.SERVICES);

    /**
     * 価格用業種フィルター.
     *
     * @param prices 銘柄
     * @return フィルタリングされた業種
     */
    public List<Price> filterSectorPrice(final List<Price> prices) {
        final List<Price> priceList = new ArrayList<>();

        for (Price price : prices) {
            for (Sector sector : MY_FILTER) {
                if (price.getSector().equals(sector.getName())) {
                    priceList.add(price);
                }
            }
        }

        return priceList;
    }

    /**
     * 指数用業種フィルター.
     *
     * @param indexes 銘柄
     * @return フィルタリングされた銘柄
     */
    public List<Index> filterSectorIndex(final List<Index> indexes) {
        final List<Index> priceList = new ArrayList<>();

        for (Index index : indexes) {
            for (Sector sector : MY_FILTER) {
                if (index.getSector().equals(sector.getName())) {
                    priceList.add(index);
                }
            }
        }

        return priceList;
    }
}
