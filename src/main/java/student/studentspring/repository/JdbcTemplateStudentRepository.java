package student.studentspring.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import student.studentspring.domain.Student;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateStudentRepository implements StudentRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateStudentRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Student save(Student student) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("student").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", student.getName());
        parameters.put("major", student.getMajor());
        parameters.put("grade", student.getGrade());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        student.setId(key.longValue());
        return student;
    }

    @Override
    public Optional<Student> findById(Long id) {
        List<Student> result = jdbcTemplate.query("select * from student where id = ?", studentRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Student> findByNameAndMajorAndGrade(Student student) {
        List<Student> result = jdbcTemplate.query("select * from student where name = ? and major = ? and grade = ?", studentRowMapper(), student.getName(), student.getMajor(), student.getGrade());
        return result.stream().findAny();
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query("select * from student", studentRowMapper());
    }

    @Override
    public boolean delete(Student student) {
        Optional<Student> findObject = findByNameAndMajorAndGrade(student);
        if(findObject.isEmpty()){ return false;}

        String sql = "delete from student where id = ?";
        jdbcTemplate.update(sql, findObject.get().getId());
        return true;
    }


    @Override
    public boolean update(Student student) {

        String sql = "update student set name = ? , major = ? , grade = ? where id = ?";
        jdbcTemplate.update(sql, student.getName(), student.getMajor(), student.getGrade(), student.getId());
        return true;
    }

    private RowMapper<Student> studentRowMapper(){
        return(rs, rowNum) -> {
           Student student = new Student();
           student.setId(rs.getLong("id"));
           student.setName(rs.getString("name"));
           student.setMajor(rs.getString("major"));
           student.setGrade(rs.getInt("grade"));
            return student;
        };
    }

}
