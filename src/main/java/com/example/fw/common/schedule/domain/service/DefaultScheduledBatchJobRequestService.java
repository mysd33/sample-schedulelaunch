package com.example.fw.common.schedule.domain.service;

import java.util.HashMap;
import java.util.Map;

import com.example.fw.common.async.model.JobRequest;
import com.example.fw.common.logging.ApplicationLogger;
import com.example.fw.common.logging.LoggerFactory;
import com.example.fw.common.message.CommonFrameworkMessageIds;
import com.example.fw.common.schedule.infra.repository.ScheduledBatchJobRequestRepositoryHolder;
import com.example.fw.common.systemdate.SystemDate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * ScheduledBatchJobRequestServiceのデフォルト実装
 *
 */
@Slf4j
@RequiredArgsConstructor
public class DefaultScheduledBatchJobRequestService implements ScheduledBatchJobRequestService {
    private static final String BATCH_PARAM_NOW = "batch_param_now";
    private static final ApplicationLogger applogger = LoggerFactory.getApplicationLogger(log);
    private final ScheduledBatchJobRequestRepositoryHolder jobRequestRepositoryHolder;
    private final SystemDate systemDate;

    @Override
    public void requestJob(ScheduledBatchJobRequestInputDto inputDto) {
        applogger.info(CommonFrameworkMessageIds.I_FW_SCHDL_0001, inputDto);
        Map<String, String> params = new HashMap<>(inputDto.getParams());
        // ジョブIDとパラメータが一致していても、何度も実行できるよう、システム現在日時を追加設定
        params.put(BATCH_PARAM_NOW, systemDate.now().toString());

        JobRequest jobRequest = JobRequest.builder()//
                .jobId(inputDto.getJobId())//
                .parameters(params).build();
        applogger.info(CommonFrameworkMessageIds.I_FW_SCHDL_0002, jobRequest);
        jobRequestRepositoryHolder.getJobRequestRepository().save(jobRequest);

    }

}
