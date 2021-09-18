## franks 工具类

## 工具类结构

| 包名 | 功能 |
| --- | --- |
|date|日期工具类|
|empty|检验工具类|
|exception|异常|
|id|唯一id生成器|
|<a href="#model">model</a>|常用实体类|
|Redisson|延迟队列|
|scheduler|动态定时任务|
|sign|加密|
|str|字符串|

***

## date-日期工具类

| 类名 | 功能 |
| --- | --- |
|DateProperties|日期常量|
|DateUtil|Date日期工具类，包含获取当前时间，时间格式转换，增加时间，获取年月日时分秒|
|InstantDateUtil|Instant日期工具类，包含获取当前时间，时间格式转换，增加时间，获取年月日时分秒|
|LongDateUtil|Long日期工具类，包含获取当前时间，时间格式转换，增加时间，获取年月日时分秒|
|StrDateUtil|String日期工具类，包含获取当前时间，时间格式转换，增加时间，获取年月日时分秒|
|TimestampDateUtil|Timestamp日期工具类，包含获取当前时间，时间格式转换，增加时间，获取年月日时分秒|

***

## empty-检验工具类

| 类名 | 功能 |
| --- | --- |
|EmptyUtil|空校验|
|ValidUtils|数据格式校验，手机号，身份证，邮箱，汉字，ip等等|

***

## exception-异常

| 类名 | 功能 |
| --- | --- |
|ApiException|自定义异常|
|GlobalExceptionHandler|异常拦截，统一返回ApiResponse|

***

## <a id="model">model-常用实体类</a>

| 类名 | 功能 |
| --- | --- |
|IdGen|雪花算法的基础生成（不对外）|
|SequenceUtil|唯一id工具类（对外）|