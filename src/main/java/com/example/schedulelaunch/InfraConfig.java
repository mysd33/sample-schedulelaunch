package com.example.schedulelaunch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import com.example.fw.common.async.config.SQSCommonConfigPackage;
import com.example.fw.common.async.repository.JobRequestRepository;
import com.example.fw.common.async.repository.JobRequestRepositoryImpl;
import com.example.fw.common.logging.config.LoggingConfigPackage;

/**
 * 
 * インフラ層の設定クラス
 *
 */
@Configuration
// 非同期処理依頼の設定、ロギング拡張機能の設定を追加
@ComponentScan(basePackageClasses = { SQSCommonConfigPackage.class, LoggingConfigPackage.class })
public class InfraConfig {
    // バッチAPと連携するキュー名
    @Value("${example.delayed.batch.queues.sample-batch.name}")
    private String sampleBatchQueueName;

    /**
     * JobRequestRepositoryの設定
     */
    @Bean
    JobRequestRepository sampleBatchJobRequestRepository(JmsTemplate jmsTemplate) {
        return new JobRequestRepositoryImpl(jmsTemplate, sampleBatchQueueName);
    }
}
