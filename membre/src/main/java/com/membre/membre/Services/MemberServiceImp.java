package com.membre.membre.Services;

import com.membre.membre.Entities.Member;
import com.membre.membre.Repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

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
}
