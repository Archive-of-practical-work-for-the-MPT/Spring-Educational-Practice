package com.example.project2.controller;

import com.example.project2.model.Task;
import com.example.project2.service.TaskService;
import com.example.project2.service.UserService;
import com.example.project2.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ProjectService projectService;

    @GetMapping("/tasks")
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.findAllTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("task", new Task());
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("projects", projectService.findAllProjects());
        return "tasks";
    }

    @PostMapping("/tasks")
    public String addTask(@Valid @ModelAttribute Task task, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Task> tasks = taskService.findAllTasks();
            model.addAttribute("tasks", tasks);
            model.addAttribute("task", task);
            model.addAttribute("users", userService.findAllUsers());
            model.addAttribute("projects", projectService.findAllProjects());
            return "tasks";
        }
        taskService.createTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        Task task = taskService.findTaskById(id);
        model.addAttribute("task", task);
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("projects", projectService.findAllProjects());
        return "editTask";
    }

    @PostMapping("/tasks/update")
    public String updateTask(@Valid @ModelAttribute Task task, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Task> tasks = taskService.findAllTasks();
            model.addAttribute("tasks", tasks);
            model.addAttribute("task", task);
            model.addAttribute("users", userService.findAllUsers());
            model.addAttribute("projects", projectService.findAllProjects());
            return "tasks";
        }
        taskService.updateTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}