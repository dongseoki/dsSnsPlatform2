
# 모든 데이터 조회
```shell
GET logstash-user_wish-2025.04.08/_search
{
  "query": {
    "match_all": {}
  }
}
```


# 모든 데이터 조회
```shell
GET logstash-user_wish-2025.04.08/_search
{
  "query": {
    "match_all": {}
  }
}

GET test234/_mapping

PUT _template/logstash-user_wish-template
{
  "index_patterns": ["logstash-user_wish-*"],
  "settings": {
    "number_of_shards": 1
  },
  "mappings": {
    "properties": {
      "eventId": { "type": "keyword" },
      "timestamp": { "type": "date" },
      "userId": { "type": "keyword" },
      "userLoginId": { "type": "keyword" },
      "jti": { "type": "keyword" },
      "eventType": { "type": "keyword" },
      "device": { "type": "keyword" },
      "location": { "type": "keyword" },
      "pageUrl": { "type": "text" },
      "targetType": { "type": "keyword" },
      "targetId": { "type": "keyword" },
      "requestUid": { "type": "keyword" }
    }
  }
}


output {
  elasticsearch {
    hosts => "elasticsearch:9200"
    user => "elastic"
    password => "elastic"
    index => "logstash-user_wish-%{+YYYY.MM.dd}"
    template_name => "logstash-user_wish-template"
    template_overwrite => true
  }
}

PUT _template/logstash-user_access-template
{
  "index_patterns": ["logstash-user_access-*"],
  "settings": {
    "number_of_shards": 1
  },
  "mappings": {
    "properties": {
      "eventId": { "type": "keyword" },
      "timestamp": { "type": "date" },
      "userId": { "type": "keyword" },
      "userLoginId": { "type": "keyword" },
      "jti": { "type": "keyword" },
      "eventType": { "type": "keyword" },
      "device": { "type": "keyword" },
      "location": { "type": "keyword" },
      "pageUrl": { "type": "text" },
      "method": { "type": "keyword" },
      "endpoint": { "type": "keyword" },
      "statusCode": { "type": "integer" },
      "responseTime": { "type": "integer" },
      "requestUid": { "type": "keyword" }
    }
  }
}

GET _cat/templates?v
```

# 남은 작업.
* [ ] es, kibana의 정상작동을 확인했지만, logstash에서 만들어낸  문서들의 필드가 json 데이터에 있는 키값들을 가지고 있지 않는 이슈가 발생
  * gpt 문의 결과는 템플릿을 만들어서 쓰라는데, kibana Dev tools 에서는 json 데이터에 대한 동적 매핑이 잘되는 것을 확인.
  * 따라서 일단 템플릿으로 하나 해보고 잘 되면, 일단 전부 템플릿으로 설정하고 작업 진행할 필요가 있음.
  * 정확한 es 공식 용어 공부 필요.