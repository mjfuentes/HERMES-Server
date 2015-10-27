package Listener;

import Model.Etiqueta;
import Service.EtiquetaService;
import Utils.ObserverCombobox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EtiquetaDeleteListener implements ActionListener {

    private ObserverCombobox combobox;

    public EtiquetaDeleteListener(ObserverCombobox combobox){
        this.combobox = combobox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EtiquetaService.getInstance().eliminarEtiqueta(((Etiqueta)combobox.getSelectedItem()).getId());
    }
}
