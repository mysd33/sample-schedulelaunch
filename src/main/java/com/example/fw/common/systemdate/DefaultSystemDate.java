package com.example.fw.common.systemdate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

import com.example.fw.common.exception.SystemException;
import com.example.fw.common.message.CommonFrameworkMessageIds;

import lombok.RequiredArgsConstructor;

/**
 * 日本のシステム日時を取得する
 * テスト実行時に日付の差し替えができるようになっている
 *
 */
@RequiredArgsConstructor
public class DefaultSystemDate implements SystemDate {
    // テスト実施時に、コマンドライン引数や環境変数等で、外部からシステム比杖ｋを固定化したい場合に利用
    private final String fixedSystemDate;
    
    @Override
    public LocalDateTime now() {
        if (fixedSystemDate == null) {
            String zoneId = "Asia/Tokyo";
            return LocalDateTime.now(ZoneId.of(zoneId));
        } else {
            try {
                return LocalDateTime.parse(fixedSystemDate);
            } catch (DateTimeParseException e) {
                throw new SystemException(e, CommonFrameworkMessageIds.E_CM_FW_9002);
            }
        }
    }
}   
