package reservationSystem.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

// Employee object
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "job")
    private String job;
    @Column(name = "date_of_birth")
    private LocalDate date;
    @Column(name = "salary")
    private int salary;
    @Column(name = "email")
    private String email;

    private String formattedDate;


    public Employee() {
    }

    public Employee(String name, String job, LocalDate date, int salary, String email) {
        super();
        this.name = name;
        this.job = job;
        this.date = date;
        this.salary = salary;
        this.email = email;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
