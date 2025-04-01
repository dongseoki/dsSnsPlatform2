CREATE TABLE notification (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,         -- 알람의 고유 ID
                              receiver_user_id BIGINT NOT NULL,             -- 알람을 받을 사용자 ID (FK: user 테이블)
                              event_type ENUM('LIKE', 'COMMENT', 'FOLLOW') NOT NULL,  -- 이벤트 유형
                              event_source_id BIGINT NOT NULL,              -- 이벤트가 발생한 대상 ID (게시글 ID, 댓글 ID 등)
                              event_user_id BIGINT NOT NULL,                -- 이벤트를 발생시킨 사용자 ID
                              message TEXT NOT NULL,                        -- 알람 메시지 (ex: "누군가 당신의 글을 좋아합니다.")
                              is_read BOOLEAN DEFAULT FALSE,                -- 읽음 여부
                              read_dt DATETIME DEFAULT NULL,                -- 읽은 시간 (NULL이면 미확인 상태)
                              created_at DATETIME DEFAULT CURRENT_TIMESTAMP -- 알람 생성 시간
);