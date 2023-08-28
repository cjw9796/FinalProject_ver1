package com.kh.myproject.member.user.service;


import com.kh.myproject.member.user.model.entity.User;
import com.kh.myproject.member.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
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

        // 자바 스크립트에서 유효성 검사를 했지만 한번더 결과값 출력을 하기위해 유효성 검사를 실행한다.

        if (result_user == null) {
            userRepository.save(user);
            result = 1;
        }

        return result;
    }


    public User getUser(String user_id, String user_password) {

        int result = 0;
        User user_result = userRepository.findByUserIdAndUserPassword(user_id, user_password);

        return user_result;
    }

    public User getUserById(String user_id) {

        User result = userRepository.findByUserId(user_id);

        return result;
    }

    public User updateUser(User user){


        System.out.println("수정전에 가져온 user값" + user);

        userRepository.updateUser(user); // save는 덮어씌우기 떄문에 직접 query문을 실행한다.


        //수정한 후 다시 수정된 객체를 가지고 온다.

        User result = userRepository.findById(user.getUserNumber()).orElse(null);
        System.out.println("수정후 가져온 result값" + result);

        return result;
    }


    public void saveFile(String img_path, String file_name){

        ClassPathResource resource = new ClassPathResource("/static/file/profile_image/"); // 빈 문자열로 생성
        String save_path = "";

        //크게 두가지 로직으로 나뉘어진다.
        // 1. 웹 서버상에 있는 이미지의 url을 통해 해당 img를 객체로 불러온다
        // 2. 현재 프로젝트에서 해당 이미지를 저장할 경로를 설정한다
        // 3. 2번에서 설정한 경로에 1번으로 얻은 객체를 저장한다.

        try {


            URL encode_url = resource.getURL();
            String decode_url = URLDecoder.decode(encode_url.getPath(),"UTF-8");


            System.out.println("decode url : " + decode_url);
            // ClassPathResouce의 전체경로를 가지고 오기 위해서는 URL로 한번 작업을 시켜줘야한다.
            // 이떄 url 인코딩이 발생하기 때문에 실제 로칼 경로로 참조하기 위해서는 다시 디코딩 시켜준다.

            String extension = img_path.substring(img_path.lastIndexOf(".")+1); // 파일이름에서 가장 끝에 있는 확장자명을 가지고온다
            // 가장 끝에 있는 .이후로 자른 글자는 곧 확장자명(jpeg,png,jpg)가 될것이다.

            save_path = decode_url + file_name;
            System.out.println("savePath "  + save_path); // 실제 파일 저장 경로.
            File imageFile = new File(save_path);

            // 웹서버의 이미지 가지고 오기.필
            URL img_url = new URL(img_path); // 매개변수로 받은 카카오톡 프로필 이미지 url
            BufferedImage image =  ImageIO.read(img_url);;



            //여기서 설정한 extension, 즉 확장자는 확장자명을 붙여주는게 아니고 해당 파일을 설정 extension으로 변환하는 그런 것 같다.
            // 파일에 붙이는 이름은 위에서 끝나는 것이다.
            ImageIO.write(image, extension, imageFile); // image를 file로 업로드
            // 첫번째 매개변수는 ImageIO.read를 한 image객체를 넣고 이후에는 저장될 파일의 확장자명을 설정해주고, 저장경로를 설정한 file객체를 마지막 매개변수로 지정한다.

        }catch (IOException e){

            e.printStackTrace();
        }





    }

}



