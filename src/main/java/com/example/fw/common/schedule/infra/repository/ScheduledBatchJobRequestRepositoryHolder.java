package com.example.fw.common.schedule.infra.repository;

import org.springframework.util.Assert;

import com.example.fw.common.async.repository.JobRequestRepository;

import lombok.Getter;

/**
 * 
 * スケジュール起動ジョブ実行要求登録用のJobRequestRepositoryを保持するクラス
 *
 */
public class ScheduledBatchJobRequestRepositoryHolder {
    @Getter
    private final JobRequestRepository jobRequestRepository;
    
    /**
     * コンストラクタ
     * @param jobRequestRepository JobRequestRepository
     */
    public ScheduledBatchJobRequestRepositoryHolder(JobRequestRepository jobRequestRepository) {
        Assert.notNull(jobRequestRepository, "jobRequestRepositoryがNullです");
        this.jobRequestRepository = jobRequestRepository;
    }

}
