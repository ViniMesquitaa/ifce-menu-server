package dev.com.menu.ifce.controller;

import dev.com.menu.ifce.entity.Student;
import dev.com.menu.ifce.entity.requestDTO.StudentUpdateRequest;
import dev.com.menu.ifce.entity.responseDTO.StudentMeResponse;
import dev.com.menu.ifce.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentMeResponse> getStudentById(@PathVariable UUID id) {
        return studentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentMeResponse> updateStudent(
            @PathVariable UUID id,
            @RequestBody StudentUpdateRequest request) {
        Student updated = studentService.updateStudent(id, request);
        StudentMeResponse dto = new StudentMeResponse(
                updated.getId(),
                updated.getFullName(),
                updated.getEnrolmentNumber(),
                updated.getCourse()
        );
        return ResponseEntity.ok(dto);
    }

}
