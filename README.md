## franks 工具类

## 工具类结构

| 包名 | 功能 |
| --- | --- |
|<a href="#date">date</a>|日期工具类|
|<a href="#empty">empty</a>|检验工具类|
|<a href="#exception">exception</a>|异常|
|<a href="#id">id</a>|唯一id生成器|
|<a href="#model">model</a>|常用实体类|
|<a href="#redisson">redisson</a>|延迟队列|
|<a href="#scheduler">scheduler</a>|动态定时任务|
|<a href="#sign">sign</a>|加密|
|<a href="#str">str</a>|字符串工具类|

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

***

## <a id="model">model-常用实体类</a>

| 类名 | 功能 |
| --- | --- |
|ApiResponse|统一返回实体类|
|PageVo|分页实体类|

***

## <a id="redisson">redisson-延迟队列</a>

| 类名 | 功能 |
| --- | --- |
|RedisDelayedQueue|新增延迟队列|
|RedisDelayedQueueInit|监听延迟队列初始化|
|RedisDelayedQueueListener|监听延迟队列统一接口|

***

## <a id="scheduler">scheduler-动态定时任务</a>

| 类名 | 功能 |
| --- | --- |
|DynamicScheduledTask|新增动态定时任务|
|SchedulerBaseVo|创建动态定时任务基础实体|
|SchedulerConfig|动态定时任务初始化|

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