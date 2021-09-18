//package com.franks.util.scheduler.job;
//
//import com.sxmaps.netschool.common.utils.SpringContextUtil;
//import com.sxmaps.netschool.service.SchoolAccountService;
//import com.sxmaps.netschool.service.dto.school.SchoolAccountPayStateRespDTO;
//import com.sxmaps.netschool.service.vo.school.SchoolAccountPayStateReqVO;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
///**
// * 定时任务执行类
// *
// * @author frank
// * @module
// * @date 2021/8/19 10:49
// */
//@Component
//public class PayQCordJob implements Job {
//
//    private final Logger logger = LoggerFactory.getLogger(PayQCordJob.class);
//
//
//    @Override
//    public void execute(JobExecutionContext context) throws JobExecutionException {
//        SchoolAccountPayStateReqVO payStateReqVO = (SchoolAccountPayStateReqVO) context.getJobDetail().getJobDataMap().get(SchoolAccountPayStateReqVO.class.getName());
//        logger.info("支付二维码-定时失效,内容:{}", payStateReqVO);
//        SchoolAccountService schoolAccountService = (SchoolAccountService) SpringContextUtil.getBeanByClass(SchoolAccountService.class);
//        SchoolAccountPayStateRespDTO respDTO = schoolAccountService.payState(payStateReqVO);
//        logger.info("支付二维码-定时失效,内容:{},处理结果:{}", payStateReqVO, respDTO);
//    }
//}
