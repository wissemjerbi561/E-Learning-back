package com.insy2s.KeyCloakAuth.ServiceMail;

import com.insy2s.KeyCloakAuth.ServiceMail.MemberService;
import com.insy2s.KeyCloakAuth.model.User;
import com.insy2s.KeyCloakAuth.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class MemberServiceImp implements MemberService {

    @Autowired
    UserRepository memberRepository;

    @Override
    public void saveMemberToDB( MultipartFile file, String firstName, String lastName, String email, String password,
                               float finalNote, String gitLink, String driveLink){
        User m = new User();

    try{

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a valid file");
        }

        m.setFirstname(firstName);
        m.setLastname(lastName);
        m.setEmail(email);
        m.setPassword(password);

         memberRepository.save(m);

    }catch (Exception e) {
            e.printStackTrace();
        }
    }



}
