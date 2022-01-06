### 旅客行程智能推荐系统

```
项目启动流程：

1、启动mysql，执行sql

2、启动redis

3、启动项目

4、测试两个接口

curl -XGET "http://127.0.0.1:8888/request?startPlace=北京"是否有数据

curl "http://127.0.0.1:8888/recommend?startPlace=宣武门&startPlaceCity=北京&endPlace=上海&startTime=08：00：00"
```