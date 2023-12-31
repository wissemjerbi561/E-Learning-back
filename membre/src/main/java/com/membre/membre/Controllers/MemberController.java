package com.membre.membre.Controllers;

import com.membre.membre.Entities.Member;
import com.membre.membre.Entities.Notification;
import com.membre.membre.Entities.Position;
import com.membre.membre.Repositories.MemberRepository;
import com.membre.membre.Repositories.PositionRepository;
import com.membre.membre.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value= "/api/member/member")
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
    @Autowired
    private PositionServiceImp positionServiceImp;

    public MemberController(EmailServiceImp emailService, MemberRepository memberRepository,
                            MemberServiceImp memberService) {
        this.emailService = emailService;
        this.memberService = memberService;
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
        String randomPassword = memberService.generateRandomPassword();
        System.out.println("password" + randomPassword);
        member.setPassword(randomPassword);
        emailService.sendmail(member.getUsername(), member.getEmail(), member.getPassword());
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

    @GetMapping("/user/{userId}")
    public Member getMemberByUserId(@PathVariable Long userId) {

        return memberRepository.findMemberByUserId(userId);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<Member> updateMember(@PathVariable Long memberId, @RequestBody Member member) {

        Member member1 = memberRepository.findById(memberId).orElse(null);

        member1.setMemberId(member.getMemberId());

        member1.setFirstName(member.getFirstName());
        member1.setLastName(member.getLastName());
        member1.setEmail(member.getEmail());
        member1.setPassword(member.getPassword());
        member1.setFinalNote(member.getFinalNote());
        member1.setGitLink(member.getGitLink());
        member1.setDriveLink(member.getDriveLink());

        Member updateMember = memberRepository.save(member1);
        return ResponseEntity.ok(updateMember);


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
    ) {
        // send mail method call
        emailService.sendmail(firstName, email, password);
        memberService.saveMemberToDB(file, firstName, lastName, email, password, finalNote, gitLink, driveLink);

    }

    @GetMapping
    public List<Member> getTutorMembers() {
        return memberService.getTutorMembers();
    }

    @GetMapping("/tuteuracademique")
    public List<Member> getTuteurAcademique() {
        return memberService.getTuteurAcademique();
    }

    @GetMapping("/apprenant")
    public List<Member> getApprenantMembers() {
        return memberService.getApprenantMembers();
    }

    @GetMapping("/count/apprenant")
    public long countMembersWithPositionApprenant() {
        return memberService.countMembersWithPositionApprenant();
    }


    @Autowired
    NotificationServiceImp notificationService;

    @PostMapping("/creatjje")
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    @GetMapping("/{code}/members")
    public List<Member> getPositionMemberByCode(@PathVariable String code) {
        return memberService.getPositionMemberByCode(code);
    }
    @PostMapping("/ajoutMember")
    public ResponseEntity<Member> ajoutMember (@RequestBody Member member){
        return ResponseEntity.ok(memberRepository.save(member));

    }
    @GetMapping("/apprenantPosition")
    public List<Position> getApprenantPositions () {
            return positionServiceImp.getApprenantPositions();
    }
    }



