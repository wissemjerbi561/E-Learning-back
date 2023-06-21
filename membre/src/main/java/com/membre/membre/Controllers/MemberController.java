package com.membre.membre.Controllers;

import com.membre.membre.Entities.Member;
import com.membre.membre.Entities.Position;
import com.membre.membre.Repositories.MemberRepository;
import com.membre.membre.Repositories.PositionRepository;
import com.membre.membre.Services.EmailServiceImp;
import com.membre.membre.Services.MemberServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value= "/member/member")
@CrossOrigin(value = "*")
public class MemberController {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    private final EmailServiceImp emailService;
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    private MemberServiceImp memberService;

    public MemberController(EmailServiceImp emailService, MemberRepository memberRepository,
                            MemberServiceImp memberService) {
        this.emailService = emailService;
        this.memberService=memberService;
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
        emailService.sendmail(member.getFirstName(), member.getEmail(), member.getPassword());
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
    @PostMapping("/addM")
    public void saveMember(@RequestParam("file") MultipartFile file, @RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName, @RequestParam("email") String email,
                           @RequestParam("password") String password, @RequestParam("finalNote") float finalNote,
                           @RequestParam("gitLink") String gitLink, @RequestParam("driveLink") String driveLink
                             ){
            // send mail method call
            emailService.sendmail(firstName,email,password);
            memberService.saveMemberToDB(file,firstName,lastName,email,password,finalNote,gitLink,driveLink);

        }
    @GetMapping
    public List<Member> getTutorMembers() {
        return memberService.getTutorMembers();
    }
    @GetMapping("apprenant")
    public List<Member> getApprenantMembers() {
        return memberService.getApprenantMembers();
    }

    @GetMapping("/count/apprenant")
    public long countMembersWithPositionApprenant() {
        return memberService.countMembersWithPositionApprenant();
    }


}



