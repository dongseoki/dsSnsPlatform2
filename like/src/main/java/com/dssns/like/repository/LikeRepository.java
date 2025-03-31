package com.dssns.like.repository;

import com.dssns.common.entity.YesOrNo;
import com.dssns.like.entity.Like;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository  extends JpaRepository<Like, Long> {
  Optional<Like> findByRefTblAndRefIdAndCreatedByAndDelYnIs(String refTbl, Long refId, Long createdBy, YesOrNo delYn);

  int countByRefTblAndRefIdAndDelYn(String refTbl, Long refId, YesOrNo delYn);
}
