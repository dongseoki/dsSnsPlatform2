package com.dssns.like.service;

import com.dssns.common.entity.YesOrNo;
import com.dssns.common.event.NotificationEvent;
import com.dssns.common.event.NotificationEventProducer;
import com.dssns.common.event.enums.EventSourceType;
import com.dssns.common.event.enums.EventType;
import com.dssns.common.exception.ServiceException;
import com.dssns.common.exception.ServiceExceptionCode;
import com.dssns.common.user_activity.UserActivityWish;
import com.dssns.like.entity.Like;
import com.dssns.like.repository.LikeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeService {
  private static final Logger userActivityLogger = LoggerFactory.getLogger("USER_ACTIVITY_WISH_LOGGER");
  private final LikeRepository likeRepository;
  private final NotificationEventProducer notificationEventProducer;
  private final ObjectMapper objectMapper;

  public void likePost(Long userId, Long postId, Long postCreatorUserId) {
    // TODO: Validate userNo and postNo
    log.info("Liking post with postNo: {} by userNo: {}", postId, userId);
    Like newLike =
    likeRepository.findByRefTblAndRefIdAndCreatedByAndDelYnIs("post", postId, userId, YesOrNo.N)
        .orElse(Like.builder().refTbl("post").refId(postId).createdBy(userId).build());


      likeRepository.save(newLike);
    try{
      NotificationEvent notificationEvent = NotificationEvent.builder()
          .eventType(EventType.LIKE)
          // FIXME  receiverUserId를 적절히 수정해야한다. Api를 통해서 받아온 postNo를 사용하도록 추후 조치 필요.
          .receiverUserId(postCreatorUserId)
          .eventUserId(userId)
          .eventSourceId(postId)
          .eventSourceType(EventSourceType.POST)
          .message(String.format("User %d liked your post %d", userId, postId))
          .createdAt(Instant.now())
          .build();

      notificationEventProducer.publishNotificationEventCreated(notificationEvent);
    } catch (Exception e) {
      log.error("Error while public event: {}", e.getMessage());
    }

    try{
      UserActivityWish userActivityWish = UserActivityWish.builder()
          .eventType("like")
          .targetType("post")
          .targetId(String.valueOf(postId))
          .timestamp(Instant.now().toString())
          .userId(String.valueOf(userId)).build();
      userActivityLogger.info(objectMapper.writeValueAsString(userActivityWish));
    }
    catch (Exception e){
      log.error("Error while create user activity log: {}", e.getMessage());
    }

  }

  public void likeCancelPost(Long userNo, Long postNo) {
    // TODO: Validate userNo and postNo
    log.info("Cancelling like for post with postNo: {} by userNo: {}", postNo, userNo);
    // Implement the logic to cancel like on a post
    likeRepository.findByRefTblAndRefIdAndCreatedByAndDelYnIs("post", postNo, userNo, YesOrNo.N)
        .ifPresentOrElse(like -> {
          like.setDelYn(YesOrNo.Y);
          likeRepository.save(like);
        }, () -> {
          log.error("Like not found for postNo: {} by userNo: {}", postNo, userNo);
          throw new ServiceException(ServiceExceptionCode.LIKE_NOT_FOUND);
        });
  }

  public int countLikesForPost(Long postNo) {
    return likeRepository.countByRefTblAndRefIdAndDelYn("post", postNo, YesOrNo.N);
  }

  public void likeComment(Long userNo, Long commentNo, Long commentCreatorUserId) {
    // TODO: Validate userNo and commentNo
    Like newLike =
        likeRepository.findByRefTblAndRefIdAndCreatedByAndDelYnIs("comment", commentNo, userNo, YesOrNo.N)
            .orElse(Like.builder().refTbl("comment").refId(commentNo).createdBy(userNo).build());
    likeRepository.save(newLike);

    NotificationEvent notificationEvent = NotificationEvent.builder()
        .eventType(EventType.LIKE)
        // FIXME receiverUserId를 적절히 수정해야한다. Api를 통해서 받아온 comment 의 createdBy를 사용하도록 추후 조치 필요.
        .receiverUserId(commentCreatorUserId) // Assuming commentNo is the ID of the user who created the comment
        .eventUserId(userNo)
        .eventSourceId(commentNo)
        .eventSourceType(EventSourceType.COMMENT)
        .message(String.format("User %d liked your comment %d", userNo, commentNo))
        .createdAt(Instant.now())
        .build();

    notificationEventProducer.publishNotificationEventCreated(notificationEvent);
  }

  public void likeCancelComment(Long userNo, Long commentNo) {
    // TODO: Validate userNo and commentNo
    log.info("Cancelling like for comment with commentNo: {} by userNo: {}", commentNo, userNo);
    likeRepository.findByRefTblAndRefIdAndCreatedByAndDelYnIs("comment", commentNo, userNo, YesOrNo.N)
            .ifPresentOrElse(like -> {
              like.setDelYn(YesOrNo.Y);
              likeRepository.save(like);
            }, () -> {
              log.error("Like not found for commentNo: {} by userNo: {}", commentNo, userNo);
              throw new ServiceException(ServiceExceptionCode.LIKE_NOT_FOUND);
            });
  }

  public int countLikesForComment(Long commentNo) {
    return likeRepository.countByRefTblAndRefIdAndDelYn("comment", commentNo, YesOrNo.N);
  }
}
