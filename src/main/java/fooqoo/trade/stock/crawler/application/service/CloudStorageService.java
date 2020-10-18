package fooqoo.trade.stock.crawler.application.service;

import fooqoo.trade.stock.crawler.domain.model.Price;
import fooqoo.trade.stock.crawler.domain.model.converter.PriceConverter;
import fooqoo.trade.stock.crawler.domain.repository.CloudStorageRepository;
import fooqoo.trade.stock.crawler.infrastructure.api.response.KabuPlusPriceApiResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * CloudStorageService.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CloudStorageService {

    private final CloudStorageRepository cloudStorageRepository;

    /**
     * cloud storageからファイル読み取り.
     *
     * @return KabuPlusApiResponse
     */
    public KabuPlusPriceApiResponse getPrice() {
        try {
            return cloudStorageRepository.getCloudResource();
        } catch (final IOException e) {
            log.error("ファイル読み取りに失敗しました");
        }
        return new KabuPlusPriceApiResponse();
    }

    /**
     * GCSに書き込む.
     *
     * @param prices 銘柄情報
     */
    public void writeCloudResource(final List<Price> prices) {

        try {
            cloudStorageRepository.writeCloudResource(
                    String.join(
                            "\n", String.join(",", PriceConverter.getColumnNames()),
                            convertCsvFormat(prices)));
        } catch (IOException e) {
            log.error("ファイル書き込みに失敗しました");
        }
    }

    /**
     * csv形式に変換.
     *
     * @param prices 銘柄情報
     * @return CSV文字列に変換した銘柄情報
     */
    private String convertCsvFormat(final List<Price> prices) {
        return prices.stream()
                .map(
                        price ->
                                String.join(
                                        ",",
                                        price.getCode(),
                                        price.getCodeName(),
                                        price.getMarket(),
                                        price.getSector(),
                                        price.getCrawledDate().toString(),
                                        price.getPrice().toString(),
                                        price.getPriceDayOverDay().toString(),
                                        price.getPriceDayOverDayPercentage().toString(),
                                        price.getClosed().toString(),
                                        price.getOpening().toString(),
                                        price.getHigh().toString(),
                                        price.getLow().toString(),
                                        price.getVwap().toString(),
                                        price.getVolume().toString(),
                                        price.getVolumePercentage().toString(),
                                        price.getTradingValue().toString(),
                                        price.getMarketCapitalization().toString(),
                                        price.getPriceLowLimit().toString(),
                                        price.getPriceHighLimit().toString(),
                                        price.getDateOfHighPrice().toString(),
                                        price.getYearlyHigh().toString(),
                                        price.getRateOfDeviationOfYearlyHigh().toString(),
                                        price.getDateOfLowPrice().toString(),
                                        price.getYearlyLow().toString(),
                                        price.getRateOfDeviationOfYearlyLow().toString()))
                .collect(Collectors.joining("\n"));
    }
}
