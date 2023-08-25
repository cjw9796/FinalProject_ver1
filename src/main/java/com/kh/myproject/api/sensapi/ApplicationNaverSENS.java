package com.kh.myproject.api.sensapi;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationNaverSENS {

    private String Serviceid = "ncp:sms:kr:313795361177:yeongchan";
    private String Accesskey = "RkBKSQcj0FNuNJR4H3Pa";
    private String Secretkey = "qDMvCZbhG4kJrIVHW3yUGhpmgajy60Q90wHtKosJ";
    private String from = "01066922679";

}
