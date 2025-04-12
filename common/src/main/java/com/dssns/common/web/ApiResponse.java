package com.dssns.common.web;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class ApiResponse<T> {

  private final Boolean result;
  private final Error error;
  private final T message;

  public ApiResponse(Boolean result, String errorCode, String errorMessage, T message) {
    this.result = result;
    this.error = Error.builder()
        .errorCode(errorCode)
        .errorMessage(errorMessage)
        .build();
    this.message = message;
  }

  public static <T> ApiResponse<T> Success(T message) {
    return new ApiResponse<>(true, null, null, message);
  }

  public static <T> ApiResponse<T> Success() {
    return new ApiResponse<>(true, null, null, null);
  }

  public static <T> ResponseEntity<ApiResponse<T>> ResponseException(String code,
      String errorMessage) {
    return ResponseEntity.ok(new ApiResponse<>(false, code, errorMessage, null));
  }

  public static <T> ResponseEntity<ApiResponse<T>> ValidException(String code,
      String errorMessage) {
    return ResponseEntity.ok(new ApiResponse<>(false, code, errorMessage, null));
  }

  // make conflict Exception
  public static <T> ResponseEntity<ApiResponse<T>> ConflictException(String code,
      String errorMessage) {
    return ResponseEntity.status(409)
        .body(new ApiResponse<>(false, code, errorMessage, null));
  }

  public static <T> ResponseEntity<ApiResponse<T>> ServerException(String code,
      String errorMessage) {
    return ResponseEntity.status(500)
        .body(new ApiResponse<>(false, code, errorMessage, null));
  }

  @Getter
  public static class Error {

    private final String errorCode;
    private final String errorMessage;

    @Builder
    public Error(String errorCode, String errorMessage) {
      this.errorCode = errorCode;
      this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
      return "Error{" +
          "errorCode='" + errorCode + '\'' +
          ", errorMessage='" + errorMessage + '\'' +
          '}';
    }
  }

  @Override
  public String toString() {
    return "ApiResponse{" +
        "result=" + result +
        ", error=" + error +
        ", message=" + message +
        '}';
  }
}