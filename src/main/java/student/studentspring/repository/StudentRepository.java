package student.studentspring.repository;

import student.studentspring.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    /**
     * student 정보 저장
     */
    Student save(Student student);

    /**
     * student 정보 저장
     */
    Optional<Student> findById(Long id);

    /**
     * id 외의 정보로 학생 정보 검색
     */
    Optional<Student> findByNameAndMajorAndGrade(Student student);
    List<Student> findAll();


    boolean delete(Student student);
    //boolean deleteById(Long id);
    boolean update(Student student);


}
