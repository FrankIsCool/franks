//package com.franks.util.mysql;
//
//
//import com.github.pagehelper.util.StringUtil;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.ibatis.cache.CacheKey;
//import org.apache.ibatis.executor.Executor;
//import org.apache.ibatis.mapping.BoundSql;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.mapping.ParameterMapping;
//import org.apache.ibatis.mapping.ParameterMode;
//import org.apache.ibatis.plugin.*;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.ibatis.session.Configuration;
//import org.apache.ibatis.session.ResultHandler;
//import org.apache.ibatis.session.RowBounds;
//import org.apache.ibatis.type.TypeHandlerRegistry;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Field;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Properties;
//
///**
// * api doc 拦截mybatis，用于加解密对象属性
// * @author：liuyindong
// */
//@Intercepts({
//        @Signature(type= Executor.class,method="update",args={MappedStatement.class,Object.class}),
//        @Signature(type=Executor.class,method="query",args={MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class}),
//        @Signature(type=Executor.class,method="query",args={MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class,CacheKey.class,BoundSql.class}),
//})
//@Component
//@Slf4j
//@AllArgsConstructor
//public class DBInterceptor implements Interceptor {
//
//    private static ThreadLocal<SimpleDateFormat> dateTimeFormatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//
//
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        String methodName = invocation.getMethod().getName();
//        Object parameter = invocation.getArgs()[1];
//
//
//        if (StringUtils.equalsIgnoreCase("query", methodName)) {
//            /**
//             * 在这里可以处理查询参数，如传递的参数为明文，要按照密文查询
//             * 本文选择使用同一参数封装处理方案
//             */
//        }
//        if (StringUtils.equalsIgnoreCase("update", methodName)|| StringUtils.equalsIgnoreCase("insert", methodName)) {
//            if (null != parameter){
//                CryptUtils.encryptField(parameter);
//            }
//
//        }
//
//        Object returnValue = invocation.proceed();
//
//        try {
//            if (returnValue instanceof ArrayList<?>) {
//                List<?> list = (ArrayList<?>) returnValue;
//                if (1 > list.size())
//                    return returnValue;
//                Object obj = list.get(0);
//                if (null == obj)
//                    return returnValue;
//                Field[] fields = obj.getClass().getDeclaredFields();
//                int len;
//                if (0 < (len = fields.length)) {
//                    // 标记是否有解密注解
//                    boolean isD = false;
//                    for (int i = 0; i < len; i++) {
//                        if (fields[i].isAnnotationPresent(DecryptField.class)) {
//                            isD = true;
//                            break;
//                        }
//                    }
//                    // 将含有DecryptField注解的字段解密
//                    if (isD)
//                        list.forEach(CryptUtils::decryptField);
//                }
//            }
//        } catch (Exception e) {
//            // 打印异常，由于拦截器本身抛出异常，比如拦截到很奇葩的返回，应算正常
//            log.info("抛出异常，正常返回==> " + e.getMessage());
//            return returnValue;
//        }finally {
//            //sql格式化且打印sql以及耗时
//          //  long endTime = System.currentTimeMillis();
//           // long sqlCost = endTime - startTime;
//            //MappedStatement statement = (MappedStatement) invocation.getArgs()[0];
//
//            //bsLog.debug("执行SQL:{}${}${}${}", authUserBo.getUserId(),authUserBo.getUserName(),sqlCost,this.getSql(statement.getConfiguration(), statement.getBoundSql(parameter)));
//        }
//        return returnValue;
//    }
//
//    @Override
//    public Object plugin(Object target) {
//        return Plugin.wrap(target, this);
//    }
//
//    public void setProperties(Properties properties) {
//    }
//
//
//    /**
//     * 获取完整的sql语句
//     *
//     * @param configuration
//     * @param boundSql
//     * @return
//     */
//    private String getSql(Configuration configuration, BoundSql boundSql) {
//        // 输入sql字符串空判断
//        String sql = boundSql.getSql();
//        if (StringUtil.isEmpty(sql)) {
//            return "";
//        }
//        return formatSql(sql, configuration, boundSql);
//    }
//
//    /**
//     * 将占位符替换成参数值
//     *
//     * @param sql
//     * @param configuration
//     * @param boundSql
//     * @return
//     */
//    private String formatSql(String sql, Configuration configuration, BoundSql boundSql) {
//
//        //美化sql
//        sql = beautifySql(sql);
//
//        //填充占位符, 目前基本不用mybatis存储过程调用,故此处不做考虑
//        Object parameterObject = boundSql.getParameterObject();
//        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
//        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
//
//        List<String> parameters = new ArrayList<>();
//        if (parameterMappings != null) {
//            MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
//            for (int i = 0; i < parameterMappings.size(); i++) {
//                ParameterMapping parameterMapping = parameterMappings.get(i);
//                if (parameterMapping.getMode() != ParameterMode.OUT) {
//                    //  参数值
//                    Object value;
//                    String propertyName = parameterMapping.getProperty();
//                    //  获取参数名称
//                    if (boundSql.hasAdditionalParameter(propertyName)) {
//                        // 获取参数值
//                        value = boundSql.getAdditionalParameter(propertyName);
//                    } else if (parameterObject == null) {
//                        value = null;
//                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
//                        // 如果是单个值则直接赋值
//                        value = parameterObject;
//                    } else {
//                        value = metaObject == null ? null : metaObject.getValue(propertyName);
//                    }
//
//                    if (value instanceof Number) {
//                        parameters.add(String.valueOf(value));
//                    } else {
//                        StringBuilder builder = new StringBuilder();
//                        builder.append("'");
//                        if (value instanceof Date) {
//                            builder.append(dateTimeFormatter.get().format((Date) value));
//                        } else if (value instanceof String) {
//                            builder.append(value);
//                        }
//                        builder.append("'");
//                        parameters.add(builder.toString());
//                    }
//                }
//            }
//        }
//
//        for (String value : parameters) {
//            sql = sql.replaceFirst("\\?", value);
//        }
//        return sql;
//    }
//
//
//
//    public static String beautifySql(String sql) {
//        sql = sql.replaceAll("[\\s\n ]+", " ");
//        return sql;
//    }
//}
