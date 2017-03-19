package ua.kpi.data.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*
* Abstract class provide basic realization of CRUD operation
*
 */
public abstract class AbstractDaoJDBC<T extends Identifiable<PK>, PK extends Integer> implements GenericDao<T, PK> {

    private DataSource dataSource;

    public AbstractDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws PersistException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }


    public abstract String getTableName();

    /**
     * return sql query for getting all records
     * <p/>
     * SELECT * FROM [Table]
     */
    public abstract String getSelectQuery();

    /**
     * return sql query for adding a new record to the data base
     * <p/>
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    public abstract String getCreateQuery();

    /**
     * return sql query for updating a record
     * <p/>
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    public abstract String getUpdateQuery();

    /**
     * return sql query for removing a record from the data base
     * <p/>
     * DELETE FROM [Table] WHERE id= ?;
     */
    public abstract String getDeleteQuery();

    /**
     * Parse ResultSet and return the list of object according to data in ResultSet
     */
    protected abstract List<T> parseResultSet(ResultSet rs) throws PersistException;

    /**
     * Устанавливает аргументы insert запроса в соответствии со значением полей объекта object.
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;

    /**
     * Устанавливает аргументы update запроса в соответствии со значением полей объекта object.
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;

    // protected abstract String getNameID();

    @Override
    public synchronized T save(T item) throws PersistException {
        Connection connection = getConnection();
        if (item.getId() != null) {
            throw new PersistException("Object is already persist.");
        }
        T persistInstance;
        int autoId = 0;
        String generatedColumns[] = {"ID"};

        // adding the record
        String query = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(query, generatedColumns)) {
            prepareStatementForInsert(statement, item);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            }
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            autoId = rs.getInt(1);

        } catch (Exception e) {
            try { connection.close(); } catch (SQLException ex) {  }
            throw new PersistException(e);
        }

        // getting the record has already added
        query = getSelectQuery() + " WHERE id = " + autoId;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                throw new PersistException("Exception on findByPK new persist data.");
            }
            persistInstance = list.iterator().next();
        } catch (Exception e) {
            try { connection.close(); } catch (SQLException ex) {  }
            throw new PersistException(e);
        }

        try { connection.close(); } catch (SQLException e) {  }

        return persistInstance;
    }

    @Override
    public T getById(Integer id) throws PersistException {
        Connection connection = getConnection();
        List<T> list;
        String query = getSelectQuery();
        query += " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list == null || list.size() == 0) {
                throw new PersistException("Record with PK = " + id + " not found.");
            }
            if (list.size() > 1) {
                throw new PersistException("Received more than one record.");
            }

            return list.iterator().next();
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            try { connection.close(); } catch (SQLException e) {  }
        }

    }

    @Override
    public void update(T item) throws PersistException {
        Connection connection = getConnection();
        String query = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(query);) {
            prepareStatementForUpdate(statement, item);   // descendant will fill in arguments of the query
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            try { connection.close(); } catch (SQLException e) {  }
        }

    }

    @Override
    public void delete(T item) throws PersistException {
        Connection connection = getConnection();
        String query = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try {
                statement.setObject(1, item.getId());
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On delete modify more then 1 record: " + count);
            }
            statement.close();
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            try { connection.close(); } catch (SQLException e) {  }
        }
    }

    @Override
    public List<T> getAll() throws PersistException {
        Connection connection = getConnection();
        List<T> list;
        String query = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            return list;
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            try { connection.close(); } catch (SQLException e) {  }
        }
    }

    @Override
    public void deleteAll() throws PersistException {
        Connection connection = getConnection();
        String query = "delete from " + getTableName();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (Exception e) {
            throw new PersistException(e);
        } finally {
            try { connection.close(); } catch (SQLException e) {  }
        }

    }
}
