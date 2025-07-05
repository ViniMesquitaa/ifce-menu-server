package dev.com.menu.ifce.entity.responseDTO;

import java.util.UUID;

public record StudentRegisterResponseDto(
        UUID id,
        String fullName,
        String enrolmentNumber,
        String course,
        String token
) {}
