package com.example.ChamaContributionSystem.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("record_payment")
    public ResponseEntity<Payment> recordPayment(@RequestParam String memberName,
                                                 @RequestBody Payment payment) {
        Payment recordedPayment = paymentService.recordPayment(memberName, payment);
        return new ResponseEntity<>(recordedPayment, HttpStatus.CREATED);
    }

    @GetMapping("/all_payments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @GetMapping("/by_member_name")
    public ResponseEntity<List<Map<String, Object>>> getPaymentsByMemberName(@RequestParam String memberName) {
        List<Payment> payments = paymentService.getPaymentsByMemberName(memberName);

        List<Map<String, Object>> paymentDetails = payments.stream()
                .map(payment -> {
                    Map<String, Object> paymentInfo = new HashMap<>();
                    paymentInfo.put("name", memberName);
                    paymentInfo.put("paymentDate", payment.getPaymentDate());
                    paymentInfo.put("paymentType", payment.getPaymentType());
                    paymentInfo.put("amount", payment.getAmount());
                    return paymentInfo;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(paymentDetails, HttpStatus.OK);
    }



}

