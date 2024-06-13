package org.example;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record MemberRequest(
        @NotNull @Length(min = 1, max = 50) String name,
        @Positive int age
) {
}
