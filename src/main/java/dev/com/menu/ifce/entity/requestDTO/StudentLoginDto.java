package dev.com.menu.ifce.entity.requestDTO;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record StudentLoginDto(UUID id,
                              @NotBlank
                              String enrolmentNumber,
                              @NotBlank
                              String password) {
}
