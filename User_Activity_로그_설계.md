
## 로그 이벤트의 설계.
### 로그 저장 경로.
* 로그의 저장 경로는 임시적으로
    * 일반 API 로그 /var/log/api/api명
    * 사용자 활동 로그 /var/log/user_activity/

### 로그 이벤트의 설계.
#### 기본 구성요소
```js
let logEventCommon = {
    "event_id" : "550e8400-e29b-41d4-a716-446655440000"
    // event_id는 UUID로 생성합니다. 
    , "timestamp" : "2025-03-03T10:15:30Z"
    // 이벤트 발생 시간 (ISO 8601)
    , "user_id" : "1"
  // 사용자 유저 ID. 현재 프로젝트에서 user_id는 user table의 pk를 의미함을 참조. 추후 유추 불가능한 pk로 작업할 예정.
    , "user_login_id" : "test_user"
  // 사용자 로그인 ID.
    , "jti" : "550e8400-e29b-41d4-a716-446655440000"
  // jwt id 식별자.
   , "event_type" : "click"
  // 이벤트 타입. 클릭, 엑세스, 보기, 좋아요, 알림 등등.
  , "device" : "mobile"
  // 디바이스 정보.
  , "location" : "Seoul, Korea"
  // 사용자 위치 정보.
  , "page_url" : "/product/12345"
  // 이벤트 발생한 url. null일 수 있음.
  
}

```

#### 클릭 이벤트
* 저장 파일명 : user_click_xxxx.log
* xxxx는 yyyyMMdd_{no} 형식으로 저장.
##### 클릭 이벤트 구성 요소.
```js
let logEventClick = {
    "event_type" : "click"
  , "targetType": "post_delete"
  // 무엇을 클릭했지? : 게시글 삭제 버튼, 댓글 완료 버튼 클릭, 좋아요 클릭, 좋아요 취소, 삭제 버튼 등등. // post | comment | like_button
  , "targetId": "67890"
  // 타겟에 대한 유일한 구별 식별자. 
  // 타겟 식별자가 있을 경우 작업.
}
```
##### 계획.
* 클릭 이벤트의 경우 설계는 하지만, 적재하지는 않겠습니다.
* 사유
    * 클릭이벤트는 어떤 버튼을 클릭하였는 지는 항상 API를 호출한다는 보장이 없음.
    * 일단, Access log와 현재로써는 용도가 겹친다고 판단해, 생략할 예정.

#### 노출 이벤트.
* 저장 파일명 : user_expose_xxxx.log
* 사용자가 게시물을 스크롤로 노출시켰을 때 (화면 내 진입)
* xxxx는 yyyyMMdd_{no} 형식으로 저장.
```js 
let logEventExpose = {
  "event_type" : "expose"
  , "targetType": "post"
  // 무엇을 노출했지? 게시글, 등등.
  , "targetId": "67890"
  // 타겟에 대한 유일한 구별 식별자. 
  // 타겟 식별자가 있을 경우 작업.
  , "viewTime": 1000 // 초단위로 노출된 시간.
  , "request_uid" : "550e8400-e29b-41d4-a716-446655440000"
  // api 요청 식별자.
}
```
##### 계획
* 노출이벤트에서 viewTime을 제외한 것만 로깅할 계획.
* 로깅의 대상 : 조회 Api들에서 작업 예정. 게시글 상세조회가 대상.

#### wish 이벤트.
* 저장 파일명 : user_wish_xxxx.log
* 사용자가 게시물을 좋아요(like)했을 때
```js 
let logEventWish = {
  "event_type" : "wish"
  , "targetType": "post"
  // 무엇을 좋아요 눌렀지? post, comment
  , "targetId": "67890"
  // 타겟에 대한 유일한 구별 식별자.
  , "request_uid" : "550e8400-e29b-41d4-a716-446655440000"
  // api 요청 식별자.
}
```
##### 계획
* 일단 좋아요 api 호출시에 처리 예정.

#### 알림 이벤트.
* 저장 파일명 : user_notification_xxxx.log
* 사용자가 알림을 클릭했을 때
```js
let logEventNotification = {
  "event_type" : "fetch_alarms"
  // fetch_alarms, read_alarm
  , "targetType": "notification"
  // notification 고정.
  , "targetId": "67890"
  // 타겟에 대한 유일한 구별 식별자. 읽음일 경우만 처리. fetch의 경우 targetId 없음.
  , "request_uid" : "550e8400-e29b-41d4-a716-446655440000"
  // api 요청 식별자.
}
```
#### access log 접속 로그.
* 저장 파일명 : user_access_xxxx.log
* api 호출 로그로 생각하면 됨.
* 해당 작업은 인터셉터의 afterCompletion에서 작업 할 예정.
```js 

let logEventAccess = {
  "event_type" : "access"
  , "method": "GET"
  , "endpoint": "/posts/123" // api endpoint
  , "queryString": {
      "key": "value"
  } // 요청 쿼리스트링
  , "requestBody": {
      "key": "value"
  } // 요청 body
  , "responseBody": {
      "key": "value"
  } // 응답 body
  , "statusCode": 200 // http 상태코드.
  , "responseTime": 120 // ms 단위.
  , "request_uid" : "550e8400-e29b-41d4-a716-446655440000"
  // api 요청 식별자.
}
```

##### 계획.
* 해당 작업은 인터셉터의 afterCompletion에서 작업 할 예정.
