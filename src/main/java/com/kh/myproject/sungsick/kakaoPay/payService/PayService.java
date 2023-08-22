package com.kh.myproject.sungsick.kakaoPay.payService;

import com.kh.myproject.sungsick.kakaoPay.payVO.KakaoPayApprovalVO;
import com.kh.myproject.sungsick.kakaoPay.payVO.KakaoPayReadyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class PayService {

    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;

    public KakaoPayReadyVO kakaoPayReady(){
        log.info("KakaoPayService => kakaoPayReady......................................... ");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid","TC0ONETIME");
        params.add("partner_order_id","1001");
        params.add("partner_user_id", "gorany");
        params.add("item_name", "갤럭시 S9");
        params.add("quantity", "1");
        params.add("total_amount", "2100");
        params.add("vat_amount","200");
        params.add("tax_free_amount", "100");
        params.add("approval_url", "http://localhost:8090/pay/success");    // 결제승인시 넘어갈 url
        params.add("cancel_url", "http://localhost:8090/pay/cancel");  // 결제취소시 넘어갈 url
        params.add("fail_url", "http://localhost:8090/pay/fail");         // 결제 실패시 넘어갈 url

        log.info("파트너주문아이디:"+ params.get("partner_order_id")) ;

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String,String>>(params,this.getHeaders()); //(parameters, this.getHeaders()
        // 외부url요청 통로 열기.
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/ready";
        // restTemplate으로 값을 보내고 받아온 KakaoPayReadyVO값 kakaoPayReadyVO 저장.
        kakaoPayReadyVO = restTemplate.postForObject(url, body, KakaoPayReadyVO.class);
        log.info("결제준비 응답객체: " + kakaoPayReadyVO);

            // 받아온 값 return
        return kakaoPayReadyVO;
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
    // header() 셋팅
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "31a5df416cc5ad95dd5dee5fdba74286"); // cbfe56d98ec364f4e7b331348437d0af
        // headers.add("Accept", MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE);
        headers.add("Content-type",  "application/x-www-form-urlencoded;charset=utf-8");

        return headers;
    }
}
