package student.studentspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import student.studentspring.repository.JdbcTemplateStudentRepository;
import student.studentspring.repository.JpaStudentRepository;
import student.studentspring.repository.StudentRepository;
import student.studentspring.service.StudentService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class StudentConfig {

    private final DataSource dataSource;
    private final EntityManager em;

    public StudentConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public StudentService studentService(){
        return new StudentService(studentRepository());
    }

    @Bean
    public StudentRepository studentRepository(){
        return new JpaStudentRepository(em);
    }
}
