package com.example.cours.Services;

import com.example.cours.Entities.ChoixUpdateRequest;
import com.example.cours.Entities.Test;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface TestService {
    public Test saveTest(Test t);
    public Test retrieveTest(Long idt);
    public List<Test> retrieveAll();
    public Test updatets(Long idt);
    public void delete(Long idt);
    public Test passTest(Long idt, List<ChoixUpdateRequest> choixUpdates);
    public Test passTestWithSession(Long idTest, List<ChoixUpdateRequest> choixUpdates, HttpSession session);
    public Test corresctionTest(Long idTest);
}
