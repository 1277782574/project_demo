package com.casicloud.controller;

import com.casicloud.send.MqttGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private MqttGateway mqttGateway;

    @RequestMapping("/sendMqtt.do")
    public String sendMqtt(String sendData,String topic){
        System.out.println(sendData+"==="+topic);
        mqttGateway.sendToMqtt(sendData,topic);
        return "OK";
    }
}
