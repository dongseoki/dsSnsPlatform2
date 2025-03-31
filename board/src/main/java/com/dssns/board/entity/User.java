package com.dssns.board.entity;

import com.dssns.common.entity.BaseEntity;
import com.dssns.common.entity.Role;
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
import lombok.ToString;
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
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name = "user_no")
	private Long id;
	private String email;
	private String password;
	private String userId;
	private String nickname;

	private String introduction;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;

	@Builder.Default
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PostLike> postLikeList = new ArrayList<>();

	public User(Long id, String email, String password, String userId, String nickname,
		String introduction, Role role, List<PostLike> postLikeList) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.userId = userId;
		this.nickname = nickname;
		this.introduction = introduction;
		this.role = role;
		this.postLikeList = postLikeList;
	}

	public static User of(Long userNo) {
		return User.builder().id(userNo).build();
	}

	// bi-directional convenience method

	private void addPostLike(PostLike postLike) {
		if (!ObjectUtils.isEmpty(postLike)) {
			this.postLikeList.add(postLike);
			postLike.setUser(this);
		}
	}

}
