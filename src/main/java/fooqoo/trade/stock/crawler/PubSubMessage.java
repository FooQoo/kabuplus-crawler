package fooqoo.trade.stock.crawler;

import lombok.Data;

import java.util.Map;

@Data
public class PubSubMessage {

  private String data;

  private Map<String, String> attributes;

  private String messageId;

  private String publishTime;
}
