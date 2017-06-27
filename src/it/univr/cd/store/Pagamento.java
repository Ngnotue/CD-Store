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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Pagamento extends JFrame {
private static final long serialVersionUID = 1L;
	
	// ATTRIBUTI
	JPanel panel;
    Model model = new Model();
    private int scelta_pagamento;
    private boolean bool_corriere;
	
	// COSTRUTTORE
	public Pagamento(int scelta_pagamento, boolean bool_corriere) throws ClassNotFoundException, SQLException, ParseException {
		this.scelta_pagamento = scelta_pagamento;
		this.bool_corriere = bool_corriere;
		
		// frame init
    	setTitle("PAGAMENTO CD-STORE");
    	setResizable(false);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(400, 200, 450, 520);
    
    	// funzioni
    	template_pagamento();
    	
    	// render template
        setVisible(true);
	}
	
	// METODI
	private void template_pagamento() throws ClassNotFoundException, SQLException, ParseException {
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		if(Control.getLogged()){
			JLabel labelTesto = new JLabel("Modalità Pagamento");
			labelTesto.setBounds(5, 5, 429, 30);
			labelTesto.setBackground(Color.WHITE);
			labelTesto.setForeground(Color.BLACK);
			labelTesto.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(labelTesto);
			
			// MODALITA' PAGAMENTO
			//
			//
			//
			
			
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
			
			JButton buttonConferma = new JButton("Conferma");
			buttonConferma.setBounds(335, 447, 100, 23);
			panel.add(buttonConferma);
			buttonConferma.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						String str_scelta_pagamento = "";
						switch(scelta_pagamento){
							case 0:
								str_scelta_pagamento = "BONIF";
								break;
							case 1:
								str_scelta_pagamento = "CARTA";
								break;
							case 2:
								str_scelta_pagamento = "PAYPA";
								break;
						}

						String scelta_consegna = "";
						if (bool_corriere)
							scelta_consegna = "CORRI";
						else
							scelta_consegna = "POSTA";
						model.createOrdine(str_scelta_pagamento, scelta_consegna);
						model.deleteCarrello();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					Ordini viewOrdini;
					try {
						viewOrdini = new Ordini();
						viewOrdini.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					setVisible(false);
				}
			});
		}
	}
}