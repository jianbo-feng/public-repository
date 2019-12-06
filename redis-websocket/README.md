# redis-websocket
## 简介
```
采用springboot集成redis、websocket，采用redis的订阅功能，进行推送消息;
推送指令包括：
{"service":"statistics_events","username":"fengjianbo"}

{"service":"statistics_user"}

// 全局推送
{"service":"broadcast","content":"你好，欢迎大家"}

// 点对点
{"service":"peertopeer","content":"你好，欢迎大家", "from":"fromUser", "to":"toUser"}
```
