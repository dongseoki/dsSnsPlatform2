package com.dssns.alarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.dssns.alarm", "com.dssns.common"})
public class AlarmApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(AlarmApiApplication.class, args);
  }

}
