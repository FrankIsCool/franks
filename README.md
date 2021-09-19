## franks 工具类

## 工具类结构

| 包名 | 功能 |
| --- | --- |
|<a href="#context">context</a>|上下文工具类|
|<a href="#date">date</a>|日期工具类|
|<a href="#empty">empty</a>|检验工具类|
|<a href="#exception">exception</a>|异常|
|<a href="#id">id</a>|唯一id生成器|
|<a href="#ip">ip</a>|ip工具类|
|<a href="#lock">lock</a>|分布式锁|
|<a href="#model">model</a>|常用实体类|
|<a href="#redis">redis</a>|redis工具类|
|<a href="#redisson">redisson</a>|延迟队列|
|<a href="#scheduler">scheduler</a>|动态定时任务|
|<a href="#sign">sign</a>|加密|
|<a href="#str">str</a>|字符串工具类|

***

## <a id="context">context-上下文工具类</a>

| 类名 | 功能 |
| --- | --- |
|SpringContextUtil|获取对象|

***

## <a id="date">date-日期工具类</a>

| 类名 | 功能 |
| --- | --- |
|DateProperties|日期常量|
|DateUtil|Date日期工具类，包含获取当前时间，时间格式转换，增加时间，获取年月日时分秒|
|InstantDateUtil|Instant日期工具类，包含获取当前时间，时间格式转换，增加时间，获取年月日时分秒|
|LongDateUtil|Long日期工具类，包含获取当前时间，时间格式转换，增加时间，获取年月日时分秒|
|StrDateUtil|String日期工具类，包含获取当前时间，时间格式转换，增加时间，获取年月日时分秒|
|TimestampDateUtil|Timestamp日期工具类，包含获取当前时间，时间格式转换，增加时间，获取年月日时分秒|

***

## <a id="empty">empty-检验工具类</a>

| 类名 | 功能 |
| --- | --- |
|EmptyUtil|空校验|
|ValidUtils|数据格式校验，手机号，身份证，邮箱，汉字，ip等等|

***

## <a id="exception">exception-异常</a>

| 类名 | 功能 |
| --- | --- |
|ApiException|自定义异常|
|GlobalExceptionHandler|异常拦截，统一返回ApiResponse|

***

## <a id="id">id-唯一id生成器</a>

| 类名 | 功能 |
| --- | --- |
|IdGen|雪花算法的基础生成（不对外）|
|SequenceUtil|唯一id工具类（对外）|
|IdRedisUtils|利用Redis生成唯一id|

***

## <a id="ip">ip-ip工具类</a>

| 类名 | 功能 |
| --- | --- |
|IpUtil|获取ip|

***

## <a id="lock">lock-分布式锁</a>

| 类名 | 功能 |
| --- | --- |
|DistributedLocks|分布式锁注解|
|DistributedLocksAspect|分布式锁拦截|

### 使用示例

```java

//key传方法名，只能同时处理一条，传其他动态参数，只有满足key冲突的才会拦截
@DistributedLocks(key = "importSchool")
public void importSchool(MultipartFile excelFile){
        //业务处理
        }
```

***

## <a id="model">model-常用实体类</a>

| 类名 | 功能 |
| --- | --- |
|ApiResponse|统一返回实体类|
|PageVo|分页实体类|

***

## <a id="redis">redis-redis工具类</a>

| 类名 | 功能 |
| --- | --- |
|RedisConfig|redis配置|
|RedisUtils|redis基础工具|

***

## <a id="redisson">redisson-延迟队列</a>

| 类名 | 功能 |
| --- | --- |
|RedisDelayedQueue|新增延迟队列|
|RedisDelayedQueueInit|监听延迟队列初始化|
|RedisDelayedQueueListener|监听延迟队列统一接口|
|RedissonConfig|Redisson配置|

### 使用示例

```java

@Component
public class PayQCordListener implements RedisDelayedQueueListener<PayStateReqVO> {

    private final Logger logger = LoggerFactory.getLogger(PayQCordListener.class);

    @Override
    public void invoke(PayStateReqVO payStateReqVO) {
        logger.info("支付二维码-延迟失效,内容:{}", payStateReqVO);
        //处理业务
        logger.info("支付二维码-延迟失效,内容:{},处理结果:{}", payStateReqVO, respDTO);
    }
}
```

***

## <a id="scheduler">scheduler-动态定时任务</a>

| 类名 | 功能 |
| --- | --- |
|DynamicScheduledTask|新增动态定时任务|
|SchedulerBaseVo|创建动态定时任务基础实体|
|SchedulerConfig|动态定时任务初始化|

### 使用示例

```java

@Component
public class PayQCordJob implements Job {

    private final Logger logger = LoggerFactory.getLogger(PayQCordJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        PayStateReqVO payStateReqVO = (PayStateReqVO) context.getJobDetail().getJobDataMap().get(PayStateReqVO.class.getName());
        logger.info("支付二维码-定时失效,内容:{}", payStateReqVO);
        //处理业务，特别注意，此处需要通过SpringContextUtil获取对象， @Autowired注解无用       
        //比如：SpringContextUtil.getBean(PayService.class);        
        logger.info("支付二维码-定时失效,内容:{},处理结果:{}", payStateReqVO, respDTO);
    }
}
```

***

## <a id="sign">sign-加密</a>

| 类名 | 功能 |
| --- | --- |
|Base64|Base64工具类|
|MD5|MD5加密解密工具类|
|RSA|RSA加密解密工具类|
|RSA2|RSA2加密解密工具类|
|SHA1|SHA1加密解密工具类|
|SHA256|SHA256加密解密工具类|
|SignUtils|同一封装加密工具类|

***

## <a id="str">str-字符串工具类</a>

| 类名 | 功能 |
| --- | --- |
|StringUtils|字符串工具类|