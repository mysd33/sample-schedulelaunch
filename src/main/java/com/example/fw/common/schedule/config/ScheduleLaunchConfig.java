package com.example.fw.common.schedule.config;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;

import com.example.fw.common.async.repository.JobRequestRepository;
import com.example.fw.common.schedule.app.laucher.BatchAppLauncher;
import com.example.fw.common.schedule.domain.service.DefaultScheduledBatchJobRequestService;
import com.example.fw.common.schedule.domain.service.ScheduledBatchJobRequestInputDto;
import com.example.fw.common.schedule.domain.service.ScheduledBatchJobRequestService;
import com.example.fw.common.schedule.infra.repository.ScheduledBatchJobRequestRepositoryHolder;
import com.example.fw.common.schedule.properties.ScheduledBatchProperties;
import com.example.fw.common.schedule.properties.ScheduledBatchProperties.ScheduledJob;
import com.example.fw.common.systemdate.SystemDate;
import com.example.fw.common.systemdate.SystemDateConfig;

/**
 * 
 * スケジュール実行用の設定クラス
 * 
 */
@Configuration
@Import(SystemDateConfig.class)
@EnableConfigurationProperties(ScheduledBatchProperties.class)
public class ScheduleLaunchConfig {
    private final ScheduledBatchProperties scheduledBatchProperties;
    private final String scheduleId;

    /**
     * コンストラクタ
     */
    public ScheduleLaunchConfig(ScheduledBatchProperties scheduledBatchProperties) {
        this.scheduledBatchProperties = scheduledBatchProperties;
        this.scheduleId = scheduledBatchProperties.getTargetId();
        if (!StringUtils.hasText(this.scheduleId)) {
            throw new IllegalStateException("ジョブ実行要求対象のスケジュールID[batch.schedule.target-id]が設定されれていません");
        }
        if (scheduledBatchProperties.getScheduledJobs().get(scheduleId) == null) {
            throw new IllegalStateException("スケジュール起動ジョブ実行要求定義が設定されていません[scheduleId: " + scheduleId + "]");
        }

    }

    /**
     * バッチのスケジュール起動クラス
     */
    @Bean
    public BatchAppLauncher batchAppLauncher(ScheduledBatchJobRequestService service) {
        ScheduledJob scheduledJob = scheduledBatchProperties.getScheduledJobs().get(scheduleId);
        ScheduledBatchJobRequestInputDto inputDto = ScheduledBatchJobRequestInputDto.builder()//
                .scheduleId(scheduleId)//
                .jobId(scheduledJob.getJobId())//
                .params(scheduledJob.getParams())//
                .build();
        return new BatchAppLauncher(service, inputDto);
    }

    /**
     * バッチのスケジュール起動Serviceクラス
     */
    @Bean
    public ScheduledBatchJobRequestService scheduledBatchJobRequestService(
            ScheduledBatchJobRequestRepositoryHolder repositoryHolder, SystemDate systemDate) {
        return new DefaultScheduledBatchJobRequestService(repositoryHolder, systemDate);
    }

    /**
     * JobRequestRepositoryを保持するHolderクラス
     */
    @Bean
    ScheduledBatchJobRequestRepositoryHolder jobRequestRepositoryHolder(ListableBeanFactory beanFactory) {
        String beanName = scheduledBatchProperties.getScheduledJobs().get(scheduleId).getJobRequestRepository();
        if (!StringUtils.hasText(beanName)) {
            throw new IllegalStateException("JobRequestRepositoryのBean明が設定されていません[scheduleId: " + scheduleId + "]");
        }
        Object repository = beanFactory.getBean(beanName);
        if (repository instanceof JobRequestRepository) {
            return new ScheduledBatchJobRequestRepositoryHolder(JobRequestRepository.class.cast(repository));
        } else {
            throw new IllegalStateException("指定されたJobRequestRepository[" + beanName + "]はサポートしていません");
        }

    }

}
