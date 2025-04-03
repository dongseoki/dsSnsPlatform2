package com.dssns.alarm.controller;

import com.dssns.alarm.service.NotificationService;
import com.dssns.alarm.webdto.GetNotificationListReq;
import com.dssns.alarm.webdto.GetNotificationListRes;
import com.dssns.common.web.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
@Tag(name = "알림", description = "알림 API")
public class AlarmController {
  private final NotificationService notificationService;

  @PostMapping()
  public ApiResponse<GetNotificationListRes> getUnreadNotifications(@RequestBody @Valid GetNotificationListReq getNotificationListReq) {
    return ApiResponse.Success(notificationService.getUnreadNotifications(getNotificationListReq.getUserId()));
  }

  @PatchMapping("/{id}/read")
  public ApiResponse<Void> markAsRead(@PathVariable @NotNull Long id) {
    notificationService.markAsRead(id);
    return ApiResponse.Success();
  }

}
