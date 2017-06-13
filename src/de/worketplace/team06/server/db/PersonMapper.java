package de.worketplace.team06.server.db;

import java.sql.*;
import java.util.Vector;
import de.worketplace.team06.shared.bo.*;

public class PersonMapper {
	
	/**
     * Die Klasse PersonMapper wird nur einmal instantiiert. Man spricht hierbei
     * von einem sogenannten <b>Singleton</b>.
     * <p>
     * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
     * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
     * einzige Instanz dieser Klasse.
     * 
     * @author Thies
     * @author Theresa
     */
	
	
	private static PersonMapper personMapper = null;
	
	/**
	   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   * 
	   * @author Thies 
	   */
	
	protected PersonMapper() {
		
	}

	/**
	 * Diese Methode stellt sicher, dass die Singleton-Eigenschaft gegeben ist. Sie sorgt
	 * dafür, dass nur eine einzige Instanz der PersonMapper-Klasse existiert. 
	 * PersonMapper sollte nicht über den New-Operator, sondern über den 
	 * Aufruf dieser statischen Methode.
	 * 
	 * @return PersonMapper
	 * @author Thies
	 * @author Theresa
	 */
	public static PersonMapper personMapper() {
		if (personMapper == null) {
			personMapper = new PersonMapper();
		}
		
		return personMapper;
	}		
	
	/**
	 * Einfuegen eines Team-Objektes in die Datenbank. Dabei wird auch der Primaerschluessel
	 * des uebergebenen Objektes geprueft und ggf. berichtigt.
	 * 
	 * @param p
	 * @return person
	 * @author Thies
	 * @author Theresa
	 */
	
	public Person insert(Person p) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			/** 
			 * Abfragen des hoechsten Primaerschluesselwertes, die aktuelle ID 
			 *wird dann um 1 erhoet und an an p vergeben
			*/
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM orgaunit ");
			
			if (rs.next()) {
				
				p.setID(rs.getInt("maxid") + 1);
				
				con.setAutoCommit(false);
				stmt = con.createStatement();
				/**
				 * Einfuegeoption, damit das neue Person-Tupel in die Datenbank eingefuegt werden kann
				 */
				stmt.executeUpdate("INSERT INTO orgaunit (id, created, googleID, description, type) "
									+ "VALUES (" + p.getID() + ",'" + p.getCreated() + "','" 
									+ p.getGoogleID() +  "','" + p.getDescription() +  "','"
									+ p.getType() + "')");
				stmt.executeUpdate("INSERT INTO person (id, created, firstName, lastName, street, zipcode, city) "
									+ "VALUES (" + p.getID() + ",'" + p.getCreated() + "','"
									+ p.getFirstName() + "','" + p.getLastName() + "','" + p.getStreet() + "'," 
									+ p.getZipcode() + ",'" + p.getCity() + "')");
				con.commit();
			}
		}
		catch (SQLException e2) {
			try {
				System.out.println("Die SQL Transaktion konnte nicht vollständig ausgeführt werden. "
						+ "Es wird versucht die Transaktion rückgängig zu machen!");
				con.rollback();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  finally {
				 
				  e2.printStackTrace();
			}	
		}
		return p;
	}
	
	/**
	 * Diese Methode findet ein Person-Objekt, anhand der übergebenen Google-ID 
	 * @param googleID
	 * @return Person-Objekt 
	 */
	public Person findByGoogleID(String googleID) {
		Connection con = DBConnection.connection();
		
		try {						
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM orgaunit INNER JOIN person "
											+ "ON orgaunit.id = person.id "
											+ "WHERE orgaunit.googleID = '" + googleID + "'");		
			
			if (rs.next()) {
				Person p = new Person();
				p.setID(rs.getInt("id"));
				p.setCreated(rs.getTimestamp("created"));
				p.setGoogleID(googleID);
				p.setDescription(rs.getString("description"));
				p.setPartnerProfileID(rs.getInt("partnerprofileID"));
				p.setType(rs.getString("type"));
				
				p.setFirstName(rs.getString("firstName"));
				p.setLastName(rs.getString("lastName"));
				p.setStreet(rs.getString("street"));
				p.setZipcode(rs.getInt("zipcode"));
				p.setCity(rs.getString("city"));
				return p;
			}			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}	
	
	/**
     * Methode ermoeglicht, dass ein Person-Objekt in der Datenbank aktualisiert werden kann.
     * 
     * @param orgaUnit 
     * @return Person
     */
	public void update(Person p) {
		Connection con = DBConnection.connection();

	    try {
	    	Statement stmt = con.createStatement();
	    	//Updaten einer Person :) 
	    	stmt.executeUpdate("UPDATE orgaunit, person SET"
	    						+ " orgaunit.description='" + p.getDescription() +
	    						"', orgaunit.partnerprofileID= " + p.getPartnerProfileID() +
	    						", person.firstName= '" + p.getFirstName() +
	    						"', person.lastName= '" + p.getLastName() + 
	    						"', person.street= '" + p.getStreet() + 
	    						"', person.city= '" + p.getCity() + 
	    						"', person.zipcode= " + p.getZipcode() + 
	    						" WHERE orgaunit.id= " + p.getID() + 
	    						" AND person.id= " + p.getID()); 
	    			
	    }
	    catch (SQLException e2) {
				  e2.printStackTrace();		
	    }
		
	}	
	
	/**
     * Loeschen eines Person-Objektes aus der Datenbank
     * @param person
     */
	
	public void delete(Person p) {
		Connection con = DBConnection.connection();

	    try {
	    	Statement stmt = con.createStatement();
	    	//Löschen der Person aus der Tabelle orgaunit und person.
	    	stmt.executeUpdate("DELETE orgaunit, person FROM orgaunit INNER JOIN person "
	    			+ "ON orgaunit.id = person.id WHERE orgaunit.id= " + p.getID());
	    	
	    }
	    catch (SQLException e2) {
				  e2.printStackTrace();		
	    }
		
	}

	/**
	 * Suchen einer Person mit vorgegebener PersonID. Duch die Eindeutigkeit der ID, 
	 * wird genau ein Objekt zurück gegeben. 
	 * 
	 * @param ouid
	 * @return Person-Objekt, das der übergebenen ID entspricht
	 */
	public Person findByID(int ouid) {
		Connection con = DBConnection.connection();
		
		try {						
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM orgaunit INNER JOIN person "
											+ "ON orgaunit.id = person.id "
											+ "WHERE orgaunit.id = " + ouid);	
			/**
			 * es kann maximal nur ein Tupel zurueckgegeben werden, da ID Primaerschluessel ist, 
			 * dann wird geprueft, ob für diese ID ein Tupel in der DB vorhanden ist
			 */
			
			
			if (rs.next()) {
				Person p = new Person();
				p.setID(rs.getInt("id"));
				p.setCreated(rs.getTimestamp("created"));
				p.setGoogleID(rs.getString("googleID"));
				p.setDescription(rs.getString("description"));
				p.setPartnerProfileID(rs.getInt("partnerprofileID"));
				p.setType(rs.getString("type"));
				
				p.setFirstName(rs.getString("firstName"));
				p.setLastName(rs.getString("lastName"));
				p.setStreet(rs.getString("street"));
				p.setZipcode(rs.getInt("zipcode"));
				p.setCity(rs.getString("city"));
				return p;
			}			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}	

}
