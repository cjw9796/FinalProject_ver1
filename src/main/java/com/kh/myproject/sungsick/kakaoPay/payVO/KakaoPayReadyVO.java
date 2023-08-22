package com.kh.myproject.sungsick.kakaoPay.payVO;

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
