package com.dssns.common.exception;

import lombok.Getter;

@Getter
public enum ServiceExceptionCode {

  NOT_FOUND_PRODUCT("NOT_FOUND_PRODUCT", "Product를 찾을 수 없습니다."),

  NOT_FOUND_USER("NOT_FOUND_USER", "User를 찾을 수 없습니다."),
  NOT_FOUND_CATEGORY("NOT_FOUND_CATEGORY", "Category를 찾을 수 없습니다."),
  INSUFFICIENT_STOCK("INSUFFICIENT_STOCK", "재고가 부족합니다."),

  // 불가능한 주문 상태 변경
  ILLIGAL_ORDER_STATUS_CHANGE("ILLIGAL_ORDER_STATUS_CHANGE",
      "불가능한 주문 상태 변경입니다.(상태 변경 가능 범위: pending → completed / canceled)"),
  NOT_FOUND_ORDER("NOT_FOUND_ORDER", "Order를 찾을 수 없습니다."),
  ALREADY_EXIST_REFUND("ALREADY_EXIST_REFUND", "이미 환불 요청된 주문입니다."),
  NOT_FOUND_REFUND("NOT_FOUND_REFUND", "Refund를 찾을 수 없습니다."),
  INVALID_DISCOUNT_TYPE("INVALID_DISCOUNT_TYPE", "할인 타입은 percentage 또는 fixed만 가능합니다."),
  INVALID_DISCOUNT_FIXED_VALUE("INVALID_DISCOUNT_FIXED_VALUE", "할인 값은 0 이상 상품 가격 이하여야 합니다."),
  INVALID_DISCOUNT_PERCENT_VALUE("INVALID_DISCOUNT_PERCENT_VALUE", "할인 비율은 0 이상 100 이하여야 합니다."),
  NOT_ACTIVE_ADMIN("NOT_ACTIVE_ADMIN", "활성화 되지 않은 관리자입니다."),
  INVALID_REFUND_AMOUNT("INVALID_REFUND_AMOUNT", "환불 금액은 0 이상 주문 금액 이하여야 합니다."),
  INVALID_TEST_REQUEST("INVALID_TEST_REQUEST", "잘못된 요청 테스트입니다."),
  INVALID_SORT("INVALID_SORT", "정렬 기준은 price 또는 createdAt만 가능합니다."),
  INVALID_FILE("INVALID_FILE", "유효하지 않은 파일"),
  OUT_OF_STOCK_PRODUCT("OUT_OF_STOCK_PRODUCT", "재고가 부족합니다."),
  NOT_FOUND_TASK("NOT_FOUND_TASK", "Task를 찾을 수 없습니다."),
  NOT_FOUND_COUPON_INFO("NOT_FOUND_COUPON_INFO", "CouponInfo를 찾을 수 없습니다."), NOT_FOUND_COUPON_CODE(
      "NOT_FOUND_COUPON_CODE", "Coupon 코드를 찾을 수 없습니다."), NOT_FOUND_COUPON("NOT_FOUND_COUPON", "Coupon을 찾을 수 없습니다."), ALREADY_USED_COUPON(
          "ALREADY_USED_COUPON", "이미 사용된 Coupon입니다."), NOT_FOUND_COUPON_USER("NOT_FOUND_COUPON_USER", "Coupon User를 찾을 수 없습니다.")
  , POST_NOT_FOUND("POST_NOT_FOUND", "게시글을 찾을 수 없습니다.")
  , COMMENT_NOT_FOUND("COMMENT_NOT_FOUND", "댓글을 찾을 수 없습니다.")
  , LIKE_NOT_FOUND("LIKE_NOT_FOUND", "좋아요를 찾을 수 없습니다.")
  , INVALID_NOTIFICATION_ID("INVALID_NOTIFICATION_ID", "Notification ID가 유효하지 않습니다.");

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