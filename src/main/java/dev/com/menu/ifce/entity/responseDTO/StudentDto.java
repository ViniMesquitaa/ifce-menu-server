package dev.com.menu.ifce.entity.responseDTO;

import java.util.UUID;

public record StudentDto(UUID id, String fullName, String enrolmentNumber, String course, String token) {

}
