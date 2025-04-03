package com.dssns.alarm.repository;

import com.dssns.alarm.entity.Notification;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

  List<Notification> findAllByReceiverUserIdAndIsReadIsFalse(@NotNull Long userId);
}