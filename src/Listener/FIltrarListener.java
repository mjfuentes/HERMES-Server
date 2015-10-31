package Listener;

import Enums.Contenido;
import Enums.Contexto;
import Model.Categoria;
import Model.Etiqueta;
import Model.Nene;
import Model.Notificacion;
import Service.NotificacionService;
import Utils.ObserverCombobox;
import Utils.Tabla;

import javax.naming.Context;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class FIltrarListener implements ActionListener {
    private Tabla tabla;
    private ObserverCombobox comboboxContenido;
    private ObserverCombobox comboboxContexto;
    private ObserverCombobox comboboxNene;
    private ObserverCombobox comboboxCategoria;
    private ObserverCombobox comboboxEtiqueta;
    private JTextField textoDesde;
    private JTextField textoHasta;

    public FIltrarListener(Tabla tabla, ObserverCombobox comboboxContenido, ObserverCombobox comboboxContexto, ObserverCombobox comboboxNene, ObserverCombobox comboboxCategoria, ObserverCombobox comboboxEtiqueta, JTextField desde, JTextField hasta){
        this.tabla = tabla;
        this.comboboxContenido = comboboxContenido;
        this.comboboxContexto = comboboxContexto;
        this.comboboxNene = comboboxNene;
        this.comboboxCategoria = comboboxCategoria;
        this.comboboxEtiqueta = comboboxEtiqueta;
        this.textoDesde = desde;
        this.textoHasta = hasta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Notificacion> lista = NotificacionService.getInstance().filtrar((Contenido) comboboxContenido.getSelectedItem(),(Contexto) comboboxContexto.getSelectedItem(),(Nene) comboboxNene.getSelectedItem(),(Categoria) comboboxCategoria.getSelectedItem(),(Etiqueta) comboboxEtiqueta.getSelectedItem(), textoDesde.getText(),textoHasta.getText());
        tabla.setItems(lista);
    }

}
