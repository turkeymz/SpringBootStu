package com.turkeymz.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.turkeymz.util.AesUtil;
import com.turkeymz.util.CommonUtil;
import com.turkeymz.util.ValidateCode;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;

/**
 * Created by turkeymz on 2017/8/6.
 */
@RestController
@RequestMapping("/service")
@Slf4j
public class ServiceController {

    @Autowired
    ServiceControllerImpl serviceController;

    @RequestMapping("/interface.do")
    @ResponseBody
    public String getInterface(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.addHeader("Access-Control-Allow-Origin", "*");

        log.info("进来了");
        // 防止中文乱码
        request.setCharacterEncoding("utf-8");
      // 定义变量  sReturn:返回数据,sJson:收到数据,sDevice:,sToken:秘钥,sUser:用户id.
        String sReturn = "", sJson = "", sDevice = "", sToken = "", sUser = "";
        //接收请求参数
          sJson = request.getParameter("a").trim();
        sDevice = request.getParameter("b");
        sToken = request.getParameter("c");
/*sJson = "fkjKpo2opM0eTNwOjklJkx e2cEm0y9t2vHOH7lp28VeNrx66sjLPtHVpSNZ/cbrMfwxAyyJuZsSPHAET9u1gQ==";
sJson = "fkjKpo2opM0eTNwOjklJkx+e2cEm0y9t2vHOH7lp28VeNrx66sjLPtHVpSNZ/cbrMfwxAyyJuZsSPHAET9u1gQ==";
sToken = "296120";*/
 /*       String sJson = "l8owSNklyJuPsHjZD4IQZaT6Frr48Hqs1A7fko9PrKFyAHWZZk1qS2Set3DI";
        String sToken = "296120";*/
        if (CommonUtil.isNull(sToken)) {
            sToken = "";
        }
        sReturn = serviceController.taskDistribution(sToken,sJson);
        log.info("sJson:={} ", sJson);
        log.info("sToken:={} ", sToken);
        sJson = decrypt(sJson, sToken);
log.info("hello={}",sJson);

/*        JsonNode node = JsonUtil.getJson(sJson);
        sUser = JsonUtil.getJsonStringValue(node, "_userid");
        String sClass = JsonUtil.getJsonStringValue(node, "a");
        String sFunction = JsonUtil.getJsonStringValue(node, "c");*/
        return "XzJGzIZZQ/19s0QPe2di0E3S2vOjCi78dzQ+EBMbSJm3KczJ6ptsFLVzzyCPP9HxEwmdn2oSyj2FhNG4BqvYDGliXQVaL+H4xPPrfOmPd1Jr96e+l5TrhiZ+My4KFiIV";
    }

    /**
     * 验证码生成
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/vcode.do")
    @ResponseBody
    public void getVcode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ValidateCode vCode = new ValidateCode(120, 40, 5, 100);

        Calendar calendar = Calendar.getInstance();
        String key = CommonUtil.getUUID();
        String code = vCode.getCode().toLowerCase();
        long times = calendar.getTimeInMillis();
        System.out.println("key为：" + key + "新验证码为" + vCode.getCode().toLowerCase() + "时间为：" + calendar.getTimeInMillis());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        vCode.write(baos);
        byte[] bytes = baos.toByteArray();
        String imgString = Base64.encodeBase64String(bytes);

        String sReturn = "{s:1,key:'" + key + "',img:'" + imgString + "'}";
        response.getOutputStream().write((CommonUtil.encrypt(sReturn, CommonUtil.getIV())).getBytes());
    }

    // 解密报文
    public String decrypt(String sJson, String sToken) {
        String s = CommonUtil.decrypt(sJson, sToken);
        s = s.replaceAll("\n", "<br/>");
        return s;
    }

    // 加密报文
    public String encrypt(String sReturn, String sToken) {
        if (!CommonUtil.isNull(sToken)) {
            return CommonUtil.encrypt(sReturn, sToken);
        } else {
            return CommonUtil.encrypt(sReturn, "");
        }
    }




}
