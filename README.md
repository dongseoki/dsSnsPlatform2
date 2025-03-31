# dsSnsPlatform

## 카프카 테스트 코드.
### 쉘 A
```sh
/opt/bitnami/kafka/bin/kafka-topics.sh --create --topic test-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

/opt/bitnami/kafka/bin/kafka-console-producer.sh --topic test-topic --bootstrap-server localhost:9092

# 위 명령어를 실행하면 입력 대기 상태가 됩니다. 메시지를 입력하고 Enter를 누르면 해당 메시지가 토픽에 발행됩니다.

```

### 쉘 B
```sh
/opt/bitnami/kafka/bin/kafka-console-consumer.sh --topic test-topic --bootstrap-server localhost:9092 --from-beginning

/opt/bitnami/kafka/bin/kafka-console-consumer.sh --topic notification-events --bootstrap-server localhost:9092 --from-beginning
```