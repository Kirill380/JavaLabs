package ua.kpi.data.dao.impl;


import ua.kpi.data.dao.AbstractDaoJDBC;
import ua.kpi.data.dao.PersistException;
import ua.kpi.data.model.Employee;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;


public class EmployeeDaoJDBC extends AbstractDaoJDBC<Employee, Integer> {

    // for saving id from changing
    private class PersistEmployee extends Employee {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public EmployeeDaoJDBC(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String getTableName() {
        return "Employee";
    }


    @Override
    public String getSelectQuery() {
        return "select id, first_name, last_name, salary, hire_date, dept_id from Employee";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO Employee(first_name, last_name, salary, hire_date, dept_id) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "Update Employee set first_name = ?, last_name = ?, salary = ?, hire_date = ?, dept_id = ?  where id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "delete from Employee where id = ?";
    }

    @Override
    protected List<Employee> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Employee> result = new LinkedList<Employee>();
        try {
            while (rs.next()) {
                PersistEmployee Employee = new PersistEmployee();
                Employee.setId(rs.getInt("id"));
                Employee.setFirstName(rs.getString("first_name"));
                Employee.setLastName(rs.getString("last_name"));
                Employee.setSalary(rs.getDouble("salary"));
                Employee.setHireDate(rs.getDate("hire_date"));
                Employee.setDepartmentId(rs.getInt("dept_id"));
                result.add(Employee);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Employee object) throws PersistException {
        try {
            statement.setString(1, object.getFirstName());
            statement.setString(2, object.getLastName());
            statement.setDouble(3, object.getSalary());
            statement.setDate(4, convert(object.getHireDate()));
            statement.setInt(5, object.getDepartmentId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Employee object) throws PersistException {
        try {
            statement.setString(1, object.getFirstName());
            statement.setString(2, object.getLastName());
            statement.setDouble(3, object.getSalary());
            statement.setDate(4, convert(object.getHireDate()));
            statement.setInt(5, object.getDepartmentId());
            statement.setInt(6, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
    
    protected java.sql.Date convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }
}
