package com.dssns.alarm.service;

import com.dssns.alarm.entity.Notification;
import com.dssns.alarm.repository.NotificationRepository;
import com.dssns.alarm.webdto.GetNotificationListRes;
import com.dssns.alarm.webdto.GetNotificationListRes.GetNotificationItem;
import com.dssns.common.exception.ServiceException;
import com.dssns.common.exception.ServiceExceptionCode;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {
  private final NotificationRepository notificationRepository;


  public GetNotificationListRes getUnreadNotifications(@NotNull Long userId) {
    List<Notification> notifications = notificationRepository.findAllByReceiverUserIdAndIsReadIsFalse(userId);
    List<GetNotificationItem> notificationItems = notifications.stream()
        .map(GetNotificationItem::fromEntity)
        .collect(Collectors.toList());
    return new GetNotificationListRes(notificationItems, (long) notificationItems.size());
  }


  @Transactional
  public void markAsRead(@NotNull Long id) {
    Notification notification = notificationRepository.findById(id)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.INVALID_NOTIFICATION_ID));
    notification.markAsRead(Instant.now());
    notificationRepository.save(notification);
  }
}
