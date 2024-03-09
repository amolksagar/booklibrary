package au.gov.nsw.revenue.booklibrary.repository;

import au.gov.nsw.revenue.booklibrary.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
