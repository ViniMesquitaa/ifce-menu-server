package dev.com.menu.ifce.infra.security;

import dev.com.menu.ifce.entity.Student;
import dev.com.menu.ifce.repositorie.StudentRepo;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private final StudentRepo studentRepo;

    @Autowired
    public CustomUserDetailService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = this.studentRepo.findByEnrolmentNumber(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return new org.springframework.security.core.userdetails.User(
                student.getEnrolmentNumber(),
                student.getPassword(),
                new ArrayList<>()
        );
    }
}
