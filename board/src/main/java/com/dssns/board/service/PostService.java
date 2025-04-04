package com.dssns.board.service;

import com.dssns.common.event.NotificationEvent;
import com.dssns.common.event.NotificationEventProducer;
import com.dssns.common.event.enums.EventSourceType;
import com.dssns.common.event.enums.EventType;
import com.dssns.common.exception.ServiceException;
import com.dssns.common.exception.ServiceExceptionCode;
import com.dssns.board.entity.Comment;
import com.dssns.board.entity.Post;
import com.dssns.board.entity.PostType;
import com.dssns.common.entity.YesOrNo;
import com.dssns.board.repository.CommentRepository;
import com.dssns.board.repository.PostRepository;
import com.dssns.board.webdto.AddCommentRequestDto;
import com.dssns.board.webdto.AddCommentResponseDto;
import com.dssns.board.webdto.AddPostResponseDto;
import com.dssns.board.webdto.CommentResponseDto;
import com.dssns.board.webdto.EditCommentRequestDto;
import com.dssns.board.webdto.EditPostRequestDto;
import com.dssns.board.webdto.GetPostCommentsResponseDto;
import com.dssns.board.webdto.PostResponseDto;
import jakarta.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
  private final NotificationEventProducer notificationEventProducer;
  private final PostRepository postRepository;
  private final CommentRepository commentRepository;

  @Transactional
  public AddPostResponseDto addPost(Post post) {
    Post savedPost = postRepository.save(post);
    return new AddPostResponseDto(savedPost.getId());
  }

  @Transactional
  public void editPost(Long postNo, @Valid EditPostRequestDto editPostRequestDto) {
    Optional<Post> optionalPost = postRepository.findByIdAndDelYnIs(postNo, YesOrNo.N);
    if (optionalPost.isPresent()) {
      Post post = optionalPost.get();
      if (editPostRequestDto.getTitle() != null) {
        post.setTitle(editPostRequestDto.getTitle());
      }
      if (editPostRequestDto.getPostContent() != null) {
        post.setContent(editPostRequestDto.getPostContent());
      }
      if (editPostRequestDto.getPostType() != null){
        post.setPostType(PostType.valueOf(editPostRequestDto.getPostType()));
      }
      // Add other fields as necessary
      postRepository.save(post);
    } else {
      log.error("Post with id {} not found", postNo);
      throw new ServiceException(ServiceExceptionCode.POST_NOT_FOUND);
    }
  }

  @Transactional
  public void deletePost(Long postNo) {
    Optional<Post> optionalPost = postRepository.findByIdAndDelYnIs(postNo, YesOrNo.N);
    if (optionalPost.isPresent()) {
      Post post = optionalPost.get();
      post.setDelYn(YesOrNo.Y); // Assuming there is a 'deleted' field in the Post entity
      postRepository.save(post);
    } else {
      log.error("Post with id {} not found", postNo);
      throw new ServiceException(ServiceExceptionCode.POST_NOT_FOUND);
    }
  }

//  public Object getPostsV2(Pageable pageable, String searchKeyword, List<Long> searchUserNoList, Boolean isLikedByMe, Boolean isCommentedByMe) {
//  }

  @Transactional(readOnly = true)
  public PostResponseDto getPostDetail(Long postNo) {
    Optional<Post> optionalPost = postRepository.findByIdAndDelYnIs(postNo, YesOrNo.N);
    if (optionalPost.isPresent()) {
      Post post = optionalPost.get();
      return PostResponseDto.builder().postNo(post.getId())
          .postTitle(post.getTitle())
          .postContent(post.getContent())
          .createdDate(post.getCreatedDate())
          .build();
    } else {
      log.error("Post with id {} not found", postNo);
      throw new ServiceException(ServiceExceptionCode.POST_NOT_FOUND);
    }
  }

  @Transactional
  public AddCommentResponseDto addComment(Long postNo, AddCommentRequestDto addCommentRequestDto) {
    Optional<Post> optionalPost = postRepository.findByIdAndDelYnIs(postNo, YesOrNo.N);
    if (optionalPost.isPresent()) {
      Post post = optionalPost.get();
      // Assuming Comment entity and CommentRepository are properly defined
      Comment comment = Comment.builder()
          .post(post)
          .content(addCommentRequestDto.getCommentContent())
          .build();
      Comment savedComment = commentRepository.save(comment);

      NotificationEvent notificationEvent = NotificationEvent.builder()
          .eventType(EventType.COMMENT)
          .receiverUserId(post.getCreatedBy())
          .eventUserId(addCommentRequestDto.getCreateUserNo())
          .eventSourceId(postNo)
          .eventSourceType(EventSourceType.POST)
          .message(String.format("사용자 번호 %d님이 당신의 게시글에 댓글을 남겼습니다.", addCommentRequestDto.getCreateUserNo()))
          .createdAt(Instant.now())
          .build();

      notificationEventProducer.publishNotificationEventCreated(notificationEvent);
      return new AddCommentResponseDto(savedComment.getId());
    } else {
      log.error("Post with id {} not found", postNo);
      throw new ServiceException(ServiceExceptionCode.POST_NOT_FOUND);
    }
  }

  public GetPostCommentsResponseDto getPostComments(Long postNo) {
    Optional<Post> optionalPost = postRepository.findByIdAndDelYnIs(postNo, YesOrNo.N);
    if (optionalPost.isPresent()) {
      Post post = optionalPost.get();
      List<Comment> comments = commentRepository.findByPostAndDelYnIs(post, YesOrNo.N);
      List<CommentResponseDto> commentResponseDtos = comments.stream()
          .map(comment -> CommentResponseDto.builder()
              .commentNo(comment.getId())
              .commentContent(comment.getContent())
              .createdDate(comment.getCreatedDate())
              .lastModifiedDate(comment.getLastModifiedDate())
              .createdBy(comment.getCreatedBy())
              .build())
          .collect(Collectors.toList());
      return new GetPostCommentsResponseDto(commentResponseDtos, (long) commentResponseDtos.size());
    } else {
      log.error("Post with id {} not found", postNo);
      throw new ServiceException(ServiceExceptionCode.POST_NOT_FOUND);
    }
  }

  public void editComment(Long commentNo,EditCommentRequestDto editCommentRequestDto) {
    Optional<Comment> optionalComment = commentRepository.findByIdAndDelYnIs(commentNo, YesOrNo.N);
    if (optionalComment.isPresent()) {
      Comment comment = optionalComment.get();
      if (editCommentRequestDto.getCommentContent() != null) {
        comment.setContent(editCommentRequestDto.getCommentContent());
      }
      // Add other fields as necessary
      commentRepository.save(comment);
    } else {
      log.error("Comment with id {} not found", commentNo);
      throw new ServiceException(ServiceExceptionCode.COMMENT_NOT_FOUND);
    }
  }

  public void deleteComment(Long postNo, Long commentNo) {
    Optional<Comment> optionalComment = commentRepository.findByIdAndDelYnIs(commentNo, YesOrNo.N);
    if (optionalComment.isPresent()) {
      Comment comment = optionalComment.get();
      comment.setDelYn(YesOrNo.Y); // Assuming there is a 'deleted' field in the Comment entity
      commentRepository.save(comment);
    } else {
      log.error("Comment with id {} not found", commentNo);
      throw new ServiceException(ServiceExceptionCode.COMMENT_NOT_FOUND);
    }
  }
}
