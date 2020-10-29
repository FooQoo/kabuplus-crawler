package fooqoo.trade.stock.crawler.infrastructure.api.reposiotryimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import fooqoo.trade.stock.crawler.domain.repository.CloudStorageRepository;
import fooqoo.trade.stock.crawler.infrastructure.api.response.KabuPlusPriceApiResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StreamUtils;

/**
 * CloudStorageRepository実装クラス.
 */
@Repository
@RequiredArgsConstructor
public class CloudStorageRepositoryImpl implements CloudStorageRepository {

    private final ObjectMapper mapper;
    @Value("gs://${gcs-resource-bucket}/stock.json")
    private Resource gcsReadFile;
    @Value("gs://${gcs-resource-bucket}/sign.csv")
    private Resource gcsWriteFile;

    /**
     * GCSからJSON形式のデータ取得.
     *
     * @return json形式のデータ
     * @throws IOException IOException
     */
    @Override
    public KabuPlusPriceApiResponse getCloudResource() throws IOException {
        return mapper.readValue(
                StreamUtils
                        .copyToString(gcsReadFile.getInputStream(), Charset.defaultCharset()),
                KabuPlusPriceApiResponse.class);
    }

    /**
     * GCSにファイル書き込み.
     *
     * @param csv csv形式の文字列
     * @throws IOException IOException
     */
    @Override
    public void writeCloudResource(final String csv) throws IOException {
        try (final OutputStream os = ((WritableResource) gcsWriteFile).getOutputStream()) {
            os.write(csv.getBytes());
        }
    }
}
