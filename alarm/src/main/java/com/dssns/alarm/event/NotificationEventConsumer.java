package com.dssns.alarm.event;

import com.dssns.common.event.NotificationEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationEventConsumer {

  // record 를 수신하기 위한 consumer 설정
  @KafkaListener(topics = "notification-events",
      containerFactory = "kafkaConsumerFactory")
  public void receive(NotificationEvent event) {
    log.info("Received NotificationEvent: {}", event.toString());
  }
}
