package com.example.valid_exception.dto;

import com.example.valid_exception.customvalidation.Brocoli;
import com.example.valid_exception.mark.ValidGroup1;
import com.example.valid_exception.mark.ValidGroup2;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class ValidTestDto {
    @Brocoli
    String name;

    @Email
    String email;

    @Positive(groups = ValidGroup1.class) // 그룹1로 선언
    int number1;

    @PositiveOrZero(groups = ValidGroup2.class) // 그룹2로 선언
    int number2;

    public ValidTestDto(String name, String email, int number1, int number2) {
        this.name = name;
        this.email = email;
        this.number1 = number1;
        this.number2 = number2;
    }

    @Override
    public String toString() {
        return "ValidTestDto{" +
                "name='" + name + '\'' +
                ", mail='" + email + '\'' +
                ", number1=" + number1 +
                ", number2=" + number2 +
                '}';
    }
}
