# miaotech-dubbo-demo
学习dubbo搭建的脚手架，基于Alibaba Spring Cloud

代码架构：DDD
 * Controller 
 * Application
 * Domain
 * Infrastructure

组件
* Shiro + Jwt 支持登录验证
* Spring Schedule 定时任务
* Redis 缓存
* Mysql 数据库
* Nacos 配置中心和注册中心
* Sentinel 限流 
* dubbo RPC
* SpringBoot Web层
* Spring Cloud Stream + RocketMQ 异步消息

自定义实现
* Idempotent 幂等
* DLock 分布式锁
* 分布式ID: 基于Snowflake

优化
* 异常统一处理
* 结果统一返回
* Dubbo AccessLog提供调用时间和返回值

包管理
* Gradle

