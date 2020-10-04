package fooqoo.trade.stock.crawler;

import java.util.Map;
import lombok.Data;

/**
 * pubsubメッセージのクラス.
 */
@Data
public class PubSubMessage {

    private String data;

    private Map<String, String> attributes;

    private String messageId;

    private String publishTime;
}
