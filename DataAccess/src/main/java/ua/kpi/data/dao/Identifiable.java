package ua.kpi.data.dao;

import java.io.Serializable;


public interface Identifiable<K extends Serializable> {
     K getId();
}
