import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.kpi.data.dao.DaoFactory;
import ua.kpi.data.dao.GenericDao;
import ua.kpi.data.dao.PersistException;
import ua.kpi.data.model.Employee;

import java.util.Date;

public class EmployeeDaoJDBCTest {


    private GenericDao<Employee, Integer> employeeDaoJDBC;

    @Before
    public void init() throws PersistException {
        employeeDaoJDBC = DaoFactory.INSTANCE.getJDBCUserDao();
        employeeDaoJDBC.deleteAll();
    }

    @Test
    public void testSave() throws PersistException {
        Employee employee = Employee.builder()
                .firstName("firstName")
                .lastName("asds")
                .salary(123)
                .hireDate(new Date())
                .departmentId(2).build();

        employeeDaoJDBC.save(employee);
        Assert.assertEquals(1, employeeDaoJDBC.getAll().size());
    }


    @Test
    public void testDelete() throws PersistException {
        Employee employee = Employee.builder()
                .firstName("firstName")
                .lastName("asds")
                .salary(123)
                .hireDate(new Date())
                .departmentId(2).build();

        Employee saved = employeeDaoJDBC.save(employee);
        Assert.assertEquals(1, employeeDaoJDBC.getAll().size());
        employeeDaoJDBC.delete(saved);
        Assert.assertEquals(0, employeeDaoJDBC.getAll().size());
    }


    @Test
    public void testUpdate() throws PersistException {
        Employee employee = Employee.builder()
                .firstName("firstName")
                .lastName("asds")
                .salary(123)
                .hireDate(new Date())
                .departmentId(2).build();

        Employee saved = employeeDaoJDBC.save(employee);
        saved.setLastName("new_last_name");
        employeeDaoJDBC.update(saved);
        Employee newEmployee = employeeDaoJDBC.getById(saved.getId());
        Assert.assertEquals("new_last_name", newEmployee.getLastName());
    }


    @Test
    public void testFindAll() throws PersistException {
        Employee employee1 = Employee.builder()
                .firstName("firstName")
                .lastName("asds")
                .salary(123)
                .hireDate(new Date())
                .departmentId(2).build();

        Employee employee2 = Employee.builder()
                .firstName("firstName2")
                .lastName("sdfs")
                .salary(123)
                .hireDate(new Date())
                .departmentId(2).build();

        employeeDaoJDBC.save(employee1);
        employeeDaoJDBC.save(employee2);
        Assert.assertEquals(2, employeeDaoJDBC.getAll().size());
    }

}
