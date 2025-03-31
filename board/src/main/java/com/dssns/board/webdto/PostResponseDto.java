package com.dssns.board.webdto;

import io.swagger.v3.oas.annotations.media.Schema;
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


//	public static PostResponseDto of(Post post, User user, Alcohol alcohol, List<Tag> tags,
//		List<Comment> comments, List<AttachDto> postAttachDtos,
//		List<AttachDto> profileAttachDtos
//		, Boolean isLikedByMe, Boolean isFollowedByMe, Long likeCount, Long quoteCount) {
//		PostResponseDtoBuilder postResponseDtoBuilder = PostResponseDto.builder();
//		if (user != null) {
//			postResponseDtoBuilder.nickname(user.getNickname()).id(user.getUserId());
//		}
//		if (alcohol != null) {
//			postResponseDtoBuilder.alcoholNo(alcohol.getId())
//				.alcoholName(alcohol.getName())
//				.alcoholType(alcohol.getAlcoholType().getName());
//		}
//		if (CollectionUtils.isNotEmpty(tags)) {
//			List<String> tagList = tags.stream().map(Tag::getName).toList();
//			postResponseDtoBuilder.tagList(tagList);
//		}
//		// TODO quoteInfo는 나중에.
//		if (ObjectUtils.isNotEmpty(postAttachDtos)) {
//			postResponseDtoBuilder.postAttachUrls(postAttachDtos);
//		}
//		if (ObjectUtils.isNotEmpty(profileAttachDtos)) {
//			postResponseDtoBuilder.profileImgUrls(profileAttachDtos);
//		}
//		if (ObjectUtils.isNotEmpty(isFollowedByMe)) {
//			postResponseDtoBuilder.isFollowedByMe(isFollowedByMe);
//		}
//		if (ObjectUtils.isNotEmpty(isLikedByMe)) {
//			postResponseDtoBuilder.isLikedByMe(isLikedByMe);
//		}
//		if (ObjectUtils.isNotEmpty(likeCount)) {
//			postResponseDtoBuilder.likeCount(likeCount);
//		}
//		if (ObjectUtils.isNotEmpty(quoteCount)) {
//			postResponseDtoBuilder.quoteCount(quoteCount);
//		}
//
//		// END
//		return postResponseDtoBuilder
//			.createdBy(post.getCreatedBy())
//			.lastModifiedDate(post.getLastModifiedDate())
//			.edited(
//				!post.getCreatedDate().isEqual(post.getLastModifiedDate()))
//			.postNo(post.getId())
//			.postContent(post.getContent())
//			.commentCount((long)comments.size())
//			.positionInfo(post.getPosition()).build();
//
//	}
//
//	public static PostResponseDto of(Post post, GetPostsToOneMvo getPostsToOneMvo,
//		List<AttachDto> postAttachDtos, List<AttachDto> userAttachDtos) {
//		List<PostTag> postTags = post.getPostTags().stream()
//			.filter(postTag -> YesOrNo.N == postTag.getDelYn())
//			.collect(Collectors.toList());
//		List<String> tagList = postTags.stream()
//			.filter(postTag -> YesOrNo.N == postTag.getTag().getDelYn())
//			.map(postTag -> postTag.getTag().getName()).toList();
//		PostResponseDto postResponseDto =
//			PostResponseDto.builder().nickname(getPostsToOneMvo.getNickname())
//				.id(getPostsToOneMvo.getUserId())
//				.createdBy(post.getCreatedBy())
//				.isFollowedByMe(getPostsToOneMvo.getIsFollowedByMe())
//				.isLikedByMe(getPostsToOneMvo.getIsLikedByMe())
//				.lastModifiedDate(post.getLastModifiedDate())
//				.edited(!post.getCreatedDate()
//					.isEqual(post.getLastModifiedDate()))
//				.postNo(post.getId())
//				.postContent(post.getContent())
//				.commentCount((long)post.getComments().stream().filter(
//						comment -> YesOrNo.N == comment.getDelYn()).toList()
//					.size())
//				.positionInfo(post.getPosition())
//				.alcoholNo(getPostsToOneMvo.getAlcoholNo())
//				.alcoholType(getPostsToOneMvo.getAlcoholType())
//				.alcoholName(getPostsToOneMvo.getAlcoholName())
//				.postAttachUrls(postAttachDtos)
//				.profileImgUrls(userAttachDtos)
//				.tagList(tagList)
//				.likeCount(
//					(long)post.getPostLikes().stream().filter(
//							postLike -> YesOrNo.N == postLike.getDelYn())
//						.collect(
//							Collectors.toList()).size())
//				.quoteCount((long)post.getPostQuoteEds().stream()
//					.filter(
//						postQuoted -> YesOrNo.N == postQuoted.getDelYn())
//					.collect(
//						Collectors.toList()).size())
//				.build();
//		return postResponseDto;
//	}
}
