package com.klgs.springcloud.controller;

import com.klgs.springcloud.service.IMessageSendService;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ProviderController {

    @Resource
    private IMessageSendService iMessageSendService;

    @GetMapping(value = "/sendMessage")
    public String send(){
        return iMessageSendService.send();
    }
}
