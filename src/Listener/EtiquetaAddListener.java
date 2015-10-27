package Listener;

import Service.EtiquetaService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EtiquetaAddListener implements ActionListener {

    private JTextField textField;
    public EtiquetaAddListener(JTextField textField){
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nombreEtiqueta = textField.getText();
        EtiquetaService.getInstance().crearEtiqueta(nombreEtiqueta);
        textField.setText("");
    }
}
