package com.example.project2.controller.web;

import com.example.project2.model.Employee;
import com.example.project2.service.EmployeeService;
import com.example.project2.service.PositionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PositionService positionService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, PositionService positionService) {
        this.employeeService = employeeService;
        this.positionService = positionService;
    }

    @GetMapping
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeService.findAllEmployees();
        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employee());
        model.addAttribute("positions", positionService.findAllPositions());
        return "employees/employees";
    }

    @PostMapping
    public String addEmployee(@Valid @ModelAttribute Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Employee> employees = employeeService.findAllEmployees();
            model.addAttribute("employees", employees);
            model.addAttribute("employee", employee);
            model.addAttribute("positions", positionService.findAllPositions());
            return "employees/employees";
        }
        employeeService.createEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        Employee employee = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("positions", positionService.findAllPositions());
        return "employees/editEmployee";
    }

    @PostMapping("/update")
    public String updateEmployee(@Valid @ModelAttribute Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Employee> employees = employeeService.findAllEmployees();
            model.addAttribute("employees", employees);
            model.addAttribute("employee", employee);
            model.addAttribute("positions", positionService.findAllPositions());
            return "employees/employees";
        }
        employeeService.updateEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}