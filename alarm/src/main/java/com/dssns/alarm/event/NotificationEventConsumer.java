package com.dssns.alarm.event;

import com.dssns.alarm.entity.Notification;
import com.dssns.alarm.repository.NotificationRepository;
import com.dssns.common.event.NotificationEvent;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationEventConsumer {
  private final NotificationRepository notificationRepository;

  // record 를 수신하기 위한 consumer 설정
  @KafkaListener(topics = "notification-events",
      containerFactory = "kafkaConsumerFactory")
  public void receive(NotificationEvent event) {
    log.info("Received NotificationEvent: {}", event.toString());
    Notification notification = Notification.builder()
        .receiverUserId(event.getReceiverUserId())
        .eventType(event.getEventType())
        .eventSourceType(event.getEventSourceType())
        .eventSourceId(event.getEventSourceId())
        .eventUserId(event.getEventUserId())
        .message(event.getMessage())
        .isRead(false)
        .createdAt(Instant.now())
        .build();

    notificationRepository.save(notification);
  }
}
