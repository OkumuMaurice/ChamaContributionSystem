package com.example.ChamaContributionSystem.Payment;

import com.example.ChamaContributionSystem.Member.Member;
import com.example.ChamaContributionSystem.Member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Payment recordPayment(String memberName, Payment payment) {
        Member member = memberRepository.findByName(memberName)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        payment.setMember(member);
        payment.setPaymentDate(LocalDate.now());

        payment.addContribution();
        memberRepository.save(member);
        return paymentRepository.save(payment);
    }


    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Retrieve payments by a given member's name
    public List<Payment> getPaymentsByMemberName(String memberName) {
        Member member = memberRepository.findByName(memberName)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        return paymentRepository.findByMember(member);
    }


}
