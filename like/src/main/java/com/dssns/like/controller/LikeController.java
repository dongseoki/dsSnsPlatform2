package com.dssns.like.controller;

import com.dssns.common.event.enums.EventType;
import com.dssns.common.user_activity.UserActivityUtil;
import com.dssns.common.web.ApiResponse;
import com.dssns.like.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
@Tag(name = "좋아요", description = "좋아요 API")
public class LikeController {
  private final LikeService likeService;


  	@Operation(summary = "[o]게시글 좋아요", description = "게시글 좋아요 API")
	@PostMapping("/posts/{postNo}/like")
//	@UserRequired
	public ApiResponse<Void> likePost(@PathVariable("postNo") Long postNo, @RequestParam Long userNo, @RequestParam Long postCreatorUserId, HttpServletRequest httpServletRequest){
		log.info(">>> PostController.likePost");
//		Long userNo = AuthUtil.getUserNoFromAuthentication();
			likeService.likePost(userNo
					, postNo
					, postCreatorUserId
					, UserActivityUtil.createUserActivityCommon(httpServletRequest
							, EventType.LIKE.getValue()
						)
			);
//		likeService.likePost(postNo);
		return ApiResponse.Success();
	}

	@Operation(summary = "[o]게시글 좋아요 취소", description = "게시글 좋아요 취소 API")
	@PostMapping("/posts/{postNo}/like-cancel")
//	@UserRequired
	public ApiResponse<Void> likeCancelPost(@PathVariable("postNo") Long postNo, @RequestParam Long userNo){
//			, Authentication authentication) {
		log.info(">>> PostController.likeCancelPost");
//		Long userNo = AuthUtil.getUserNoFromAuthentication();
//		likeService.likeCancelPost(userNo, postNo, UserAuthInfo.of(authentication));
		likeService.likeCancelPost(userNo, postNo);
		return ApiResponse.Success();
	}

	@Operation(summary = "[o]게시글 좋아요 개수", description = "게시글 좋아요 개수 API")
	@GetMapping("/posts/{postNo}/like-count")
	public ApiResponse<Integer> countLikesForPost(@PathVariable("postNo") Long postNo) {
		log.info(">>> PostController.countLikesForPost");
		int likeCount = likeService.countLikesForPost(postNo);
		return ApiResponse.Success(likeCount);
	}



	@Operation(summary = "[o]댓글 좋아요", description = "댓글 좋아요 API")
	@PostMapping("/comments/{commentNo}/like")
	public ApiResponse<Void> likeComment(
			@PathVariable("commentNo") Long commentNo
			, @RequestParam Long userNo
			, @RequestParam Long commentCreatorUserId
			, HttpServletRequest httpServletRequest) {
		log.info(">>> PostController.likeComment");
		likeService.likeComment(userNo
				, commentNo
				, commentCreatorUserId
				, UserActivityUtil.createUserActivityCommon(httpServletRequest, EventType.LIKE.getValue()));
		return ApiResponse.Success();
	}

	@Operation(summary = "[o]댓글 좋아요 취소", description = "댓글 좋아요 취소 API")
	@PostMapping("/comments/{commentNo}/like-cancel")
	public ApiResponse<Void> likeCancelComment(@PathVariable("commentNo") Long commentNo, @RequestParam Long userNo) {
		log.info(">>> PostController.likeCancelComment");
		likeService.likeCancelComment(userNo, commentNo);
		return ApiResponse.Success();
	}

	// LikeController.java
	@Operation(summary = "[o]댓글 좋아요 개수", description = "댓글 좋아요 개수 API")
	@GetMapping("/comments/{commentNo}/like-count")
	public ApiResponse<Integer> countLikesForComment(@PathVariable("commentNo") Long commentNo) {
		log.info(">>> PostController.countLikesForComment");
		int likeCount = likeService.countLikesForComment(commentNo);
		return ApiResponse.Success(likeCount);
	}
}
