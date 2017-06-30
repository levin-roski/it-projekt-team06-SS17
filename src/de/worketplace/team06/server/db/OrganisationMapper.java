package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.worketplace.team06.shared.bo.Organisation;

/**
 * Die Mapper-Klasse OrganisationMapper bildet Organisation-Objekte als Datensätze
 * in einer relationalen Datenbank ab. Durch die Bereitstellung verschiedener Methoden
 * können mit deren Hilfe Objekte erzeugt, verändert, gelöscht und ausgelesen werden.
 * Das Mapping erfolgt bidirektional: Objekte können in Datensätze und Datensätze in 
 * Objekte umgewandelt werden.
 * 
 * @see ApplicationMapper
 * @see CallMapper
 * @see EnrollmentMapper
 * @see MarketplaceMapper
 * @see OrgaUnitMapper
 * @see PartnerProfileMapper
 * @see PersonMapper
 * @see ProjectMapper
 * @see PropertyMapper
 * @see RatingMapper
 * @see TeamMapper
 * 
 * @author Patrick
 * @author Theresa
 */
public class OrganisationMapper {
	
	/**
     * Die Klasse OrganisationMapper wird nur einmal instanziiert. Man spricht hierbei
     * von einem sogenannten <b>Singleton</b>.
     * <p>
     * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
     * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
     * einzige Instanz dieser Klasse.
     * 
     * @author Thies
     * @author Theresa
     */

	private static OrganisationMapper organisationMapper = null;
	
	 /**
	   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   * 
	   * @author Thies 
	   */
	
	protected OrganisationMapper(){
		
	}
	
	/**
	 * Diese Methode stellt sicher, dass die Singleton-Eigenschaft gegeben ist. Sie sorgt
	 * dafür, dass nur eine einzige Instanz der OrganisationMapper-Klasse existiert. 
	 * OrganisationMapper sollte nicht über den New-Operator, sondern über den 
	 * Aufruf dieser statischen Methode ausgeführt werden.
	 * 
	 * @return OrganisationMapper
	 * @author Thies
	 * @author Theresa
	 */
	
	public static OrganisationMapper organisationMapper(){
		if (organisationMapper == null){
			organisationMapper = new OrganisationMapper();
		}
		return organisationMapper; 
	}
	
    /**
	 * Einfuegen eines Organisation-Objektes in die Datenbank. Dabei wird auch der Primaerschluessel
	 * des uebergebenen Objektes geprueft und ggf. berichtigt.
	 * 
	 * @param orgaUnit
	 * @return Organisation
	 * @throws  
	 * @author Thies 
	 * @author Theresa
     */
	
    public Organisation insert (Organisation o) {

    	Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			/* 
			 * Abfragen des hoechsten Primaerschluesselwertes, die aktuelle ID 
			 * wird dann um 1 erhoeht und an an o vergeben
			*/
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM orgaunit ");
			
			if (rs.next()) {
				
				o.setID(rs.getInt("maxid") + 1);
		
				con.setAutoCommit(false);
				stmt = con.createStatement();
				// Einfuegeoption, damit das neue Organisation-Tupel in die Datenbank eingefuegt werden kann
				stmt.executeUpdate("INSERT INTO orgaunit (id, created, google_id, description, type) " + "VALUES (" + o.getID() + ",'" + o.getCreated() + "','" + o.getGoogleID() +  "','" + o.getDescription() +  "','" + o.getType() + "')");
				stmt.executeUpdate("INSERT INTO organisation (id, created, name, street, zipcode, city) " + "VALUES (" + o.getID() + ",'" + o.getCreated() + "','" + o.getName() + "','" + o.getStreet() + "'," + o.getZipcode() + ",'" + o.getCity() + "')");
				con.commit();
			}
		}
		catch (SQLException e2) {
			try {
				System.out.println("Die SQL Transaktion konnte nicht vollständig ausgeführt werden. Es wird versucht die Transaktion rückgängig zu machen!");
				con.rollback();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			  finally {
				 
				  e2.printStackTrace();
			}	
		}
		finally{
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return o;
    }
    
    /**
	 * Auslesen eines Organisation-Objektes anhand der übergebenen GoogleID.
	 * 
	 * @param googleID 
	 * @return Organisation-Objekt 
	 * @author Theresa
	 */
	public Organisation findByGoogleID(String googleID) {
		Connection con = DBConnection.connection();
		
		try {						
			Statement stmt = con.createStatement();
			// Verbinden der Tabellen orgaunit und organisation, da jede Organisation eine Organisationseinheit ist
			ResultSet rs = stmt.executeQuery("SELECT * FROM orgaunit INNER JOIN organisation ON"
					+ " orgaunit.id = organisation.id WHERE orgaunit.google_id ='" + googleID + "'");		
			
			if (rs.next()) {
				Organisation o = new Organisation();
				o.setID(rs.getInt("id"));
				o.setCreated(rs.getTimestamp("created"));
				o.setGoogleID(googleID);
				o.setDescription(rs.getString("description"));
				o.setPartnerProfileID(rs.getInt("partnerprofile_id"));
				o.setType(rs.getString("type"));
				
				o.setName(rs.getString("name"));
				o.setStreet(rs.getString("street"));
				o.setZipcode(rs.getInt("zipcode"));
				o.setCity("city");
				return o;
			}			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}	

	/**
     * Methode ermoeglicht, dass ein Organisation-Objekt in der Datenbank aktualisiert werden kann.
     * 
     * @param orgaUnit 
     * @return Organisation
     * @author Theresa
     */
	public void update(Organisation o) {
		Connection con = DBConnection.connection();

	    try {
	    	Statement stmt = con.createStatement();
	    	stmt.executeUpdate("UPDATE orgaunit, organisation SET"
	    						+ " orgaunit.description='" + o.getDescription() +
	    						"', orgaunit.partnerprofile_id= " + o.getPartnerProfileID() +
	    						", organisation.name= '" + o.getName() +
	    						"', organisation.street= '" + o.getStreet() + 
	    						"', organisation.zipcode= " + o.getZipcode() + 
	    						", organisation.city= '" + o.getCity() + 
	    						"' WHERE orgaunit.id= " + o.getID() + 
	    						" AND organisation.id= " + o.getID()); 
	    }
	    catch (SQLException e2) {
				  e2.printStackTrace();		
	    }
		
	}
    
    /**
     * Löschen eines Organisation-Objektes aus der Datenbank.
     * 
     * @param orga
     * @author Theresa
     */
    public void delete(Organisation o) {
		Connection con = DBConnection.connection();
	    try {
	    	Statement stmt = con.createStatement();
	    	//Löschen des Organisation-Objektes aus der Tabelle orgaunit und organisation
	    	stmt.executeUpdate("DELETE orgaunit, organisation FROM orgaunit INNER JOIN organisation ON orgaunit.id = organisation.id WHERE orgaunit.id= " + o.getID());	
	    }
	    catch (SQLException e2) {
				  e2.printStackTrace();		
	    }
	}
    
    /**
	 * Auslesen eines Organisation-Objekts anhand übergebener ID.
	 * Durch die Eindeutigkeit der ID wird genau ein Objekt zurück gegeben. 
	 * 
	 * @param ouid
	 * @return Organisation-Objekt, das der übergebenen ID entspricht
	 * @author Theresa
	 */
    public Organisation findByID(Integer ouid) {
    	Connection con = DBConnection.connection();
		
		try {						
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM orgaunit INNER JOIN "
					+ "organisation ON orgaunit.id = organisation.id WHERE orgaunit.id = " + ouid);		
			/*
			 * es kann maximal nur ein Tupel zurueckgegeben werden, da ID Primaerschluessel ist, 
			 * dann wird geprueft, ob für diese ID ein Tupel in der DB vorhanden ist
			 */
			if (rs.next()) {
				Organisation o = new Organisation();
				o.setID(rs.getInt("id"));
				o.setCreated(rs.getTimestamp("created"));
				o.setGoogleID(rs.getString("google_id"));
				o.setDescription(rs.getString("description"));
				o.setPartnerProfileID(rs.getInt("partnerprofile_id"));
				o.setType(rs.getString("type"));
				
				o.setName(rs.getString("name"));
				o.setStreet(rs.getString("street"));
				o.setZipcode(rs.getInt("zipcode"));
				o.setCity("city");
				return o;
			}			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}

	public Vector<Organisation> findAll() {
		Connection con = DBConnection.connection();
		Vector<Organisation> allOrganisations = new Vector<Organisation>();
		
		try {						
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM orgaunit INNER JOIN "
					+ "organisation ON orgaunit.id = organisation.id");		
		
			while (rs.next()) {
				Organisation o = new Organisation();
				o.setID(rs.getInt("id"));
				o.setCreated(rs.getTimestamp("created"));
				o.setGoogleID(rs.getString("google_id"));
				o.setDescription(rs.getString("description"));
				o.setPartnerProfileID(rs.getInt("partnerprofile_id"));
				o.setType(rs.getString("type"));
				
				o.setName(rs.getString("name"));
				o.setStreet(rs.getString("street"));
				o.setZipcode(rs.getInt("zipcode"));
				o.setCity("city");
				
				allOrganisations.addElement(o);
			}			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return allOrganisations;
	}
   
}
