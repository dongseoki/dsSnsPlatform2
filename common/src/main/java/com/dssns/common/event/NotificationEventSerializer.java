package com.dssns.common.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class NotificationEventSerializer implements Serializer<NotificationEvent> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public byte[] serialize(String topic, NotificationEvent data) {
    try {
      return objectMapper.writeValueAsBytes(data);
    } catch (Exception e) {
      throw new SerializationException("Error serializing NotificationEvent", e);
    }
  }
}