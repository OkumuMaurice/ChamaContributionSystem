package com.example.ChamaContributionSystem.Payment;


import com.example.ChamaContributionSystem.Member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate paymentDate;
    private String paymentType; // (contribution,fine )
    private Double amount;

    @ManyToOne
    private Member member;

    public void addContribution() {

    }
}
