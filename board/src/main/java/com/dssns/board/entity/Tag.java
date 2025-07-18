package com.dssns.board.entity;

import com.dssns.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Tag extends BaseEntity {

  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  @Column(name = "tag_no")
  private Long id;

  private String name;

  @Builder.Default
  @OneToMany(mappedBy = "tag")
  private List<PostTag> postTags = new ArrayList<>();

  public static Tag of(String name) {
    return Tag.builder().name(name).build();
  }

  // 연관관계 편의 메서드.
  public void addPostTag(PostTag postTag) {
    if (!ObjectUtils.isEmpty(postTag)) {
      postTags.add(postTag);
      postTag.setTag(this);
    }
  }

}
