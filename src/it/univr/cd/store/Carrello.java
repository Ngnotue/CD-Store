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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Carrello extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// ATTRIBUTI
	JPanel panel;
    Model model = new Model();
    private int id_disco_select;
	
	// COSTRUTTORE
	public Carrello() throws ClassNotFoundException, SQLException, ParseException {
		// frame init
    	setTitle("CARRELLO CD-STORE");
    	setResizable(false);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(400, 200, 450, 520);
    
    	// funzioni
    	template_carrello();
    	
    	// render template
        setVisible(true);
	}
	
	// METODI
	private void template_carrello() throws ClassNotFoundException, SQLException, ParseException {
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel labelTesto;		
		if(Control.getLogged()){
			labelTesto = new JLabel("User: " + model.getUsername(Control.getUserId()));
			labelTesto.setBounds(5, 5, 100, 30);
			labelTesto.setBackground(Color.WHITE);
			labelTesto.setForeground(Color.BLACK);
			panel.add(labelTesto);
			
			JButton buttonCarrello = new JButton("Il mio Carrello");
			buttonCarrello.setBounds(5, 46, 189, 23);
			panel.add(buttonCarrello);
			buttonCarrello.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						Carrello viewCarrello = new Carrello();
						viewCarrello.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					setVisible(false);
				}
			});
				
			JButton buttonOrdine = new JButton("I miei Ordini");
			buttonOrdine.setBounds(5, 100, 189, 23);
			panel.add(buttonOrdine);
			buttonOrdine.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						Ordini viewOrdini = new Ordini();
						viewOrdini.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					setVisible(false);
				}
			});
			
			labelTesto = new JLabel("Carrello");
			labelTesto.setBounds(5, 150, 429, 30);
			labelTesto.setBackground(Color.WHITE);
			labelTesto.setForeground(Color.BLACK);
			labelTesto.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(labelTesto);
			
			// VIEW CARRELLO
			String[] columnNames = {"#","Titolo","Prezzo","Data inserimento"};
			Object[][] data = model.getCarrello();
			
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
					try{
						id_disco_select = (int) table.getValueAt(table.getSelectedRow(), 0);
					}
					catch(Exception e1){}
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
			
			JLabel labelTextField = new JLabel("Totale: ");
			labelTextField.setHorizontalAlignment(SwingConstants.LEFT);
			labelTextField.setBounds(5, 395, 97, 20);
			panel.add(labelTextField);
			
			if(model.rowCarrello() != 0){
				JLabel labelTextTotale = new JLabel(model.getTotaleCarrello());
				labelTextTotale.setHorizontalAlignment(SwingConstants.LEFT);
				labelTextTotale.setBounds(55, 395, 97, 20);
				panel.add(labelTextTotale);
			}
			else{
				JLabel labelTextTotale = new JLabel("0,00 €");
				labelTextTotale.setHorizontalAlignment(SwingConstants.LEFT);
				labelTextTotale.setBounds(55, 395, 97, 20);
				panel.add(labelTextTotale);
			}
			
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
			
			JButton buttonCatalogo = new JButton("Catalogo");
			buttonCatalogo.setBounds(95, 447, 100, 23);
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
			
			if(model.rowCarrello() != 0){
				JButton buttonEliminaCatalogo = new JButton("Elimina Disco");
				buttonEliminaCatalogo.setBounds(190, 447, 150, 23);
				panel.add(buttonEliminaCatalogo);
				buttonEliminaCatalogo.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						try {
							model.deleteDiscoCarrello(id_disco_select);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						Carrello viewCarrello;
						try {
							viewCarrello = new Carrello();
							viewCarrello.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						setVisible(false);
					}
				});
				
				JButton buttonOrdina = new JButton("Ordina");
				buttonOrdina.setBounds(335, 447, 100, 23);
				panel.add(buttonOrdina);
				buttonOrdina.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						ModalitaOrdine viewModalitaOrdine;
						try {
							viewModalitaOrdine = new ModalitaOrdine();
							viewModalitaOrdine.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						setVisible(false);
					}
				});
			}
		}
	}
}