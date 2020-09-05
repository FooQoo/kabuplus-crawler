package fooqoo.trade.stock.crawler.application.service;

import fooqoo.trade.stock.crawler.domain.repository.CloudStorageRepository;
import fooqoo.trade.stock.crawler.infrastructure.api.response.KabuPlusApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * CloudStorageService
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CloudStorageService {

  private final CloudStorageRepository cloudStorageRepository;

  /**
   * cloud storageからファイル読み取り
   *
   * @return KabuPlusApiResponse
   */
  public KabuPlusApiResponse getPrice() {
    try {
      return cloudStorageRepository.getCloudResource();
    } catch (IOException e) {
      log.error("ファイル読み取りに失敗しました");
    }
    return new KabuPlusApiResponse();
  }
}
