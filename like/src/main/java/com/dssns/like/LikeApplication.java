package com.dssns.like;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.dssns.like", "com.dssns.common"})
public class LikeApplication {

  public static void main(String[] args) {
    SpringApplication.run(LikeApplication.class, args);
  }

}
