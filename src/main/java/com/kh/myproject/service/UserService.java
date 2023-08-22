package com.kh.myproject.service;


import com.kh.myproject.model.entity.User;
import com.kh.myproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j // 데이터베이스 로그를 확인
@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    // Article 전체 목록 조회 실행
    public List<User> index() {

        log.info("ArticleService index()실행");


        return userRepository.findAll();

    }

    public int joinUser(User user) {


        // 유효성 검사는 자바스크립트에서 실시한다.

        int result = 0; // 반환할 리턴값
        System.out.println("service에서 받은user :" + user);


        User result_user = userRepository.findByUserId(user.getUserId());


        if (result_user == null) {
            userRepository.save(user);
            result = 1;


        }

        return result;
    }


    public int checkLogin(String user_id, String user_password) {

        int result = 0;
        User user_result = userRepository.findByUserIdAndUserPassword(user_id, user_password);

        System.out.println("로그인 결과값" + user_result);
        result = user_result == null ? result : 1;

        return result;


    }
}



