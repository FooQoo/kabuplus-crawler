package fooqoo.trade.stock.crawler.domain.repository;

import fooqoo.trade.stock.crawler.infrastructure.api.response.KabuPlusApiResponse;

public interface KabuPlusApiRepository {

  KabuPlusApiResponse getLatestPrices();
}
