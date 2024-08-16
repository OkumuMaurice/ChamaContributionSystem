package com.example.ChamaContributionSystem.ContributionSummary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContributionSummary {
    private String memberName;
    private Double amount;
    private String paymentType;
    private LocalDate paymentDate;
}
