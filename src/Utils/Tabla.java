package Utils;

import Enums.Contenido;
import Model.Contexto;
import Model.Categoria;
import Model.Etiqueta;
import Model.Nene;
import Model.Notificacion;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.*;

public class Tabla extends AbstractTableModel implements TableModel, Observer {

    List<Notificacion> items;
    String[] columnNames = {"Fecha/Hora envio","Contenido","Contexto","Categoria","Niñ@","Etiquetas"};
    Class[] columnClass = {Date.class, Contenido.class, Contexto.class, Categoria.class, Nene.class, String.class};
    JTable tabla;

    public void setTabla(JTable tabla){
        this.tabla = tabla;
    }

    public Tabla(List<Notificacion> items){
        this.items = items;
    }

    public void setItems(List<Notificacion> items){

        this.items = items;
        tabla.tableChanged(new TableModelEvent(this));
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
                return etiquetasToString(((Notificacion) items.toArray()[rowIndex]).getEtiquetas());
            default:
                return null;
        }
    }

    private String etiquetasToString(List<Etiqueta> etiquetas){
        StringBuilder sb = new StringBuilder();
        Iterator iterator = etiquetas.iterator();
        while (iterator.hasNext()){
            Etiqueta etiqueta = (Etiqueta) iterator.next();
            sb.append(etiqueta.toString());
            if (iterator.hasNext()){
                sb.append(',');
            }
        }
        return sb.toString();
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
        this.setItems(((CurrentSearch) o).getResults());
    }

    public Notificacion getAtRow(int rowIndex){
        return ((Notificacion) items.toArray()[rowIndex]);
    }
}
