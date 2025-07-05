package dev.com.menu.ifce.service;

import dev.com.menu.ifce.entity.Student;
import dev.com.menu.ifce.entity.requestDTO.StudentUpdateRequest;
import dev.com.menu.ifce.entity.responseDTO.StudentMeResponse;
import dev.com.menu.ifce.repositorie.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepo studentRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StudentService(StudentRepo studentRepo, PasswordEncoder passwordEncoder) {
        this.studentRepo = studentRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Optional<StudentMeResponse> findById(UUID id) {
        return studentRepo.findById(id)
                .map(student -> new StudentMeResponse(
                        student.getId(),
                        student.getFullName(),
                        student.getEnrolmentNumber(),
                        student.getCourse()
                ));
    }

    @Transactional
    public Student updateStudent(UUID id, StudentUpdateRequest request) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado"));

        student.setFullName(request.fullName());
        student.setCourse(request.course());

        if (request.password() != null && !request.password().isBlank()) {
            String encodedPassword = passwordEncoder.encode(request.password());
            student.setPassword(encodedPassword);
        }

        return studentRepo.save(student);
    }
}
