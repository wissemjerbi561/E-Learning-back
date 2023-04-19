package com.membre.membre.Controllers;

import com.membre.membre.Entities.Evaluation;
import com.membre.membre.Entities.Member;
import com.membre.membre.Repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= "/member")
@CrossOrigin(value = "*")
public class MemberController {
    @Autowired
    MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository){

        this.memberRepository=memberRepository;
    }
    @PostMapping("/create")
    public ResponseEntity<Member> saveMember(@RequestBody Member member){

        return ResponseEntity.ok(memberRepository.save(member));
    }
    @GetMapping("/members")
    public ResponseEntity getAllMembers(){

        return  ResponseEntity.ok(this.memberRepository.findAll());
    }


    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id){

        return  memberRepository.findById(id).orElse(null);
    }
    @PutMapping("/update/{id}")
    public void  updateMember(@PathVariable Long id,@RequestBody Member member) {
        Member member1 = memberRepository.findById(id).orElse(null);
        if (member1 != null) {
            member1.setMemberId(member.getMemberId());
            member1.setFirstName(member.getFirstName());
            member1.setLastName(member.getLastName());
            member1.setEmail(member.getEmail());
            member1.setPassword(member.getPassword());
            member1.setFinalNote(member.getFinalNote());
            member1.setGitLink(member.getGitLink());
            member1.setDriveLink(member.getDriveLink());

            memberRepository.save(member1);
        }
    }
    @DeleteMapping("/delete/{id}")
    public void deleteMemberById(@PathVariable Long id){

        memberRepository.deleteById(id);}
}
