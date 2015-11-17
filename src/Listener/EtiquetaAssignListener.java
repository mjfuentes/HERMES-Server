package Listener;

import Model.Etiqueta;
import Model.Notificacion;
import Service.EtiquetaService;
import Utils.ObserverCombobox;
import Utils.Tabla;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EtiquetaAssignListener implements ActionListener {

    private ObserverCombobox combobox;
    private JTable tabla;
    public EtiquetaAssignListener(ObserverCombobox combobox, JTable tabla){
        this.combobox = combobox;
        this.tabla = tabla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Notificacion> notificaciones = new ArrayList<>();
        for (int row_number : tabla.getSelectedRows()){
            int selected = tabla.convertRowIndexToModel(row_number);
            notificaciones.add(((Tabla)tabla.getModel()).getAtRow(selected));
        }
        EtiquetaService.getInstance().asignarEtiqueta(notificaciones,(Etiqueta)combobox.getSelectedItem());
    }
}
