package com.example.ChamaContributionSystem.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("register_member")
    public ResponseEntity<String> createMember(@RequestBody Member member) {
        memberService.createMember(member);
        return ResponseEntity.ok("Member successfully registered.");
    }

    @GetMapping("get_byId/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable Long id) {
        Optional<Member> member = memberService.getMemberById(id);
        if (member.isPresent()) {
            return ResponseEntity.ok(member.get());
        } else {
            return ResponseEntity.status(404).body("Member with ID " + id + " not found!");
        }
    }

    @GetMapping("get_all_members")
    public ResponseEntity<?> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        if (members.isEmpty()) {
            return ResponseEntity.status(404).body("No members found!");
        }
        return ResponseEntity.ok(members);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateMember(@PathVariable Long id, @RequestBody Member memberDetails) {
        memberService.updateMember(id, memberDetails);
        return ResponseEntity.ok("Member details successfully updated.");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        boolean isRemoved = memberService.deleteMember(id);
        if (!isRemoved) {
            return ResponseEntity.status(404).body("Member with ID " + id + " not found!");
        }
        return ResponseEntity.ok("Member successfully deleted.");
    }
}
