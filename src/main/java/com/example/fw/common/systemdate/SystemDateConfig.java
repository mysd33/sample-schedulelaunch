package com.example.fw.common.systemdate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * システム日付取得機能の設定クラス
 *
 */
public class SystemDateConfig {
    // テスト実施時、コマンドラインや環境変数等で、外部からシステム日付を固定化したい場合に利用
    // 指定する値の例：2020-01-01T12:00:00.123
    @Value("${systemdate:#{null}}")
    private String fixedSystemDate;
    
    
    @Bean
    public SystemDate systemDate() {
        return new SystemDateImpl(fixedSystemDate);
    }

}
