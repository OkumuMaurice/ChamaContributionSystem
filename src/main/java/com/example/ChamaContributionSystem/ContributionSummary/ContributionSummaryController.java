package com.example.ChamaContributionSystem.ContributionSummary;

import com.example.ChamaContributionSystem.Payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ContributionSummaryController {

    @Autowired
    private ContributionSummaryService contributionSummaryService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/member_contributions")
    public List<ContributionSummary> getMemberContributions(@RequestParam String memberName) {
        return contributionSummaryService.getContributionsByMember(memberName);
    }

    @GetMapping("/all_contributions_summary")
    public List<Object[]> getMembersWithTotalContributions() {
        return contributionSummaryService.getMembersWithTotalContributions();
    }
}
