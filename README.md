## springmad 微服务基础架构
* 后端采用 SpringCloud全家桶
* 使用封装jhipster
* 主要功能: 缓存，elk日志,prometheus监控


## 工程结构
``` 
springmad
├── mad-cache-redis -- redis缓存
├── mad-core-aop -- aop服务（日志）
├── mad-core-common -- 常用工具封装包
├── mad-core-config -- 缓存，日志，线程等基本服务配置
├── mad-core-lock -- 分布式锁
├── mad-core-security -- 权限认证系统
├── mad-elasticsearch -- ES搜索引擎
├── mad-kafka -- kafka
├── mad-mybatis -- mybatis
├── mad-redis -- redis
├── mad-test -- 测试配置
├── mad-pom-plugin -- 公用pom配置
├── mad-dependencies -- 公用共用jar
├── mad-mongodb -- mongodb配置
├── mad-shardingsphere -- shardingsphere配置
```
