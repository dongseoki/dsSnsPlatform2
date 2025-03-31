package com.dssns.board.repository;

import com.dssns.board.entity.Post;
import com.dssns.common.entity.YesOrNo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
	Optional<Post> findByIdAndDelYnIs(Long postNo, YesOrNo delYn);
}
