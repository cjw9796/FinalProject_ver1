package com.kh.myproject.member.user.model.dto;


import com.kh.myproject.member.user.model.entity.User;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor // 모든 멤버 변수를 초기화하는 생성자
@NoArgsConstructor // 기본 생성자.
@EqualsAndHashCode // equals() 메서드랑 hashcode 메서드도 자동완성된다. 객체의 값을 비교할 때 equals나 hashcode메서드를 사용하는데
// 그 떄 굳이 오버라이딩 시킬 필요가 없어진다.
public class UserForm {

    private String user_id;           // 유저 아이디
    // private Long user_number;        // 유저 번호   auto_increment, primary key
    private String user_name;       // 유저 이름
    private String user_password;   // 유저패스워드
    private String user_phone;      // 핸드폰번호
    private String user_gender;     // 성별
    private Date user_date;       // 생년월일
    private String user_mbti;       // mbti
    private String user_img;       // mbti


    // DTO 클래스에 데이터를 Entity(테이블과 매핑되는 클래스)로
    // 변환하는 메서드를 추가한다.




    public User toEntity(){

        return new User(null,user_id, user_name,  user_password, user_phone, user_gender, user_date, user_mbti,user_img);

    }

}
