package com.dssns.common.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TimeUtilTest {

  @Test
  void getKSTTimestamp() {
    String kstTimestamp = TimeUtil.getKSTTimestamp();
    System.out.println("kstTimestamp = " + kstTimestamp);
  }
}