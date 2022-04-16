package student.studentspring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import student.studentspring.domain.Student;
import student.studentspring.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class StudentServiceIntegrationTest {

    @Autowired StudentService studentService;
    @Autowired StudentRepository studentRepository;

    @Test
    void 학생등록(){
        //given
        Student student = new Student();
        student.setName("대훈");
        student.setMajor("software");
        student.setGrade(1);

        //when
        Long saveId = studentService.join(student);

        //then
        Optional<Student> result = studentService.findOne(student);

        assertThat(saveId).isEqualTo(result.get().getId());
    }

    @Test
    void 중복_학생_예외(){
        //given
        Student student = new Student();
        student.setName("대훈");
        student.setMajor("software");
        student.setGrade(1);
        studentService.join(student);

        //when
        Student student1 = new Student();
        student1.setName("대훈");
        student1.setMajor("software");
        student1.setGrade(1);


        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> studentService.join(student1));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 학생입니다.");

    }

    @Test
    void 학생찾기(){
        //given
        Student student = new Student();
        student.setName("대훈");
        student.setMajor("software");
        student.setGrade(1);
        studentService.join(student);

        //when
        Optional<Student> result = studentService.findOne(student);

        //then
        assertThat(result.get().getName()).isEqualTo(student.getName());
    }


    @Test
    void 학생삭제(){
        //given
        Student student = new Student();
        student.setName("대훈");
        student.setMajor("software");
        student.setGrade(1);
        studentService.join(student);

        //when
        studentService.deleteStudent(student);
        List<Student> result = studentRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(0);

    }

    @Test
    void 학생수정(){
        //given
        Student student = new Student();
        student.setName("대훈");
        student.setMajor("software");
        student.setGrade(1);
        Long saveId = studentService.join(student);

        Student student1 = new Student();
        student1.setName("대훈123");
        student1.setMajor("software");
        student1.setGrade(1);
        student1.setId(saveId);

        //when
        studentService.updateStudent(student1);
        Optional<Student> findOne = studentService.findOne(student1);

        //then
        assertThat(findOne.get().getId()).isEqualTo(student1.getId());
    }
}
