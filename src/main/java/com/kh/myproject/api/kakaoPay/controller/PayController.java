package com.kh.myproject.api.kakaoPay.controller;

import com.kh.myproject.api.kakaoPay.model.dto.KakaoPayApprovalVO;
import com.kh.myproject.api.kakaoPay.model.dto.KakaoPayReadyVO;
import com.kh.myproject.api.kakaoPay.service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URISyntaxException;

@Slf4j
@RestController
@RequiredArgsConstructor
@SessionAttributes({"tid", "order"}) // 세션에 저장된 값 탐색
public class PayController {

    private final PayService payService;

    @GetMapping("/pay/payButton")
    public ModelAndView payButton() {
        ModelAndView payButton = new ModelAndView();
        payButton.setViewName("pay/payButton");
        return payButton;
    }

    @GetMapping("/pay/paymentPage")
    public ModelAndView paymentPage() {
        ModelAndView paymentPage = new ModelAndView();
        paymentPage.setViewName("pay/paymentPage");
        return paymentPage;
    }

    // 결제요청
    @GetMapping("/kakaoPay")
    // RedirectView 형식으로 html에서 카카오 api 호출시 CORS오류 (보안정책이라고 함). @ResponseBody로 POST 캡슐화 후 readyResponse 직접 호출하니 해결됨.
    public @ResponseBody KakaoPayReadyVO kakaoPay() {

        log.info("kakaoPay post.....................................");
        //KakaoPayReadyVO kakaoPayReadyVO = kakaopay.kakaoPayReady();
        KakaoPayReadyVO readyResponse = payService.kakaoPayReady();
        log.info(".........................결제고유 번호 : " + readyResponse.getTid());

        return readyResponse;
    }

    // 결제 승인요청
    @GetMapping("/pay/success")
    public ModelAndView kakaoPayCompleted(@RequestParam("pg_token") String pg_token, Model model) throws URISyntaxException {
        log.info("kakaoPaySuccess get......................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        // 카카오 결재 요청하기
        KakaoPayApprovalVO approveResponse = payService.rentcarInsert(pg_token);
        model.addAttribute("info", approveResponse);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pay/success");
        log.info("info : " + approveResponse);
        // 5. payment 저장
        //	orderNo, payMathod, 주문명.
        // - 카카오 페이로 넘겨받은 결재정보값을 저장.
//        Payment payment = Payment.builder()
//                .paymentClassName(approveResponse.getItem_name())
//                .payMathod(approveResponse.getPayment_method_type())
//                .payCode(tid)
//                .build();
//
//        orderService.saveOrder(order,payment);

        return modelAndView;
    }

    // 결제 취소시 실행 url
    @GetMapping("/pay/cancel")
    public ModelAndView payCancel() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/pay/cancel");
        return modelAndView;
    }

    // 결제 실패시 실행 url
    @GetMapping("/pay/fail")
    public ModelAndView payFail() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/pay/fail");
        return modelAndView;
    }

    @GetMapping("/pay/test11")
    public ModelAndView successFront() {
        ModelAndView successFront = new ModelAndView();
        successFront.setViewName("/pay/test11");
        return successFront;
    }

}



