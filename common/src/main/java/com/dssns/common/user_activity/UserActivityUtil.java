package com.dssns.common.user_activity;

import com.dssns.common.util.TimeUtil;
import com.dssns.common.util.UUIDGenerator;
import jakarta.servlet.http.HttpServletRequest;

public class UserActivityUtil {
  public static UserActivityCommon createUserActivityCommon(HttpServletRequest request, String eventType) {
    String eventId = UUIDGenerator.generateUUIDWithPrefix("event");
    String timestamp = TimeUtil.getKSTTimestamp();
//    String userId = request.getParameter("userId");
//    String userLoginId = request.getParameter("userLoginId");
//    String jti = UUID.randomUUID().toString();
//    String location = request.getParameter("location");
    String device = request.getHeader("User-Agent");
    String pageUrl = request.getRequestURL().toString();

    return UserActivityCommon.builder()
        .eventId(eventId)
        .timestamp(timestamp)
//        .userId(userId)
//        .userLoginId(userLoginId)
//        .jti(jti)
        .eventType(eventType)
        .device(device)
//        .location(location)
        .pageUrl(pageUrl)
        .build();
  }
}
