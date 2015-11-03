package Listener;

import Enums.Contenido;
import Model.Contexto;
import Model.Categoria;
import Model.Etiqueta;
import Model.Nene;
import Utils.CurrentSearch;
import Utils.ObserverCombobox;
import Utils.Tabla;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FIltrarListener implements ActionListener {
    private Tabla tabla;
    private ObserverCombobox comboboxContenido;
    private ObserverCombobox comboboxContexto;
    private ObserverCombobox comboboxNene;
    private ObserverCombobox comboboxCategoria;
    private ObserverCombobox comboboxEtiqueta;
    private JTextField textoDesde;
    private JTextField textoHasta;
    private CurrentSearch search;

    public FIltrarListener(CurrentSearch search, Tabla tabla, ObserverCombobox comboboxContenido, ObserverCombobox comboboxContexto, ObserverCombobox comboboxNene, ObserverCombobox comboboxCategoria, ObserverCombobox comboboxEtiqueta, JTextField desde, JTextField hasta){
        this.tabla = tabla;
        this.comboboxContenido = comboboxContenido;
        this.comboboxContexto = comboboxContexto;
        this.comboboxNene = comboboxNene;
        this.comboboxCategoria = comboboxCategoria;
        this.comboboxEtiqueta = comboboxEtiqueta;
        this.textoDesde = desde;
        this.textoHasta = hasta;
        this.search = search;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        search.setParams((Contenido) comboboxContenido.getSelectedItem(),(Contexto) comboboxContexto.getSelectedItem(),(Nene) comboboxNene.getSelectedItem(),(Categoria) comboboxCategoria.getSelectedItem(),(Etiqueta) comboboxEtiqueta.getSelectedItem(), textoDesde.getText(),textoHasta.getText());
    }

}
