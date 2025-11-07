package com.dssns.board.habit.entity;

import com.dssns.common.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
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

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Habit extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(nullable = false, length = 120)
  private String title;

  @Column(length = 500)
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "goal_type", nullable = false)
  private GoalType goalType;

  @Column(name = "goal_value", nullable = false)
  @Builder.Default
  private Integer goalValue = 1;

  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "repeats_type", nullable = false)
  @Builder.Default
  private RepeatsType repeatsType = RepeatsType.DAY;

  @Column(name = "repeat_every_value", nullable = false)
  @Builder.Default
  private Integer repeatEveryValue = 1;

  @Column(name = "repeat_detail_info", length = 1000)
  private String repeatDetailInfo;

  @Column(length = 500)
  private String tags;

  @Column(name = "is_active", nullable = false)
  @Builder.Default
  private Boolean isActive = true;

  @Builder.Default
  @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<HabitLog> habitLogs = new ArrayList<>();
}

