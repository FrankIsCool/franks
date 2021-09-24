## franks 工具类

## 工具类结构

| 包名 | 功能 |
| --- | --- |
|<a href="#amount">amount</a>|金额工具类|
|<a href="#context">context</a>|上下文工具类|
|<a href="#date">date</a>|日期工具类|
|<a href="#empty">empty</a>|检验工具类|
|<a href="#enums">enums</a>|枚举|
|<a href="#exception">exception</a>|异常|
|<a href="#export">export</a>|导出|
|<a href="#id">id</a>|唯一id生成器|
|<a href="#ip">ip</a>|ip工具类|
|<a href="#lock">lock</a>|分布式锁|
|<a href="#model">model</a>|常用实体类|
|<a href="#redis">redis</a>|redis工具类|
|<a href="#redisson">redisson</a>|延迟队列|
|<a href="#encrypt">encrypt</a>|数据加密,解密|
|<a href="#scheduler">scheduler</a>|动态定时任务|
|<a href="#sign">sign</a>|加密|
|<a href="#str">str</a>|字符串工具类|
|<a href="#transition">transition</a>|数据类型转换|

***

## <a id="amount">amount-金额工具类</a>

| 类名 | 功能 |
| --- | --- |
|AmountUtil|转成大写金额|

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

## <a id="enums">enums-枚举</a>

| 类名 | 功能 |
| --- | --- |
|ExpireTimeEnums|时间枚举|

***

## <a id="exception">exception-异常</a>

| 类名 | 功能 |
| --- | --- |
|ApiException|自定义异常|
|GlobalExceptionHandler|异常拦截，统一返回ApiResponse|

***

## <a id="export">export-导出</a>

| 类名 | 功能 |
| --- | --- |
|BaseExcelController|导入导出基础封装类|
|EasyPoiUtils|导出工具类|
|FranksExcelExportStylerImpl|导出样式类|


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

### 使用场景
* 支付二维码，定时失效，比如一个新的支付二维码，自动两个小时后失效。
* 创建订单，30分钟未支付，自动取消订单
以上场景，可以使用延迟的消息队列进行实现。最优解


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

## <a id="encrypt">encrypt-数据加密，解密</a>

* 【EncryptField】、【EncryptFieldUtil】、【IEncryptField】、【IDecryptField】是基本的加密，解密工具，可以针对各种复杂使用场景，也可在此基础上自行封装。
* 【PhoneEncrypt】、【PhoneEncryptUtil】是在基础之上，再次封装，满足常见场景需求。

| 类名 | 功能 |
| --- | --- |
|EncryptField|基础-加密注解|
|EncryptFieldUtil|基础-数据加密，解密|
|IEncryptField|基础-数据加密接口|
|IDecryptField|基础-数据解密接口|
|PhoneEncrypt|简化功能-手机号加密注解|
|PhoneEncryptUtil|简化功能-手机号加密|
|IDCardEncrypt|简化功能-身份证加密注解|
|IDCardEncryptUtil|简化功能-身份证加密|
|EncryptUtil|简化功能-手机号，身份证加密工具类|


### 使用场景
* 用户的手机号，身份证号在持久化时，自行加密
* 在数据库中获取的值是密文（例如用户手机号），自行解密
* 返回前端时，把手机号等敏感信息，自行加密（示例如下）

### 使用示例

```java
//返回结果属性添加注解
......
@EncryptField
private String kindName;
@PhoneEncrypt
private String phone;
@IDCardEncrypt
private String idCard;
......
//返回结果进行拦截处理
@ControllerAdvice(basePackages = { "com.xxx.xxx.controller" })
public class ResponseMessageAdvice implements ResponseBodyAdvice<Object> {
    
    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> converter, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        EncryptFieldAdvice.encryptField(object, EncryptField.class, new IEncryptField() {
            @Override
            public String encrypt(String s) {
                return SignUtils.MD5.createSign(s,"frank","utf-8");
            }
        });
        IDCardEncryptAdvice.encryptField(object);
        PhoneEncryptAdvice.encryptField(object);
        return object;
    }

    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> httpMessageConverter) {
        return true;
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

***

## <a id="transition">transition-数据类型转换</a>

| 类名 | 功能 |
| --- | --- |
|MapUtil|转map对象|
|ObjectUtil|转对象工具类|