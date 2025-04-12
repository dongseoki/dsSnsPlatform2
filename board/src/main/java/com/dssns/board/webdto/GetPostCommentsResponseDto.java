package com.dssns.board.webdto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
public class GetPostCommentsResponseDto {

  private List<CommentResponseDto> list;
  private Long totalCount;

}
