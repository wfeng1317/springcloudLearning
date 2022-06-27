package com.klgs.springcloud.service.impl;

import com.klgs.springcloud.service.IMessageSendService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;


import javax.annotation.Resource;
import java.util.UUID;

@EnableBinding(Source.class)
public class IMessageSendServiceImpl implements IMessageSendService {

    @Resource
    private MessageChannel output;

    @Value("${server.port}")
    private String serverPort;

    @Override
    public String send() {
        String message = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(message).build());
        System.out.println("message: " + message + " port: " + serverPort);
        return message;
    }
}
