package org.example;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    public MemberResponse save(MemberRequest request){
        return new MemberResponse(1L, "testName", 20);
    }

    public MemberResponses findAll() {
        return new MemberResponses(
                List.of(
                        new MemberResponse(1L, "testName1", 20),
                        new MemberResponse(2L, "testName2", 30)
            )
        );
    }

    public MemberResponse findByName(String name) {
        return new MemberResponse(1L, name,20);
    }

    public MemberResponse findById(Long id) {
        return new MemberResponse(id, "testName", 20);
    }
}
