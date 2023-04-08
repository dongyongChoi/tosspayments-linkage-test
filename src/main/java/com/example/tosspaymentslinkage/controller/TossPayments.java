package com.example.tosspaymentslinkage.controller;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Data
@Component
public class TossPayments {

    private String paymentKey;
    private String orderId;
    private String amount;

}
