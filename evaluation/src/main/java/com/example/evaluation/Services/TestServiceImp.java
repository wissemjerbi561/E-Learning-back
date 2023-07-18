package com.example.evaluation.Services;

import com.example.evaluation.Entities.Test;
import com.example.evaluation.Repositories.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImp implements TestService{
    @Autowired
    TestRepo testRepo;

    @Override
    public Test saveTest(Test t) {
        return testRepo.save(t);
    }

    @Override
    public Test retrieveTest(Long idt) {
        return testRepo.findById(idt).get();
    }

    @Override
    public List<Test> retrieveAll() {
        List<Test> tests=(List<Test>)testRepo.findAll();
        return tests;
    }

    @Override
    public Test updatets(Long idt) {
        Test test=testRepo.findById(idt).get();
        return testRepo.save(test);
    }

    @Override
    public void delete(Long idt) {
testRepo.deleteById(idt);
    }
}
