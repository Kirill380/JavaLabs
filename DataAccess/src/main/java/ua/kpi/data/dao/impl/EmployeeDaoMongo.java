package ua.kpi.data.dao.impl;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ua.kpi.data.dao.AbstractDaoMongo;
import ua.kpi.data.dao.IdGenerator;
import ua.kpi.data.model.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDaoMongo extends AbstractDaoMongo<Employee, Integer> {

    public EmployeeDaoMongo(MongoDatabase dataBase) {
        super(dataBase);
    }

    @Override
    public MongoCollection<Document> getCollection() {
        return dataBase.getCollection("employee");
    }

    @Override
    protected List<Employee> convertDocumentsToObjects(List<Document> documents) {
        return documents.stream().map(d -> Employee.builder()
                .id(d.getInteger("_id"))
                .firstName(d.getString("firstName"))
                .lastName(d.getString("lastName"))
                .salary(d.getDouble("salary"))
                .hireDate(d.getDate("hireDate"))
                .departmentId(d.getInteger("departmentId"))
                .build()
        ).collect(Collectors.toList());
    }

    @Override
    protected Document convertObjectToDocument(Employee object) {
        Document document = new Document();
        document.put("firstName", object.getFirstName());
        document.put("lastName", object.getLastName());
        document.put("salary", object.getSalary());
        document.put("hireDate", object.getHireDate());
        document.put("departmentId", object.getDepartmentId());
        return document;
    }

    @Override
    protected Integer generateId(Employee object) {
        int id = IdGenerator.INSTANCE.generate();
        object.setId(id);
        return id;
    }

}
