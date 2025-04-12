```shell
GET logstash-user_wish-2025.04.09/_mapping


GET logstash-user_access-2025.04.09/_search
{
  "size": 0,
  "query": {
    "bool": {
      "filter": [
        {
          "term": {
            "eventType.keyword": "access"
          }
        }
      ]
    }
  },
  "aggs": {
    "endpoint_counts": {
      "terms": {
        "field": "endpoint.keyword",
        "size": 100
      }
    }
  }
}



GET logstash-user_wish-2025.04.09/_mapping


GET logstash-user_wish-2025.04.09/_search
{
  "size": 0,
  "query": {
    "bool": {
      "must": [
        {
          "term": {
            "eventType.keyword": "like"
          }
        },
        {
          "range": {
            "timestamp": {
              "gte": "2025-04-07T20:00:00+09:00",
              "lt": "2025-04-08T00:00:00+09:00"
            }
          }
        }
      ]
    }
  },
  "aggs": {
    "likes_per_hour": {
      "date_histogram": {
        "field": "timestamp",
        "fixed_interval": "1h",
        "format": "yyyy-MM-dd HH:mm",
        "time_zone": "Asia/Seoul",
        "min_doc_count": 0
      }
    }
  }
}


```

# 대쉬보드를 만들기위한 gpt 대화 링크

## endpoint 별 API 호출 비율을 파이차트로 보여주기.

* https://chatgpt.com/share/67f67391-6bb4-8011-96d3-b5b0c7f9e4b5

## 시간대별 좋아요 횟수 통계 라인 차트

* https://chatgpt.com/share/67f67375-6004-8011-a928-eb1de7e31714