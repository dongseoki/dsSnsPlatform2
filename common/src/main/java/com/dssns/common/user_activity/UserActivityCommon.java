package com.dssns.common.user_activity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserActivityCommon {
  private String eventId;
  private String timestamp;
  private String userId;
  private String userLoginId;
  private String jti;
  private String eventType;
  private String device;
  private String location;
  private String pageUrl;
}
