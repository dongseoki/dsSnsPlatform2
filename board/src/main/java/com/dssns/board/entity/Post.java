package com.dssns.board.entity;

import com.dssns.common.entity.BaseEntity;
import com.dssns.board.webdto.AddPostRequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.ObjectUtils;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name = "post_no")
	private Long id;

	private String title;

	private String content;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private PostType postType;

	@Builder.Default
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();

	@Builder.Default
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PostLike> postLikes = new ArrayList<>();

	@Builder.Default
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PostTag> postTags = new ArrayList<>();

	public static Post of(AddPostRequestDto addPostRequestDto) {
		Post post = Post.builder()
			.title(addPostRequestDto.getTitle())
			.content(addPostRequestDto.getPostContent())
			.postType(PostType.valueOf(addPostRequestDto.getPostType()))
			.comments(new ArrayList<>())
			.postLikes(new ArrayList<>())
			.postTags(new ArrayList<>())
			.build();

		// FIXME superbuilder 사용으로 변경.
		post.setCreatedBy(addPostRequestDto.getCreateUserNo());
		return post;
	}

	public static Post of(Post post) throws CloneNotSupportedException {
		return (Post)post.clone();
	}

	// 연관관계 편의 메서드.
	public void addComment(Comment comment) {
		if (!ObjectUtils.isEmpty(comment)) {
			this.comments.add(comment);
			comment.setPost(this);
		}
	}

	public void addPostLike(PostLike postLike) {
		if (!ObjectUtils.isEmpty(postLike)) {
			this.postLikes.add(postLike);
			postLike.setPost(this);
		}
	}

	public void addPostTag(PostTag postTag) {
		if (!ObjectUtils.isEmpty(postTag)) {
			this.postTags.add(postTag);
			postTag.setPost(this);
		}
	}
}
