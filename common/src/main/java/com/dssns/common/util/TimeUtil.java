package com.dssns.common.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
  public static String getKSTTimestamp() {
    ZonedDateTime kstTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
    return kstTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
  }
}