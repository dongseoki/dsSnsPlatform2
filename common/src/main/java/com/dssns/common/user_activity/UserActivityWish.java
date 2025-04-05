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
}
