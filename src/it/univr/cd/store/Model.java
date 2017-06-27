package it.univr.cd.store;

import java.sql.*;
import java.text.*;
import java.util.Calendar;

public class Model {
	// ATTRIBUTI
	
	// COSTRUTTORE
	public Model(){
		super();
	}
	
	// METODI
	private String getConnectionServer(){
		String server   = "37.59.212.144";
		String database = "id367xdk";
		return "jdbc:postgresql://"+server+":5432/"+database;
	}
	
	private String getConnectionUser(){
		String user = "postgres";
		return user;
	}
	
	private String getConnectionPwd(){
		String pwd = "apassword";
		return pwd;
	}
	
	public int login(String user_id, String pwd) throws ClassNotFoundException,SQLException,ParseException {
		// Caricamento driver
		Class.forName("org.postgresql.Driver");
		
		// Creazione connessione
		try (Connection con=DriverManager.getConnection(getConnectionServer(),getConnectionUser(),getConnectionPwd())) {
			
			// Interrogazione e stampa
			try(Statement st=con.createStatement()) {
				PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) AS rowcount "
														   + "FROM cliente "
														   + "WHERE user_id=? AND pwd=?");
				pst.setString(1, user_id);
				pst.setString(2, pwd);
				ResultSet rs = pst.executeQuery();
				rs.next();
				int size = rs.getInt("rowcount");
				
				if (size==1){
					pst = con.prepareStatement("SELECT id AS id_cliente "
							                 + "FROM cliente "
							                 + "WHERE user_id=? AND pwd=?");
					pst.setString(1, user_id);
					pst.setString(2, pwd);
					rs = pst.executeQuery();
					rs.next();
					return rs.getInt("id_cliente");
				}
				else
					return 0;
				
			}catch(SQLException e) {
				System.out.println("Errore durante estrazione dati: " + e.getMessage());
				return 0;
			}
				
		}catch(SQLException e) {
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
			return 0;
		}
	}
	
	public Object[][] getCatalogo() throws ClassNotFoundException,SQLException,ParseException {
		// Caricamento driver
		Class.forName("org.postgresql.Driver");
		
		// Creazione connessione
		try (Connection con=DriverManager.getConnection(getConnectionServer(),getConnectionUser(),getConnectionPwd())) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			// Interrogazione e stampa
			try(Statement st=con.createStatement()) {
				ResultSet rs = st.executeQuery("SELECT COUNT(*) AS rowcount FROM disco");
				rs.next();
				int size = rs.getInt("rowcount");
				
				rs = st.executeQuery("SELECT id,titolo,prezzo,data_sito FROM disco");
				Object[][] obj = new Object[size][4];
				int i = 0;
				while (rs.next()) {
					obj[i][0] = rs.getInt("id");
					obj[i][1] = rs.getString("titolo");
					obj[i][2] = String.format("%3.2f", rs.getFloat("prezzo"));
					obj[i][3] = sdf.format(rs.getDate("data_sito"));
					i++;
				}
				return obj;		
			}catch(SQLException e) {
				System.out.println("Errore durante estrazione dati: " + e.getMessage());
				return null;
			}
				
		}catch(SQLException e) {
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
			return null;
		}
	}
	
	public String getInfo(int id) throws ClassNotFoundException,SQLException,ParseException {
		String result = "";
		
		// Caricamento driver
		Class.forName("org.postgresql.Driver");
				
		// Creazione connessione
		try (Connection con=DriverManager.getConnection(getConnectionServer(),getConnectionUser(),getConnectionPwd())) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			// Interrogazione e stampa
			try(Statement st=con.createStatement()) {
				PreparedStatement pst = con.prepareStatement("SELECT D.titolo AS titolo,D.prezzo AS prezzo,D.data_sito AS data_sito,A.nome AS artista,D.descrizione AS descrizione,D.quantita AS quantita "
						                                   + "FROM disco AS D JOIN artista AS A ON D.id_artista=A.id "
						                                   + "WHERE D.id=?");
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				rs.next();
				result += String.format("%-24s: %-50s", "Titolo",rs.getString("titolo")) + "\n";
				result += String.format("%-23s: %-3.2f euro", "Prezzo",rs.getFloat("prezzo")) + "\n";
				result += String.format("%-16s: %-20s", "Data Inserimento",sdf.format(rs.getDate("data_sito"))) + "\n";
				result += String.format("%-24s: %-50s", "Artista",rs.getString("artista")) + "\n";
				result += String.format("%-20s: %-50s", "Descrizione",rs.getString("descrizione")) + "\n";
				result += String.format("%-22s: %-50s", "Quantità",rs.getInt("quantita")) + "\n";
				
				result += getGenere(id);
				result += getTitle(id);
				result += getMusicisti(id);
				
				return result;		
			}catch(SQLException e) {
				System.out.println("Errore durante estrazione dati: " + e.getMessage());
				return null;
			}
						
		}catch(SQLException e) {
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
			return null;
		}
	}
	
	public String getTitle(int id) throws ClassNotFoundException,SQLException,ParseException {
		String result = "";
		
		// Caricamento driver
		Class.forName("org.postgresql.Driver");
				
		// Creazione connessione
		try (Connection con=DriverManager.getConnection(getConnectionServer(),getConnectionUser(),getConnectionPwd())) {

			// Interrogazione e stampa
			try(Statement st=con.createStatement()) {
				PreparedStatement pst = con.prepareStatement("SELECT T.nome_canzone AS nome_canzone "
														   + "FROM titolo AS T JOIN disco AS D ON T.id_disco=D.id "
														   + "WHERE D.id=?");
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				int i = 1;
				result += String.format("%-22s ", "Canzoni") + "\n";
				while(rs.next())
					result += String.format("%24s. %-20s", i++, rs.getString("nome_canzone")) + "\n";
				return result;		
			}catch(SQLException e) {
				System.out.println("Errore durante estrazione dati: " + e.getMessage());
				return null;
			}
						
		}catch(SQLException e) {
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
			return null;
		}
	}
	
	public String getGenere(int id) throws ClassNotFoundException,SQLException,ParseException {
		String result = "";
		
		// Caricamento driver
		Class.forName("org.postgresql.Driver");
				
		// Creazione connessione
		try (Connection con=DriverManager.getConnection(getConnectionServer(),getConnectionUser(),getConnectionPwd())) {

			// Interrogazione e stampa
			try(Statement st=con.createStatement()) {
				PreparedStatement pst = con.prepareStatement("SELECT G.nome AS nome_genere "
														   + "FROM disco_genere AS DG JOIN genere AS G ON DG.id_genere=G.id "
														   + "WHERE DG.id_disco=?");
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				result += String.format("%-22s ", "Genere") + "\n";
				while(rs.next())
					result += String.format("%22s -%-20s", " ", rs.getString("nome_genere")) + "\n";
				return result;		
			}catch(SQLException e) {
				System.out.println("Errore durante estrazione dati: " + e.getMessage());
				return null;
			}
						
		}catch(SQLException e) {
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
			return null;
		}
	}
	
	public String getMusicisti(int id) throws ClassNotFoundException,SQLException,ParseException {
		String result = "";
		
		// Caricamento driver
		Class.forName("org.postgresql.Driver");
				
		// Creazione connessione
		try (Connection con=DriverManager.getConnection(getConnectionServer(),getConnectionUser(),getConnectionPwd())) {

			// Interrogazione e stampa
			try(Statement st=con.createStatement()) {
				PreparedStatement pst = con.prepareStatement("SELECT M.nome_arte AS nome_arte, G.nome AS nome_genere, ST.nome AS strumento, M.anno_nascita AS anno_nascita  "
														   + "FROM suona AS S JOIN musicista AS M ON M.id=S.id_musicista JOIN strumento AS ST ON ST.id=S.id_strumento JOIN genere AS G ON G.id=M.id_genere "
														   + "WHERE S.id_disco=?");
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				int i = 1;
				result += String.format("%-22s ", "Musicisti") + "\n";
				while(rs.next()){
					result += String.format("%24s. %s", i++, rs.getString("nome_arte")) + ": ";
					result += rs.getString("nome_genere") + "-";
					result += rs.getString("strumento") + "-";
					result += rs.getString("anno_nascita") + "\n";
				}
				return result;		
			}catch(SQLException e) {
				System.out.println("Errore durante estrazione dati: " + e.getMessage());
				return null;
			}
						
		}catch(SQLException e) {
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
			return null;
		}
	}
	
	public Object[][] getCarrello() throws ClassNotFoundException,SQLException,ParseException {
		// Caricamento driver
		Class.forName("org.postgresql.Driver");
		
		// Creazione connessione
		try (Connection con=DriverManager.getConnection(getConnectionServer(),getConnectionUser(),getConnectionPwd())) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			// Interrogazione e stampa
			try(Statement st=con.createStatement()) {
				PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) AS rowcount "
											               + "FROM carrello_disco AS CD JOIN carrello AS C ON CD.id_carrello=C.id "
											               + "WHERE C.id_cliente=?");
				pst.setInt(1, Control.getUserId());
				ResultSet rs = pst.executeQuery();
				rs.next();
				int size = rs.getInt("rowcount");
				
				pst = con.prepareStatement("SELECT D.id AS id, D.titolo AS titolo, D.prezzo AS prezzo, D.data_sito AS data_sito "
			               				 + "FROM carrello_disco AS CD JOIN carrello AS C ON CD.id_carrello=C.id JOIN disco AS D ON D.id=CD.id_disco "
			               				 + "WHERE C.id_cliente=? "
			               				 + "ORDER BY D.id");
				pst.setInt(1, Control.getUserId());
				rs = pst.executeQuery();
				Object[][] obj = new Object[size][4];
				int i = 0;
				while (rs.next()) {
					obj[i][0] = rs.getInt("id");
					obj[i][1] = rs.getString("titolo");
					obj[i][2] = String.format("%3.2f", rs.getFloat("prezzo"));
					obj[i][3] = sdf.format(rs.getDate("data_sito"));
					i++;
				}
				return obj;		
			}catch(SQLException e) {
				System.out.println("EErrore durante estrazione dati: " + e.getMessage());
				return null;
			}
				
		}catch(SQLException e) {
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
			return null;
		}
	}
	
	public boolean isCarrello() throws ClassNotFoundException,SQLException,ParseException {
		// Caricamento driver
		Class.forName("org.postgresql.Driver");
		
		// Creazione connessione
		try (Connection con=DriverManager.getConnection(getConnectionServer(),getConnectionUser(),getConnectionPwd())) {
			
			// Interrogazione e stampa
			try(Statement st=con.createStatement()) {
				PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) AS rowcount "
											               + "FROM carrello "
											               + "WHERE id_cliente=?");
				pst.setInt(1, Control.getUserId());
				ResultSet rs = pst.executeQuery();
				rs.next();
				int size = rs.getInt("rowcount");
				
				if (size>0)
					return true;
				else
					return false;		
			}catch(SQLException e) {
				System.out.println("Errore durante estrazione dati: " + e.getMessage());
				return false;
			}
				
		}catch(SQLException e) {
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
			return false;
		}
	}
	
	public int rowCarrello() throws ClassNotFoundException,SQLException,ParseException {
		// Caricamento driver
		Class.forName("org.postgresql.Driver");
		
		// Creazione connessione
		try (Connection con=DriverManager.getConnection(getConnectionServer(),getConnectionUser(),getConnectionPwd())) {
			
			// Interrogazione e stampa
			try(Statement st=con.createStatement()) {
				PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) AS rowcount "
											               + "FROM carrello_disco AS CD JOIN carrello AS C ON C.id=CD.id_carrello "
											               + "WHERE C.id_cliente=?");
				pst.setInt(1, Control.getUserId());
				ResultSet rs = pst.executeQuery();
				rs.next();
				int size = rs.getInt("rowcount");
				
				return size;		
			}catch(SQLException e) {
				System.out.println("Errore durante estrazione dati: " + e.getMessage());
				return 0;
			}
				
		}catch(SQLException e) {
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
			return 0;
		}
	}
	
	public void createCarrello() throws ClassNotFoundException,SQLException,ParseException {
		// Caricamento driver
		Class.forName("org.postgresql.Driver");
		
		// Creazione connessione
		try (Connection con=DriverManager.getConnection(getConnectionServer(),getConnectionUser(),getConnectionPwd())) {
			
			// Inserimento dati
			try (PreparedStatement pst=con.prepareStatement("INSERT INTO carrello(id_cliente) VALUES (?)")) {
				pst.clearParameters();
				pst.setInt(1, Control.getUserId());
				pst.executeUpdate();
			}catch(SQLException e) {
				System.out.println("Errore durante inserimento dati: " + e.getMessage());
				return;
			}
				
		}catch(SQLException e) {
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
		}
	}
	
	public void setCarrello(int id_disco) throws ClassNotFoundException,SQLException,ParseException {
		// Caricamento driver
		Class.forName("org.postgresql.Driver");
		int id_carrello = 0;
		
		// Creazione connessione
		try (Connection con=DriverManager.getConnection(getConnectionServer(),getConnectionUser(),getConnectionPwd())) {
			
			// Interrogazione e stampa
			try(Statement st=con.createStatement()) {
				PreparedStatement pst = con.prepareStatement("SELECT id AS id_carrello "
														   + "FROM carrello "
														   + "WHERE id_cliente=?");
				pst.setInt(1, Control.getUserId());
				ResultSet rs = pst.executeQuery();
				rs.next();
				id_carrello = rs.getInt("id_carrello");
			}catch(SQLException e) {
				System.out.println("Errore durante estrazione dati: " + e.getMessage());
			}
			
			// Inserimento dati
			try (PreparedStatement pst=con.prepareStatement("INSERT INTO carrello_disco(id_carrello,id_disco) VALUES (?,?)")) {
				pst.clearParameters();
				pst.setInt(1, id_carrello);
				pst.setInt(2, id_disco);
				pst.executeUpdate();
			}catch(SQLException e) {
				System.out.println("Errore durante inserimento dati: " + e.getMessage());
				return;
			}
				
		}catch(SQLException e) {
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
		}
	}
	
	public void deleteDiscoCarrello(int id_disco) throws ClassNotFoundException,SQLException,ParseException {
		// Caricamento driver
		Class.forName("org.postgresql.Driver");
		int id_carrello = 0;
		
		// Creazione connessione
		try (Connection con=DriverManager.getConnection(getConnectionServer(),getConnectionUser(),getConnectionPwd())) {
			
			// Interrogazione e stampa
			try(Statement st=con.createStatement()) {
				PreparedStatement pst = con.prepareStatement("SELECT id AS id_carrello "
														   + "FROM carrello "
														   + "WHERE id_cliente=?");
				pst.setInt(1, Control.getUserId());
				ResultSet rs = pst.executeQuery();
				rs.next();
				id_carrello = rs.getInt("id_carrello");
			}catch(SQLException e) {
				System.out.println("Errore durante estrazione dati: " + e.getMessage());
			}
			
			// Eliminazione dati
			try (PreparedStatement pst=con.prepareStatement("DELETE FROM carrello_disco WHERE id_carrello=? AND id_disco=?")) {
				pst.clearParameters();
				pst.setInt(1, id_carrello);
				pst.setInt(2, id_disco);
				pst.executeUpdate();
			}catch(SQLException e) {
				System.out.println("Errore durante eliminazione dati: " + e.getMessage());
				return;
			}
				
		}catch(SQLException e) {
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
		}
	}
	
	public void deleteCarrello() throws ClassNotFoundException,SQLException,ParseException {
		// Caricamento driver
		Class.forName("org.postgresql.Driver");
		int id_carrello = 0;
		
		// Creazione connessione
		try (Connection con=DriverManager.getConnection(getConnectionServer(),getConnectionUser(),getConnectionPwd())) {
			
			// Interrogazione e stampa
			try(Statement st=con.createStatement()) {
				PreparedStatement pst = con.prepareStatement("SELECT id AS id_carrello "
														   + "FROM carrello "
														   + "WHERE id_cliente=?");
				pst.setInt(1, Control.getUserId());
				ResultSet rs = pst.executeQuery();
				rs.next();
				id_carrello = rs.getInt("id_carrello");
			}catch(SQLException e) {
				System.out.println("Errore durante estrazione dati: " + e.getMessage());
			}
			
			// Eliminazione dati carrello-disco
			try (PreparedStatement pst=con.prepareStatement("DELETE FROM carrello_disco WHERE id_carrello=?")) {
				pst.clearParameters();
				pst.setInt(1, id_carrello);
				pst.executeUpdate();
			}catch(SQLException e) {
				System.out.println("Errore durante eliminazione dati: " + e.getMessage());
				return;
			}
			
			// Eliminazione carrello
			try (PreparedStatement pst=con.prepareStatement("DELETE FROM carrello WHERE id=?")) {
				pst.clearParameters();
				pst.setInt(1, id_carrello);
				pst.executeUpdate();
			}catch(SQLException e) {
				System.out.println("Errore durante eliminazione dati: " + e.getMessage());
				return;
			}
				
		}catch(SQLException e) {
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
		}
	}
	
	public String getTotaleCarrello() throws ClassNotFoundException,SQLException,ParseException {
		// Caricamento driver
		Class.forName("org.postgresql.Driver");
		int id_carrello = 0;
		String tot_carrello = "";
		
		// Creazione connessione
		try (Connection con=DriverManager.getConnection(getConnectionServer(),getConnectionUser(),getConnectionPwd())) {
			
			// Interrogazione e stampa
			try(Statement st=con.createStatement()) {
				PreparedStatement pst = con.prepareStatement("SELECT id AS id_carrello "
														   + "FROM carrello "
														   + "WHERE id_cliente=?");
				pst.setInt(1, Control.getUserId());
				ResultSet rs = pst.executeQuery();
				rs.next();
				id_carrello = rs.getInt("id_carrello");
				
				pst = con.prepareStatement("SELECT SUM(D.prezzo) AS tot_carrello "
						                 + "FROM carrello_disco AS CD JOIN disco AS D ON CD.id_disco=D.id "
						                 + "WHERE CD.id_carrello=?");
				pst.setInt(1, id_carrello);
				rs = pst.executeQuery();
				rs.next();
				tot_carrello = String.format("%3.2f", rs.getFloat("tot_carrello"));
				return tot_carrello;
			}catch(SQLException e) {
				System.out.println("EErrore durante estrazione dati: " + e.getMessage());
				return null;
			}
				
		}catch(SQLException e) {
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
			return null;
		}
	}
	
	public void createOrdine(String mod_pagamento,String mod_consegna) throws ClassNotFoundException,SQLException,ParseException {
		// Caricamento driver
		Class.forName("org.postgresql.Driver");
		int id_ordine = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
		
		// Creazione connessione
		try (Connection con=DriverManager.getConnection(getConnectionServer(),getConnectionUser(),getConnectionPwd())) {
			
			// Inserimento dati ordine
			try (PreparedStatement pst=con.prepareStatement("INSERT INTO ordine(id_cliente,prezzo,data_acquisto,ora_acquisto,modalita_pagamento,modalita_consegna) VALUES (?,?,?,?,?,?)")) {
				pst.clearParameters();
				pst.setInt(1, Control.getUserId());
				String tot = getTotaleCarrello().replace(",", ".");
				if (mod_consegna.equals("CORRI"))
					pst.setFloat(2, (Float.parseFloat(tot)+(float)5.00));
				else
					pst.setFloat(2, Float.parseFloat(tot));
				pst.setDate(3, new Date( sdf.parse(sdf.format(Calendar.getInstance().getTime())).getTime() ));
				pst.setTime(4, new Time( stf.parse(stf.format(Calendar.getInstance().getTime())).getTime() ));
				pst.setString(5, mod_pagamento);
				pst.setString(6, mod_consegna);
				pst.executeUpdate();
			}catch(SQLException e) {
				System.out.println("Errore durante inserimento dati: " + e.getMessage());
				return;
			}
			
			// select last id ordine
			try(Statement st=con.createStatement()) {
				PreparedStatement pst = con.prepareStatement("SELECT id AS id_ordine "
														   + "FROM ordine "
														   + "WHERE id_cliente=? "
														   + "ORDER BY id DESC "
														   + "LIMIT 1");
				pst.setInt(1, Control.getUserId());
				ResultSet rs = pst.executeQuery();
				rs.next();
				id_ordine = rs.getInt("id_ordine");
				
			}catch(SQLException e) {
				System.out.println("Errore durante estrazione dati: " + e.getMessage());
			}
			
			// inserimento dati ordine-disco
			try(Statement st=con.createStatement()) {
				PreparedStatement pst = con.prepareStatement("SELECT CD.id_disco AS id_disco "
										      			   + "FROM carrello_disco AS CD JOIN carrello AS C ON CD.id_carrello=C.id "
										      			   + "WHERE C.id_cliente=? "
										      			   + "ORDER BY CD.id_disco");
				pst.setInt(1, Control.getUserId());
				ResultSet rs = pst.executeQuery();
				while (rs.next()){
					try (PreparedStatement pst2=con.prepareStatement("INSERT INTO ordine_disco(id_ordine,id_disco) VALUES (?,?)")) {
						pst2.clearParameters();
						pst2.setInt(1, id_ordine);
						pst2.setInt(2, rs.getInt("id_disco"));
						pst2.executeUpdate();
					}catch(SQLException e) {
						System.out.println("Errore durante inserimento dati: " + e.getMessage());
						return;
					}
				}
			}catch(SQLException e) {
				System.out.println("Errore durante estrazione dati: " + e.getMessage());
			}
				
		}catch(SQLException e) {
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
		}
	}
	
	public Object[][] getOrdini() throws ClassNotFoundException,SQLException,ParseException {
		// Caricamento driver
		Class.forName("org.postgresql.Driver");
		
		// Creazione connessione
		try (Connection con=DriverManager.getConnection(getConnectionServer(),getConnectionUser(),getConnectionPwd())) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");

			// Interrogazione e stampa
			try(Statement st=con.createStatement()) {
				PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) AS rowcount FROM ordine WHERE id_cliente=?");
				pst.setInt(1, Control.getUserId());
				ResultSet rs = pst.executeQuery();
				rs.next();
				int size = rs.getInt("rowcount");
				
				pst = con.prepareStatement("SELECT id,prezzo,data_acquisto,ora_acquisto,indirizzo_ip,modalita_pagamento,modalita_consegna "
						                 + "FROM ordine "
						                 + "WHERE id_cliente=?");
				pst.setInt(1, Control.getUserId());
				rs = pst.executeQuery();
				Object[][] obj = new Object[size][7];
				int i = 0;
				while (rs.next()) {
					obj[i][0] = rs.getInt("id");
					obj[i][1] = String.format("%3.2f", rs.getFloat("prezzo"));
					obj[i][2] = sdf.format(rs.getDate("data_acquisto"));
					obj[i][3] = stf.format(rs.getTime("ora_acquisto"));
					obj[i][4] = rs.getString("indirizzo_ip");
					String str_pagamento = "";
					String pagamento = rs.getString("modalita_pagamento");
					if (pagamento.equals("BONIF"))
						str_pagamento = "BONIFICO";
					if (pagamento.equals("CARTA"))
						str_pagamento = "CARTA DI CREDITO";
					if (pagamento.equals("PAYPA"))
						str_pagamento = "PAYPAL";
					obj[i][5] = str_pagamento;
					String str_consegna = "";
					String consegna  = rs.getString("modalita_consegna");
					if (consegna.equals("CORRI"))
						str_consegna = "CORRIERE";
					if (consegna.equals("POSTA"))
						str_consegna = "POSTA";
					obj[i][6] = str_consegna;
					i++;
				}
				return obj;		
			}catch(SQLException e) {
				System.out.println("Errore durante estrazione dati: " + e.getMessage());
				return null;
			}
				
		}catch(SQLException e) {
			System.out.println("Problema durante la connessione iniziale alla base di dati: " + e.getMessage());
			return null;
		}
	}
}