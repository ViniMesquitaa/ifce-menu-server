package dev.com.menu.ifce.entity.responseDTO;

import java.util.UUID;

public record StudentResponseLoginDto(UUID id, String fullName, String token) {
}
