package com.dssns.common.config;

import com.dssns.common.event.NotificationEvent;
import com.dssns.common.event.NotificationEventSerializer;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  // Kafka Admin 설정 추가
  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    return new KafkaAdmin(configs);
  }

  // 토픽 자동 생성 설정
  @Bean
  public NewTopic notificationTopic() {
    return TopicBuilder.name("notification-events")  // 토픽 이름 확인
        .partitions(3)
        .replicas(1)
        .configs(Map.of(
            "cleanup.policy", "delete",
            "retention.ms", "604800000" // 7 days
        ))
        .build();
  }

  // Kafka Producer 설정
  @Bean
  public ProducerFactory<String, NotificationEvent> producerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        NotificationEventSerializer.class);
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  // KafkaTemplate 설정
  @Bean
  public KafkaTemplate<String, NotificationEvent> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }
}