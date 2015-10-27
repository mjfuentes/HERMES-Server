package Listener;

import Model.Etiqueta;
import Service.EtiquetaService;
import Utils.ObserverCombobox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EtiquetaRenameListener implements ActionListener {

    private ObserverCombobox combobox;
    private JTextField textfield;
    public EtiquetaRenameListener(ObserverCombobox combobox, JTextField textfield){
        this.combobox = combobox;
        this.textfield = textfield;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EtiquetaService.getInstance().renombrarEtiqueta(((Etiqueta) combobox.getSelectedItem()).getId(),textfield.getText());
        textfield.setText("");
    }
}
