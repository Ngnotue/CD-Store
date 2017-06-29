package it.univr.cd.store;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Registrazione extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// ATTRIBUTI
	JPanel panel;
	Model model = new Model();
    JFrame frame = this;
	
	// COSTRUTTORE
	public Registrazione() throws ClassNotFoundException, SQLException, ParseException{
		// frame init
    	setTitle("REGISTRAZIONE CD-STORE");
    	setResizable(false);
    	setBounds(400, 200, 450, 520);
    
    	// funzioni
    	template_registrazione();
    	
    	// render template
        setVisible(true);
	}
	
	// METODI
	private void template_registrazione() throws ClassNotFoundException, SQLException, ParseException {
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel labelTesto = new JLabel("Inserisci i tuoi dati");
		labelTesto.setBounds(5, 5, 440, 16);
		labelTesto.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(labelTesto);
		
		labelTesto = new JLabel("Nome");
		labelTesto.setBounds(45, 40, 118, 23);
		panel.add(labelTesto);
		
		JTextField textNome = new JTextField();
		textNome.setBounds(200, 40, 200, 22);
		textNome.setColumns(16);
		panel.add(textNome);
		
		labelTesto = new JLabel("Cognome");
		labelTesto.setHorizontalAlignment(SwingConstants.LEFT);
		labelTesto.setBounds(45, 80, 118, 16);
		panel.add(labelTesto);
		
		JTextField textCognom = new JTextField();
		textCognom.setBounds(200, 80, 200, 22);
		textCognom.setColumns(16);
		panel.add(textCognom);
		
		labelTesto = new JLabel("User ID");
		labelTesto.setHorizontalAlignment(SwingConstants.LEFT);
		labelTesto.setBounds(45, 120, 118, 16);
		panel.add(labelTesto);
		
		JTextField textUserID = new JTextField();
		textUserID.setBounds(200, 120, 200, 22);
		textUserID.setColumns(16);
		panel.add(textUserID);
		
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setHorizontalAlignment(SwingConstants.LEFT);
		labelPassword.setBounds(45, 160, 118, 16);
		panel.add(labelPassword);
		
		JPasswordField textPassword = new JPasswordField();
		textPassword.setEchoChar('*');
		textPassword.setBounds(200, 160, 200, 22);
		panel.add(textPassword);
		
		labelTesto = new JLabel("Codice Fiscale");
		labelTesto.setHorizontalAlignment(SwingConstants.LEFT);
		labelTesto.setBounds(45, 200, 118, 16);
		panel.add(labelTesto);
		
		JTextField textCodiceFiscale = new JTextField();
		textCodiceFiscale.setBounds(200, 200, 200, 22);
		textCodiceFiscale.setColumns(16);
		panel.add(textCodiceFiscale);
		
		labelTesto = new JLabel("Città di residenza");
		labelTesto.setHorizontalAlignment(SwingConstants.LEFT);
		labelTesto.setBounds(45, 240, 118, 16);
		panel.add(labelTesto);
		
		JTextField textCitta = new JTextField();
		textCitta.setBounds(200, 240, 200, 22);
		textCitta.setColumns(16);
		panel.add(textCitta);
		
		labelTesto = new JLabel("Telefono");
		labelTesto.setHorizontalAlignment(SwingConstants.LEFT);
		labelTesto.setBounds(45, 280, 118, 23);
		panel.add(labelTesto);
		
		JTextField textTelefono = new JTextField();
		textTelefono.setBounds(200, 280, 200, 22);
		textTelefono.setColumns(16);
		panel.add(textTelefono);
		
		labelTesto = new JLabel("Cellulare");
		labelTesto.setHorizontalAlignment(SwingConstants.LEFT);
		labelTesto.setBounds(45, 320, 118, 16);
		panel.add(labelTesto);
		
		JTextField textCellulare = new JTextField();
		textCellulare.setBounds(200, 320, 200, 22);
		textCellulare.setColumns(16);
		panel.add(textCellulare);
		
		JButton buttonRegistrati = new JButton("Registrati");
		buttonRegistrati.setBounds(340, 447, 100, 23);
		panel.add(buttonRegistrati);
		buttonRegistrati.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String error = "";
				boolean exception = false;
				String pwd = "";
				for(int i=0; i<textPassword.getPassword().length; i++)
					pwd += textPassword.getPassword()[i];
						
				if(textNome.getText().equals("") || textCognom.getText().equals("") || textUserID.getText().equals("") 
						|| pwd.equals("") || textCitta.getText().equals("") 
						|| textTelefono.getText().equals("") || textCellulare.getText().equals("")){
					error += "Uno o piu' campi sono vuoti\n";
					exception = true;
				}
				
				if (textCodiceFiscale.getText().length() != 16){
					error += "I caratteri del codice fiscale devono essere 16\n";
					exception = true;
				}
				
				if (isLetter(textCodiceFiscale.getText().charAt(0)) || isLetter(textCodiceFiscale.getText().charAt(1)) ||
					isLetter(textCodiceFiscale.getText().charAt(2)) || isLetter(textCodiceFiscale.getText().charAt(3)) ||
					isLetter(textCodiceFiscale.getText().charAt(4)) || isLetter(textCodiceFiscale.getText().charAt(5)) ||
					isNumeric(textCodiceFiscale.getText().charAt(6)) || isNumeric(textCodiceFiscale.getText().charAt(7)) ||
					isLetter(textCodiceFiscale.getText().charAt(8)) || isNumeric(textCodiceFiscale.getText().charAt(9)) ||
					isNumeric(textCodiceFiscale.getText().charAt(10)) || isLetter(textCodiceFiscale.getText().charAt(11)) ||
					isNumeric(textCodiceFiscale.getText().charAt(12)) || isNumeric(textCodiceFiscale.getText().charAt(13)) ||
					isNumeric(textCodiceFiscale.getText().charAt(14)) || isLetter(textCodiceFiscale.getText().charAt(15))){
					error += "Il codice fiscale non e' conforme allo standard\n";
					exception = true;
				}
				
				if (exception == false){
					// INSERT
					try {
						pwd = model.md5(pwd);
						if(model.registrazione(textUserID.getText(), pwd, textCodiceFiscale.getText(), textNome.getText(), textCognom.getText(), textCitta.getText(), textTelefono.getText(), textCellulare.getText())){
							JOptionPane.showMessageDialog(null, "Registrazione avvenuta!","Report",1);
							setVisible(false);
						}
						else{
							JOptionPane.showMessageDialog(null, "Impossibile inserire i valori, UserId già esistente. Riprovare|","Errore",1);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null, error + "Impossibile inserire i valori. Riprovare!","Errore",1);
				}
			}
		});
	}
	
	private boolean isLetter(char c){
		if (c>=65 && c<=90 && c>=97 && c<=122)
			return true;
		else
			return false;
	}
	
	private boolean isNumeric(char c){
		if (c>=48 && c<=57)
			return true;
		else
			return false;
	}
}