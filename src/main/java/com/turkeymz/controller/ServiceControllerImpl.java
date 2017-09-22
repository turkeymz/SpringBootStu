package com.turkeymz.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.turkeymz.cache.ServiceMethodCache;
import com.turkeymz.cache.ServiceMethodEntity;

/**
 * Created by turkeymz on 2017/8/6.
 */
@Service("serviceControllerImpl")
public class ServiceControllerImpl {

    @Autowired
    private ApplicationContext context;

    private String getService(String jsonStr,String type) throws Exception{
        String result = "";
        ServiceMethodEntity sm = ServiceMethodCache.map.get(type);
        String className = sm.getClassName();
        String methodName = sm.getMethodName();
        //利用spring工具类反射进java
        Object o = context.getBean(className);
        Method m = ReflectionUtils.findMethod(o.getClass(), methodName,String.class);
        result = (String) ReflectionUtils.invokeMethod(m, o,jsonStr);

        return result;
    }

    public String taskDistribution(String type,String jsonStr){
        String result = "";
        String message = "请求失败!";
        String state = "-1";
        JSONObject result_json = new JSONObject();
        try {
            result = this.getService(jsonStr, type);//开始进行任务分发
            message = "请求成功";
            state = "0";
            result_json.put("data", result);
            result_json.put("message", message);
            result_json.put("state", state);
            return result_json.toString();
        } catch (InvocationTargetException  e) {
            e.printStackTrace();
            Throwable t = e.getTargetException();
            result = t.getMessage();// 错误信息
            result_json.put("data", result);
            result_json.put("message", message);
            result_json.put("state", state);
            return result_json.toString();
        } catch (Exception e) {
            e.printStackTrace();
            result = e.getMessage();// 错误信息
            result_json.put("data", result);
            result_json.put("message", message);
            result_json.put("state", state);
            return result_json.toString();
        }
    }

}
