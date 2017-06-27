package it.univr.cd.store;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ModalitaOrdine extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// ATTRIBUTI
	JPanel panel;
    Model model = new Model();
	
	// COSTRUTTORE
	public ModalitaOrdine() throws ClassNotFoundException, SQLException, ParseException {
		// frame init
    	setTitle("MODALITA' ORDINE CD-STORE");
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
		
		if(Control.getLogged()){
			JLabel labelTesto = new JLabel("Modalità");
			labelTesto.setBounds(5, 5, 429, 30);
			labelTesto.setBackground(Color.WHITE);
			labelTesto.setForeground(Color.BLACK);
			labelTesto.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(labelTesto);
			
			// MODALITA' ORDINI			
			JLabel labelPagamento = new JLabel("Pagamento:");
			labelPagamento.setBounds(5, 60, 429, 30);
			labelPagamento.setBackground(Color.WHITE);
			labelPagamento.setForeground(Color.BLACK);

			JRadioButton pag_bonifico = new JRadioButton("Bonifico");
			JRadioButton pag_cartacredito = new JRadioButton("Carta di credito");
			JRadioButton pag_paypal = new JRadioButton("Paypal");
			pag_bonifico.setBounds(120, 60, 130, 30);
			pag_cartacredito.setBounds(120, 100, 130, 30);
			pag_paypal.setBounds(120, 140, 130, 30);
			ButtonGroup grp_pag = new ButtonGroup();
			grp_pag.add(pag_bonifico);
			grp_pag.add(pag_cartacredito);
			grp_pag.add(pag_paypal);
			pag_bonifico.setSelected(true);
			pag_cartacredito.setSelected(false);
			pag_paypal.setSelected(false);
			panel.add(labelPagamento);
			panel.add(pag_bonifico);
			panel.add(pag_cartacredito);
			panel.add(pag_paypal);
			
			JLabel labelConsegna = new JLabel("Consegna:");
			labelConsegna.setBounds(5, 220, 429, 30);
			labelConsegna.setBackground(Color.WHITE);
			labelConsegna.setForeground(Color.BLACK);

			JRadioButton cons_corriere = new JRadioButton("Corriere");
			JRadioButton cons_posta = new JRadioButton("Posta");
			cons_corriere.setBounds(120, 220, 130, 30);
			cons_posta.setBounds(120, 300, 130, 30);
			ButtonGroup grp_cons = new ButtonGroup();
			grp_cons.add(cons_corriere);
			grp_cons.add(cons_posta);
			cons_corriere.setSelected(true);
			cons_posta.setSelected(false);
			panel.add(labelConsegna);
			panel.add(cons_corriere);
			panel.add(cons_posta);
			
			JLabel labelPrezzoCorriere = new JLabel("Prezzo:    + 5,00 €");
			labelPrezzoCorriere.setBounds(250, 220, 429, 30);
			labelPrezzoCorriere.setBackground(Color.WHITE);
			labelPrezzoCorriere.setForeground(Color.BLACK);
			panel.add(labelPrezzoCorriere);
			
			JLabel labelPrezzoCorriere2 = new JLabel("Consegna: 2/3 giorni");
			labelPrezzoCorriere2.setBounds(250, 260, 429, 30);
			labelPrezzoCorriere2.setBackground(Color.WHITE);
			labelPrezzoCorriere2.setForeground(Color.BLACK);
			panel.add(labelPrezzoCorriere2);
			
			JLabel labelPrezzoPosta = new JLabel("Prezzo:    + 0,00 €");
			labelPrezzoPosta.setBounds(250, 300, 429, 30);
			labelPrezzoPosta.setBackground(Color.WHITE);
			labelPrezzoPosta.setForeground(Color.BLACK);
			panel.add(labelPrezzoPosta);
			
			JLabel labelPrezzoPosta2 = new JLabel("Consegna: 5/7 giorni");
			labelPrezzoPosta2.setBounds(250, 340, 429, 30);
			labelPrezzoPosta2.setBackground(Color.WHITE);
			labelPrezzoPosta2.setForeground(Color.BLACK);
			panel.add(labelPrezzoPosta2);
			
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
						boolean bool_cartacredito = pag_cartacredito.isSelected();
						boolean bool_paypal = pag_paypal.isSelected();
						int scelta = 0;
						if (bool_cartacredito)
							scelta = 1;
						if (bool_paypal)
							scelta = 2;
						boolean bool_corriere = cons_corriere.isSelected();
						
						Pagamento viewPagamento = new Pagamento(scelta,bool_corriere);
						viewPagamento.setVisible(true);
						setVisible(true);
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					setVisible(false);
				}
			});
		}
	}
}