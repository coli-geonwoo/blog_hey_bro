package org.example;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.customannotation.SwaggerBadRequest;
import org.example.customannotation.SwaggerOk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "member", description = "member 관련 API")
@RestController
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping("/member")
    @Operation(requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = MemberRequest.class))))
    @SwaggerOk(summary = "회원 등록", description = "신규 회원 정보 반환")
    @SwaggerBadRequest(description = "요청 정보가 잘못되었을 때")
    ResponseEntity<MemberResponse> saveMember(@org.springframework.web.bind.annotation.RequestBody MemberRequest request) {
        MemberResponse memberResponse = memberService.save(request);
        return ResponseEntity.ok(memberResponse);
    }

    @GetMapping("/member")
    @Operation(summary = "전체 멤버 조회", description = "전체 멤버 정보 반환")
    @ApiResponse(responseCode = "200", description = "전체 회원 정보입니다.",
                    content = @Content(schema = @Schema(implementation = MemberResponses.class)))
    ResponseEntity<MemberResponses> getAllMembers() {
        MemberResponses all = memberService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/member-name")
    @SwaggerOk(summary = "이름으로 멤버 조회", description = "일치하는 멤버 반환")
    @SwaggerBadRequest(description = "이름과 일치하는 멤버가 없을 때")
    ResponseEntity<MemberResponse> getMemberByName(
            @Parameter(description = "조회할 멤버 이름", required = true)
            @RequestParam(value = "name") String name
    ) {
        MemberResponse member = memberService.findByName(name);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/member/{id}")
    @SwaggerOk(summary="아이디로 멤버 조회", description = "일치하는 멤버 반환")
    @SwaggerBadRequest(description = "id와 일치하는 멤버가 없을 때")
    ResponseEntity<MemberResponse> findById(
            @Parameter(description = "조회할 멤버 아이디", required = true)
            @PathVariable(name = "id") Long id) {
        MemberResponse member = memberService.findById(id);
        return ResponseEntity.ok(member);

    }
}
