package com.example.ChamaContributionSystem.Payment;

import com.example.ChamaContributionSystem.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByMember(Member member);

}
