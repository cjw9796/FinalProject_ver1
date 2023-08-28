package com.kh.myproject.api.sensapi.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.kh.myproject.api.sensapi.service.SmsService;
import com.kh.myproject.api.sensapi.vo.SendSmsResponseDto;
import com.kh.myproject.api.sensapi.vo.SmsRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

@Controller
class SensController {


    // service ID : ncp:sms:kr:313795361177:yeongchan
    // accessKeyID : RkBKSQcj0FNuNJR4H3Pa
    // Secret Key : qDMvCZbhG4kJrIVHW3yUGhpmgajy60Q90wHtKosJ


    //https://sens.apigw.ntruss.com/sms/v2/services/{serviceId}/messages
//    {
//        "type":"SMS",
//            "contentType":"COMM",
//            "countryCode":"string",
//            "from":"string",
//            "subject":"string",
//            "content":"string",
//            "messages":[
//        {
//            "to":"string",
//                "subject":"string",
//                "content":"string"
//        }
//    ],
//        "files":[
//        {
//            "fileId": "string"
//        }
//    ],
//        "reserveTime": "yyyy-MM-dd HH:mm",
//            "reserveTimeZone": "string"
//    }

    @Autowired
    SmsService smsService;

    @RequestMapping("/senstest2")
    public String home(){

        System.out.println("senstest2");


        try {
            smsService.sendSms("01066922679","안녕하십니까");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return "naversens/home";

    }

}


