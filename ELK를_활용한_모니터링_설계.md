# 과제 내용

이제 다음으로 ### 5. docker-compose로 ELK 스택 구축

<aside>

- docker-compose로 ELK 스택을 명시해주시고, 각 컨테이너를 network로 연결해주세요.
- 각 ELK의 Dockerfile은 직접 생성해도 되고, docker hub image를 사용하셔도 됩니다.
- 3번에 적재된 로그를 logstash input에 정의하여 output을 elasticsearch로 적재하도록 구현해주세요. (logstash.conf)
- Elasticsearch에 인덱스가 생성되는지 확인해주세요.
    - http://localhost:9200/_cat/indices?v
- 적재된 인덱스를 kibana에 연결하시고 적재된 logs를 조회해봅니다.
- 해당 로그 중 필요하다고 생각되는 집계 데이터를 생성하여 kibana 대시보드에 line graph 등 필요한 차트로 가시화 합니다.
</aside>이거 작업을 진행하려고해. 어떤 단계로 작업을 진행하는게 좋을까

# gpt 설명

# 과제 수행 계획.
* 이전에 잘 되던 ELK 테스트.
* docker-compose 설계(es, logstash, kibans 설정)
  * elk 폴더 분리. 하위에 elk dockercompose 명시 및 필요한 파일 및 폴더 명시
  * es 설정
  * kibana 설정
  * logstash 설정.
* 정상 동작의 확인
  * es
  * kibana 
  * logstash
* 대쉬보드 생성 작업.
  * 설계
  * 작업.
* 일반 API 로그도 해당 형태로 적재 시도.
  * 대쉬보드 생성.
  * 확인.
* 일부 잘된거 이미지 캡처
* 필요시 작동 방법 README 정리.

# 참고자료
https://chatgpt.com/share/67f0dea1-c06c-8011-bd45-ba0bbe7df90d