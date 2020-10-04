package fooqoo.trade.stock.crawler.application.job.price;

import fooqoo.trade.stock.crawler.application.service.PriceConverterService;
import fooqoo.trade.stock.crawler.domain.model.Price;
import fooqoo.trade.stock.crawler.domain.model.PriceConverter;
import java.io.IOException;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * Spring Batch Readerクラス.
 */
@Component
@StepScope
public class PriceFileReader extends FlatFileItemReader<Price> {

    /**
     * ファイル読み取り.
     *
     * @param priceConverterService priceConverter
     * @param resourceLoader        resourceLoader
     * @throws IOException IOException
     */
    public PriceFileReader(
            final PriceConverterService priceConverterService, final ResourceLoader resourceLoader)
            throws IOException {
        Resource resource = resourceLoader.getResource("classpath:sample.csv");

        setResource(new FileSystemResource(resource.getFile().toString()));
        setLineMapper(
                new DefaultLineMapper<>() {
                    {
                        setLineTokenizer(
                                new DelimitedLineTokenizer() {
                                    {
                                        setNames(PriceConverter.getColumnNames());
                                        setDelimiter(",");
                                        setQuoteCharacter('"');
                                        setLinesToSkip(1);
                                    }
                                });
                        setFieldSetMapper(new PriceFieldSetMapper(priceConverterService));
                    }
                });
    }
}
