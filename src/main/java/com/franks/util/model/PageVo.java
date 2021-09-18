package com.franks.util.model;

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
    private Integer pageNum = 1;
    /**
     * 每页条数
     */
    @ApiModelProperty("每页条数")
    private Integer pageSize = 10;

    public void setPageNum(Integer pageNum) {
        if(EmptyUtil.isEmpty(pageNum)){
            return;
        }
        this.pageNum = pageNum;
    }

    public void setPageSize(Integer pageSize) {
        if(EmptyUtil.isEmpty(pageSize)){
            return;
        }
        this.pageSize = pageSize;
    }
}
