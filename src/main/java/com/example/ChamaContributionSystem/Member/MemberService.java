package com.example.ChamaContributionSystem.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member updateMember(Long id, Member memberDetails) {
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        if (memberDetails.getName() != null) {
            existingMember.setName(memberDetails.getName());
        }
        if (memberDetails.getGender() != null) {
            existingMember.setGender(memberDetails.getGender());
        }
        if (memberDetails.getPhoneNumber() != null) {
            existingMember.setPhoneNumber(memberDetails.getPhoneNumber());
        }
        if (memberDetails.getEmail() != null) {
            existingMember.setEmail(memberDetails.getEmail());
        }
        if (memberDetails.getRegistrationDate() != null) {
            existingMember.setRegistrationDate(memberDetails.getRegistrationDate());
        }

        return memberRepository.save(existingMember);
    }

    public boolean deleteMember(Long id) {
        Optional<Member> existingMember = memberRepository.findById(id);
        if (existingMember.isPresent()) {
            memberRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
