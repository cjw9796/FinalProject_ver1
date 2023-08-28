package com.kh.myproject.member.user.controller;


import com.kh.myproject.api.kakaoapi.vo.MemberVO;
import com.kh.myproject.api.sensapi.service.SmsService;
import com.kh.myproject.member.user.model.dto.UserForm;
import com.kh.myproject.member.user.model.entity.User;
import com.kh.myproject.member.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@SessionAttributes("user")
public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    SmsService smsService;


    @GetMapping("/")
    public String home() {



        return "community/home";
    }




    @GetMapping("member/login")
    public String login() {

        System.out.println("memberlogin");

        return "member/user/login";
    }


    @GetMapping("member/loginPro")
    public ModelAndView loginProByKakao(ModelAndView modelAndView,
                                        @ModelAttribute("memberVO") MemberVO result) {

        // 카카오로 로그인이 성공시 오게되는 페이지.

        if (result == null) { // 비정상적인 경로로 접속했을 경우.

            System.out.println("비정상경로로 loginPro접속");
            modelAndView.setViewName("community/home");
            return modelAndView;

        }

        User user = userService.getUserById(result.getEmail()); // 이메일 값으로 db에서 user정보를 꺼내온다.

        String msg = String.format("반갑습니다 %s님", user.getUserName());


        modelAndView.addObject("msg", msg);
        modelAndView.addObject("user", user); // 세션을 설정한다.
        modelAndView.setViewName("member/user/loginPro"); // msg출력을 위한 html 파일 거치기


        return modelAndView;

    }

    @ResponseBody
    @PostMapping("member/checkId")
    public String checkId(@ModelAttribute("user_id") String user_id){

        System.out.println("넘어온값 :" + user_id);
        User user = userService.getUserById(user_id);
        if(user == null){

            return "success";
        }else{

            return "fail";
        }


    }

    @PostMapping("member/loginPro")
    public ModelAndView loginPro(@RequestParam("user_id") String user_id,
                                 @RequestParam("user_password") String user_password,
                                 ModelAndView modelAndView) {

        System.out.println(user_id);
        System.out.println(user_password);

        User result = userService.getUser(user_id, user_password);
        String msg = "";
        if (result != null) {
            msg = String.format("반갑습니다 %s님", result.getUserName());
            modelAndView.addObject("user", result); // 세션을 설정한다.

        } else {
            msg = "아이디 혹은 비밀번호를 확인해주세요.";
        }
        modelAndView.addObject("msg", msg);
        modelAndView.setViewName("member/user/loginPro"); // msg출력을 위한 html 파일 거치기

        return modelAndView;
    }


    //비밀번호 찾기
    @GetMapping("member/findPw")
    public String findPw() {

        return "member/findPw";
    }


    // join 회원가입


    @GetMapping("member/join")
    public String join(@ModelAttribute("email") String email,
                       @ModelAttribute("gender") String gender,
                       @ModelAttribute("profile_img") String profile_img, Model model) {


        // 쿼리스트링으로 매개변수 넘기면 MemberVo로 바로 바인딩시킬 수 있지만 url에 데이터가 노출되는단점이 있기 때문에 redirectAttributes를 이용해 post방식으로 데이털르 전송.
        // 그떄 데이터를 받으려면 RequestParam은 당연히 안될거고(get방식의 쿼리스트링이니까) modelattribue 어노테이션을 이용해야한다.

        MemberVO memberVO = new MemberVO(email, gender, profile_img);
        model.addAttribute("kakao_user", memberVO);

        return "member/user/join";
    }


    @ResponseBody
    @PostMapping("member/joinAuth")
    public String joinAuth(@ModelAttribute("user_phone")String user_phone){

//
//        System.out.println("join_auth 메서드 실행");
//        Map<String,Object> result = smsService.authUser(user_phone);
//        SendSmsResponseDto ssrd = (SendSmsResponseDto) result.get("ssrd");
//        String ran_num = (String) result.get("ran_num");
//        System.out.println("ran_num" + ran_num);
//        System.out.println("srrd  : " + ssrd);
//        String response = "";
//        System.out.println(ran_num);
//        if(ssrd.getStatusCode().equals("202")){
//
//            response = ran_num;
//
//        }else{
//
//            response = "fail";
//
//        }


        String ran_num = "";
        int i = 0;

        while(i < 6){

            int ran = (int)(Math.random()*10);// 난수 생성.
            if(!ran_num.contains(ran+"")){

                ran_num += ran+"";
                i++;
            }

        }



        return ran_num;
    }

    // 회원가입 완료
    @PostMapping("member/joinPro")
    public String joinPro(UserForm userForm, @RequestParam("user_year") int user_year,
                          @RequestParam("user_month") int user_month,
                          @RequestParam("user_day") int user_day
    ) {


        System.out.println("user의 값 :" + userForm);
        // year, month ,day 값을 date타입으로 포매팅

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date.setYear(user_year - 1900);
        date.setMonth(user_month - 1);
        date.setDate(user_day);
        String formatted_date = sdf.format(date);
        System.out.println("formatted_ddate = " + formatted_date);
        System.out.println("date = " + date);
        userForm.setUser_date(date);

//        System.out.println("user_img2 : " + user_img2);

        String user_img = userForm.getUser_img(); // img 경로

        // 카카오로 가입한게 아니라면 img는 null일 것이다.
        if ( (user_img == null || user_img.equals("")  )  && userForm.getUser_gender().equals("M")) {

            userForm.setUser_img("default1.png"); // 남성일 경우 default1.png, 여성일 경우 default2.png설정

        } else if ( (user_img == null || user_img.equals("")  ) && userForm.getUser_gender().equals("F")) {

            userForm.setUser_img("default2.png"); // 남성일 경우 default1.png, 여성일 경우 default2.png설정
        } else if(user_img !=null || !user_img.equals("")){

            // 기본 프로필 이미지가 있을 경우에는 해당 url에 접속해 이미지 파일을 서버에 저장한다.

            String extension = user_img.substring(user_img.lastIndexOf(".")); // .을 포함한 확장자명

            String filename = userForm.getUser_id().split("@")[0] + extension; // 이메일 앞글자랑 확장자명까지 합친 파일명이 진짜 파일명이된다.
            System.out.println("새로 저장되는 user_img이름" + filename);
            userForm.setUser_img(filename);
            userService.saveFile(user_img, filename);

        }


        User user = userForm.toEntity();

        int join_result = userService.joinUser(user); // 회원가입 결과.

        String msg = join_result == 1 ? "회원가입이 완료됐습니다" : "아이디가 중복 됐습니다.";

//        modelAndView.setViewName("index");
//        modelAndView.addObject("msg",msg);


//        return "redirect:/joinSuccess?msg="+msg;
        return "community/home";
    }

    // 로그아웃
    @GetMapping("member/logout")

    public String logout(SessionStatus sessionStatus) {

        sessionStatus.setComplete(); // 세션삭제

        return "member/user/logout";
    }

    @GetMapping("member/mypage")
    public String mypage(HttpSession session, Model model) {


        User user = (User)session.getAttribute("user");; // @ModelAttribute로 받게되면 처음에 session 설정이 돼있지 않기 때문에 에러발생.
        System.out.println(user);
        if(user == null){ // 세션값이 없다면

            return "redirect:/";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = user.getUserDate();
        String[] formatted_date = sdf.format(date).split("-");
        String user_year = formatted_date[0];
        String user_month = formatted_date[1];
        String user_day = formatted_date[2];

        model.addAttribute("user_year", user_year);
        model.addAttribute("user_month", user_month);
        model.addAttribute("user_day", user_day);


        User newUser = userService.getUserById(user.getUserId());
        // session 정보를 최신화 해준다.
        // 세션에서 현재 가지고 있는 user값을 업데이트해준다.
        model.addAttribute("user",newUser);


        return "/member/user/mypage";
    }


    @PostMapping("member/mypageUpdate")
    public String mypageUpdate(
            @RequestParam("user_year") int user_year,
            @RequestParam("user_month") int user_month,
            @RequestParam("user_day") int user_day,

            @ModelAttribute("user") User session_user,
            UserForm userForm,
            Model model
    ) {


        // 기본키값을 넘겨줘야 save메서드에서 id값을 이용해 수정이 가능하다.


        User user = new User();
        Date date = new Date();
        date.setYear(user_year - 1900);
        date.setMonth(user_month - 1);
        date.setDate(user_day);


        // date세팅 제대로해야한다.
        user.setUserName(userForm.getUser_name());
        user.setUserDate(date);
        user.setUserPassword(userForm.getUser_password());
        user.setUserGender(userForm.getUser_gender());

        user.setUserNumber(session_user.getUserNumber());

        User result = userService.updateUser(user);

        model.addAttribute("user", result);
        System.out.println(user);
        System.out.println(result);


        return "redirect:/member/login";
    }


    @GetMapping("member/uploadProfile")

    public String uploadProfile() {


        return "member/user/uploadTest";
    }


    // 프로필 이미지 업로드 하는 url
    @PostMapping("member/uploadProfilePro")

    public String uploadProfilePro(MultipartHttpServletRequest request, Model model) {

        String re = "";
        boolean isUpload = false;
        String resourcesPath = "";

        try {
            ClassPathResource resource = new ClassPathResource("/static/file/profile_image"); // 빈 문자열로 생성
            File file = resource.getFile();
            resourcesPath = file.getAbsolutePath();

            System.out.println("Resources 폴더 경로: " + resourcesPath);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 실제 로직에서는 유저 아이디의 앞부분 (@스플릿)과 확장자명을 더해서 저장한다.


        // request 객체를 이용해서 여기서 파일업로드를 진행한다.
        MultipartFile profile_img = request.getFile("profile_img");
        String fileName = "/" + profile_img.getOriginalFilename();
        System.out.println("filename :  " + fileName);


        // user 세션값을 이용해 user_img 명을 가지고 오고(default아니면 본인 닉네임일 것)
        // 그 이미지 명을 현재 프로젝트의 build경로로 접근해 model에 저장한 후에
        // 다른 viewpage에서 보여지게 한다.

        // 유저 아이디(이메일)을 @로 분리시킨후 해당 앞자리 아이디_profile 이름을 가진.png로 저장한다.

        String filePath = resourcesPath + fileName;

        File file = new File(filePath);

        System.out.println(filePath);
        try {
            profile_img.transferTo(file);

        } catch (IOException e) {
            e.printStackTrace();

        }

        User user = new User();
        user.setUserImg(fileName);
        model.addAttribute("user", user);
        model.addAttribute("rp", resourcesPath);
        // 현재 user 세션값을 이용해서 user table의 user정보에서 image필드에 해당 파일명을 삽입한다.


        return "member/user/uploadTestPro";
    }


}
