package Employee_Management_System.service;

import org.springframework.data.domain.Page;
import Employee_Management_System.entity.Employee;

public interface EmployeeService {
    Page<Employee> searchAndPaginateEmployee(String keyword, int pageNo, int pageSize, String sortField, String sortDir);

    Employee saveEmployee(Employee reservation);

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Employee employee);

    void deleteEmployeeById(Long id);

}
