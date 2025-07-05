package dev.com.menu.ifce.entity.requestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StudentRegisterDto(
        @NotBlank
        String fullName,

        @NotBlank
        @Size(min = 14, max = 14)
        String enrolmentNumber,
        @NotBlank
        String password,

        @NotBlank
        String course) {
}
