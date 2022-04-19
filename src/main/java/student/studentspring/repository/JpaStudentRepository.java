package student.studentspring.repository;

import student.studentspring.domain.Student;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class JpaStudentRepository implements StudentRepository{

    private final EntityManager em;

    public JpaStudentRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Student save(Student student) {
        em.persist(student);
        return student;
    }

    @Override
    public Optional<Student> findById(Long id) {
        Student student = em.find(Student.class, id);
        return Optional.ofNullable(student);
    }

    @Override
    public Optional<Student> findByNameAndMajorAndGrade(Student student) {
        List<Student> result = em.createQuery("select s from Student s where s.name = :name and s.major = :major and s.grade = :grade", Student.class)
                .setParameter("name", student.getName())
                .setParameter("major", student.getMajor())
                .setParameter("grade", student.getGrade())
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Student> findAll() {
        return em.createQuery("select s from Student s", Student.class).getResultList();
    }

    @Override
    public boolean delete(Student student) {
        Optional<Student> findObj = findByNameAndMajorAndGrade(student);
        if(findObj.isEmpty()) return false;

        em.createQuery("delete from Student s where s.id = :id")
                .setParameter("id", findObj.get().getId()).executeUpdate();

        return true;
    }

    @Override
    public boolean update(Student student) {
        em.createQuery("update Student s set s.name = :name, s.major = :major, s.grade = :grade where s.id = :id")
                .setParameter("name", student.getName())
                .setParameter("major", student.getMajor())
                .setParameter("grade", student.getGrade())
                .setParameter("id", student.getId()).executeUpdate();

        return true;
    }
}
