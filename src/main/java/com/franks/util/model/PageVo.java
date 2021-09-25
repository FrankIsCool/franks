package com.franks.util.model;

import com.franks.util.constant.Constant;
import com.franks.util.empty.EmptyUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页实体类
 *
 * @author frank
 * @date 2021/9/18 10:04
 */
@Data
@ApiModel("统一返回实体类")
public class PageVo {
    /**
     * 页码
     */
    @ApiModelProperty("页码")
    private Integer pageNum = Constant.ONE;
    /**
     * 每页条数
     */
    @ApiModelProperty("每页条数")
    private Integer pageSize = Constant.TEN;

    public PageVo() {
    }

    public PageVo(Integer pageNum, Integer pageSize) {
        this.pageNum = EmptyUtil.isEmpty(pageNum) || pageNum < Constant.ONE ? Constant.ONE : pageNum;
        this.pageSize = EmptyUtil.isEmpty(pageSize) || pageSize < Constant.ONE ? Constant.TEN : pageSize;
    }

    public static PageVo setPage(Integer pageNum, Integer pageSize) {
        return of(pageNum, pageSize);
    }

    public static PageVo of(Integer pageNum, Integer pageSize) {
        return new PageVo(EmptyUtil.isEmpty(pageNum) || pageNum < Constant.ONE ? Constant.ONE : pageNum, EmptyUtil.isEmpty(pageSize) || pageSize < Constant.ONE ? Constant.TEN : pageSize);
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = EmptyUtil.isEmpty(pageNum) || pageNum < Constant.ONE ? Constant.ONE : pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = EmptyUtil.isEmpty(pageSize) || pageSize < Constant.ONE ? Constant.TEN : pageSize;
    }

    public void clean() {
        this.pageNum = null;
        this.pageSize = null;
    }
}
