package com.dssns.common.exception;

import lombok.Getter;

@Getter
public enum ServiceExceptionCode {
  NOT_FOUND_USER("NOT_FOUND_USER", "User를 찾을 수 없습니다."), POST_NOT_FOUND("POST_NOT_FOUND",
      "게시글을 찾을 수 없습니다."), COMMENT_NOT_FOUND("COMMENT_NOT_FOUND", "댓글을 찾을 수 없습니다."), LIKE_NOT_FOUND(
      "LIKE_NOT_FOUND", "좋아요를 찾을 수 없습니다."), INVALID_NOTIFICATION_ID("INVALID_NOTIFICATION_ID",
      "Notification ID가 유효하지 않습니다.");

  private final String code;
  private final String message;

  ServiceExceptionCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

  @Override
  public String toString() {
    return "code : " + code + ", message : " + message;
  }
}