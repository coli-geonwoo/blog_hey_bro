package org.example;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="member", description = "멤버")
public record MemberResponse (
        @Schema(name="id", description = "멤버 아이디", example = "1")
        long id,
        @Schema(name = "name", description = "멤버 이름", example = "memberName")
        String name,
        @Schema(name = "age", description = "멤버의 나이", example = "10")
        int age
){
}
