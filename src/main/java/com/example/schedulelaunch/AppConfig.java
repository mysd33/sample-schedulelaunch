package com.example.schedulelaunch;

import com.example.fw.common.schedule.config.ScheduleLaunchConfigPackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// スケジュールバッチAP起動機能の追加
@ComponentScan(basePackageClasses = {ScheduleLaunchConfigPackage.class})
public class AppConfig {

}
