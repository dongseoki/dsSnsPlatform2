package com.dssns.alarm.webdto;

import com.dssns.alarm.entity.Notification;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
public class GetNotificationListRes {
  private List<GetNotificationItem> notifications;
  private Long totalCount;

  @AllArgsConstructor
  @Getter
  @NoArgsConstructor
  public static class GetNotificationItem {

    private Long id;
    private Long receiverUserId;
    private String eventType;
    private String eventSourceType;
    private Long eventSourceId;
    private Long eventUserId;
    private String message;
    private Boolean isRead;
    private Instant readDt;
    private Instant createdAt;

    public static GetNotificationItem fromEntity(Notification notification) {
      return new GetNotificationItem(
          notification.getId(),
          notification.getReceiverUserId(),
          notification.getEventType().name(),
          notification.getEventSourceType().name(),
          notification.getEventSourceId(),
          notification.getEventUserId(),
          notification.getMessage(),
          notification.getIsRead(),
          notification.getReadDt(),
          notification.getCreatedAt()
      );
    }
  }
}