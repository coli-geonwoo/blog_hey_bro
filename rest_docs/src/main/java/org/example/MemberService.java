package org.example;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    public MemberResponse save(MemberRequest request){
        return new MemberResponse(1L, "testName", 20);
    }

    public MemberResponses findAll() {
        return null;
    }

    public MemberResponse findByName(String name) {
        return null;
    }

    public MemberResponse findById(Long id) {
        return null;
    }
}
