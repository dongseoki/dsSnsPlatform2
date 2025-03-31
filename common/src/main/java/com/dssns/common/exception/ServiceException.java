package com.dssns.common.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

  private String code;
  private String message;

  public ServiceException() {
  }

  public ServiceException(ServiceExceptionCode response) {
    super(response.getMessage());
    this.code = response.getCode();
    this.message = super.getMessage();
  }

  public ServiceException(ServiceExceptionCode response, String customMessage) {
    super(customMessage);
    this.code = response.getCode();
    this.message = super.getMessage();
  }

  @Override
  public String getMessage() {
    return message;
  }
}
