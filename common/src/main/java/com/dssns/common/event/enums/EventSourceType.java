package com.dssns.common.event.enums;

public enum EventSourceType {
  POST("post"),
  COMMENT("comment");

  private final String value;

  EventSourceType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public String toString() {
    return value;
  }
}
