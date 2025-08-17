package com.example.fw.common.schedule.app.laucher;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.example.fw.common.logging.ApplicationLogger;
import com.example.fw.common.logging.LoggerFactory;
import com.example.fw.common.logging.MonitoringLogger;
import com.example.fw.common.message.CommonFrameworkMessageIds;
import com.example.fw.common.schedule.domain.service.ScheduledBatchJobRequestInputDto;
import com.example.fw.common.schedule.domain.service.ScheduledBatchJobRequestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * バッチアプリケーションの起動クラス
 * 
 *
 */
@Slf4j
@RequiredArgsConstructor
public class BatchAppLauncher implements ApplicationRunner {
    private static final ApplicationLogger appLogger = LoggerFactory.getApplicationLogger(log);
    private static final MonitoringLogger monitorLogger = LoggerFactory.getMonitoringLogger(log);
    private final ScheduledBatchJobRequestService scheduledBatchJobRequestService;
    private final ScheduledBatchJobRequestInputDto inputDto;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        execute();
    }
    
    /**
     * ジョブの実行依頼を実施バッチアプリケーションを起動する。
     */
    private void execute() {
        appLogger.debug("BatchAppLauncher起動");
        
        try {
            //ジョブの実行依頼
            scheduledBatchJobRequestService.requestJob(inputDto);
            
        } catch (Exception e) {
            monitorLogger.error(CommonFrameworkMessageIds.E_FW_CMMN_9001, e);
            
        }
        
        
    }

}
