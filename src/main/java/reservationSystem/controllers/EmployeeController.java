package reservationSystem.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reservationSystem.entity.Employee;
import reservationSystem.service.EmployeeService;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    /**
     * View Home page
     * @param model
     * @param keyword filter keyword
     * @param pageNo pagination page number
     * @param pageSize number of items on a page
     * @param sortField sorting field
     * @param sortDir direction of sorting
     * @return employees html page with sorted and paginated content
     */
    @GetMapping("/employees")
    public String viewHomePage(Model model,
                               @RequestParam(value = "keyword", required = false) String keyword,
                               @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
                               @RequestParam(value = "sortField", required = false, defaultValue = "date") String sortField,
                               @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir) {

        Page<Employee> page = employeeService.searchAndPaginateEmployee(keyword, pageNo, pageSize, sortField, sortDir);
        List<Employee> listEmployees = page.getContent();

        // Format the date for each employee
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (Employee employee : listEmployees) {
            String formattedDate = employee.getDate().format(dateFormatter);
            employee.setFormattedDate(formattedDate);
        }

        model.addAttribute("listEmployees", listEmployees);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "employees";
    }

    /**
     * add employee form
     * @param model
     * @return add employee html page
     */
    @GetMapping("/addEmployeeForm")
    public String addEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "add_employee";
    }

    /**
     * save employee to database
     * @param employee
     * @return save employee to database and redirect to main page
     */
    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";

    }

    /**
     * edit reservation info in database based on id
     * @param id id of employee
     * @param model
     * @return edit reservation html page
     */
    @GetMapping("/employees/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "edit_employee";
    }

    /**
     * Handler for update employee form request
     * @param id id of employee
     * @param employee employee
     * @param model
     * @return update employee to database and redirect to main page
     */
    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee employee, Model model) {
        // getting customer from database by id

        Employee existingEmployee = employeeService.getEmployeeById(id);
        existingEmployee.setId(id);
        existingEmployee.setName(employee.getName());
        existingEmployee.setJob(employee.getJob());
        existingEmployee.setDate(employee.getDate());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setEmail(employee.getEmail());


        // save updated employee object
        employeeService.updateEmployee(existingEmployee);
        return "redirect:/employees";
    }

    /**
     * Delete button handler
     * @param id delete emlployee from databse based on id
     * @return redirect to main html page
     */
    @GetMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }
}
