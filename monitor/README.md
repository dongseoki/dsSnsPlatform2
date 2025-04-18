# 메뉴얼
* 프로메테우스 접속 링크.
  * http://localhost:9090/query
* 그라파나
  * http://localhost:3000/login

# 진행 상태 저장.
* boardAPi 를 대상으로 프로메테우스에 엔드 포인트 노출,
* 이제 그라파나로 시각화 하면 됨.

# 프로메테우스와 그라파나를 이용한 모니터링 계획.

1. 프로메테우스, 그라파나 docker compose 초기 설정 (데이터 영구 보관을 위한 설정 필)
2. like-api 모니터링.
3. like api의 mysql 모니터링.
4. like-api의 redis 모니터링 대쉬보드 완.
5. kafka, zookeeper 모니터링 대쉬보드 완.
6. elasticeasrch, kibana, logstash 모니터링 대쉬보드 완.
7. 캡처 정리 및 REAME 업데이트

# 연동시 참고자료
https://chatgpt.com/share/68025fb5-68fc-8011-9ded-636032f66ad6