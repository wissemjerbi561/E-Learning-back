package com.example.evaluation.Services;

import com.example.evaluation.Entities.Test;

import java.util.List;

public interface TestService {
    public Test saveTest(Test t);
    public Test retrieveTest(Long idt);
    public List<Test> retrieveAll();
    public Test updatets(Long idt);
    public void delete(Long idt);
}
