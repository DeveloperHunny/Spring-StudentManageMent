package student.studentspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import student.studentspring.repository.JdbcTemplateStudentRepository;
import student.studentspring.repository.MemoryStudentRepository;
import student.studentspring.repository.StudentRepository;
import student.studentspring.service.StudentService;

import javax.sql.DataSource;

@Configuration
public class StudentConfig {

    private final DataSource dataSource;

    public StudentConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public StudentService studentService(){
        return new StudentService(studentRepository());
    }

    @Bean
    public StudentRepository studentRepository(){
        return new JdbcTemplateStudentRepository(dataSource);
    }
}
