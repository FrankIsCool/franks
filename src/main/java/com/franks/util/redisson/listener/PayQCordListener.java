//package com.franks.util.redisson.listener;
//
//import com.sxmaps.netschool.common.redisson.RedisDelayedQueueListener;
//import com.sxmaps.netschool.service.SchoolAccountService;
//import com.sxmaps.netschool.service.dto.school.SchoolAccountPayStateRespDTO;
//import com.sxmaps.netschool.service.vo.school.SchoolAccountPayStateReqVO;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * 支付二维码监听器
// *
// * @module
// * @author frank
// * @date 2021/8/19 10:49
// */
//@Component
//public class PayQCordListener implements RedisDelayedQueueListener<SchoolAccountPayStateReqVO> {
//
//    private final Logger logger = LoggerFactory.getLogger(PayQCordListener.class);
//    @Autowired
//    private SchoolAccountService schoolAccountService;
//
//    @Override
//    public void invoke(SchoolAccountPayStateReqVO payStateReqVO) {
//        logger.info("支付二维码-延迟失效,内容:{}", payStateReqVO);
//        SchoolAccountPayStateRespDTO respDTO = schoolAccountService.payState(payStateReqVO);
//        logger.info("支付二维码-延迟失效,内容:{},处理结果:{}", payStateReqVO,respDTO);
//    }
//}
