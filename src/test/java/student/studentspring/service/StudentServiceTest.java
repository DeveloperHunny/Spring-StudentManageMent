package student.studentspring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import student.studentspring.domain.Student;
import student.studentspring.repository.MemoryStudentRepository;
import student.studentspring.repository.StudentRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private StudentService studentService;
    private MemoryStudentRepository repository;

    @BeforeEach
    public void BeforeEach(){
        repository = new MemoryStudentRepository();
        studentService = new StudentService(repository);
    }

    @AfterEach
    public void AfterEach(){
        repository.clearData();
    }

    @Test
    void 학생등록(){
        //given
        Student student = new Student();
        student.setName("Hun");
        student.setMajor("소프트웨어");
        student.setGrade(3);

        //when
        Long id = studentService.join(student);

        //then
        Optional<Student> result = studentService.findOne(student);
        assertThat(id).isEqualTo(result.get().getId());

    }

    @Test
    void 중복_학생_예외처리(){
        //given
        Student student = new Student();
        student.setName("Hun");
        student.setMajor("소프트웨어");
        student.setGrade(3);

        Student student1 = new Student();
        student1.setName("Hun");
        student1.setMajor("소프트웨어");
        student1.setGrade(3);

        //when
        studentService.join(student);
        IllegalStateException e = assertThrows(IllegalStateException.class, ()-> studentService.join(student1));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 학생입니다.");

        //then
    }

    @Test
    void 학생정보_삭제(){
        //given
        Student student = new Student();
        student.setName("Hun");
        student.setMajor("소프트웨어");
        student.setGrade(3);
        studentService.join(student);

        //when
        studentService.deleteStudent(student);

        //then
        Optional<Student> result = studentService.findOne(student);
        assertThat(result.isEmpty()).isEqualTo(true);

    }

    @Test
    void 학생정보_삭제_예외처리(){
        //given
        Student student = new Student();
        student.setName("testNo");
        student.setMajor("japsdijvioa");
        student.setGrade(4);

        //when
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> studentService.deleteStudent(student));

        //then
        assertThat(e.getMessage()).isEqualTo("존재하지 않는 학생입니다.");
    }

    @Test
    void 학생정보_업데이트(){
        //given
        Student student = new Student();
        student.setName("Hun");
        student.setMajor("소프트웨어");
        student.setGrade(3);
        Long id = studentService.join(student);

        //when
        Student modifyStu = new Student();
        modifyStu.setName("Hun");
        modifyStu.setMajor("소프트웨어");
        modifyStu.setGrade(4);
        modifyStu.setId(id);
        studentService.updateStudent(modifyStu);


        //then
        Optional<Student> result = studentService.findOne(modifyStu);
        assertThat(result.isPresent()).isEqualTo(true);

    }
}
