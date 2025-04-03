package com.dssns.common.event;

import com.dssns.common.event.enums.EventSourceType;
import com.dssns.common.event.enums.EventType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.Instant;
import lombok.ToString;

@Builder
@Getter
@NoArgsConstructor
@ToString
public class NotificationEvent {
  private EventType eventType;      // LIKE, COMMENT
  private Long receiverUserId;      // 알림을 받을 사용자 ID
  private Long eventUserId;         // 이벤트를 발생시킨 사용자 ID
  private Long eventSourceId;       // 이벤트 대상 ID (게시글, 댓글 등)
  private EventSourceType eventSourceType; // 대상 유형 (POST, COMMENT)
  private String message;           // 알람 메시지
  private Instant createdAt;        // 생성 시간

  public NotificationEvent(EventType eventType, Long receiverUserId, Long eventUserId,
      Long eventSourceId, EventSourceType eventSourceType, String message, Instant createdAt) {
    if (eventType == null || receiverUserId == null || eventUserId == null ||
        eventSourceId == null || eventSourceType == null || message == null || createdAt == null) {
      throw new IllegalArgumentException("All fields must be non-null");
    }
    this.eventType = eventType;
    this.receiverUserId = receiverUserId;
    this.eventUserId = eventUserId;
    this.eventSourceId = eventSourceId;
    this.eventSourceType = eventSourceType;
    this.message = message;
    this.createdAt = createdAt;
  }
}