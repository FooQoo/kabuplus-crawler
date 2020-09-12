package fooqoo.trade.stock.crawler.application.service;

import fooqoo.trade.stock.crawler.domain.model.Sector;
import fooqoo.trade.stock.crawler.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/** Priceを絞り込むServiceクラス */
@Service
@RequiredArgsConstructor
public class PriceFilterService {

  /** 大循環分析が当てはまりやすい業種 */
  private final List<Sector> MY_FILTER =
      List.of(
          Sector.INFORMATION_AND_COMMUNICATION,
          Sector.WAREHOUSING_AND_HARBOR_TRANSPORTATION,
          Sector.OTHER_PRODUCTS,
          Sector.PHARMACEUTICALS,
          Sector.PRECISION_INSTRUMENTS,
          Sector.CONSTRUCTION,
          Sector.SERVICES);

  /**
   * 業種フィルター
   *
   * @param prices 銘柄
   * @param sectors 業種
   * @return フィルタリングされた業種
   */
  private List<Price> filterSector(List<Price> prices, List<Sector> sectors) {
    List<Price> priceList = new ArrayList<>();

    for (Price price : prices) {
      for (Sector sector : sectors) {
        if (price.getSector().equals(sector.getName())) {
          priceList.add(price);
        }
      }
    }

    return priceList;
  }

  /**
   * 独自フィルタを適用するメソッド
   *
   * @param price 銘柄
   * @return フィルタリングされた銘柄
   */
  public List<Price> getFilteredPrice(List<Price> price) {
    return filterSector(price, MY_FILTER);
  }
}
