package com.dssns.common.util;

import io.micrometer.common.util.StringUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class UUIDGenerator {

  public static String generateUUIDWithPrefix(String prefixCode) {
    if (StringUtils.isEmpty(prefixCode)) {
      prefixCode = "";
    }
    String prefix = prefixCode + "_";
    String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    String randomCode = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    return prefix + date + "_" + randomCode;
  }
}