  package de.hwg_lu.bwi520.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class AppInstallTables {

	Connection dbConn;
	
	public static void main(String[] args) throws SQLException {
		AppInstallTables myApp = new AppInstallTables();
		myApp.dbConn = new PostgreSQLAccess().getConnection();
		myApp.doSomething();
	}

	public void doSomething() throws SQLException {
		this.executeUpdateWithoutParms("DROP TABLE IF EXISTS Bericht cascade");
		this.executeUpdateWithoutParms("DROP TABLE IF EXISTS Anfrage cascade");
		this.executeUpdateWithoutParms("DROP TABLE IF EXISTS Rückgabe cascade");
		this.executeUpdateWithoutParms("DROP TABLE IF EXISTS Ausleihen cascade");
		this.executeUpdateWithoutParms("DROP TABLE IF EXISTS Gegenstände cascade");
		this.executeUpdateWithoutParms("DROP TABLE IF EXISTS Mitarbeiter cascade");
		this.createTableMitarbeiter();
		this.insertDataMitarbeiter();
		this.createTableAnfrage();
		this.createTableGegenstaende(); 
		this.createTableAusleihen();
		this.createTableRückgabe();
		this.insertDataGegenstände();
		this.insertDataAnfragen();
		this.createTableBericht();
		
	
	}
	public void executeUpdateWithoutParms(String sql) throws SQLException{
		System.out.println(sql);
		this.dbConn.prepareStatement(sql).executeUpdate();
	}
	public void createTableMitarbeiter() throws SQLException {
		this.executeUpdateWithoutParms(
				"CREATE TABLE Mitarbeiter (" +
						"Personalnummer SERIAL NOT NULL primary key," +
                        "Vorname VARCHAR(100) NOT NULL," +
                        "Nachname VARCHAR(100) NOT NULL," +
                        "Email VARCHAR(100)NOT NULL," +
                        "Benutzername VARCHAR(100) NOT NULL unique," + 
                        "Password VARCHAR(255) NOT NULL," +
                        "Telefonnummer bigint NOT NULL," +  
                        "Rolle VARCHAR(50) NOT NULL, " +
                        "Abteilung VARCHAR(100) NOT NULL," +
                        "active  boolean not null default true," +
                        "admin  boolean not null default false)"
                       
		);		
	}
	
	public void insertDataMitarbeiter() throws SQLException {
		this.executeUpdateWithoutParms(
			    "INSERT INTO Mitarbeiter (Vorname, Nachname, Email, Benutzername, Password, Telefonnummer, Rolle, Abteilung, active, admin) VALUES " +
			    "('Emmanuel', 'Sonna', 'emmanuel.sonna@elalogroup.de', 'emmanuel.sonna', 'password1234', 06212001, 'Techniker', 'IT', TRUE, FALSE), " +
			    "('Nina', 'Becker', 'nina.becker@elalogroup.de', 'nina.becker', 'passw0rd890', 06212002, 'Techniker', 'IT', TRUE, FALSE), " +
			    "('Laurel', 'Pangui', 'laurel.pangui@elalogroup.de', 'laurel.pangui', '1234567', 06212003, 'Super Techniker', 'IT', TRUE, TRUE), " +
			    "('Loic', 'Fodjo', 'loic.fodjo@elalogroup.de', 'loic.fodjo', '1234567', 06212004, 'Super Techniker', 'IT', TRUE, TRUE), " +
			    "('David', 'Weber', 'david.weber@elalogroup.de', 'david.weber', 'securepass000', 06212005, 'Techniker', 'IT', TRUE, FALSE), " +
			    "('Laura', 'Schwarz', 'laura.schwarz@elalogroup.de', 'laura.schwarz', 'securepass789', 06212006, 'Mitarbeiter', 'HR', TRUE, FALSE), " +
			    "('Oliver', 'Wagner', 'oliver.wagner@elalogroup.de', 'oliver.wagner', 'password12345', 06212007, 'Mitarbeiter', 'Finance', TRUE, FALSE), " +
			    "('Rita', 'Lang', 'rita.lang@elalogroup.de', 'rita.lang', 'adminpass7890', 06212008, 'Mitarbeiter', 'Sales', FALSE, FALSE), " +
			    "('Ben', 'Schmidt', 'ben.schmidt@elalogroup.de', 'ben.schmidt', 'mysecurepass', 06212009, 'Mitarbeiter', 'R&D', FALSE, FALSE), " +
			    "('Xander', 'Wolf', 'xander.wolf@elalogroup.de', 'xander.wolf', 'adminpass1234', 06212010, 'Mitarbeiter', 'HR', FALSE, FALSE), " +
			    "('Eva', 'Wagner', 'eva.wagner@elalogroup.de', 'eva.wagner', 'pass7891011', 06212011, 'Mitarbeiter', 'Sales', FALSE, FALSE), " +
			    "('Petra', 'Müller', 'petra.mueller@elalogroup.de', 'petra.mueller', 'securepass2023', 06212012, 'Mitarbeiter', 'Marketing', TRUE, FALSE), " +
			    "('Thomas', 'Schneider', 'thomas.schneider@elalogroup.de', 'thomas.schneider', 'securepass2023', 06212013, 'Mitarbeiter', 'Finance', FALSE, FALSE), " +
			    "('Mona', 'Schulz', 'mona.schulz@elalogroup.de', 'mona.schulz', 'password2024', 06212014, 'Mitarbeiter', 'R&D', FALSE, FALSE), " +
			    "('Stefan', 'Meier', 'stefan.meier@elalogroup.de', 'stefan.meier', 'simplepass', 06212015, 'Mitarbeiter', 'Marketing', TRUE, FALSE)"
			);
		
	}
	
	public void createTableAnfrage() throws SQLException {
		this.executeUpdateWithoutParms(
				"CREATE TABLE ANFRAGE (" +
                        "Anfrage_id SERIAL NOT NULL PRIMARY KEY," +
                        "Anfragetype varchar(30) NOT NULL," +
                        "Kategorie varchar(70) NOT NULL," +
                        "Beschreibung TEXT NOT NULL," +                    
                        "Festellungsdatum varchar(30) NOT NULL," +  
                        "erstellt_am TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,"+
                        "Status VARCHAR(30) not null default 'Reported'," +
                        "Personalnummer int not null," +
                        "last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                        "Foreign key (Personalnummer) references mitarbeiter(Personalnummer) ON DELETE CASCADE)"
           
                       
		);
	}
	
	
	public void createTableGegenstaende() throws SQLException {
		this.executeUpdateWithoutParms(
			    "CREATE TABLE Gegenstände (" +
			    "Produktnummer SERIAL NOT NULL PRIMARY KEY, " +
			    "Name VARCHAR(100) NOT NULL, " +
			    "Beschreibung TEXT NOT NULL, " +
			    "Verfügbarkeit BOOLEAN NOT NULL DEFAULT TRUE, " +
			    "Status VARCHAR(100) NOT NULL DEFAULT 'verfügbar'" +
			    ")"
			);
	
	}
	
	public void createTableAusleihen() throws SQLException {
		this.executeUpdateWithoutParms(
			    "CREATE TABLE Ausleihen (" +
			    "AusleihID SERIAL NOT NULL PRIMARY KEY, " +
			    "Produktnummer INT NOT NULL, " +
			    "MitarbeiterID INT NOT NULL, " +
			    "Leihdatum TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP, " +
			    "FOREIGN KEY (Produktnummer) REFERENCES Gegenstände(Produktnummer), " +
			    "FOREIGN KEY (MitarbeiterID) REFERENCES Mitarbeiter(Personalnummer) ON DELETE CASCADE" +
			    ")"
			);

	
	}
	
	public void createTableRückgabe() throws SQLException {
		this.executeUpdateWithoutParms(
			    "CREATE TABLE Rückgabe (" +
			    "RückgabeID SERIAL NOT NULL PRIMARY KEY, " +
			    "Produktnummer INT NOT NULL, " +
			    "MitarbeiterID INT NOT NULL, " +
			    "Rückgabedatum TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP," +
			    "FOREIGN KEY (Produktnummer) REFERENCES Gegenstände(Produktnummer), " +
			    "FOREIGN KEY (MitarbeiterID) REFERENCES Mitarbeiter(Personalnummer) ON DELETE CASCADE" +
			    ")"
			);

	
	}
	
	public void createTableBericht() throws SQLException {
		this.executeUpdateWithoutParms(
				"CREATE TABLE Bericht (" +
                        "	BerichtId SERIAL NOT NULL PRIMARY KEY,"
                        + "	anfrage_id INT NOT NULL,"
                        + "	Techniker_id INT NOT NULL,"
                        + "	Datum DATE NOT NULL DEFAULT CURRENT_DATE,"
                        + "	LoesungsDetails TEXT NOT NULL,"
                        + "	FOREIGN KEY (anfrage_id) REFERENCES Anfrage(anfrage_id),"
                        + "	FOREIGN KEY (Techniker_id) REFERENCES Mitarbeiter(Personalnummer) ON DELETE CASCADE)"
		);		
	}
	
	public void insertDataAnfragen() throws SQLException {
		this.executeUpdateWithoutParms(
			    "INSERT INTO ANFRAGE (Anfragetype, Kategorie, Beschreibung, Festellungsdatum, Status, Personalnummer) VALUES " +
			   // "('Hardware', 'Fehler melden', 'Der Laptop startet nicht.', '2024-09-19', 'Reported', 6), " +
			    "('Software', 'Verbesserungsvorschlag', 'Neue Funktion für das CRM-System.', '2024-09-18', 'Reported', 12), " +
			    "('Hardware', 'Fehler melden', 'Monitor zeigt kein Bild.', '2024-09-17', 'Reported', 15), " +
			    "('Software', 'Fehler melden', 'Excel stürzt ab, wenn große Dateien geöffnet werden.', '2024-09-16', 'Reported', 7)"
			);

	}

	
	public void insertDataGegenstände() throws SQLException {
		this.executeUpdateWithoutParms(
			    "INSERT INTO Gegenstände (Name, Beschreibung, Verfügbarkeit, Status) VALUES " +
			    "('Laptop Dell XPS 13', 'Ultrabook mit 13 Zoll Display', TRUE, 'verfügbar'), " +
			    "('MacBook Pro', 'Apple Laptop 16 Zoll', TRUE, 'verfügbar'), " +
			    "('Surface Pro 7', 'Microsoft Tablet mit Tastatur', TRUE, 'verfügbar'), " +
			    "('HP Spectre x360', 'Convertible Laptop 14 Zoll', TRUE, 'verfügbar'), " +
			    "('Lenovo ThinkPad', 'Business Laptop 15 Zoll', TRUE, 'verfügbar'), " +
			    "('iPad Pro', 'Apple Tablet 11 Zoll', TRUE, 'verfügbar'), " +
			    "('Dell Monitor', '27 Zoll 4K Monitor', TRUE, 'verfügbar'), " +
			    "('Logitech Maus MX', 'Kabellose Maus', TRUE, 'verfügbar'), " +
			    "('HP Drucker', 'Multifunktionsdrucker', TRUE, 'verfügbar'), " +
			    "('Samsung Galaxy S21', 'Smartphone', TRUE, 'verfügbar'), " +
			    "('USB-C Dockingstation', 'USB-C Dockingstation für Laptops', TRUE, 'verfügbar'), " +
			    "('Jabra Headset', 'Bluetooth Headset', TRUE, 'verfügbar'), " +
			    "('Canon Kamera', 'Digitalkamera', TRUE, 'verfügbar'), " +
			    "('Lenovo Tablet', 'Android Tablet 10 Zoll', TRUE, 'verfügbar'), " +
			    "('Dell XPS Desktop', 'Desktop-PC', TRUE, 'verfügbar')"
			);

	}
	
}
