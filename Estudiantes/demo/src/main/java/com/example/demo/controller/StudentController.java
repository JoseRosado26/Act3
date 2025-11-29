package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;
    public StudentController(StudentService service) { this.service = service; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", service.findAll());
        model.addAttribute("student", new Student());
        model.addAttribute("editing", false);
        return "students";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("student") Student student, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("students", service.findAll());
            return "students";
        }
        service.save(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Student s = service.findById(id).orElseThrow();
        model.addAttribute("student", s);
        model.addAttribute("students", service.findAll());
        model.addAttribute("editing", true);
        return "students";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("student") Student student, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("students", service.findAll());
            model.addAttribute("editing", true);
            return "students";
        }
        student.setId(id);
        service.save(student);
        return "redirect:/students";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/students";
    }
}