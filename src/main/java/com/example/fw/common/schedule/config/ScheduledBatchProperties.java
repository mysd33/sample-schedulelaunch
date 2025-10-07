package com.example.fw.common.schedule.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.example.fw.common.constants.FrameworkConstants;

import lombok.Data;

/**
 * バッチAPスケジュール起動定義のプロパティクラス
 *
 */
@ConfigurationProperties(ScheduledBatchProperties.PROPERTY_PREFIX)
@Data
public class ScheduledBatchProperties {
    static final String PROPERTY_PREFIX = FrameworkConstants.PROPERTY_BASE_NAME + "batch.schedule";
    /**
     * スケジュール起動ジョブ実行要求定義
     */
    private Map<String, ScheduledJob> scheduledJobs = new HashMap<>();

    /**
     * ジョブ実行要求登録対象のスケジュールID
     */
    private String targetId;

    @Data
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
