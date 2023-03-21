package com.example.schedulelaunch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import com.example.fw.common.async.config.SQSCommonConfigPackage;
import com.example.fw.common.async.repository.JobRequestRepository;
import com.example.fw.common.async.repository.JobRequestRepositoryImpl;

/**
 * 
 * インフラ層の設定クラス
 *
 */
@Configuration
@ComponentScan(basePackageClasses = { SQSCommonConfigPackage.class })
public class InfraConfig {
    // バッチAPと連携するキュー名
    @Value("${delayed.batch.queues.sample-batch.name}")
    private String sampleBatchQueueName;
    
    
    /**
     * JobRequestRepositoryの設定
     */
    @Bean
    public JobRequestRepository sampleBatchJobRequestRepository(JmsTemplate jmsTemplate) {
        return new JobRequestRepositoryImpl(jmsTemplate, sampleBatchQueueName);
    }
}
