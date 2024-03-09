package au.gov.nsw.revenue.booklibrary;

import au.gov.nsw.revenue.booklibrary.entity.Employee;
import au.gov.nsw.revenue.booklibrary.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookLibraryApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BookLibraryApplication.class, args);
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) {
        Employee employee1 = Employee.builder()
                .firstName("Amol")
                .lastName("Kshirsagar")
                .email("amol@gmail.com")
                .build();

        Employee employee2 = Employee.builder()
                .firstName("Tony")
                .lastName("Stark")
                .email("tony@gmail.com")
                .build();

        Employee employee3 = Employee.builder()
                .firstName("Peter")
                .lastName("Parker")
                .email("peter@gmail.com")
                .build();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
    }
}
