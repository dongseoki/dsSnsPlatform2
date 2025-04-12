# dsSnsPlatform
## 프로젝트 소개.
- dsSnsPlatform은 소셜 네트워크 서비스 기능을 구현하기 위한 플랫폼 프로젝트입니다.
- Kafka를 활용하여 메시징 시스템을 구축하고, 효율적인 데이터 처리를 지원합니다.
- 사용자는 토픽 생성, 메시지 발행 및 구독을 통해 분산 환경에서 데이터를 주고받을 수 있습니다.
- 본 프로젝트는 Kafka와 같은 분산 메시징 툴 사용법을 학습하고 실제로 적용해보는 데 중점을 둡니다.
- 확장 가능한 설계로 다양한 SNS 기능을 추가적으로 구현할 수 있습니다.

## 프로젝트 구조.
- [README.md] : 프로젝트 개요 및 구조, 실행방법을 포함한 파일입니다.
- [build.gradle] : 의존성 및 작업 관리를 위한 Gradle 빌드 스크립트입니다.
- [settings.gradle] : 멀티 모듈 구성을 위한 Gradle 설정 파일입니다.
- [logs/] : 애플리케이션 로그, user activity 로그 파일을 저장하는 디렉터리입니다.
- [kafka/] : Kafka 관련 스크립트와 설정 파일이 포함된 디렉터리입니다.
- [elk/] : ELK 스택 설정 파일과 Docker 구성 파일이 포함된 디렉터리입니다.
- [monitor/] : 모니터링 하기위한 promethus, grafana 설정 파일과 Docker 구성 파일이 포함된 디렉터리입니다.
- [alarm/] : 알림 기능을 구현한 모듈로, 알림 관련 엔티티 및 서비스 로직이 포함된 디렉터리입니다.  
- [board/] : 게시판 기능을 구현한 모듈로, 게시글 관련 엔티티 및 서비스 로직이 포함된 디렉터리입니다.  
- [common/] : 공통적으로 사용되는 유틸리티, 설정, 엔티티 등이 포함된 디렉터리입니다.  
- [like/] : 좋아요 기능을 구현한 모듈로, 좋아요 관련 엔티티 및 서비스 로직이 포함된 디렉터리입니다.
- [.gitignore] : 특정 파일을 버전 관리에서 제외하기 위한 Git 설정 파일입니다.

## 프로젝트 실행 방법
### 로컬 개발 환경
#### API 서버 실행 (board, like, alarm)
1. [Docker](https://www.docker.com/)와 [Docker Compose](https://docs.docker.com/compose/)를 설치합니다.
2. 프로젝트 루트 디렉토리에서 다음 명령어를 실행하여 Docker Compose로 Kafka와 Zookeeper를 시작합니다.
   ```bash
   docker-compose -f kafka/docker-compose.yml up -d
   ```
3. Kafka와 Zookeeper가 정상적으로 실행되면, 애플리케이션 하위에 있는 docker compose 파일을 실행합니다.
   ```bash
   # ex)
   docker-compose -f board/docker-compose.yml up -d
   ```
4. 애플리케이션을 익숙한 다음의 명령어를 참고하여 실행합니다.
```
# ex)
./gradlew :board:bootRun
```
5. 애플리케이션이 정상적으로 실행되면, 각 애플리케이션 폴더별 README.md에서 API 명세 링크(swagger)를 확인할 수 있습니다.
   ```
   # ex
   http://localhost:8201/swagger-ui/index.html
   ```
   
#### ELK 스택 실행
1. 다음 명령어를 실행하여 ELK 스택을 시작합니다.
```bash
 docker-compose -f elk/docker-compose.yml up -d
 ```

2. ELK 스택이 정상적으로 실행되면, Kibana 대시보드에 접속합니다.
   ```
   http://localhost:5601
   ```
3. elasticsearch에 인덱스가 생성되는지 확인합니다.
   ```
   http://localhost:9200/_cat/indices?v
   ```

#### 모니터링 도구 실행
1. 다음 명령어를 실행하여 Prometheus와 Grafana를 시작합니다.
```bash
 docker-compose -f monitor/docker-compose.yml up -d
 ```

## 설명, faq
- 빠른 개발을 위해 monorepo 방식으로 진행하였습니다.
  - 추후 각 모듈을 독립적으로 버전 관리 할 수 있도록 리팩토링 예정입니다.