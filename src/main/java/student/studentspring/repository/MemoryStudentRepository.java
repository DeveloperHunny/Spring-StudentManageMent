package student.studentspring.repository;

import student.studentspring.domain.Student;

import java.util.*;

public class MemoryStudentRepository implements StudentRepository{

    private static Map<Long, Student> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Student save(Student student) {
        student.setId(++sequence);
        store.put(student.getId(), student);
        return student;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Student> findByNameAndMajorAndGrade(Student student) {
        String name = student.getName();
        String major = student.getMajor();
        Integer grade = student.getGrade();
        return store.values().stream()
                .filter(s -> s.getName().equals(name) && s.getGrade() == grade && s.getMajor().equals(major))
                .findAny();
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(store.values());
    }



    @Override
    public boolean delete(Student student) {
        Optional<Student> findObject = findByNameAndMajorAndGrade(student);
        if(findObject.isEmpty()){ return false;}

        store.remove(findObject.get().getId());
        return true;
    }


    @Override
    public boolean update(Student student) {
        Student result = store.replace(student.getId(), student);

        if(result == null){return false;}
        else{ return true; }
    }


    public void clearData(){
        store.clear();
    }
}
