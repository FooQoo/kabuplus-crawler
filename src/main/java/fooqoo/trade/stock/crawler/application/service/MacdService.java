package fooqoo.trade.stock.crawler.application.service;

import fooqoo.trade.stock.crawler.infrastructure.mapper.mysql.MacdMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/** CloudStorageService */
@Slf4j
@Service
@RequiredArgsConstructor
public class MacdService {

  private final ZonedDateTime nowZonedDt = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));

  private static final String DATE_OF_PRICE_FORMAT = "yyyy-MM-dd";

  private final MacdMapper mapper;

  public LocalDate getToday() {
    return nowZonedDt.toLocalDate();
  }

  public LocalDate getLatestDate() {
    return mapper.getLatestDate().getCrawledDate();
  }

  public void insertMacd(LocalDate today, LocalDate latestDate) {
    String todayString = today.format(DateTimeFormatter.ofPattern(DATE_OF_PRICE_FORMAT));
    String latestDateString = latestDate.format(DateTimeFormatter.ofPattern(DATE_OF_PRICE_FORMAT));
    mapper.insertMacd(todayString, latestDateString);
  }
}
