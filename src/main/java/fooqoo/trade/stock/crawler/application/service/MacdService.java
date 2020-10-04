package fooqoo.trade.stock.crawler.application.service;

import fooqoo.trade.stock.crawler.infrastructure.mapper.mysql.MacdMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * CloudStorageService.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MacdService {

    private static final String DATE_OF_PRICE_FORMAT = "yyyy-MM-dd";
    private final ZonedDateTime nowZonedDt = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
    private final MacdMapper mapper;

    /**
     * 当日の日付を獲得する.
     *
     * @return 当日の日付
     */
    public LocalDate getToday() {
        return nowZonedDt.toLocalDate();
    }

    /**
     * macdテーブルの最新日付を取得する.
     *
     * @return 最新日付
     */
    public LocalDate getLatestDate() {
        return mapper.getLatestDate().getCrawledDate();
    }

    /**
     * macdテーブルへの挿入.
     *
     * @param today      当日日付
     * @param latestDate 最新日付
     */
    public void insertMacd(LocalDate today, LocalDate latestDate) {
        final String todayString = today.format(DateTimeFormatter.ofPattern(DATE_OF_PRICE_FORMAT));
        final String latestDateString =
                latestDate.format(DateTimeFormatter.ofPattern(DATE_OF_PRICE_FORMAT));
        mapper.insertMacd(todayString, latestDateString);
    }
}
