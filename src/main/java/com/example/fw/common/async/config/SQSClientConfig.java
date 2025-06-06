package com.example.fw.common.async.config;

import jakarta.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

/**
 * SQSの設定クラス
 */
@Configuration
public class SQSClientConfig {

    /**
     * JMSTemplateの定義
     * 
     * @param connectionFactory
     */
    @Bean
    JmsTemplate defaultJmsTemplate(ConnectionFactory connectionFactory,
            MessageConverter jacksonJmsMessageConverter) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter);
        return jmsTemplate;
    }

}
