package com.kh.myproject.api.kakaoPay.service;


import com.kh.myproject.api.kakaoPay.model.dto.KakaoPayApprovalVO;
import com.kh.myproject.api.kakaoPay.model.dto.KakaoPayReadyVO;
import com.kh.myproject.api.kakaoPay.model.entity.PaymentBill;
import com.kh.myproject.api.kakaoPay.repository.PayRepository;
import com.kh.myproject.member.user.model.dto.UserForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class PayService {

    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;

    @Autowired
    private PayRepository payRepository;

    public KakaoPayReadyVO kakaoPayReady() {


        log.info("KakaoPayService => kakaoPayReady......................................... ");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid","TC0ONETIME");
        params.add("partner_order_id", "1001");                           // 가맹점 주문번호 (주문 id로 쓰면 될듯)
        params.add("partner_user_id", "gorany");                          // 가맹점 회원 id (구매자 유저 id 쓰면 될듯)
        params.add("item_name", "갤럭시 S9");                              //상품명 (상품 id or name 쓰면될듯)
        params.add("quantity", "1");                                      //상품 수량 (딱히 쓸모는 없는데 0이면 렌트카 1이면 항공으로 써먹어도 될듯)
        params.add("total_amount", "2100");                               //상품 총액
        params.add("tax_free_amount", "0");                               //상품 비과세 금액 (필수지만 필요 없는 항목)
        params.add("approval_url", "http://localhost:8080/pay/success");  // 결제승인시 넘어갈 url
        params.add("cancel_url", "http://localhost:8080/pay/cancel");     // 결제취소시 넘어갈 url
        params.add("fail_url", "http://localhost:8080/pay/fail");         // 결제 실패시 넘어갈 url

        log.info("파트너주문아이디:" + params.get("partner_order_id"));

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, this.getHeaders()); //(parameters, this.getHeaders()
        // 외부url요청 통로 열기.
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/ready";
        // restTemplate으로 값을 보내고 받아온 KakaoPayReadyVO값 kakaoPayReadyVO 저장.
        kakaoPayReadyVO = restTemplate.postForObject(url, body, KakaoPayReadyVO.class);
        log.info("결제준비 응답객체: " + kakaoPayReadyVO);

        // 받아온 값 return
        return kakaoPayReadyVO;
    }

    // 결제 승인 메서드 ( 승인 완료시 필요한 데이터 담아서 저장하는 컨트롤러 or 서비스 호출)
    public KakaoPayApprovalVO rentcarInsert(String pg_token) {
        // 렌터카
        KakaoPayApprovalVO test = this.payApprove(pg_token);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        //        구매내역 저장 예시 (미리구현, 팀원과 상의후 데이터 저장)
        UserForm user = new UserForm();

        params.add("user_id", user.getUser_id());
        params.add("user_name", user.getUser_name());

        PaymentBill paymentBill = new PaymentBill();
        paymentBill.setUser_id(user.getUser_id());
        paymentBill.setUser_name(user.getUser_name());

        payRepository.save(paymentBill);

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, this.getHeaders());

        return test;
    }

    // header() 셋팅
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "31a5df416cc5ad95dd5dee5fdba74286"); // cbfe56d98ec364f4e7b331348437d0af
        // headers.add("Accept", MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return headers;
    }

    public KakaoPayApprovalVO payApprove(String pg_token) {
        log.info("KakaoPayInfoVO..................................................");
        log.info(".......................");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");
        params.add("pg_token", pg_token);
        params.add("total_amount", "2100");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, this.getHeaders());


        // 외부url 통신
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/approve";

        // 보낼 외부 url, 요청 메시지(header,parameter), 처리후 값을 받아올 클래스.
        kakaoPayApprovalVO = restTemplate.postForObject(url, body, KakaoPayApprovalVO.class);
        log.info("결제승인 응답객체: " + kakaoPayApprovalVO);



        return kakaoPayApprovalVO;
    }
}
