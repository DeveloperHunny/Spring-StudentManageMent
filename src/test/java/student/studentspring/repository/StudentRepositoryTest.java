package student.studentspring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import student.studentspring.domain.Student;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class StudentRepositoryTest {
    private MemoryStudentRepository repository = new MemoryStudentRepository();

    @AfterEach
    public void afterEach(){
        repository.clearData();
    }



    @Test
    public void save(){
        Student student = new Student();
        student.setName("대훈");
        student.setMajor("Software");
        student.setGrade(3);

        repository.save(student);
        Student result = repository.findByNameAndMajorAndGrade(student).get();
        assertThat(student).isEqualTo(result);
    }

    @Test
    public void findAll(){
        Student student = new Student();
        student.setName("대훈");
        student.setMajor("Software");
        student.setGrade(3);
        repository.save(student);

        Student student1 = new Student();
        student1.setName("June");
        student1.setMajor("Computer");
        student1.setGrade(3);
        repository.save(student1);

        Student student2 = new Student();
        student2.setName("Junt");
        student2.setMajor("Computer");
        student2.setGrade(3);
        repository.save(student2);

        Student student3 = new Student();
        student3.setName("Juna");
        student3.setMajor("Computer");
        student3.setGrade(3);
        repository.save(student3);

        List<Student> result = repository.findAll();
        assertThat(result.size()).isEqualTo(4);


    }


    @Test
    public void findByNameAndMajorAndGrade(){
        Student student = new Student();
        student.setName("June");
        student.setMajor("Computer");
        student.setGrade(3);

        repository.save(student);
        Optional<Student> result = repository.findByNameAndMajorAndGrade(student);

        assertThat(result.get()).isEqualTo(student);
    }

    @Test
    public void delete(){
        Student student = new Student();
        student.setName("June");
        student.setMajor("Computer");
        student.setGrade(3);
        repository.save(student);

        boolean result = repository.delete(student);

        assertThat(result).isEqualTo(true);
    }



}
