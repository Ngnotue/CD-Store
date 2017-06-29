package it.univr.cd.store;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Catalogo extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// ATTRIBUTI
	JPanel panel;
    JTextField username,field;
    JPasswordField password;
    Model model = new Model();
    JFrame frame = this;
	
	// COSTRUTTORE
	public Catalogo() throws ClassNotFoundException, SQLException, ParseException{
		// frame init
    	setTitle("CATALOGO CD-STORE");
    	setResizable(false);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(400, 200, 450, 520);
    
    	// funzioni
    	template_catalogo();
    	
    	// render template
        setVisible(true);
	}
	
	// METODI	
	private void template_catalogo() throws ClassNotFoundException, SQLException, ParseException {    
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel labelTesto;
		if(Control.getLogged()){
			labelTesto = new JLabel("Hai effettuato il login come: " + model.getUsername(Control.getUserId()));
			labelTesto.setBounds(5, 5, 300, 30);
			labelTesto.setBackground(Color.WHITE);
			labelTesto.setForeground(Color.BLACK);
			panel.add(labelTesto);
		}else{
			labelTesto = new JLabel("Non hai effettuato il login");
			labelTesto.setBounds(5, 5, 300, 30);
			labelTesto.setBackground(Color.WHITE);
			labelTesto.setForeground(Color.BLACK);
			panel.add(labelTesto);
		}
		
		JButton buttonCarrello = new JButton("Il mio Carrello");
		buttonCarrello.setBounds(5, 46, 189, 23);
		panel.add(buttonCarrello);
		buttonCarrello.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Control.getLogged()){
					try {
						Carrello viewCarrello = new Carrello();
						viewCarrello.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					setVisible(false);
				}
				else{
					Login viewLogin = new Login(frame);
					viewLogin.setVisible(true);
				}
			}
		});
		
		JButton buttonOrdine = new JButton("I miei Ordini");
		buttonOrdine.setBounds(5, 100, 189, 23);
		panel.add(buttonOrdine);
		buttonOrdine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Control.getLogged()){
					try {
						Ordini viewOrdini = new Ordini();
						viewOrdini.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					setVisible(false);
				}
				else{
					Login viewLogin = new Login(frame);
					viewLogin.setVisible(true);
				}
			}
		});
		
		labelTesto = new JLabel("Catalogo");
		labelTesto.setBounds(5, 150, 429, 30);
		labelTesto.setBackground(Color.WHITE);
		labelTesto.setForeground(Color.BLACK);
		labelTesto.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(labelTesto);
		
		String[] columnNames = {"#","Titolo","Artista","Prezzo","Quantità"};
		Object[][] data = model.getCatalogo();
		
		TableModel modelTable = new DefaultTableModel(data, columnNames){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){
				return false;//This causes all cells to be not editable
		    }
		};

		JTable table = new JTable(modelTable);
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		scrollPane.setBounds(5, 190, 440, 200);
		panel.add(scrollPane);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int id = 0;
					try{
						id = (int) table.getValueAt(table.getSelectedRow(), 0);
					}
					catch(Exception e1){}
					if(id != 0){
						Info viewInfo;
						try {
							viewInfo = new Info(id);
							viewInfo.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						setVisible(false);
					}
				}
			}
		});
		
		if(!(Control.getLogged())){
			JButton buttonInvia = new JButton("Login");
			buttonInvia.setBounds(5, 447, 89, 23);
			panel.add(buttonInvia);
			buttonInvia.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Login viewLogin = new Login(frame);
					viewLogin.setVisible(true);
				}
			});
			
			JButton buttonRegistrazione = new JButton("Registrazione");
			buttonRegistrazione.setBounds(105, 447, 117, 23);
			panel.add(buttonRegistrazione);
			buttonRegistrazione.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Registrazione viewRegistrazione;
					try {
						viewRegistrazione = new Registrazione();
						viewRegistrazione.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		else{
			JButton buttonInvia = new JButton("Logout");
			buttonInvia.setBounds(5, 447, 89, 23);
			panel.add(buttonInvia);
			buttonInvia.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Control.setUserId(0);
					Control.setLogged(false);
					Catalogo viewCatalogo;
					try {
						viewCatalogo = new Catalogo();
						viewCatalogo.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					setVisible(false);
				}
			});
		}
		
		JButton buttonCatalogo = new JButton("Catalogo");
		buttonCatalogo.setBounds(235, 447, 100, 23);
		panel.add(buttonCatalogo);
		buttonCatalogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Catalogo viewCatalogo;
				try {
					viewCatalogo = new Catalogo();
					viewCatalogo.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				setVisible(false);
			}
		});
    }
}