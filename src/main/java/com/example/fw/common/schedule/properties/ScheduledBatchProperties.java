package com.example.fw.common.schedule.properties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * バッチAPスケジュール起動定義のプロパティクラス
 *
 */
@ConfigurationProperties("batch.schedule")
@Getter
@Setter
public class ScheduledBatchProperties {
    /**
     * バッチAPスケジュール起動を有効化する
     */
    private boolean enabled;
    
    /**
     * スケジュール起動ジョブ実行要求定義
     */
    private Map<String, ScheduledJob> scheduledJobs = new HashMap<>();
    
    /**
     * ジョブ実行要求登録対象のスケジュールID
     */
    private String targetId;

    
    @Getter
    @Setter
    public static class ScheduledJob {
        /**
         * ジョブID
         */
        private String jobId;
        
        /**
         * パラメータ
         */
        private Map<String, String> params;
        
        /**
         * JobRequestRepositoryのID（Bean名）
         */
        private String jobRequestRepository;
        
    }
    
    
}
