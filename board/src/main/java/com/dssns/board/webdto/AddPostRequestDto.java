package com.dssns.board.webdto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class AddPostRequestDto {
	@Schema(example = "게시글 제목", description = "게시글 제목입니다.")
	private String title;
	@Schema(example = "게시글 내용", description = "게시글 내용")
	private String postContent;
	@Schema(example = "BASIC", description = "게시글 타입")
	private String postType;

	// FIXME 추후 SecurityContextHolder.getContext().getAuthentication()에서 가져오도록 수정
	@Schema(example = "1", description = "생성 유저 번호")
	@NotNull
	private long createUserNo;

//	TODO 추후.
//	@Builder.Default
//	@Schema(description = "태그 리스트. 해당 문자열 없을경우 데이터 생성.")
//	private List<String> tagList = new ArrayList<>();
}
