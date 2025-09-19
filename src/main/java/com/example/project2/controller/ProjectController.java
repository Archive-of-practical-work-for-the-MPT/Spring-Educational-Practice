package com.example.project2.controller;

import com.example.project2.model.Project;
import com.example.project2.service.ProjectService;
import com.example.project2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/projects")
    public String getAllProjects(Model model) {
        List<Project> projects = projectService.findAllProjects();
        model.addAttribute("projects", projects);
        model.addAttribute("project", new Project());
        model.addAttribute("users", userService.findAllUsers());
        return "projects";
    }

    @PostMapping("/projects")
    public String addProject(@Valid @ModelAttribute Project project, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Project> projects = projectService.findAllProjects();
            model.addAttribute("projects", projects);
            model.addAttribute("project", project);
            model.addAttribute("users", userService.findAllUsers());
            return "projects";
        }
        project.setCreationDate(LocalDateTime.now());
        projectService.createProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/projects/edit/{id}")
    public String editProject(@PathVariable Long id, Model model) {
        Project project = projectService.findProjectById(id);
        model.addAttribute("project", project);
        model.addAttribute("users", userService.findAllUsers());
        return "editProject";
    }

    @PostMapping("/projects/update")
    public String updateProject(@Valid @ModelAttribute Project project, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Project> projects = projectService.findAllProjects();
            model.addAttribute("projects", projects);
            model.addAttribute("project", project);
            model.addAttribute("users", userService.findAllUsers());
            return "projects";
        }
        projectService.updateProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/projects/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "redirect:/projects";
    }
}