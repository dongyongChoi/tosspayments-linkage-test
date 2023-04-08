package com.example.tosspaymentslinkage.controller;

import com.example.tosspaymentslinkage.dto.TossPayments;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Controller
@RequestMapping("/tosspayments")
public class TossPaymentsWindowController {

    @GetMapping
    public String tosspayments() {

        return "tosspaymentswindow/payment";
    }

//    @ResponseBody
    @GetMapping("/success")
    public String tossPaymentsSuccess(TossPayments tossPayments) throws InterruptedException {
        // 성공 정보에 대한 처리를 진행하면됨.
        // 값이 유효한 값인지
        // 금액, 아이디 등...
        log.info("결제 성공 정보");
        log.info("paymentKey={}", tossPayments.getPaymentKey());
        log.info("orderId={}", tossPayments.getOrderId());
        log.info("amount={}", tossPayments.getAmount());

        RestTemplate restTemplate = new RestTemplate();

        // 요청 url
        String url = "https://api.tosspayments.com/v1/payments/confirm";

        // header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic dGVzdF9za196WExrS0V5cE5BcldtbzUwblgzbG1lYXhZRzVSOg=1");

        HttpEntity<?> httpEntity = new HttpEntity<>(tossPayments, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class);

        log.info("response={}", response);

        // 이후 리다이렉트 처리?
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/fail")
    public String tossPaymentsFail() {

        return "결제 실패창";
    }

    @ResponseBody
    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
