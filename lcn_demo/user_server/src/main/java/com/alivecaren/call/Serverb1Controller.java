package com.alivecaren.call;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient(name = "service-b1")
public interface Serverb1Controller {

    @RequestMapping("/txlcn")
    public Map txlcn(@RequestBody Integer userId);
}
