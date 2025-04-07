package com.dssns.common.interceptor;

import com.dssns.common.user_activity.UserActivityAccess;
import com.dssns.common.user_activity.UserActivityCommon;
import com.dssns.common.user_activity.UserActivityUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Slf4j
@RequiredArgsConstructor
public class UserActivityInterceptor implements HandlerInterceptor {
    private final ObjectMapper objectMapper;

    private static final Logger accessLogger = LoggerFactory.getLogger("USER_ACTIVITY_ACCESS_LOGGER");

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion start: {}", request.getRequestURI());
        try{
            UserActivityCommon commonLogEvent = UserActivityUtil.createUserActivityCommon(request, "access");

            long requestStartTime = (long) request.getAttribute("requestStartTime");

            UserActivityAccess logEvent = UserActivityAccess.builder()
                .eventId(commonLogEvent.getEventId())
                .timestamp(commonLogEvent.getTimestamp())
                .userId(commonLogEvent.getUserId())
                .userLoginId(commonLogEvent.getUserLoginId())
                .jti(commonLogEvent.getJti())
                .eventType(commonLogEvent.getEventType())
                .device(commonLogEvent.getDevice())
                .location(commonLogEvent.getLocation())
                .pageUrl(commonLogEvent.getPageUrl())
                .method(request.getMethod())
                .endpoint(request.getRequestURI())
                .statusCode(response.getStatus())
                .responseTime(System.currentTimeMillis() - requestStartTime)
                .requestUid(request.getHeader("X-Request-UID"))
                .build();

            accessLogger.info(objectMapper.writeValueAsString(logEvent));
        } catch (Exception e) {
            log.error("Error while create user activity access log: {}", e.getMessage());
        }
        log.info("afterCompletion end: {}", request.getRequestURI());

    }
}
