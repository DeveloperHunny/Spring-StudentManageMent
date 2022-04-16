package student.studentspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import student.studentspring.domain.Student;
import student.studentspring.service.StudentService;

import java.util.List;

@Controller
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students/new")
    public String createForm(){
        return "students/createForm";
    }

    @PostMapping("/students/new")
    public String create(StudentForm form){
        Student student = new Student();
        student.setName(form.getName());
        student.setMajor(form.getMajor());
        student.setGrade(form.getGrade());
        studentService.join(student);

        return "redirect:/";
    }

    @GetMapping("/students")
    public String list(Model model){
        List<Student> students = studentService.findStudents();
        model.addAttribute("students", students);
        return "students/studentList";
    }

    @GetMapping("/students/delete")
    public String deleteForm(){
        return "students/deleteForm";
    }

    @PostMapping("/students/delete")
    public String delete(StudentForm form){
        Student student = new Student();
        student.setName(form.getName());
        student.setMajor(form.getMajor());
        student.setGrade(form.getGrade());
        studentService.deleteStudent(student);

        return "redirect:/";
    }

    @GetMapping("/students/update")
    public String updateForm(){
        return "students/updateForm";
    }

    @PostMapping("/students/update")
    public String update(StudentForm_id form){
        Student student = new Student();
        student.setId(form.getId());
        student.setName(form.getName());
        student.setMajor(form.getMajor());
        student.setGrade(form.getGrade());
        studentService.updateStudent(student);

        return "redirect:/";
    }


}
