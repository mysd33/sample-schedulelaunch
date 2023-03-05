package com.example.schedulelauch;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.example.fw.common.schedule.config.ScheduleLaunchConfig;

@Configuration
@Import(ScheduleLaunchConfig.class)
public class AppConfig {

}
