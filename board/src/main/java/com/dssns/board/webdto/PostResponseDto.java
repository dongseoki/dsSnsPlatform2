package com.dssns.board.webdto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
public class PostResponseDto {

  private Long postNo;
  private String postTitle;
  private String postContent;
  private LocalDateTime createdDate;

  @Builder.Default
  private List<String> tagList = new ArrayList<>();

}
