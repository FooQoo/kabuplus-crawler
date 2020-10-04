package fooqoo.trade.stock.crawler.application.job.price;

import fooqoo.trade.stock.crawler.application.service.PriceConverterService;
import fooqoo.trade.stock.crawler.domain.model.Price;
import fooqoo.trade.stock.crawler.domain.model.PriceConverter;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * PriceFieldSetMapper.
 */
@Component
@RequiredArgsConstructor
public class PriceFieldSetMapper implements FieldSetMapper<Price> {

    private final PriceConverterService priceConverterService;

    /**
     * ファイル要見取り結果をPriceに変換する.
     *
     * @param arg ファイルの行
     * @return Price
     */
    @NonNull
    public Price mapFieldSet(@NonNull final FieldSet arg) {
        String[] priceArray =
                Arrays.stream(PriceConverter.values())
                        .map(column -> arg.readString(column.getColumnName()))
                        .toArray(String[]::new);

        return priceConverterService.getPrice(priceArray);
    }
}
