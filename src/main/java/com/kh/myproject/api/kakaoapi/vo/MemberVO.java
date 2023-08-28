package com.kh.myproject.api.kakaoapi.vo;


import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MemberVO {

    private String email;
    private String gender;
    private String profile_img;

}
