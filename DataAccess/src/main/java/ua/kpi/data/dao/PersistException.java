package ua.kpi.data.dao;


public class PersistException extends Exception {

    public PersistException(String message) {
        super(message);
    }

    public PersistException(Throwable cause) {
        super(cause);
    }
}
