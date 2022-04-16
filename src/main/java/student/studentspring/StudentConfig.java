package student.studentspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import student.studentspring.repository.MemoryStudentRepository;
import student.studentspring.repository.StudentRepository;
import student.studentspring.service.StudentService;

@Configuration
public class StudentConfig {

    @Bean
    public StudentService studentService(){
        return new StudentService(studentRepository());
    }

    @Bean
    public StudentRepository studentRepository(){
        return new MemoryStudentRepository();
    }
}
