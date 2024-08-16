package com.example.ChamaContributionSystem.ContributionSummary;

import com.example.ChamaContributionSystem.Member.Member;
import com.example.ChamaContributionSystem.Member.MemberRepository;
import com.example.ChamaContributionSystem.Payment.Payment;
import com.example.ChamaContributionSystem.Payment.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContributionSummaryService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public List<ContributionSummary> getContributionsByMember(String memberName) {
        Member member = memberRepository.findByName(memberName)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // Fetching existing payments
        List<ContributionSummary> contributions = paymentRepository.findByMember(member).stream()
                .map(payment -> new ContributionSummary(
                        member.getName(),
                        payment.getAmount(),
                        payment.getPaymentType(),
                        payment.getPaymentDate()
                ))
                .collect(Collectors.toList());

        // Adding the registration fee of 2000
        contributions.add(new ContributionSummary(
                member.getName(),
                2000.0,
                "Registration Fee",
                member.getRegistrationDate()
        ));

        return contributions;
    }
    public List<Object[]> getMembersWithTotalContributions() {
        return memberRepository.findAll().stream()
                .map(member -> {
                    // Calculate the total payments for the member
                    double totalPayments = paymentRepository.findByMember(member).stream()
                            .mapToDouble(Payment::getAmount)
                            .sum();

                    // Add the registration fee (2000) to the total payments
                    double totalContributions = totalPayments + 2000;

                    return new Object[] {
                            member.getName(),
                            totalContributions
                    };
                })
                .collect(Collectors.toList());
    }
}
