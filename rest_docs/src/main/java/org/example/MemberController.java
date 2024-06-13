package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping("/member")
    ResponseEntity<MemberResponse> saveMember(@RequestBody MemberRequest request) {
        MemberResponse memberResponse = memberService.save(request);
        return ResponseEntity.ok(memberResponse);
    }

    @GetMapping("/member")
    ResponseEntity<MemberResponses> getAllMembers() {
        MemberResponses all = memberService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/member-name")
    ResponseEntity<MemberResponse> getMemberByName(@RequestParam(value = "name") String name) {
        MemberResponse member = memberService.findByName(name);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/member/{id}")
    ResponseEntity<MemberResponse> findById(@PathVariable(name = "id") Long id) {
        MemberResponse member = memberService.findById(id);
        return ResponseEntity.ok(member);

    }
}
