package com.dssns.alarm.entity;

import com.dssns.common.event.enums.EventSourceType;
import com.dssns.common.event.enums.EventType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "notification")
@Getter
@DynamicInsert
@DynamicUpdate
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "receiver_user_id", nullable = false)
  private Long receiverUserId;

  @Enumerated(EnumType.STRING)
  @Column(name = "event_type", nullable = false)
  private EventType eventType;

  @Enumerated(EnumType.STRING)
  @Column(name = "event_source_type", nullable = false)
  private EventSourceType eventSourceType;

  @Column(name = "event_source_id", nullable = false)
  private Long eventSourceId;

  @Column(name = "event_user_id", nullable = false)
  private Long eventUserId;

  @Column(name = "message", nullable = false)
  private String message;

  @Column(name = "is_read", nullable = false)
  private Boolean isRead = false;

  @Column(name = "read_dt")
  private Instant readDt;

  @Column(name = "created_at", nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Instant createdAt = Instant.now();

  public void markAsRead(Instant time) {
    this.isRead = true;
    this.readDt = time;
  }
}
