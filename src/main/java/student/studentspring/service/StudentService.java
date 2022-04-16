package student.studentspring.service;

import student.studentspring.domain.Student;
import student.studentspring.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

public class StudentService {

    private StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Long join(Student student){

        validateDuplicate(student);
        Student result = studentRepository.save(student);
        return result.getId();
    }

    private void validateDuplicate(Student student) {
        studentRepository.findByNameAndMajorAndGrade(student)
                        .ifPresent(m -> 
                        { throw new IllegalStateException("이미 존재하는 학생입니다.");});
    }

    public List<Student> findStudents(){
        return studentRepository.findAll();
    }

    public void deleteStudent(Student student){
        if(!studentRepository.delete(student)){
            throw new IllegalStateException("존재하지 않는 학생입니다.");
        }
    }

    public void updateStudent(Student student){

        if(!studentRepository.update(student)){
            throw new IllegalStateException("존재하지 않는 학생입니다.");
        }
    }

    public Optional<Student> findOne(Student student){
        return studentRepository.findByNameAndMajorAndGrade(student);
    }

}
