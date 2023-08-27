package com.kh.myproject.api.sensapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.myproject.api.sensapi.ApplicationNaverSENS;
import com.kh.myproject.api.sensapi.vo.MessagesRequestDto;
import com.kh.myproject.api.sensapi.vo.SendSmsResponseDto;
import com.kh.myproject.api.sensapi.vo.SmsRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@Component
public class SmsService {

    private final ApplicationNaverSENS applicationNaverSENS;
    
    public SendSmsResponseDto sendSms(String recipientPhoneNumber, String content) throws ParseException, JsonProcessingException, UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException {
        Long time = System.currentTimeMillis();
        List<MessagesRequestDto> messages = new ArrayList<>();
        // 보내는 사람에게 내용을 보냄.
        messages.add(new MessagesRequestDto(recipientPhoneNumber,content)); // content부분이 내용임

        // 전체 json에 대해 메시지를 만든다.
        SmsRequestDto smsRequestDto = new SmsRequestDto("SMS", "COMM", "82", applicationNaverSENS.getFrom(), "MangoLtd", messages);

        // 쌓아온 바디를 json 형태로 변환시켜준다.
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(smsRequestDto);

        // 헤더에서 여러 설정값들을 잡아준다.
        HttpHeaders headers =new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", applicationNaverSENS.getAccesskey());

        // 제일 중요한 signature 서명하기.
        String sig = makeSignature(time); // 시그니처를 생성할떄 사용하는 시간과 요청했을 때의 시간이 같아야하기 때문에 한번 생성해놓은 time변수를 사용한다.
        System.out.println("sig -> " + sig);
        headers.set("x-ncp-apigw-signature-v2", sig);

        // 위에서 조립한 jsonBody와 헤더를 조립한다.
        HttpEntity<String> body = new HttpEntity<>(jsonBody, headers);
        System.out.println(body.getBody());

        // restTemplate로 post 요청을 보낸다. 별 일 없으면 202 코드 반환된다.
        RestTemplate restTemplate = new RestTemplate();
        SendSmsResponseDto sendSmsResponseDto = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+applicationNaverSENS.getServiceid()+"/messages"), body, SendSmsResponseDto.class);
        System.out.println(sendSmsResponseDto.getStatusCode());

        return sendSmsResponseDto;
    }

    public String makeSignature(Long time) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
        String space = " ";					// one space
        String newLine = "\n";					// new line
        String method = "POST";					// method
        String url = "/sms/v2/services/"+applicationNaverSENS.getServiceid()+"/messages";	// url (include query string)
        String timestamp = time.toString();			// current timestamp (epoch)
        String accessKey = applicationNaverSENS.getAccesskey();			// access key id (from portal or Sub Account)
        String secretKey = applicationNaverSENS.getSecretkey();

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;
    }


    public Map<String,Object> authUser(String phone_number){

        // 내용은 임의의 난수를 생성해서 보내준다.

        String ran_num = "";
        int i = 0;

        while(i < 6){

            int ran = (int)(Math.random()*10);// 난수 생성.
            if(!ran_num.contains(ran+"")){

                ran_num += ran+"";
                i++;
            }

        }

        SendSmsResponseDto ssrd = null;
        Map<String, Object> result = new HashMap<>();
        String content = String.format("고객님의 인증번호는 %s입니다.",ran_num);
        try {
            ssrd = sendSms(phone_number,content);
            result.put("ssrd",ssrd);
            result.put("ran_num",ran_num);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
