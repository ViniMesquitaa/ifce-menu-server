package dev.com.menu.ifce.entity.responseDTO;

import java.util.UUID;

public record StudentMeResponse(UUID id,String fullName, String enrolmentNumber, String course) {
}
