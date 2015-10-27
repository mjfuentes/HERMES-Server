package Utils;

import Dao.ObservableDAO;

import javax.swing.*;
import java.util.*;

public class ObserverCombobox extends JComboBox implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        ObservableDAO dao = (ObservableDAO) o;
        this.removeAllItems();
        List list = dao.getAllItems();
        list.forEach(this::addItem);
    }

    public void populate(List list) {
        list.forEach(this::addItem);
    }

}
