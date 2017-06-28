package it.univr.cd.store;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Pagamento extends JFrame {
private static final long serialVersionUID = 1L;
	
	// ATTRIBUTI
	JTextField numero_carta;
	JTextField codice_sicurezza;
	JTextField titolare_carta;
	JTextField email;
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
			// scelta_pagamento = 0 --> bonifico
			// scelta_pagamento = 1 --> carta di credito
			// scelta_pagamento = 2 --> paypal
			switch(scelta_pagamento){
				case 0:
					labelTesto = new JLabel("BONIFICO");
					labelTesto.setBounds(5, 50, 429, 30);
					labelTesto.setBackground(Color.WHITE);
					labelTesto.setForeground(Color.BLACK);
					panel.add(labelTesto);
					
					labelTesto = new JLabel("Puoi effettuare il bonifico accreditando il nostro conto corrente:");
					labelTesto.setBounds(5, 70, 429, 30);
					labelTesto.setBackground(Color.WHITE);
					labelTesto.setForeground(Color.BLACK);
					panel.add(labelTesto);
					
					labelTesto = new JLabel("Banca: MONTE DEI PASCHI DI SIENA");
					labelTesto.setBounds(5, 110, 429, 30);
					labelTesto.setBackground(Color.WHITE);
					labelTesto.setForeground(Color.BLACK);
					panel.add(labelTesto);
					
					labelTesto = new JLabel("Iban: IT 39 Q 01030 03413 000000666221");
					labelTesto.setBounds(5, 130, 429, 30);
					labelTesto.setBackground(Color.WHITE);
					labelTesto.setForeground(Color.BLACK);
					panel.add(labelTesto);
					
					labelTesto = new JLabel("Intestatario: GIRADA SRLS");
					labelTesto.setBounds(5, 150, 429, 30);
					labelTesto.setBackground(Color.WHITE);
					labelTesto.setForeground(Color.BLACK);
					panel.add(labelTesto);
					
					labelTesto = new JLabel("Causale: Inserire il numero dell'ordine");
					labelTesto.setBounds(5, 170, 429, 30);
					labelTesto.setBackground(Color.WHITE);
					labelTesto.setForeground(Color.BLACK);
					panel.add(labelTesto);
					
					labelTesto = new JLabel("Il pagamento deve essere effetuato entro sette giorni.");
					labelTesto.setBounds(5, 210, 429, 30);
					labelTesto.setBackground(Color.WHITE);
					labelTesto.setForeground(Color.BLACK);
					panel.add(labelTesto);
					break;
				case 1:
					labelTesto = new JLabel("CARTA DI CREDITO");
					labelTesto.setBounds(5, 50, 429, 30);
					labelTesto.setBackground(Color.WHITE);
					labelTesto.setForeground(Color.BLACK);
					panel.add(labelTesto);
					
					labelTesto = new JLabel("Numero Carta: ");
					labelTesto.setBounds(5, 90, 429, 30);
					labelTesto.setBackground(Color.WHITE);
					labelTesto.setForeground(Color.BLACK);
					panel.add(labelTesto);
					
					numero_carta = new JTextField();
					numero_carta.setBounds(150, 90, 172, 30);
					numero_carta.setColumns(16);
					panel.add(numero_carta);
					
					labelTesto = new JLabel("Scadenza: ");
					labelTesto.setBounds(5, 120, 429, 30);
					labelTesto.setBackground(Color.WHITE);
					labelTesto.setForeground(Color.BLACK);
					panel.add(labelTesto);
					
					String[] mese = {"01","02","03","04","05","06","07","08","09","10","11","12"};
					String[] anno = {"2017","2018","2019","2020","2021","2022","2023","2024","2025","2026"};
					JComboBox<String> mese_scadenza = new JComboBox<String>(mese);
					mese_scadenza.setBounds(150, 120, 70, 30);
					mese_scadenza.setEditable(false);
					panel.add(mese_scadenza);
					
					labelTesto = new JLabel("/");
					labelTesto.setBounds(220, 120, 30, 30);
					labelTesto.setBackground(Color.WHITE);
					labelTesto.setForeground(Color.BLACK);
					panel.add(labelTesto);
					
					JComboBox<String> anno_scadenza = new JComboBox<String>(anno);
					anno_scadenza.setBounds(232, 120, 90, 30);
					anno_scadenza.setEditable(false);
					panel.add(anno_scadenza);
					
					labelTesto = new JLabel("Codice di sicurezza: ");
					labelTesto.setBounds(5, 150, 429, 30);
					labelTesto.setBackground(Color.WHITE);
					labelTesto.setForeground(Color.BLACK);
					panel.add(labelTesto);
					
					codice_sicurezza = new JTextField();
					codice_sicurezza.setBounds(150, 150, 172, 30);
					codice_sicurezza.setColumns(16);
					panel.add(codice_sicurezza);
					
					labelTesto = new JLabel("Titolare Carta: ");
					labelTesto.setBounds(5, 180, 429, 30);
					labelTesto.setBackground(Color.WHITE);
					labelTesto.setForeground(Color.BLACK);
					panel.add(labelTesto);
					
					titolare_carta = new JTextField();
					titolare_carta.setBounds(150, 180, 172, 30);
					titolare_carta.setColumns(16);
					panel.add(titolare_carta);
					break;
				case 2:
					labelTesto = new JLabel("PAYPAL");
					labelTesto.setBounds(5, 50, 429, 30);
					labelTesto.setBackground(Color.WHITE);
					labelTesto.setForeground(Color.BLACK);
					panel.add(labelTesto);
					
					labelTesto = new JLabel("Email: ");
					labelTesto.setBounds(5, 90, 429, 30);
					labelTesto.setBackground(Color.WHITE);
					labelTesto.setForeground(Color.BLACK);
					panel.add(labelTesto);
					
					email = new JTextField();
					email.setBounds(150, 90, 172, 30);
					email.setColumns(16);
					panel.add(email);
					break;
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
			
			JButton buttonConferma = new JButton("Conferma");
			buttonConferma.setBounds(335, 447, 100, 23);
			panel.add(buttonConferma);
			buttonConferma.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String error = "";
					boolean exception = false;
					try {
						String str_scelta_pagamento = "";
						switch(scelta_pagamento){
							case 0:
								str_scelta_pagamento = "BONIF";
								break;
							case 1:
								if(numero_carta.getText().equals("") || codice_sicurezza.getText().length() != 3 || titolare_carta.getText().equals("")){
									error += "Uno o piu' campi sono vuoti o non validi\n";
									exception = true;
								}
								
								if(exception == false){
									str_scelta_pagamento = "CARTA";
								}
								else{
									JOptionPane.showMessageDialog(null, error + "Riprova!","Errore", 1);
								}
								break;
							case 2:
								if(email.getText().equals("") || email.getText().indexOf('@') == -1){
									error += "Mail inserita non valida\n";
									exception = true;
								}
								
								if(exception == false){
									str_scelta_pagamento = "PAYPA";
								}
								else{
									JOptionPane.showMessageDialog(null, error + "Riprova!","Errore", 1);
								}
								break;
						}

						String scelta_consegna = "";
						if (bool_corriere)
							scelta_consegna = "CORRI";
						else
							scelta_consegna = "POSTA";
						
						if(exception == false){
							model.createOrdine(str_scelta_pagamento, scelta_consegna);
							model.deleteCarrello();
							JOptionPane.showMessageDialog(null,"Ordine confermato con successo!","Report", 1);
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					if(exception == false){
						Ordini viewOrdini;
						try {
							viewOrdini = new Ordini();
							viewOrdini.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						setVisible(false);
					}
				}
			});
		}
	}
}