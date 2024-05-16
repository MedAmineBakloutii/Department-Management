package com.department_management.department_management.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.department_management.department_management.model.AdminStaff;
import com.department_management.department_management.model.Gender;
import com.department_management.department_management.model.Student;
import com.department_management.department_management.model.StudentClass;
import com.department_management.department_management.model.Teacher;
import com.department_management.department_management.repository.AdminStaffRepository;
import com.department_management.department_management.repository.StudentRepository;
import com.department_management.department_management.repository.TeacherRepository;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminStaffController {

    @Autowired
    private AdminStaffRepository adminStaffRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    // Register a new admin
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("adminStaff", new AdminStaff());
        return "register";
    }

    @PostMapping("/register")
    public String registerAdmin(@ModelAttribute AdminStaff adminStaff) {
        adminStaffRepository.save(adminStaff);
        return "redirect:/admin/login";
    }

    // Login
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginAdmin(@RequestParam String email, @RequestParam String password, Model model) {
        Optional<AdminStaff> adminStaff = adminStaffRepository.findByEmailAndPassword(email, password);
        if (adminStaff.isPresent()) {
            model.addAttribute("adminStaff", adminStaff.get());
            return "redirect:/admin/dashboard";
        }
        return "login";
    }

    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    // CRUD for Students
    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "students";
    }

    @GetMapping("/students/new")
    public String newStudentForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("studentClasses", StudentClass.values());
        model.addAttribute("genders", Gender.values());
        return "new_student";
    }

    @PostMapping("/students")
    public String createStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/admin/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id)));
        model.addAttribute("studentClasses", StudentClass.values());
        model.addAttribute("genders", Gender.values());
        return "edit_student";
    }

    @PostMapping("/students/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/admin/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return "redirect:/admin/students";
    }

    // CRUD for Teachers
    @GetMapping("/teachers")
    public String listTeachers(Model model) {
        model.addAttribute("teachers", teacherRepository.findAll());
        return "teachers";
    }

    @GetMapping("/teachers/new")
    public String newTeacherForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("genders", Gender.values());
        return "new_teacher";
    }

    @PostMapping("/teachers")
    public String createTeacher(@ModelAttribute Teacher teacher) {
        teacherRepository.save(teacher);
        return "redirect:/admin/teachers";
    }

    @GetMapping("/teachers/edit/{id}")
    public String editTeacherForm(@PathVariable Long id, Model model) {
        model.addAttribute("teacher", teacherRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid teacher Id:" + id)));
        model.addAttribute("genders", Gender.values());
        return "edit_teacher";
    }

    @PostMapping("/teachers/update/{id}")
    public String updateTeacher(@PathVariable Long id, @ModelAttribute Teacher teacher) {
        teacherRepository.save(teacher);
        return "redirect:/admin/teachers";
    }

    @GetMapping("/teachers/delete/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        teacherRepository.deleteById(id);
        return "redirect:/admin/teachers";
    }
}
