package Interfaz;

import Dao.DaoFactory;
import Listener.EtiquetaAddListener;
import Listener.EtiquetaDeleteListener;
import Listener.EtiquetaRenameListener;
import Utils.ObserverCombobox;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class Monitor {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Monitor window = new Monitor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Monitor() {
		initialize();
	}

	public void updateEtiquetas(){

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1000, 663);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 489, 258);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblContenido = new JLabel("Contenido:");
		lblContenido.setBounds(10, 11, 58, 14);
		panel.add(lblContenido);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(69, 9, 127, 19);
		panel.add(comboBox);
		
		JLabel label = new JLabel("Contenido:");
		label.setBounds(10, 44, 58, 14);
		panel.add(label);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(69, 41, 127, 19);
		panel.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(282, 42, 127, 19);
		panel.add(comboBox_2);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa:");
		lblCategora.setBounds(223, 44, 58, 14);
		panel.add(lblCategora);
		
		JLabel lblNi = new JLabel("Ni\u00F1@:");
		lblNi.setBounds(10, 75, 46, 14);
		panel.add(lblNi);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(69, 75, 127, 19);
		panel.add(comboBox_3);
		
		JLabel lblFechaHora = new JLabel("Fecha / Hora");
		lblFechaHora.setBounds(10, 116, 69, 14);
		panel.add(lblFechaHora);
		
		JLabel lblDesde = new JLabel("desde:");
		lblDesde.setBounds(89, 136, 69, 14);
		panel.add(lblDesde);
		
		JLabel lblHasta = new JLabel("hasta:");
		lblHasta.setBounds(237, 136, 69, 14);
		panel.add(lblHasta);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(69, 156, 127, 19);
		panel.add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(223, 156, 127, 19);
		panel.add(comboBox_5);
		
		JLabel lblEtiqueta = new JLabel("Etiqueta:");
		lblEtiqueta.setBounds(10, 189, 69, 14);
		panel.add(lblEtiqueta);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setBounds(69, 187, 127, 19);
		panel.add(comboBox_6);
		
		JButton btnFiltrar = new JButton("FILTRAR");
		btnFiltrar.setBounds(20, 224, 438, 23);
		panel.add(btnFiltrar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 280, 964, 333);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(519, 11, 455, 258);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblCrearEtiqueta = new JLabel("Crear Etiqueta:");
		lblCrearEtiqueta.setBounds(10, 15, 80, 14);
		panel_2.add(lblCrearEtiqueta);
		
		textField = new JTextField();
		textField.setBounds(123, 12, 207, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnCrear = new JButton("CREAR");
		btnCrear.setBounds(340, 11, 89, 23);
		panel_2.add(btnCrear);

		btnCrear.addActionListener(new EtiquetaAddListener(textField));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(34, 47, 381, 14);
		panel_2.add(separator);
		
		JLabel lblEliminarEtiqueta = new JLabel("Eliminar Etiqueta:");
		lblEliminarEtiqueta.setBounds(10, 64, 89, 14);
		panel_2.add(lblEliminarEtiqueta);
		


		ObserverCombobox comboBox_7 = new ObserverCombobox();
		comboBox_7.setBounds(123, 61, 207, 20);
		panel_2.add(comboBox_7);
		DaoFactory.getEtiquetaDao().addObserver(comboBox_7);
		comboBox_7.populate(DaoFactory.getEtiquetaDao().getAllItems());

		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBounds(340, 60, 89, 23);
		panel_2.add(btnEliminar);
		btnEliminar.addActionListener(new EtiquetaDeleteListener(comboBox_7));

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(34, 100, 381, 14);
		panel_2.add(separator_1);

		ObserverCombobox comboBox_8 = new ObserverCombobox();
		comboBox_8.setBounds(123, 117, 207, 20);
		panel_2.add(comboBox_8);
		DaoFactory.getEtiquetaDao().addObserver(comboBox_8);
		comboBox_8.populate(DaoFactory.getEtiquetaDao().getAllItems());


		JLabel lblAsignarEtiqueta = new JLabel("Asignar Etiqueta:");
		lblAsignarEtiqueta.setBounds(10, 120, 89, 14);
		panel_2.add(lblAsignarEtiqueta);
		
		JButton btnAsignar = new JButton("ASIGNAR");
		btnAsignar.setBounds(340, 116, 89, 23);
		panel_2.add(btnAsignar);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(34, 156, 381, 14);
		panel_2.add(separator_2);
		
		JLabel lblRenombrarEtiqueta = new JLabel("Renombrar Etiqueta:");
		lblRenombrarEtiqueta.setBounds(10, 174, 101, 14);
		panel_2.add(lblRenombrarEtiqueta);

		ObserverCombobox comboBox_9 = new ObserverCombobox();
		comboBox_9.setBounds(123, 171, 207, 20);
		panel_2.add(comboBox_9);
		DaoFactory.getEtiquetaDao().addObserver(comboBox_9);
		comboBox_9.populate(DaoFactory.getEtiquetaDao().getAllItems());


		JLabel lblNuevoNombre = new JLabel("Nuevo Nombre:");
		lblNuevoNombre.setBounds(10, 217, 101, 14);
		panel_2.add(lblNuevoNombre);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(123, 214, 207, 20);
		
		JButton btnRenombrar = new JButton("RENOMBRAR");
		btnRenombrar.setBounds(340, 213, 105, 23);
		panel_2.add(btnRenombrar);
		btnRenombrar.addActionListener(new EtiquetaRenameListener(comboBox_7, textField_1));

		panel_2.add(textField_1);
	}
}
