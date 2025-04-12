package com.dssns.common.user_activity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@Getter
public class UserActivityWish extends UserActivityCommon {

  private String targetType;
  private String targetId;
  private String requestUid;

  public UserActivityWish(String targetType, String targetId, String userId,
      UserActivityCommon userActivityCommon) {
    super(userActivityCommon.getEventId(), userActivityCommon.getTimestamp(), userId,
        userActivityCommon.getUserLoginId(), userActivityCommon.getJti(),
        userActivityCommon.getEventType(), userActivityCommon.getDevice(),
        userActivityCommon.getLocation(), userActivityCommon.getPageUrl());
    this.targetType = targetType;
    this.targetId = targetId;
  }
}
