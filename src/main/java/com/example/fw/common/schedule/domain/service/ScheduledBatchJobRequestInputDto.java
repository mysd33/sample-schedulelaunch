package com.example.fw.common.schedule.domain.service;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * {@link ScheduledBatchJobRequestService}の入力DTO
 *
 */
@Data
@Builder
@AllArgsConstructor
public class ScheduledBatchJobRequestInputDto  {
    
    /**
     * スケジュールID（スケジュール起動ジョブ実行要求定義のキー）
     */
    private String scheduleId;
    
    /**
     * ジョブID
     */
    private String jobId;
    
    /**
     * パラメータ
     */
    private Map<String, String> params;

}
