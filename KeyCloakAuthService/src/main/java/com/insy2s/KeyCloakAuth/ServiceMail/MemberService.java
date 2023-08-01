package com.insy2s.KeyCloakAuth.ServiceMail;

import org.springframework.web.multipart.MultipartFile;

public interface MemberService {
    public void saveMemberToDB(MultipartFile file, String firstName, String lastName, String email, String password,
                               float finalNote, String gitLink, String driveLink);
}
