package com.franks.util.resource;

import com.franks.util.constant.Constant;
import com.franks.util.exception.ApiException;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SysResource {

    private static Reader newClassPathReader(String classpath) throws UnsupportedEncodingException {
        return new InputStreamReader(SysResource.class.getResourceAsStream(classpath), Constant.CHARSET);
    }

    private static Reader newFileReader(String path) throws FileNotFoundException, UnsupportedEncodingException {
        return new InputStreamReader(new FileInputStream(path), Constant.CHARSET);
    }

    private static Map<String, String> getResource(Reader reader) throws IOException {
        ConcurrentHashMap map = new ConcurrentHashMap();
        BufferedReader br = new BufferedReader(reader);
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] tokens = line.split("=");
            map.put(tokens[0], tokens[1]);
        }
        br.close();
        return map;
    }

    private static <T> T getResource(Reader reader, Class<T> classs) throws IOException, InstantiationException, IllegalAccessException {
        Map<String, String> resource = getResource(reader);
        T t = classs.newInstance();
        //获取所有字段数组
        Field[] fields = classs.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (resource.containsKey(field.getName())) {
                field.set(t, resource.get(field.getName()));
            }
        }
        return t;
    }

    public static Map<String, String> getResource(String classpath){
        try {
            return getResource(newClassPathReader(classpath));
        } catch (IOException e) {
            throw new ApiException("加载资源失败");
        }
    }

    public static <T> T getResource(String classpath, Class<T> classs) {
        try {
            return getResource(newClassPathReader(classpath), classs);
        } catch (IOException e) {
            throw new ApiException("加载资源失败");
        } catch (InstantiationException e) {
            throw new ApiException("加载资源失败");
        } catch (IllegalAccessException e) {
            throw new ApiException("加载资源失败");
        }
    }
}
