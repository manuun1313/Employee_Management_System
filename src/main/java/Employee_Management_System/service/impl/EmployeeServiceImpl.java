package Employee_Management_System.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import Employee_Management_System.entity.Employee;
import Employee_Management_System.repository.EmployeeRepository;
import Employee_Management_System.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    /**
     *
     * @param keyword filter keyword
     * @param pageNo pagination page number
     * @param pageSize number of items on a page
     * @param sortField sorting field
     * @param sortDir direction of sorting
     * @return employees html page with sorted and paginated content
     */
    @Override
    public Page<Employee> searchAndPaginateEmployee(String keyword, int pageNo, int pageSize, String sortField, String sortDir) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        if (keyword != null && !keyword.isEmpty()) {
            return employeeRepository.findByKeyword(keyword, pageable);
        } else {
            return employeeRepository.findAll(pageable);
        }
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }


}
