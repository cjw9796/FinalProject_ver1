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

        int result = 1; // 반환할 리턴값
        System.out.println("service에서 받은user :" + user);
        User result_user = userRepository.findByUser_id(user.getUser_id());
        if(result_user == null){ // 유저정보가 없다면

            result = 0;
        }

        return result;

    }
//
//    public Article show(Long id) {
//
//        log.info("ArticleService의 show()메서드 실행");
//        return articleRepository.findById(id).orElse(null);
//
//    }
//
//    public Article save(ArticleForm dto) {
//
//        Article article = dto.toEntity();
//        // id는 db가 자동으로 생성하므로 id가 넘어오는 데이터를 저장하지 않는다
//
//        if (article.getId() != null) {
//
//            return null;
//        }
//
//        log.info("ArticleService의 save()메서드 실행");
//        return articleRepository.save(article);
//
//    }

}
