package com.dssns.common.event;

import com.dssns.common.config.ModuleConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

public class NotificationEventSerializer implements Serializer<NotificationEvent> {

  private final ObjectMapper objectMapper = ModuleConfig.generateTimeSupportObjectMapper();


  @Override
  public byte[] serialize(String topic, NotificationEvent data) {
    try {
      return objectMapper.writeValueAsBytes(data);
    } catch (Exception e) {
      throw new SerializationException("Error serializing NotificationEvent", e);
    }
  }
}