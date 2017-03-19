import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.kpi.data.dao.DaoFactory;
import ua.kpi.data.dao.GenericDao;
import ua.kpi.data.dao.PersistException;
import ua.kpi.data.model.Employee;

import java.util.Date;

public class EmployeeDaoMongoTest {


    private GenericDao<Employee, Integer> employeeDaoMongo;

    @Before
    public void init() throws PersistException {
        employeeDaoMongo = DaoFactory.INSTANCE.getMongoUserDao();
        employeeDaoMongo.deleteAll();
    }

    @Test
    public void testSave() throws PersistException {
        Employee employee = Employee.builder()
                .firstName("firstName")
                .lastName("asds")
                .salary(123)
                .hireDate(new Date())
                .departmentId(2).build();

        employeeDaoMongo.save(employee);
        Assert.assertEquals(1, employeeDaoMongo.getAll().size());
    }


    @Test
    public void testDelete() throws PersistException {
        Employee employee = Employee.builder()
                .firstName("firstName")
                .lastName("asds")
                .salary(123)
                .hireDate(new Date())
                .departmentId(2).build();

        employeeDaoMongo.save(employee);
        Assert.assertEquals(1, employeeDaoMongo.getAll().size());
        employeeDaoMongo.delete(employee);
        Assert.assertEquals(0, employeeDaoMongo.getAll().size());
    }


    @Test
    public void testUpdate() throws PersistException {
        Employee employee = Employee.builder()
                .firstName("firstName")
                .lastName("asds")
                .salary(123)
                .hireDate(new Date())
                .departmentId(2).build();

        employeeDaoMongo.save(employee);
        employee.setLastName("new_last_name");
        employeeDaoMongo.update(employee);
        Employee newEmployee = employeeDaoMongo.getById(employee.getId());
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

        employeeDaoMongo.save(employee1);
        employeeDaoMongo.save(employee2);
        Assert.assertEquals(2, employeeDaoMongo.getAll().size());
    }



}
