package com.kh.myproject.member.user.model.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor // 모든 멤버 변수를 초기화하는 생성자
@NoArgsConstructor // 기본 생성자.
@EqualsAndHashCode // equa
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 기본키 값을 자동으로 생성한다.
    private Long userNumber;
    // user_number 필드는 auto_increment이면서 pk이다.



    // ArticleForm에서 생성한 멤버변수와 테이블을 매핑시키는 과정?
    @Column
    private String userId; // 유저 아이디

    @Column
    private String userName; // 유저 이름

    @Column
    private String userPassword; // 유저 패스워드

    @Column
    private String userPhone;  // 핸드폰 번호


    @Column
    private String userGender; // 유저 성별

    @Column
    private Date userDate; // 유저 생년월일

    @Column
    private String userMbti; // 유저 성향(mbti)

    @Column
    private String userImg; // 유저 프로필 이미지.


}
