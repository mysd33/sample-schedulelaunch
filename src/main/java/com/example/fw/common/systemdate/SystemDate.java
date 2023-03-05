package com.example.fw.common.systemdate;

import java.time.LocalDateTime;

/**
 * システム日時取得用インタフェース
 * 
 * @author dell
 *
 */
public interface SystemDate {

    /**
     * システム日時を取得する
     * 
     * @return システム日時
     */
    LocalDateTime now();

}
