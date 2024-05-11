package com.example.valid_exception.controller;

import com.example.valid_exception.dto.ValidTestDto;
import com.example.valid_exception.mark.ValidGroup1;
import com.example.valid_exception.mark.ValidGroup2;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ValidationController {

    @PostMapping("/")
    public ResponseEntity<String> checkValidationByValid(
            @RequestBody @Valid ValidTestDto validTestDto) {
        return ResponseEntity.ok(validTestDto.toString());
    }

    // 그룹 지정 안함 => groups 속성을 지정하지 않은 필드만 검증
    @PostMapping("/validation")
    public ResponseEntity<String> checkValidationByValidated (
            @RequestBody @Validated ValidTestDto validTestDto) {
        return ResponseEntity.ok(validTestDto.toString());
    }

    // 그룹1만 검증
    @PostMapping("/validation/group1")
    public ResponseEntity<String> checkValidationGroup1 (
            @RequestBody @Validated(ValidGroup1.class) ValidTestDto validTestDto) {
        return ResponseEntity.ok(validTestDto.toString());
    }

    // 그룹2만 검증
    @PostMapping("/validation/group2")
    public ResponseEntity<String> checkValidationGroup2 (
            @RequestBody @Validated(ValidGroup2.class) ValidTestDto validTestDto) {
        return ResponseEntity.ok(validTestDto.toString());
    }

    // 그룹 1,2 모두 검증
    @PostMapping("/validation/all-group")
    public ResponseEntity<String> checkValidationAllGroup (
            @RequestBody
            @Validated({ValidGroup1.class, ValidGroup2.class}) ValidTestDto validTestDto) {
        return ResponseEntity.ok(validTestDto.toString());
    }
}
