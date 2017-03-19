package ua.kpi.data.dao;


public enum  IdGenerator {
    INSTANCE;

    private int current = 0;

    public int getCurrent() {
        return current;
    }

    public int generate() {
        return ++current;
    }
}
