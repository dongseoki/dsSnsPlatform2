package com.dssns.common.user_activity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Represents an access log event.
 */
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class UserActivityAccess extends UserActivityCommon {

  /**
   * The HTTP method used for the request.
   */
  private String method;

  /**
   * The API endpoint that was accessed.
   */
  private String endpoint;

  /**
   * The HTTP status code of the response.
   */
  private int statusCode;

  /**
   * The response time in milliseconds.
   */
  private long responseTime;

  /**
   * The unique identifier for the API request.
   */
  private String requestUid;
}
