package it.univr.cd.store;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// ATTRIBUTI
	JPanel panel;
    JTextField username;
    JPasswordField password;
    JButton buttonClick;
    Model model = new Model();
    private JFrame frame;
    
    // COSTRUTTORE
	public Login(JFrame frame) {
		this.frame = frame;
		
		// frame init
    	setTitle("LOGIN CD-STORE");
    	setResizable(false);
    	setBounds(400, 200, 450, 300);
    
    	// funzioni
    	template_login();
    	
    	// render template
        setVisible(true);
	}
    
    // METODI
	private void template_login() {    
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		if(!(Control.getLogged())){
			JLabel labelTesto = new JLabel("Accedi");
			labelTesto.setBounds(5, 5, 429, 30);
			labelTesto.setBackground(Color.WHITE);
			labelTesto.setForeground(Color.BLACK);
			labelTesto.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(labelTesto);
			
			JLabel labelUsername = new JLabel("Username");
			labelUsername.setHorizontalAlignment(SwingConstants.LEFT);
			labelUsername.setBounds(5, 46, 97, 20);
			panel.add(labelUsername);
			
			username = new JTextField();
			username.setBounds(262, 46, 172, 20);
			panel.add(username);
			username.setColumns(16);
			
			JLabel labelPassword = new JLabel("Password");
			labelPassword.setHorizontalAlignment(SwingConstants.LEFT);
			labelPassword.setBounds(5, 90, 97, 20);
			panel.add(labelPassword);
			
			password = new JPasswordField();
			password.setEchoChar('*');
			password.setBounds(262, 90, 172, 20);
			panel.add(password);
			
			JButton buttonInvia = new JButton("Login");
			buttonInvia.setBounds(5, 227, 89, 23);
			panel.add(buttonInvia);
			buttonInvia.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						String pwd = "";
						for(int i=0; i<password.getPassword().length; i++)
							pwd += password.getPassword()[i];
						Control.setUserId(model.login(username.getText(), pwd));
						if(Control.getUserId() != 0)
							Control.setLogged(true);
						else
							Control.setLogged(false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					// open new frame
					if (Control.getLogged()){
						Catalogo viewCatalogo;
						try {
							viewCatalogo = new Catalogo();
							viewCatalogo.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						setVisible(false);
						// close old frame
						frame.setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(null,"Username o Password errati!","Errore", 1);
						Login viewLogin = new Login(frame);
						viewLogin.setVisible(true);
						setVisible(false);
					}
				}
			});
			
			JButton buttonRegistrazione = new JButton("Registrazione");
			buttonRegistrazione.setBounds(105, 227, 117, 23);
			panel.add(buttonRegistrazione);
			buttonRegistrazione.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Registrazione viewRegistrazione = new Registrazione();
					viewRegistrazione.setVisible(true);
				}
			});
		}
		
		JButton buttonExit = new JButton("Esci");
		buttonExit.setBounds(345, 227, 89, 23);
		panel.add(buttonExit);
		buttonExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
    }
}