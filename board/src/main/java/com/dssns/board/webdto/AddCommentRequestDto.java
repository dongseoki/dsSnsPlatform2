package com.dssns.board.webdto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
public class AddCommentRequestDto {
	@Schema(example = "댓글 내용", description = "댓글 내용")
	private String commentContent;

	// FIXME 추후 SecurityContextHolder.getContext().getAuthentication()에서 가져오도록 수정
	@Schema(example = "1", description = "생성 유저 번호")
	@NotNull
	private Long createUserNo;
}
