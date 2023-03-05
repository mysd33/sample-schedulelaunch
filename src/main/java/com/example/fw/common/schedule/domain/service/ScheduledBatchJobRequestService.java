package com.example.fw.common.schedule.domain.service;

/**
 * 
 * ジョブ実行依頼するサービスインタフェース
 *
 */
public interface ScheduledBatchJobRequestService {

    /**
     * ジョブの実行を依頼する
     * @param inputDto ジョブ実行依頼用入力DTO
     */
    void requestJob(ScheduledBatchJobRequestInputDto inputDto);

}
