package com.example.valid_exception.customvalidation;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // 이 어노테이션을 어디에 선언할 수 있는지 설정
@Retention(RetentionPolicy.RUNTIME) // 어노테이션의 유지범위
@Constraint(validatedBy = BrocoliValidator.class) // ConstraintValidator 등록
public @interface Brocoli {

    String message() default "{이름은 브로콜리로 시작해야 합니다.}"; // 검증에 실패할 경우 반환할 메시지

    Class [] groups() default {}; //유효성 검사를 사용하는 그룹으로 설정

    Class [] payload() default {}; // 사용자가 추가정보를 위해 전달하는 값
}
