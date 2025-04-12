package com.dssns.board.repository;

import com.dssns.board.entity.Comment;
import com.dssns.board.entity.Post;
import com.dssns.common.entity.YesOrNo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  Optional<Comment> findByIdAndDelYnIs(Long commentNo, YesOrNo delYn);

  List<Comment> findByPostAndDelYnIs(Post post, YesOrNo delYn);
}
