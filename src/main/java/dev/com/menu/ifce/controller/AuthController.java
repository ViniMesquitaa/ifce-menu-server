package dev.com.menu.ifce.controller;

import dev.com.menu.ifce.entity.Student;
import dev.com.menu.ifce.entity.requestDTO.StudentLoginDto;
import dev.com.menu.ifce.entity.requestDTO.StudentRegisterDto;
import dev.com.menu.ifce.entity.responseDTO.StudentDto;
import dev.com.menu.ifce.entity.responseDTO.StudentRegisterResponseDto;
import dev.com.menu.ifce.infra.security.TokenService;
import dev.com.menu.ifce.repositorie.StudentRepo;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final StudentRepo studentRepo;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    AuthController(StudentRepo studentRepo, PasswordEncoder passwordEncoder) {
        this.studentRepo = studentRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = new TokenService();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid StudentLoginDto studentLoginDto) {
        Student student = studentRepo.findByEnrolmentNumber(studentLoginDto.enrolmentNumber())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (passwordEncoder.matches(studentLoginDto.password(), student.getPassword())) {
            String token = tokenService.generateToken(student);
            StudentDto response = new StudentDto(
                    student.getId(),
                    student.getFullName(),
                    student.getEnrolmentNumber(),
                    student.getCourse(),
                    token
            );
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Matrícula ou senha inválidos");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid StudentRegisterDto body) {
        Optional<Student> existingStudent = studentRepo.findByEnrolmentNumber(body.enrolmentNumber());

        if (existingStudent.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Matrícula já registrada");
        }

        Student newStudent = new Student();
        newStudent.setId(UUID.randomUUID());
        newStudent.setFullName(body.fullName());
        newStudent.setEnrolmentNumber(body.enrolmentNumber());
        newStudent.setCourse(body.course());
        newStudent.setPassword(passwordEncoder.encode(body.password()));

        studentRepo.save(newStudent);

        String token = tokenService.generateToken(newStudent);

        StudentRegisterResponseDto response = new StudentRegisterResponseDto(
                newStudent.getId(),
                newStudent.getFullName(),
                newStudent.getEnrolmentNumber(),
                newStudent.getCourse(),
                token
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
