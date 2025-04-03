package com.dssns.alarm.config;

import com.dssns.alarm.event.NotificationEventDeserializer;
import com.dssns.common.event.NotificationEvent;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Value("${spring.kafka.consumer.group-id}")
  private String groupId;


  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, NotificationEvent> kafkaConsumerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, NotificationEvent> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }


  @Bean
  public ConsumerFactory<String, NotificationEvent> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
        bootstrapServers);
    props.put(
        ConsumerConfig.GROUP_ID_CONFIG,
        groupId);
    props.put(
        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class);
    props.put(
        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        NotificationEventDeserializer.class);
    return new DefaultKafkaConsumerFactory<>(props);
  }
}
