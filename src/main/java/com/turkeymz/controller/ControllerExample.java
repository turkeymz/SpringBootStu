package com.turkeymz.controller;

import com.turkeymz.VO.ResultVO;
import com.turkeymz.service.ServiceExample;
import com.turkeymz.util.ResultVOUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 样例Controller
 * Created by turkeymz on 2017/8/3.
 */
@RestController
@RequestMapping("/Example")
@Slf4j
public class ControllerExample {

    @Autowired
    ServiceExample serviceExample;

    @RequestMapping("/helloWorld")
    public ResultVO helloWorld(@RequestParam("id") String id){
        log.info("进来了id={}",id);
        return ResultVOUtil.success(serviceExample.helloWorld(id));
    }


}
