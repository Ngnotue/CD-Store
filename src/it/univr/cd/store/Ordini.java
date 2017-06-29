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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Ordini extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// ATTRIBUTI
	JPanel panel;
    Model model = new Model();
	
	// COSTRUTTORE
	public Ordini() throws ClassNotFoundException, SQLException, ParseException {
		// frame init
    	setTitle("ORDINI CD-STORE");
    	setResizable(false);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(400, 200, 450, 520);
    
    	// funzioni
    	template_ordine();
    	
    	// render template
        setVisible(true);
	}
	
	// METODI
	private void template_ordine() throws ClassNotFoundException, SQLException, ParseException {
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
			
			labelTesto = new JLabel("Ordini");
			labelTesto.setBounds(5, 150, 429, 30);
			labelTesto.setBackground(Color.WHITE);
			labelTesto.setForeground(Color.BLACK);
			labelTesto.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(labelTesto);
			
			// VIEW ORDINI
			String[] columnNames = {"#","Totale","Data","Ora","Pagamento","Consegna"};
			Object[][] data = model.getOrdini();
			
			TableModel modelTable = new DefaultTableModel(data, columnNames){
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int row, int column){
					return false;//This causes all cells to be not editable
			    }
			};

			JTable table = new JTable(modelTable);
			table.setAutoCreateRowSorter(true);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			table.setDefaultRenderer(Object.class, centerRenderer);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollPane = new JScrollPane(table);
			table.setFillsViewportHeight(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			scrollPane.setBounds(5, 190, 440, 200);
			panel.add(scrollPane);
			
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
		}else{
			labelTesto = new JLabel("Non hai effettuato il login");
			labelTesto.setBounds(5, 5, 300, 30);
			labelTesto.setBackground(Color.WHITE);
			labelTesto.setForeground(Color.BLACK);
			panel.add(labelTesto);
		}
	}
}