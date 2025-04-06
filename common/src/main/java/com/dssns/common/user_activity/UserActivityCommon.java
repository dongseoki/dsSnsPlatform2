package com.dssns.common.user_activity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Represents a common user activity log event.
 */
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserActivityCommon {

  /**
   * The unique identifier for the event, generated as a UUID.
   */
  private String eventId;

  /**
   * The timestamp when the event occurred, in ISO 8601 format.
   */
  private String timestamp;

  /**
   * The user ID, which is the primary key in the user table.
   */
  private String userId;

  /**
   * The user login ID.
   */
  private String userLoginId;

  /**
   * The JWT ID identifier.
   */
  private String jti;

  /**
   * The type of event, such as click, access, view, like, notification, etc.
   */
  private String eventType;

  /**
   * The device information from which the event was triggered.
   */
  private String device;

  /**
   * The location information of the user.
   */
  private String location;

  /**
   * The URL where the event occurred. It can be null.
   */
  private String pageUrl;
}