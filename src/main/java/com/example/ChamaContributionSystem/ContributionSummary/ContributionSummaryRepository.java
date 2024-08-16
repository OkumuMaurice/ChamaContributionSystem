package com.example.ChamaContributionSystem.ContributionSummary;

import com.example.ChamaContributionSystem.Member.Member;
import com.example.ChamaContributionSystem.Payment.Payment;
import java.util.List;

public interface ContributionSummaryRepository {
    List<Payment> findByMember(Member member);
}
