package Utils;

import Enums.Contenido;
import Enums.Contexto;
import Model.Categoria;
import Model.Etiqueta;
import Model.Nene;
import Model.Notificacion;
import org.omg.CORBA.Current;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Tabla implements TableModel, Observer {

    List<Notificacion> items;
    String[] columnNames = {"Fecha/Hora envio","Contenido","Contexto","Categoria","Niñ@","Etiquetas"};
    Class[] columnClass = {Date.class, Contenido.class, Contexto.class, Categoria.class, Nene.class, Etiqueta.class};
    JTable tabla;

    public void setTabla(JTable tabla){
        this.tabla = tabla;
    }

    public Tabla(List<Notificacion> items){
        this.items = items;
    }

    public void setItems(List<Notificacion> items){
        this.items = items;
        this.tabla.repaint();
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return ((Notificacion) items.toArray()[rowIndex]).getFechaEnvio();
            case 1:
                return ((Notificacion) items.toArray()[rowIndex]).getContenido();
            case 2:
                return ((Notificacion) items.toArray()[rowIndex]).getContexto();
            case 3:
                return ((Notificacion) items.toArray()[rowIndex]).getCategoria();
            case 4:
                return ((Notificacion) items.toArray()[rowIndex]).getNene();
            case 5:
                return ((Notificacion) items.toArray()[rowIndex]).getEtiqueta();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    @Override
    public void update(Observable o, Object arg) {
        this.items = ((CurrentSearch) o).getResults();
        this.tabla.repaint();
    }

    public Notificacion getAtRow(int rowIndex){
        return ((Notificacion) items.toArray()[rowIndex]);
    }
}
