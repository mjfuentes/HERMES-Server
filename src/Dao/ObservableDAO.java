package Dao;

import java.util.List;
import java.util.Observable;

public abstract class ObservableDAO<T> extends Observable{
    public abstract List<T> getAllItems();
}
