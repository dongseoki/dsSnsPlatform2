package com.dssns.board.habit.entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dssns.board.BoardApplication;
import com.dssns.common.entity.YesOrNo;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = BoardApplication.class)
@Transactional
class HabitEntityTest {

  @Autowired
  private EntityManager entityManager;

  @Test
  void habitEntityCreationTest() {
    // given
    Habit habit = Habit.builder()
        .userId(1L)
        .title("허리 스트레칭")
        .description("매일 허리 스트레칭하기")
        .goalType(GoalType.COUNT)
        .goalValue(10)
        .startDate(LocalDate.now())
        .repeatsType(RepeatsType.DAY)
        .repeatEveryValue(1)
        .isActive(true)
        .delYn(YesOrNo.N)
        .build();

    // when
    entityManager.persist(habit);
    entityManager.flush();
    entityManager.clear();

    Habit found = entityManager.find(Habit.class, habit.getId());

    // then
    assertNotNull(found);
    assertNotNull(found.getId());
    assertTrue(found.getTitle().equals("허리 스트레칭"));
    assertTrue(found.getGoalType() == GoalType.COUNT);
    assertTrue(found.getGoalValue() == 10);
    assertTrue(found.getRepeatsType() == RepeatsType.DAY);
    System.out.println("Habit 엔티티가 정상적으로 생성되었습니다. ID: " + found.getId());
  }

  @Test
  void habitLogEntityCreationTest() {
    // given
    Habit habit = Habit.builder()
        .userId(1L)
        .title("허리 스트레칭")
        .goalType(GoalType.COUNT)
        .goalValue(10)
        .startDate(LocalDate.now())
        .repeatsType(RepeatsType.DAY)
        .delYn(YesOrNo.N)
        .build();

    entityManager.persist(habit);
    entityManager.flush();

    HabitLog habitLog = HabitLog.builder()
        .habit(habit)
        .userId(1L)
        .logDate(LocalDate.now())
        .value(5)
        .note("오늘 5회 수행")
        .delYn(YesOrNo.N)
        .build();

    // when
    entityManager.persist(habitLog);
    entityManager.flush();
    entityManager.clear();

    HabitLog found = entityManager.find(HabitLog.class, habitLog.getId());

    // then
    assertNotNull(found);
    assertNotNull(found.getId());
    assertTrue(found.getValue() == 5);
    assertTrue(found.getNote().equals("오늘 5회 수행"));
    assertNotNull(found.getHabit());
    System.out.println("HabitLog 엔티티가 정상적으로 생성되었습니다. ID: " + found.getId());
  }

  @Test
  void habitAndHabitLogRelationshipTest() {
    // given
    Habit habit = Habit.builder()
        .userId(1L)
        .title("매일 운동")
        .goalType(GoalType.DURATION)
        .goalValue(30)
        .startDate(LocalDate.now())
        .repeatsType(RepeatsType.DAY)
        .delYn(YesOrNo.N)
        .build();

    entityManager.persist(habit);
    entityManager.flush();

    HabitLog habitLog1 = HabitLog.builder()
        .habit(habit)
        .userId(1L)
        .logDate(LocalDate.now())
        .value(30)
        .delYn(YesOrNo.N)
        .build();

    HabitLog habitLog2 = HabitLog.builder()
        .habit(habit)
        .userId(1L)
        .logDate(LocalDate.now().minusDays(1))
        .value(25)
        .delYn(YesOrNo.N)
        .build();

    // when
    entityManager.persist(habitLog1);
    entityManager.persist(habitLog2);
    entityManager.flush();
    entityManager.clear();

    Habit foundHabit = entityManager.find(Habit.class, habit.getId());

    // then
    assertNotNull(foundHabit);
    assertNotNull(foundHabit.getHabitLogs());
    assertTrue(foundHabit.getHabitLogs().size() == 2);
    System.out.println("Habit과 HabitLog의 연관관계가 정상적으로 설정되었습니다. HabitLog 개수: " + foundHabit.getHabitLogs().size());
  }
}

