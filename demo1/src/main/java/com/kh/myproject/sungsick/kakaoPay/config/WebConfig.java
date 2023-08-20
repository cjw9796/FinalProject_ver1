//package com.kh.myproject.sungsick.kakaoPay.config;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.List;
//
//@Configuration
//@RequiredArgsConstructor
//public class WebConfig implements WebMvcConfigurer {
// // private final LoginUserArgumentResolver loginUserArgumentResolver;
//
//  @Override
//  public void addCorsMappings(CorsRegistry registry) {
//    registry.addMapping("/**")
//        .allowedOrigins("http://localhost:3000","http://172.20.10.3:3000")
//        .allowedMethods("GET","POST","DELETE","PUT")
//        .allowCredentials(true);
//  }
//
//  /*
//  * Method : addArgumentResolvers
//  * describe :
//  * LoginUserArgumentResolver가 스프링에서 인식될 수 있도록 WebMvcConfigurer에 추가
//  * */
//  /*@Override
//  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
//    argumentResolvers.add(loginUserArgumentResolver);
//  }*/
//}