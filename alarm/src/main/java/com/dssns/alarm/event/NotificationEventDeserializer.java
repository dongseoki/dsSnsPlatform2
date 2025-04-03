package com.dssns.alarm.event;


import com.dssns.common.config.ModuleConfig;
import com.dssns.common.event.NotificationEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class NotificationEventDeserializer implements Deserializer<NotificationEvent> {

  private final ObjectMapper objectMapper = ModuleConfig.generateTimeSupportObjectMapper();



  @Override
  public NotificationEvent deserialize(String topic, byte[] data) {
    try {
      if (data == null) {
        return null;
      }
      return objectMapper.readValue(data, NotificationEvent.class);
    } catch (Exception e) {
      throw new SerializationException("Error deserializing NotificationEvent", e);
    }
  }

  @Override
  public void close() {
    // No resources to close
  }
}