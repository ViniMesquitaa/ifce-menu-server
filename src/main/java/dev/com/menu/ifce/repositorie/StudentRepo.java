package dev.com.menu.ifce.repositorie;

import dev.com.menu.ifce.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepo extends CrudRepository<Student, UUID> {
    Optional<Student> findByEnrolmentNumber(String enrolmentNumber);
}
