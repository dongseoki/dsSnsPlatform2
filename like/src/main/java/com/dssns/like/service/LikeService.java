package com.dssns.like.service;

import com.dssns.common.entity.YesOrNo;
import com.dssns.common.exception.ServiceException;
import com.dssns.common.exception.ServiceExceptionCode;
import com.dssns.like.entity.Like;
import com.dssns.like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeService {
  private final LikeRepository likeRepository;

  public void likePost(Long userNo, Long postNo) {
    // TODO: Validate userNo and postNo
    log.info("Liking post with postNo: {} by userNo: {}", postNo, userNo);
    Like newLike =
    likeRepository.findByRefTblAndRefIdAndCreatedByAndDelYnIs("post", postNo, userNo, YesOrNo.N)
        .orElse(Like.builder().refTbl("post").refId(postNo).createdBy(userNo).build());

    likeRepository.save(newLike);
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

  public void likeComment(Long userNo, Long commentNo) {
    // TODO: Validate userNo and commentNo
    Like newLike =
        likeRepository.findByRefTblAndRefIdAndCreatedByAndDelYnIs("comment", commentNo, userNo, YesOrNo.N)
            .orElse(Like.builder().refTbl("comment").refId(commentNo).createdBy(userNo).build());
    likeRepository.save(newLike);
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
