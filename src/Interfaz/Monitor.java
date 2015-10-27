package Interfaz;

import Dao.DaoFactory;
import Enums.Contexto;
import Listener.EtiquetaAddListener;
import Listener.EtiquetaDeleteListener;
import Listener.EtiquetaRenameListener;
import Enums.Contenido;
import Listener.FIltrarListener;
import Utils.JsonFromFileReader;
import Utils.ObserverCombobox;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.Color;
import java.awt.List;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;

public class Monitor {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Monitor window = new Monitor();
					JsonFromFileReader reader = new JsonFromFileReader();
					reader.read("datos");
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
		
		JLabel label_contenido = new JLabel("Contenido:");
		label_contenido.setBounds(10, 11, 58, 14);
		panel.add(label_contenido);
		
		ObserverCombobox comboBox_contenido = new ObserverCombobox();
		comboBox_contenido.setBounds(69, 9, 127, 19);
		panel.add(comboBox_contenido);
		comboBox_contenido.addItem(Contenido.Alegre);
		comboBox_contenido.addItem(Contenido.Entusiasmado);
		comboBox_contenido.addItem(Contenido.Molesto);


		JLabel label_contexto = new JLabel("Contexto:");
		label_contexto.setBounds(10, 44, 58, 14);
		panel.add(label_contexto);
		
		ObserverCombobox combobox_contexto = new ObserverCombobox();
		combobox_contexto.setBounds(69, 41, 127, 19);
		panel.add(combobox_contexto);
		combobox_contexto.addItem(Contexto.Establo);
		combobox_contexto.addItem(Contexto.Pista);
		combobox_contexto.addItem(Contexto.Hogar);
		combobox_contexto.addItem(Contexto.Terapia);

		ObserverCombobox combobox_categoria = new ObserverCombobox();
		combobox_categoria.setBounds(282, 42, 127, 19);
		panel.add(combobox_categoria);
		DaoFactory.getCategoriaDao().addObserver(combobox_categoria);
		combobox_categoria.populate(DaoFactory.getCategoriaDao().getCategorias());

		JLabel label_categoria = new JLabel("Categor\u00EDa:");
		label_categoria.setBounds(223, 44, 58, 14);
		panel.add(label_categoria);
		
		JLabel label_nene = new JLabel("Ni\u00F1@:");
		label_nene.setBounds(10, 75, 46, 14);
		panel.add(label_nene);

		ObserverCombobox combobox_nene = new ObserverCombobox();
		combobox_nene.setBounds(69, 75, 127, 19);
		panel.add(combobox_nene);
		DaoFactory.getNeneDao().addObserver(combobox_nene);
		combobox_nene.populate(DaoFactory.getNeneDao().getNenes());
		
		JLabel lblFechaHora = new JLabel("Fecha / Hora");
		lblFechaHora.setBounds(10, 116, 69, 14);
		panel.add(lblFechaHora);
		
		JLabel label_desde = new JLabel("desde:");
		label_desde.setBounds(89, 136, 69, 14);
		panel.add(label_desde);
		
		JLabel label_hasta = new JLabel("hasta:");
		label_hasta.setBounds(237, 136, 69, 14);
		panel.add(label_hasta);

		JTextField textfield_desde = new JTextField();
		textfield_desde.setBounds(69, 156, 127, 19);
		panel.add(textfield_desde);

		JTextField textfield_hasta = new JTextField();
		textfield_hasta.setBounds(223, 156, 127, 19);
		panel.add(textfield_hasta);
		
		JLabel lblEtiqueta = new JLabel("Etiqueta:");
		lblEtiqueta.setBounds(10, 189, 69, 14);
		panel.add(lblEtiqueta);
		
		ObserverCombobox combobox_etiqueta1 = new ObserverCombobox();
		combobox_etiqueta1.setBounds(69, 187, 127, 19);
		panel.add(combobox_etiqueta1);
		DaoFactory.getEtiquetaDao().addObserver(combobox_etiqueta1);
		combobox_etiqueta1.populate(DaoFactory.getEtiquetaDao().getAllItems());
		
		JButton botonFiltrar = new JButton("FILTRAR");
		botonFiltrar.setBounds(20, 224, 438, 23);
		panel.add(botonFiltrar);
		botonFiltrar.addActionListener(new FIltrarListener(new JTable(), comboBox_contenido, combobox_contexto, combobox_nene, combobox_categoria, combobox_etiqueta1, textfield_desde, textfield_hasta));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 280, 964, 333);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		table = new JTable();
		table.setBorder(null);
		table.setFont(new Font("Dialog", Font.PLAIN, 17));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Fecha/Hora", "Contenido", "Contexto", "Categoria", "Niñ@", "Etiquetas"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		panel_1.add(table);
		
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

		ObserverCombobox combobox_etiqueta2 = new ObserverCombobox();
		combobox_etiqueta2.setBounds(123, 61, 207, 20);
		panel_2.add(combobox_etiqueta2);
		DaoFactory.getEtiquetaDao().addObserver(combobox_etiqueta2);
		combobox_etiqueta2.populate(DaoFactory.getEtiquetaDao().getAllItems());

		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBounds(340, 60, 89, 23);
		panel_2.add(btnEliminar);
		btnEliminar.addActionListener(new EtiquetaDeleteListener(combobox_etiqueta2));

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(34, 100, 381, 14);
		panel_2.add(separator_1);

		ObserverCombobox combobox_etiqueta3 = new ObserverCombobox();
		combobox_etiqueta3.setBounds(123, 117, 207, 20);
		panel_2.add(combobox_etiqueta3);
		DaoFactory.getEtiquetaDao().addObserver(combobox_etiqueta3);
		combobox_etiqueta3.populate(DaoFactory.getEtiquetaDao().getAllItems());


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

		ObserverCombobox combobox_etiqueta4 = new ObserverCombobox();
		combobox_etiqueta4.setBounds(123, 171, 207, 20);
		panel_2.add(combobox_etiqueta4);
		DaoFactory.getEtiquetaDao().addObserver(combobox_etiqueta4);
		combobox_etiqueta4.populate(DaoFactory.getEtiquetaDao().getAllItems());


		JLabel lblNuevoNombre = new JLabel("Nuevo Nombre:");
		lblNuevoNombre.setBounds(10, 217, 101, 14);
		panel_2.add(lblNuevoNombre);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(123, 214, 207, 20);
		
		JButton btnRenombrar = new JButton("RENOMBRAR");
		btnRenombrar.setBounds(340, 213, 105, 23);
		panel_2.add(btnRenombrar);
		btnRenombrar.addActionListener(new EtiquetaRenameListener(combobox_etiqueta4, textField_1));

		panel_2.add(textField_1);
	}
}
