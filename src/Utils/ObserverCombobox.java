package Utils;

import Dao.ObservableDAO;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ObserverCombobox extends JComboBox implements Observer {

    private Boolean hasNull;
    public ObserverCombobox(Boolean hasNull){
        this.hasNull = hasNull;
    }
    @Override
    public void update(Observable o, Object arg) {
        ObservableDAO dao = (ObservableDAO) o;
        this.removeAllItems();
        if (hasNull){
            this.addItem(null);
        }
        List list = dao.getAllItems();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            this.addItem(iterator.next());
        }
    }

    public void populate(List list) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            this.addItem(iterator.next());
        }
    }

}
