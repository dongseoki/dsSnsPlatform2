-- V1__init_habit_subscription_payment.sql
-- MySQL 8.x / InnoDB / utf8mb4
-- 모든 DATETIME은 UTC로 저장/해석한다고 가정

-- 안전 설정
-- SET NAMES utf8mb4;
-- SET time_zone = '+00:00';

-- -- id 자동증가 기본 옵션 (필요시)
-- SET SESSION sql_require_primary_key=0;

-- 1) user
CREATE TABLE IF NOT EXISTS `user` (
  `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `email`         VARCHAR(255) NOT NULL,
  `password_hash` VARCHAR(255) NULL,
  `name`          VARCHAR(100) NULL,
  `phone`         VARCHAR(30)  NULL,
  `created_at`    DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `updated_at`    DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),

  CONSTRAINT `pk_user` PRIMARY KEY (`id`),
--   CONSTRAINT `uk_user_email` UNIQUE (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 2) habit
CREATE TABLE IF NOT EXISTS `habit` (
  `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id`       BIGINT UNSIGNED NOT NULL,
  `title`         VARCHAR(120) NOT NULL,                       -- 예: "허리 스트레칭"
  `description`   VARCHAR(500) NULL,
  `goal_type`     ENUM('COUNT','DURATION') NOT NULL,           -- 횟수/시간
  `goal_value`    INT UNSIGNED NOT NULL DEFAULT 1,             -- 목표값 (COUNT = 회수, DURATION = 분 단위 등)
  `unit`          VARCHAR(32) NOT NULL DEFAULT 'times',        -- 표시 단위: times, minutes 등
  `is_active`     TINYINT(1) NOT NULL DEFAULT 1,
  `deleted_at`    DATETIME(3) NULL,
  `created_at`    DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `updated_at`    DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),

  CONSTRAINT `pk_habit` PRIMARY KEY (`id`),
  CONSTRAINT `fk_habit_user` FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE,
  CONSTRAINT `ck_habit_goal_value_nonzero` CHECK (`goal_value` > 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 사용자 내 동일 제목 중복 방지(소프트 삭제 제외 원하면 부분 수정)
CREATE UNIQUE INDEX `uk_habit_user_title_active`
  ON `habit` (`user_id`, `title`, `is_active`)
  WHERE `deleted_at` IS NULL;

CREATE INDEX `ix_habit_user` ON `habit` (`user_id`);

-- 3) habit_log
CREATE TABLE IF NOT EXISTS `habit_log` (
  `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `habit_id`    BIGINT UNSIGNED NOT NULL,
  `user_id`     BIGINT UNSIGNED NOT NULL,                      -- 조인 최적화용 중복 컬럼
  `log_date`    DATE NOT NULL,                                 -- 기록 날짜 (로컬이 아닌 UTC 날짜 기준 권장)
  `value`       INT UNSIGNED NOT NULL,                         -- 기록값 (COUNT: 횟수, DURATION: 분 등)
  `note`        VARCHAR(500) NULL,
  `created_at`  DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),

  CONSTRAINT `pk_habit_log` PRIMARY KEY (`id`),
  CONSTRAINT `fk_habit_log_habit` FOREIGN KEY (`habit_id`) REFERENCES `habit`(`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_habit_log_user` FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE,
  CONSTRAINT `ck_habit_log_value_positive` CHECK (`value` > 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 조회/통계 최적화 인덱스
CREATE INDEX `ix_habit_log_habit_date` ON `habit_log` (`habit_id`, `log_date`);
CREATE INDEX `ix_habit_log_user_date`  ON `habit_log` (`user_id`, `log_date`);

-- (옵션) 동일 habit/날짜/메모 중복을 막고 싶다면 아래 유니크 사용
-- CREATE UNIQUE INDEX `uk_habit_log_one_per_day`
--   ON `habit_log` (`habit_id`, `log_date`);
