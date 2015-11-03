package Interfaz;

import Dao.DaoFactory;
import Model.Contexto;
import Listener.*;
import Enums.Contenido;
import Utils.*;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

public class Monitor {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTable tabla;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JsonFromFileReader reader = new JsonFromFileReader();
					reader.read(System.getProperty("user.home") + "/datos");
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		CurrentSearch search = new CurrentSearch();
		DaoFactory.getEtiquetaDao().addObserver(search);
		DaoFactory.getNeneDao().addObserver(search);
		DaoFactory.getCategoriaDao().addObserver(search);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 0, 578, 286);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label_contenido = new JLabel("Contenido:");
		label_contenido.setBounds(31, 28, 100, 14);
		panel.add(label_contenido);
		
		ObserverCombobox comboBox_contenido = new ObserverCombobox(true);
		comboBox_contenido.setBounds(143, 25, 180, 20);
		panel.add(comboBox_contenido);
		comboBox_contenido.addItem(null);
		comboBox_contenido.addItem(Contenido.Alegre);
		comboBox_contenido.addItem(Contenido.Entusiasmado);
		comboBox_contenido.addItem(Contenido.Molesto);


		JLabel label_contexto = new JLabel("Contexto:");
		label_contexto.setBounds(31, 61, 100, 14);
		panel.add(label_contexto);
		
		ObserverCombobox combobox_contexto = new ObserverCombobox(true);
		combobox_contexto.setBounds(143, 57, 180, 20);
		panel.add(combobox_contexto);
		combobox_contexto.addItem(null);
		combobox_contexto.populate(DaoFactory.getContextoDao().getContextos());

		ObserverCombobox combobox_categoria = new ObserverCombobox(true);
		combobox_categoria.setBounds(143, 124, 180, 20);
		panel.add(combobox_categoria);
		combobox_categoria.addItem(null);
		DaoFactory.getCategoriaDao().addObserver(combobox_categoria);
		combobox_categoria.populate(DaoFactory.getCategoriaDao().getCategorias());

		JLabel label_categoria = new JLabel("Categor\u00EDa:");
		label_categoria.setBounds(31, 127, 100, 14);
		panel.add(label_categoria);
		
		JLabel label_nene = new JLabel("Ni\u00F1@:");
		label_nene.setBounds(31, 92, 46, 14);
		panel.add(label_nene);

		ObserverCombobox combobox_nene = new ObserverCombobox(true);
		combobox_nene.setBounds(143, 90, 180, 20);
		panel.add(combobox_nene);
		DaoFactory.getNeneDao().addObserver(combobox_nene);
		combobox_nene.addItem(null);
		combobox_nene.populate(DaoFactory.getNeneDao().getNenes());
		
		JLabel lblFechaHora = new JLabel("Fecha / Hora (AAAA-MM-DD)");
		lblFechaHora.setBounds(32, 156, 197, 14);
		panel.add(lblFechaHora);
		
		JLabel label_desde = new JLabel("desde:");
		label_desde.setBounds(31, 177, 69, 14);
		panel.add(label_desde);
		
		JLabel label_hasta = new JLabel("hasta:");
		label_hasta.setBounds(224, 177, 69, 14);
		panel.add(label_hasta);

		JTextField textfield_desde = new JTextField();
		textfield_desde.setBounds(84, 174, 127, 19);
		panel.add(textfield_desde);

		JTextField textfield_hasta = new JTextField();
		textfield_hasta.setBounds(275, 174, 127, 19);
		panel.add(textfield_hasta);
		
		JLabel lblEtiqueta = new JLabel("Etiqueta:");
		lblEtiqueta.setBounds(31, 206, 69, 14);
		panel.add(lblEtiqueta);
		
		ObserverCombobox combobox_etiqueta1 = new ObserverCombobox(true);
		combobox_etiqueta1.setBounds(143, 203, 180, 20);
		panel.add(combobox_etiqueta1);
		combobox_etiqueta1.addItem(null);
		DaoFactory.getEtiquetaDao().addObserver(combobox_etiqueta1);
		combobox_etiqueta1.populate(DaoFactory.getEtiquetaDao().getAllItems());

		JScrollPane scrollPane = new JScrollPane();
		Tabla model = new Tabla(DaoFactory.getNotificacionDAO().getAllItems());
		tabla = new JTable(model);
		tabla.setAutoCreateRowSorter(true);
		model.setTabla(tabla);
		tabla.getColumnModel().getColumn(0).setCellRenderer(new DateRenderer());
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		scrollPane.setBounds(44, 326, 1108, 416);
		scrollPane.setViewportView(tabla);
		search.addObserver(model);
		frame.getContentPane().add(scrollPane);
		
		JButton botonFiltrar = new JButton("FILTRAR");
		botonFiltrar.setBounds(54, 251, 438, 23);
		panel.add(botonFiltrar);
		botonFiltrar.addActionListener(new FIltrarListener(search,model, comboBox_contenido, combobox_contexto, combobox_nene, combobox_categoria, combobox_etiqueta1, textfield_desde, textfield_hasta));

		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Etiquetas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(602, 0, 586, 287);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblCrearEtiqueta = new JLabel("Crear Etiqueta:");
		lblCrearEtiqueta.setBounds(12, 32, 207, 14);
		panel_2.add(lblCrearEtiqueta);
		
		textField = new JTextField();
		textField.setBounds(252, 26, 207, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnCrear = new JButton("CREAR");
		btnCrear.setBounds(471, 26, 89, 23);
		panel_2.add(btnCrear);

		btnCrear.addActionListener(new EtiquetaAddListener(textField));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(36, 58, 462, 14);
		panel_2.add(separator);
		
		JLabel lblEliminarEtiqueta = new JLabel("Eliminar Etiqueta:");
		lblEliminarEtiqueta.setBounds(12, 81, 207, 14);
		panel_2.add(lblEliminarEtiqueta);

		ObserverCombobox combobox_etiqueta2 = new ObserverCombobox(false);
		combobox_etiqueta2.setBounds(252, 75, 207, 20);
		panel_2.add(combobox_etiqueta2);
		DaoFactory.getEtiquetaDao().addObserver(combobox_etiqueta2);
		combobox_etiqueta2.populate(DaoFactory.getEtiquetaDao().getAllItems());

		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBounds(471, 75, 89, 23);
		panel_2.add(btnEliminar);
		btnEliminar.addActionListener(new EtiquetaDeleteListener(combobox_etiqueta2));

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(36, 117, 462, 14);
		panel_2.add(separator_1);

		ObserverCombobox combobox_asignar = new ObserverCombobox(false);
		combobox_asignar.setBounds(252, 131, 207, 20);
		panel_2.add(combobox_asignar);
		DaoFactory.getEtiquetaDao().addObserver(combobox_asignar);
		combobox_asignar.populate(DaoFactory.getEtiquetaDao().getAllItems());

		JLabel lblAsignarEtiqueta = new JLabel("Asignar/Desasignar Etiqueta:");
		lblAsignarEtiqueta.setBounds(12, 137, 207, 14);
		panel_2.add(lblAsignarEtiqueta);
		
		JButton btnAsignar = new JButton("ASIGNAR");
		btnAsignar.setBounds(471, 131, 89, 23);
		btnAsignar.addActionListener(new EtiquetaAssignListener(combobox_asignar, tabla));
		panel_2.add(btnAsignar);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(36, 173, 462, 14);
		panel_2.add(separator_2);
		
		JLabel lblRenombrarEtiqueta = new JLabel("Renombrar Etiqueta:");
		lblRenombrarEtiqueta.setBounds(12, 191, 207, 14);
		panel_2.add(lblRenombrarEtiqueta);

		ObserverCombobox combobox_renombrar = new ObserverCombobox(false);
		combobox_renombrar.setBounds(252, 185, 207, 20);
		panel_2.add(combobox_renombrar);
		DaoFactory.getEtiquetaDao().addObserver(combobox_renombrar);
		combobox_renombrar.populate(DaoFactory.getEtiquetaDao().getAllItems());


		JLabel lblNuevoNombre = new JLabel("Nuevo Nombre:");
		lblNuevoNombre.setBounds(12, 243, 193, 14);
		panel_2.add(lblNuevoNombre);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(252, 237, 207, 20);
		
		JButton btnRenombrar = new JButton("RENOMBRAR");
		btnRenombrar.setBounds(471, 237, 105, 23);
		panel_2.add(btnRenombrar);
		btnRenombrar.addActionListener(new EtiquetaRenameListener(combobox_renombrar, textField_1));

		panel_2.add(textField_1);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(46, 220, 462, 14);
		panel_2.add(separator_3);



	}
}
