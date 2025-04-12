package com.dssns.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.dssns.board", "com.dssns.common"})
public class BoardApplication {

  public static void main(String[] args) {
    SpringApplication.run(BoardApplication.class, args);
  }

}
