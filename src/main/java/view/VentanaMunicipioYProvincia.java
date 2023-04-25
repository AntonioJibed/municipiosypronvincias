package view;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import controller.ControladorMunicipio;
import controller.ControladorProvincia;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Municipio;
import model.Provincia;

import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMunicipioYProvincia extends JPanel {
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("provinciasymunicipios");
	private static JTextField jtfNombre;
	private static JTextField jtfNombreDeMuni;
	private static List<Municipio> municipios = new ArrayList<Municipio>();
	private static JComboBox<Municipio> jcbMunicipios = new JComboBox<Municipio>();
	private static JComboBox jcbProvincias = new JComboBox();

	/**
	 * Create the panel.
	 */
	public VentanaMunicipioYProvincia() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{492, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		jtfNombre = new JTextField();
		GridBagConstraints gbc_jtfNombre = new GridBagConstraints();
		gbc_jtfNombre.insets = new Insets(0, 0, 5, 5);
		gbc_jtfNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfNombre.gridx = 0;
		gbc_jtfNombre.gridy = 0;
		add(jtfNombre, gbc_jtfNombre);
		jtfNombre.setColumns(10);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				municipios = ControladorMunicipio.findLikeNombre(jtfNombre.getText());
				rellenarcomboMunicipios(municipios);			}
		});
		GridBagConstraints gbc_btnFiltrar = new GridBagConstraints();
		gbc_btnFiltrar.insets = new Insets(0, 0, 5, 0);
		gbc_btnFiltrar.gridx = 1;
		gbc_btnFiltrar.gridy = 0;
		add(btnFiltrar, gbc_btnFiltrar);
		
		jcbMunicipios = new JComboBox();
		GridBagConstraints gbc_jcbMunicipios = new GridBagConstraints();
		gbc_jcbMunicipios.weightx = 1.0;
		gbc_jcbMunicipios.insets = new Insets(0, 0, 5, 5);
		gbc_jcbMunicipios.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbMunicipios.gridx = 0;
		gbc_jcbMunicipios.gridy = 1;
		add(jcbMunicipios, gbc_jcbMunicipios);
		
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Municipio m = (Municipio) jcbMunicipios.getSelectedItem();
				
				m = ControladorMunicipio.findById(m.getId());
				
				mostrarDatosMunicipio(m);
			}
		});
		GridBagConstraints gbc_btnSeleccionar = new GridBagConstraints();
		gbc_btnSeleccionar.insets = new Insets(0, 0, 5, 0);
		gbc_btnSeleccionar.gridx = 1;
		gbc_btnSeleccionar.gridy = 1;
		add(btnSeleccionar, gbc_btnSeleccionar);
		
		JLabel lblMunicipioSeleccionado = new JLabel("Municipio Seleccionado:");
		GridBagConstraints gbc_lblMunicipioSeleccionado = new GridBagConstraints();
		gbc_lblMunicipioSeleccionado.gridwidth = 2;
		gbc_lblMunicipioSeleccionado.insets = new Insets(0, 0, 5, 0);
		gbc_lblMunicipioSeleccionado.gridx = 0;
		gbc_lblMunicipioSeleccionado.gridy = 2;
		add(lblMunicipioSeleccionado, gbc_lblMunicipioSeleccionado);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Nombre del municipio:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		jtfNombreDeMuni = new JTextField();
		GridBagConstraints gbc_jtfNombreDeMuni = new GridBagConstraints();
		gbc_jtfNombreDeMuni.insets = new Insets(0, 0, 5, 0);
		gbc_jtfNombreDeMuni.gridwidth = 11;
		gbc_jtfNombreDeMuni.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfNombreDeMuni.gridx = 1;
		gbc_jtfNombreDeMuni.gridy = 0;
		panel.add(jtfNombreDeMuni, gbc_jtfNombreDeMuni);
		jtfNombreDeMuni.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Provincia del municipio");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		jcbProvincias = new JComboBox();
		GridBagConstraints gbc_jcbProvinciaDeMuni = new GridBagConstraints();
		gbc_jcbProvinciaDeMuni.gridwidth = 11;
		gbc_jcbProvinciaDeMuni.insets = new Insets(0, 0, 5, 5);
		gbc_jcbProvinciaDeMuni.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbProvinciaDeMuni.gridx = 1;
		gbc_jcbProvinciaDeMuni.gridy = 1;
		panel.add(jcbProvincias, gbc_jcbProvinciaDeMuni);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.gridwidth = 2;
		gbc_btnGuardar.gridx = 10;
		gbc_btnGuardar.gridy = 2;
		panel.add(btnGuardar, gbc_btnGuardar);
		findByLikeNombre(jtfNombre.getText());
		
		cargarTodasProvincias();

	}
	public static List<Municipio> findByLikeNombre (String nombre) {
		EntityManager em = entityManagerFactory.createEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM municipio where nombre like ?", Municipio.class);
		q.setParameter(1, "%" + nombre + "%");
		List<Municipio> l = (List<Municipio>) q.getResultList();
		
		em.close();
		return l;
	}
	public static void rellenarcomboMunicipios(List<Municipio> municipios) {
		limpiarMunicipios();
		
		
		for (Municipio municipio : municipios) {
			jcbMunicipios.addItem(municipio);
		}
	}
		
		public static void limpiarMunicipios() {
			jcbMunicipios.removeAllItems();
		}
		
		public static void cargarTodasProvincias() {
			limpiarMunicipios();
			
			List<Provincia> provincias = ControladorProvincia.findAllProvincias();
			for (Provincia p : provincias) {
				jcbProvincias.addItem(p);
			}
		}
		
		public static void mostrarDatosMunicipio(Municipio m) {
			jtfNombreDeMuni.setText(m.getNombre());
			List<Provincia> ps = ControladorProvincia.findAllProvincias();
			
			Municipio mu = (Municipio) jcbMunicipios.getSelectedItem();
			
			for (int i = 0; i < ps.size(); i++) {
				Provincia provincia = (Provincia) jcbProvincias.getItemAt(i);
				
				if (provincia.getId() == mu.getProvincia().getId()) {
					jcbProvincias.setSelectedIndex(i);
				}
			}
		}
		
		public static void guardar() {
			
			Provincia p = (Provincia) jcbProvincias.getSelectedItem();
			
			Municipio mu = (Municipio) jcbMunicipios.getSelectedItem();
			
			
			mu.setNombre(jtfNombreDeMuni.getText());
			mu.setProvincia(p);
			
			ControladorMunicipio.guardar(mu);
			
			JOptionPane.showMessageDialog(null, "Se ha guadado los datos con exito");
			
		}
	

}
