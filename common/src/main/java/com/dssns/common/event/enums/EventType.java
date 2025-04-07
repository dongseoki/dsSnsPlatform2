package com.dssns.common.event.enums;

public enum EventType {
  LIKE("like"),
  COMMENT("comment");
  private final String value;

  EventType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public String toString() {
    return value;
  }
}