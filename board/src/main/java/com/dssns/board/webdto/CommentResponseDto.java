package com.dssns.board.webdto;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
public class CommentResponseDto {

  private Long commentNo;
  private String commentContent;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
  private Long createdBy;
  private String userId;
  private String nickname;

}
