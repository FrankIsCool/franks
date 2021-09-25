package com.franks.util.scheduler;

import com.franks.util.constant.Constant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * 创建动态定时任务基础父类
 *
 * @module
 * @author frank
 * @date 2021/8/21 11:51
 */
@Data
public class SchedulerBaseVo implements Serializable {

    /**
     * 任务名称-唯一
     */
    @ApiModelProperty(value = "任务名称")
    private String jobName;
    /**
     * 触发器名称-唯一
     */
    @ApiModelProperty(value = "触发器名称")
    private String triggerName;
    /**
     * 分组名称-唯一
     */
    @ApiModelProperty(value = "分组名称")
    private String groupName;

    public SchedulerBaseVo() {
        this.groupName = Constant.DEFAULT_SERVICE_NAME;
        this.jobName = UUID.randomUUID().toString();
        this.triggerName = UUID.randomUUID().toString();
    }

    public SchedulerBaseVo(String jobName) {
        this.groupName = Constant.DEFAULT_SERVICE_NAME;
        this.jobName = jobName;
        this.triggerName = jobName;
    }

    public SchedulerBaseVo(String jobName, String groupName) {
        this.jobName = jobName;
        this.triggerName = jobName;
        this.groupName = groupName;
    }

    public SchedulerBaseVo(String jobName, String triggerName, String groupName) {
        this.jobName = jobName;
        this.triggerName = triggerName;
        this.groupName = groupName;
    }

    public void init(String jobName,String groupName) {
        this.jobName = jobName;
        this.triggerName = jobName;
        this.groupName = groupName;
    }

}
