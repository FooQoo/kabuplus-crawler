package fooqoo.trade.stock.crawler.infrastructure.mapper.mysql;

import fooqoo.trade.stock.crawler.domain.model.write.Price;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PriceMapper {
  int insertPrice(final Price price);
}
