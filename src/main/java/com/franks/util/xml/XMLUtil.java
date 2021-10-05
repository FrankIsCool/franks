package com.franks.util.xml;

import com.franks.util.exception.ApiException;
import com.franks.util.model.PageVo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * xml工具类
 *
 * @author frank
 * @module
 * @date 2021/9/30 16:56
 */
public class XMLUtil {
    /**
     * 对象转成xml字符串
     *
     * @param obj
     * @return java.lang.String
     * @Author frank
     * @Date 2021/9/30 17:02
     */
    public static String toXml(Object obj) {
        try {
            StringWriter sw = new StringWriter();
            Marshaller marshaller = JAXBContext.newInstance(obj.getClass()).createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(obj, sw);
            return sw.toString();
        } catch (JAXBException e) {
            throw new ApiException("对象转xml字符串失败");
        }
    }

    /**
     * xml字符串转对象
     *
     * @param clazz  转换成的类
     * @param xmlStr xml字符串
     * @return T
     * @Author frank
     * @Date 2021/9/30 16:58
     */
    public static <T> T toObject(String xmlStr, Class<T> clazz) {
//        try {
        try {
            return (T) JAXBContext.newInstance(clazz).createUnmarshaller().unmarshal(new StringReader(xmlStr));
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new ApiException("转换xml失败:"+e.getMessage());
        }
    }

    public static void main(String[] args) {
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><pageNum>12</pageNum><pageSize>1002</pageSize>";
        PageVo o = XMLUtil.toObject(str, PageVo.class);
        System.out.println(o);
    }
}
