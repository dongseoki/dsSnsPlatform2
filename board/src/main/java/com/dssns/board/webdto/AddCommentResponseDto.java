package com.dssns.board.webdto;

import com.dssns.board.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class AddCommentResponseDto {
	@Schema(example = "1", description = "댓글 번호")
	private Long commentNo;

	public static AddCommentResponseDto of(Comment comment) {
		return new AddCommentResponseDto(comment.getId());
	}
}
