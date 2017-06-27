package it.univr.cd.store;

import javax.swing.JFrame;

public class Registrazione extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// ATTRIBUTI
	
	// COSTRUTTORE
	public Registrazione(){
		// frame init
    	setTitle("REGISTRAZIONE CD-STORE");
    	setResizable(false);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(400, 200, 450, 300);
    
    	// funzioni
    	
    	// render template
        setVisible(true);
	}
	
	// METODI
}