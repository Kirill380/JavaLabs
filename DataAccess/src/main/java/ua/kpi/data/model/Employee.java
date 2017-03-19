package ua.kpi.data.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ua.kpi.data.dao.Identifiable;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Employee implements Identifiable<Integer>, Serializable, Comparable<Employee> {

    private Integer id = null;
    private String firstName;
    private String lastName;
    private double salary;
    private Date hireDate;
    private int departmentId;

    public Employee() {
    }

    @Builder
    public Employee(Integer id, String firstName, String lastName, double salary, Date hireDate, int departmentId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.hireDate = hireDate;
        this.departmentId = departmentId;
    }

    @Override
    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return firstName;
    }

    @Override
    public int compareTo(Employee o) {
        return firstName.compareTo(o.getFirstName());
    }
}
