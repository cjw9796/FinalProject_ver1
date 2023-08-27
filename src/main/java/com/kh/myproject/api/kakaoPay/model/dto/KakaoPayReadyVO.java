package com.kh.myproject.api.kakaoPay.model.dto;

import java.util.Date;

import lombok.*;

@Data
@Getter
@Setter
public class KakaoPayReadyVO {

    //response
    private String tid;
    private String next_redirect_pc_url;
    private Date created_at;

}
