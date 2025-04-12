package com.dssns.board.controller;

import com.dssns.common.web.ApiResponse;
import com.dssns.board.entity.Post;
import com.dssns.board.service.PostService;
import com.dssns.board.webdto.AddCommentRequestDto;
import com.dssns.board.webdto.AddCommentResponseDto;
import com.dssns.board.webdto.AddPostRequestDto;
import com.dssns.board.webdto.AddPostResponseDto;
import com.dssns.board.webdto.EditCommentRequestDto;
import com.dssns.board.webdto.EditPostRequestDto;
import com.dssns.board.webdto.GetPostCommentsResponseDto;
import com.dssns.board.webdto.PostResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Tag(name = "게시글", description = "게시글 API")
public class PostController {
	private final PostService postService;

	@Operation(summary = "[-]게시글 등록 ", description = "게시글 등록 API")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse<AddPostResponseDto> addPost(@RequestBody @Valid AddPostRequestDto addPostRequestDto) {
		log.info(">>> PostController.addPost");
		return ApiResponse.Success(postService.addPost(Post.of(addPostRequestDto)));
	}

	@Operation(summary = "[o]게시글 수정", description = "게시글 수정 API")
	@PatchMapping("/{postNo}")
	public ApiResponse<Void> editPost(@PathVariable("postNo") Long postNo,
		@RequestBody @Valid EditPostRequestDto editPostRequestDto){
		log.info(">>> PostController.editPost");
		postService.editPost(postNo, editPostRequestDto);
		return ApiResponse.Success();
	}

	@Operation(summary = "[o]게시글 삭제", description = "게시글 삭제 API")
	@DeleteMapping("/{postNo}")
	public ApiResponse<Void> deletePost(@PathVariable("postNo") Long postNo){
		log.info(">>> PostController.deletePost");
		postService.deletePost(postNo);
		return ApiResponse.Success();
	}

	@Operation(summary = "[o]게시글 상세 조회", description = "게시글 상세 조회 API")
	@GetMapping("/{postNo}")
	public ApiResponse<PostResponseDto> getPostDetail(@PathVariable("postNo") Long postNo) {
		log.info(">>> PostController.getPostDetail");
		return ApiResponse.Success(postService.getPostDetail(postNo));
	}

	@Operation(summary = "[o]게시글 댓글 등록", description = "게시글 댓글 등록 API")
	@PostMapping("/{postNo}/comments")
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse<AddCommentResponseDto> addComment(@PathVariable("postNo") Long postNo, @Valid @RequestBody
	AddCommentRequestDto addCommentRequestDto) {
		log.info(">>> PostController.addComment");
		return ApiResponse.Success(postService.addComment(postNo, addCommentRequestDto));
	}

	@Operation(summary = "[o]게시글 댓글 조회", description = "게시글 댓글 조회 API")
	@GetMapping("/{postNo}/comments")
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse<GetPostCommentsResponseDto> getPostComments(@PathVariable("postNo") Long postNo) {
		log.info(">>> PostController.getPostComments");
		return ApiResponse.Success(postService.getPostComments(postNo));
	}

	@Operation(summary = "[o]게시글 댓글 수정", description = "게시글 댓글 수정 API")
	@PatchMapping("/comments/{commentNo}")
	public ApiResponse<Void> editComment(
		@PathVariable("commentNo") Long commentNo,
		@Valid @RequestBody
		EditCommentRequestDto editCommentRequestDto){
		log.info(">>> PostController.editComment");
		postService.editComment(commentNo, editCommentRequestDto);
		return ApiResponse.Success();
	}

	@Operation(summary = "[o]게시글 댓글 삭제", description = "게시글 댓글 삭제 API")
	@DeleteMapping("/{postNo}/comments/{commentNo}")
	public ApiResponse<Void> deleteComment(@PathVariable("postNo") Long postNo,
		@PathVariable("commentNo") Long commentNo){
		log.info(">>> PostController.deleteComment");
		postService.deleteComment(postNo, commentNo);
		return ApiResponse.Success();
	}

}
