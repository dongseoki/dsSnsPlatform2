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
//import org.springframework.security.core.Authentication;
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
//	@UserRequired
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse<AddPostResponseDto> addPost(@RequestBody @Valid AddPostRequestDto addPostRequestDto) {
		log.info(">>> PostController.addPost");
		return ApiResponse.Success(postService.addPost(Post.of(addPostRequestDto)));
	}

	@Operation(summary = "[o]게시글 수정", description = "게시글 수정 API")
//	@UserRequired
	@PatchMapping("/{postNo}")
	public ApiResponse<Void> editPost(@PathVariable("postNo") Long postNo,
		@RequestBody @Valid EditPostRequestDto editPostRequestDto){
//		Authentication authentication) {
		log.info(">>> PostController.editPost");
//		postFacade.editPost(postNo, editPostRequestDto, UserAuthInfo.of(authentication));
		postService.editPost(postNo, editPostRequestDto);
		return ApiResponse.Success();
	}

	@Operation(summary = "[o]게시글 삭제", description = "게시글 삭제 API")
//	@UserRequired
	@DeleteMapping("/{postNo}")
	public ApiResponse<Void> deletePost(@PathVariable("postNo") Long postNo){
//			, Authentication accessAuthentication) {
		log.info(">>> PostController.deletePost");
//		postFacade.deletePost(postNo, UserAuthInfo.of(accessAuthentication));
		postService.deletePost(postNo);
		return ApiResponse.Success();
	}

//	TODO 추후 필요시 요구사항에 맞게 적절히 구현
//	@Operation(summary = "[o]게시글 목록 조회(v2)", description = "게시글 조회 API Page, size 사용법. <br> ex1) /posts/v2?page=0&size=10&sort=id,desc <br> ex2) /posts/v2?page=0&size=10&sort=id,desc&sort=content,asc&searchKeyword=키워드&searchUserNos=1,2,4")
//	@GetMapping("/v2")
//	public Page<PostResponseDto> getPostsV2(
//		//                                        @Schema(description = "페이지 번호와 사이즈.정렬 까지.(0부터) ex)[1]page=0&size=5&sort=id,desc [2]page=1&size=15&sort=id,desc&sort=content,asc", example = "0")
//		Pageable pageable,
//		@RequestParam(required = false, name = "searchKeyword")
//		@Schema(description = "키워드", example = "키워드")
//		String searchKeyword,
//		@RequestParam(required = false, name = "searchUserNos")
//		@Schema(description = "검색 유저 번호들.", example = "1,2,4")
//		String searchUserNos,
//		@RequestParam(required = false, name = "isLikedByMe")
//		@Schema(description = "나에 의해서 좋아하는 게시글 필터 여부(true or false).", example = "false")
//		Boolean isLikedByMe,
//		@RequestParam(required = false, name = "isCommentedByMe")
//		@Schema(description = "내가 댓글을 단 게시글 필터 여부.(true or false)", example = "false")
//		Boolean isCommentedByMe
//	) {
//		log.info(">>> PostController.getPost");
//		List<Long> searchUserNoList = new ArrayList<>();
//		if (StringUtils.hasText(searchUserNos)) {
//			try {
//				searchUserNoList =
//					Arrays.stream(searchUserNos.split(",")).map(Long::parseLong).toList();
//			} catch (NumberFormatException e) {
//				log.error(">>> PostController.getPost searchUserNos 파싱 에러 NumberFormatException", e);
//				throw new IllegalArgumentException("searchUserNos 파싱 에러 NumberFormatException", e);
//			} catch (Exception e) {
//				log.error(">>> PostController.getPost searchUserNos 파싱 에러 Exception", e);
//				throw new IllegalArgumentException("searchUserNos 파싱 에러 Exception", e);
//			}
//		}
//
//		return ApiResponse.Success(
//				postService.getPostsV2(pageable, searchKeyword, searchUserNoList, isLikedByMe,
//				isCommentedByMe));
//	}

	@Operation(summary = "[o]게시글 상세 조회", description = "게시글 상세 조회 API")
	@GetMapping("/{postNo}")
	public ApiResponse<PostResponseDto> getPostDetail(@PathVariable("postNo") Long postNo) {
		log.info(">>> PostController.getPostDetail");
		return ApiResponse.Success(postService.getPostDetail(postNo));
//				, AuthUtil.getUserNoFromAuthentication()));
	}

	@Operation(summary = "[o]게시글 댓글 등록", description = "게시글 댓글 등록 API")
	@PostMapping("/{postNo}/comments")
//	@UserRequired
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
//	@UserRequired
	public ApiResponse<Void> editComment(
		@PathVariable("commentNo") Long commentNo,
		@Valid @RequestBody
		EditCommentRequestDto editCommentRequestDto){
//		Authentication authentication) {
		log.info(">>> PostController.editComment");
		postService.editComment(commentNo, editCommentRequestDto);
//			UserAuthInfo.of(authentication));
		return ApiResponse.Success();
	}

	@Operation(summary = "[o]게시글 댓글 삭제", description = "게시글 댓글 삭제 API")
	@DeleteMapping("/{postNo}/comments/{commentNo}")
//	@UserRequired
	public ApiResponse<Void> deleteComment(@PathVariable("postNo") Long postNo,
		@PathVariable("commentNo") Long commentNo){
//		Authentication authentication) {
		log.info(">>> PostController.deleteComment");
		postService.deleteComment(postNo, commentNo);
//				, UserAuthInfo.of(authentication));
		return ApiResponse.Success();
	}

//	@Operation(summary = "[o]게시글 좋아요", description = "게시글 좋아요 API")
//	@PostMapping("/like/{postNo}")
////	@UserRequired
//	public ApiResponse<Void> likePost(@PathVariable("postNo") Long postNo) {
//		log.info(">>> PostController.likePost");
////		Long userNo = AuthUtil.getUserNoFromAuthentication();
//		postFacade.likePost(userNo, postNo);
//		return ApiResponse.Success();
//	}
//
//	@Operation(summary = "[o]게시글 좋아요 취소", description = "게시글 좋아요 취소 API")
//	@PostMapping("/like-cancel/{postNo}")
////	@UserRequired
//	public ApiResponse<Void> likeCancelPost(@PathVariable("postNo") Long postNo){
////			, Authentication authentication) {
//		log.info(">>> PostController.likeCancelPost");
////		Long userNo = AuthUtil.getUserNoFromAuthentication();
//		postFacade.likeCancelPost(userNo, postNo, UserAuthInfo.of(authentication));
//		return ApiResponse.Success();
//	}

}
