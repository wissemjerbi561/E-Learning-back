package com.membre.membre.Controllers;

import com.membre.membre.Entities.Member;
import com.membre.membre.Entities.Position;
import com.membre.membre.Repositories.MemberRepository;
import com.membre.membre.Repositories.PositionRepository;
import com.membre.membre.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value= "/member")
@CrossOrigin(value = "*")
public class MemberController {
    @Autowired
    private JavaMailSender mailSenderObj;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    private final EmailService emailService;
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    MemberRepository memberRepository;

    public MemberController( EmailService emailService, MemberRepository memberRepository) {
        this.emailService = emailService;

        this.memberRepository = memberRepository;

    }

    @PostMapping("/create")
    public ResponseEntity<Member> saveMember(@RequestBody Member member) {
        if (member.getLstPositionId() != null) {
            List<Position> ps = new ArrayList<>();
            for (Long id : member.getLstPositionId()) {
                ps.add(positionRepository.findById(id).orElse(null));
            }
            member.setPositions(ps);
        }
        // send mail method call
        emailService.sendmail(member);
        return ResponseEntity.ok(memberRepository.save(member));



    }

    @GetMapping("/members")
    public ResponseEntity getAllMembers() {

        return ResponseEntity.ok(this.memberRepository.findAll());
    }


    @GetMapping("/{memberId}")
    public Member getMemberById(@PathVariable Long memberId) {

        return memberRepository.findById(memberId).orElse(null);
    }

    @PutMapping("/update/{id}")
    public void updateMember(@PathVariable Long id, @RequestBody Member member) {
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

    @DeleteMapping("/delete/{memberId}")
    public void deleteMemberById(@PathVariable Long memberId) {

        memberRepository.deleteById(memberId);
    }

}
