package com.alivecaren.call;

import com.alivecaren.model.RespBean;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "student-server")
public interface CallController {

    @RequestMapping("/add")
    public RespBean add();

}



