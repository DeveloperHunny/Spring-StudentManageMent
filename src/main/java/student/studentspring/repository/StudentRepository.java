package student.studentspring.repository;

import student.studentspring.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    Student save(Student student);
    Optional<Student> findById(Long id);

    List<Student> findAll();

    //내가 추가한 것
    boolean delete(Student student);

    boolean deleteById(Long id);
    boolean update(Student student);
    Optional<Student> findByNameAndMajorAndGrade(Student student);

}
