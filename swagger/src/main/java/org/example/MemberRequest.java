package org.example;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import org.springdoc.api.annotations.ParameterObject;


@Schema(name = "신규 멤버 request", description = "신규 멤버 관련 정보입니다.")
@ParameterObject
public record MemberRequest(
        @Schema(description = "신규 멤버의 이름입니다.", example = "신규 멤버 이름", type = "string")
        @NotNull @Length(min = 1, max = 50) String name,
        @Schema(description = "신규 멤버 나이", example = "10", type="integer")
        @Positive int age
) {
}
