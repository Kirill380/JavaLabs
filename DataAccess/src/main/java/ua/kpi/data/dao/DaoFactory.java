package ua.kpi.data.dao;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ua.kpi.data.dao.impl.EmployeeDaoJDBC;
import ua.kpi.data.dao.impl.EmployeeDaoMongo;
import ua.kpi.data.model.Employee;

import javax.sql.DataSource;

public enum DaoFactory {
    INSTANCE;

    public static final String MONGO_HOST = "localhost";
    public static final int MONGO_PORT = 27017;
    public static final String MONGO_DB_NAME = "test";
    public static final int DB_PORT = 3306;
    public static final String DB_HOST = "localhost";
    public static final String DB_NAME = "test";
    public static final String DB_USER_NAME = "root";
    public static final String DB_USER_PASSWORD = "send08";
    public static final String JDBC_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
    public static final int MAX_POOL_SIZE = 3;

    DataSource getDatasource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JDBC_URL);
        config.setUsername(DB_USER_NAME);
        config.setPassword(DB_USER_PASSWORD);
        config.setMaximumPoolSize(MAX_POOL_SIZE);
        return new HikariDataSource(config);
    }

    MongoDatabase getMongoClient() {
        return new MongoClient(MONGO_HOST, MONGO_PORT).getDatabase(MONGO_DB_NAME);
    }


    public GenericDao<Employee, Integer> getMongoUserDao() {
        return new EmployeeDaoMongo(getMongoClient());
    }

    public GenericDao<Employee, Integer> getJDBCUserDao() {
        return new EmployeeDaoJDBC(getDatasource());
    }
}
