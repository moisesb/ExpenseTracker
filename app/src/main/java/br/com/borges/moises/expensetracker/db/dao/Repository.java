package br.com.borges.moises.expensetracker.db.dao;

import java.util.List;

/**
 * Created by moise on 23/12/2015.
 */
public interface Repository<T> {
    T getItem(int id);
    List<T> getItems();
    int addItem(T t);
    boolean deleteItem(T t);
    void updateItem(T t);
}
