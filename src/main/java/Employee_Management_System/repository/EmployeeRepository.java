package Employee_Management_System.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import Employee_Management_System.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Searching and pagination
     * @param keyword keyword for seaching
     * @param pageable pagination
     * @return search result
     */
    @Query("SELECT r FROM Employee r WHERE r.name LIKE %?1%"
            + " OR r.job LIKE %?1%"
            + " OR r.email LIKE %?1%"
            + " OR CONCAT(r.salary, '') LIKE %?1%"
            + " OR CONCAT(r.date, '') LIKE %?1%")
    Page<Employee> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
