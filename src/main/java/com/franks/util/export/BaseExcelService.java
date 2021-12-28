package com.franks.util.export;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSONObject;
import com.franks.util.constant.Constant;
import com.franks.util.date.DateProperties;
import com.franks.util.date.StrDateUtil;
import com.franks.util.empty.EmptyUtil;
import com.franks.util.exception.ApiException;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 导入导出基础
 *
 * @author frank
 * @module
 * @date 2021/7/24 15:17
 */
@Service
public class BaseExcelService {

    private final Logger log = LoggerFactory.getLogger(BaseExcelService.class);

    @Autowired
    private EasyPoiUtils easyPoiUtils;

    /**
     * 导入，文件转对象
     *
     * @param excelFile 文件流
     * @param classs    转换对象class
     * @return java.util.List<T>
     * @Author frank
     * @Date 2021/7/25 11:03
     */
    public <T> List<T> getList(MultipartFile excelFile, Class<T> classs) {
        if (null == excelFile) {
            log.error("导入对象为空");
            throw new ApiException("导入对象为空");
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        try (InputStream inputStream = excelFile.getInputStream()) {
            return ExcelImportUtil.importExcel(inputStream, classs, params);
        } catch (Exception e) {
            log.error("导入失败，文件流转对象失败", e);
            throw new ApiException(String.format("当前模板错误,请下载最新版本!原因:%s", e.getMessage()));
        }
    }

    /**
     * 导出，对象转文件
     *
     * @param exportVos  数据源
     * @param exportName 文件名称
     * @param response   返回
     * @return void
     * @Author frank
     * @Date 2021/7/25 11:03
     */
    public <T> void exportExcel(List<T> exportVos, Class<T> classs, String exportName, HttpServletResponse response) {
        if (EmptyUtil.isEmpty(exportVos)) {
            throw new ApiException("导出数据为空！");
        }
        String date = StrDateUtil.getDate(DateProperties.DATE_YEAR_MONTH_DAY);
        try {
            easyPoiUtils.exportExcel(exportVos, exportName, date, classs, exportName + "(" + date + ").xls", response);
        } catch (Exception e) {
            log.error("导出失败，对象转文件失败", e);
            throw new ApiException(String.format("导出失败!原因:%s", e.getMessage()));
        }
    }

    /**
     * 下载模板
     *
     * @param exportName 文件名称
     * @param response   返回
     * @return void
     * @Author frank
     * @Date 2021/7/25 11:03
     */
    public <T> void downloadTemplate(Class<T> classs, String exportName, HttpServletResponse response) {
        try {
            List<T> exportVos = new ArrayList<>();
            easyPoiUtils.exportExcel(exportVos, null, exportName, classs, exportName + ".xls", response);
        } catch (Exception e) {
            log.error("下载模板失败", e);
            throw new ApiException(String.format("下载模板失败!原因:%s", e.getMessage()));
        }
    }

    public String getDataParm(String dataParm) {
        if (EmptyUtil.isEmpty(dataParm)) {
            dataParm = Constant.BLANK_STR;
        }
        try {
            return new String(Base64.decodeBase64(dataParm.replaceAll(" ", "+")
                    .replaceAll("\n", Constant.BLANK_STR)
                    .getBytes(Constant.CHARSET)), Constant.CHARSET);
        } catch (UnsupportedEncodingException e) {
            log.error("导出失败，参数解密失败", e);
            throw new ApiException("导出失败!参数解密失败");
        }
    }

    public <T> T getDataParm(String dataParm, Class<T> classs) {
        return JSONObject.parseObject(getDataParm(dataParm), classs);
    }
}
