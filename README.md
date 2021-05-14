# miaotech-dubbo-demo
学习dubbo搭建的脚手架，基于Alibaba Spring Cloud

代码架构：DDD
 * Controller 
 * Application
 * Domain
 * Infrastructure

组件
* Web接入 => SpringBoot
    + Restful
    + 安全认证
      + Shiro + Jwt
* RPC dubbo
    + nacos注册中心
* 消息队列 RocketMQ
    + Spring Cloud Stream
* 任务调度
  + Spring Schedule
* 缓存 Redis
    + Lettuce
  + spring integration
* 数据库 Mysql
  + MyBatis 
  + MyBatis Plus
* 配置中心 Nacos
* 限流 Sentinel


自定义实现组件
* Idempotent 幂等
* DLock 分布式锁
* 分布式ID: 基于Snowflake

通用处理
* 异常统一处理
* 结果统一返回
* Dubbo AccessLog 访问日志，计算调用时间和返回值
* Dozer实现DO、DTO、VO等相互转化
* SpringBoot Validator数据校验

包管理
* Gradle

