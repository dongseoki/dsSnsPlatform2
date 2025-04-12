package com.dssns.board.entity;

public enum PostType {
  BASIC("BASIC"),
  HELP("HELP"),
  FAQ("FAQ");

  private final String value;

  PostType(String value) {
    this.value = value;
  }

  public String toString() {
    return value;
  }
}
