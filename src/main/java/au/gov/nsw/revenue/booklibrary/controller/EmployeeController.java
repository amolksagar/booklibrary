package au.gov.nsw.revenue.booklibrary.controller;

import au.gov.nsw.revenue.booklibrary.entity.Employee;
import au.gov.nsw.revenue.booklibrary.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> fetchEmployees(){
        return employeeRepository.findAll();
    }
}
