package com.dssns.common.event;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationEventProducer {
  private final String ALARM_TOPIC_NAME = "notification-events";
  private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;

  public void sendNotificationOld(NotificationEvent event) {
    kafkaTemplate.send(ALARM_TOPIC_NAME, event);
  }

  public void publishNotificationEventCreated(NotificationEvent event) {

    try {
      SendResult<String, NotificationEvent> sendResult = kafkaTemplate.send(ALARM_TOPIC_NAME,
          event).get();
      int partition = sendResult.getRecordMetadata().partition();
      RecordMetadata recordMetadata = sendResult.getRecordMetadata();
      log.info("Message sent successfully to topic: {}, partition: {}, metaData: {}"
          , "test", partition, recordMetadata);
    } catch (Exception ex) {
      log.error("Failed to send message to topic: {}", "test", ex);
      throw new RuntimeException("Failed to publish order event", ex);
    }
  }
}