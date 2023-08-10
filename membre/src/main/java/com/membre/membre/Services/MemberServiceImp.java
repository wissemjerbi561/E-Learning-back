package com.membre.membre.Services;

import com.membre.membre.Entities.Member;
import com.membre.membre.Repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class MemberServiceImp implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public void saveMemberToDB( MultipartFile file, String firstName, String lastName, String email, String password,
                               float finalNote, String gitLink, String driveLink){
        Member m = new Member();

    try{

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a valid file");
        }
        try {
            m.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        m.setFirstName(firstName);
        m.setLastName(lastName);
        m.setEmail(email);
        m.setPassword(password);
        m.setFinalNote(finalNote);
        m.setGitLink(gitLink);
        m.setDriveLink(driveLink);

         memberRepository.save(m);

    }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Member> getTutorMembers() {
        return memberRepository.findByPositionCode("PRO");
    }
    public List<Member> getTuteurAcademique() {
        return memberRepository.findByPositionCode("ACAD");
    }
    public List<Member> getApprenantMembers() {
        return memberRepository.findByPositionCode("APP");
    }

    public long countMembersWithPositionApprenant() {
        return memberRepository.countByPositionNameApprenant();
    }
    public static String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();

        int length = 30; // Longueur du mot de passe souhaitée

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
