package de.worketplace.team06.server.db;

import java.sql.*;
import java.util.Vector;

import de.worketplace.team06.shared.bo.*;
/**
 * Die Mapper-Klasse MarketplaceMapper bildet Marketplace-Objekte als Datensätze
 * in einer relationalen Datenbank ab. Durch die Bereitstellung verschiedener Methoden
 * können mit deren Hilfe Objekte erzeugt, verändert, gelöscht und ausgelesen werden.
 * Das Mapping erfolgt bidirektional: Objekte können in Datensätze und Datensätze in 
 * Objekte umgewandelt werden.
 * 
 * @see ApplicationMapper
 * @see CallMapper
 * @see EnrollmentMapper
 * @see OrganisationMapper
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
public class MarketplaceMapper {
	/**
     * Die Klasse MarketplaceMapper wird nur einmal instanziiert. Man spricht hierbei
     * von einem sogenannten <b>Singleton</b>.
     * <p>
     * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
     * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
     * einzige Instanz dieser Klasse.
     * 
     * @author Thies
     * @author Theresa
     */
	private static MarketplaceMapper marketplaceMapper = null;
	
	/**
	   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   * 
	   * @author Thies 
	   */
	protected MarketplaceMapper() {
		
	}
	
	/**
	 * Diese Methode stellt sicher, dass die Singleton-Eigenschaft gegeben ist. Sie sorgt
	 * dafür, dass nur eine einzige Instanz der MarketplaceMapper-Klasse existiert. 
	 * MarketplaceMapper sollte nicht über den New-Operator, sondern über den 
	 * Aufruf dieser statischen Methode ausgeführt werden.
	 * 
	 * @return TeamMapper
	 * @author Thies
	 * @author Theresa
	 */
	public static MarketplaceMapper marketplaceMapper() {
		if (marketplaceMapper == null) {
			marketplaceMapper = new MarketplaceMapper();
		}
		
		return marketplaceMapper;
	}
	
	
	/**
	 * Einfuegen eines Maketplace-Objektes in die Datenbank. Dabei wird auch der Primaerschluessel
	 * des uebergebenen Objektes geprueft und ggf. berichtigt.
	 * 
	 * @param m
	 * @return Marketplace
	 * @author Thies
	 * @author Theresa
	 */
	public Marketplace insert(Marketplace m) {
		
	Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			/*
			 * Abfragen des hoechsten Primaerschluesselwertes, die aktuelle ID 
			 * wird dann um 1 erhoeht und an an m vergeben
			*/
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM marketplace ");
			
			if (rs.next()) {
				
				m.setID(rs.getInt("maxid") + 1);
		
				stmt = con.createStatement();
				//Einfuegeoption, damit das neue Marketplace-Tupel in die Datenbank eingefuegt werden kann
				stmt.executeUpdate("INSERT INTO marketplace (id, created, title, description) " + "VALUES (" + m.getID() + ",'" + m.getCreated() + "','" + m.getTitle() + "','" + m.getDescription() + "')");
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return m;
	}
	/**
	 * Auslesen aller Marktplätze aus der Datenbank.
	 * 
	 * @return Vektor<Marketplace>
	 * @author Theresa
	 */
	public Vector<Marketplace> findAll() {
		
		Connection con = DBConnection.connection();
			
			Vector<Marketplace> result = new Vector<Marketplace>();
			
			try {
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("Select id, created, title, description FROM marketplace " + "ORDER BY id");
				
				
				while (rs.next()) {
					
					Marketplace m = new Marketplace();
					m.setID(rs.getInt("id"));
					m.setCreated(rs.getTimestamp("created"));	
					m.setTitle(rs.getString("title"));
					m.setDescription(rs.getString("description"));
					
					result.addElement(m);
				}
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
			return result;
		}
	
		/**
		 * Die update Methode aktualisiert ein bereits in der Datenbank gespeichertes 
		 * Marktplatz-Objekt.
		 * 
		 * @param m
		 * @return
		 * @author Theresa
		 */
	
		public Marketplace update(Marketplace m) {
			 Connection con = DBConnection.connection();

			    try {
			      Statement stmt = con.createStatement();

			      stmt.executeUpdate("UPDATE marketplace " + "SET title=\""
			          + m.getTitle() + "\", " + "description=\"" + m.getDescription() + "\" "
			          + "WHERE id=" + m.getID());

			    }
			    catch (SQLException e) {
			      e.printStackTrace();
			    }

			    // Um Analogie zu insert(Marketplace m) zu wahren, geben wir m zurück
			    return m;	
		}
		

/*
		public Vector<Marketplace> findByOrgaUnitID(Integer ouid) {	
			Connection con = DBConnection.connection();
			Vector<Marketplace> result = new Vector<Marketplace>();
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT id, created, title, description, orgaunit_id FROM marketplace "
				          + "WHERE orgaunit_id=" + ouid + " ORDER BY id");

				      // Für jeden Eintrag im Suchergebnis wird nun ein Marketplace Objekt erstellt.
				      while (rs.next()) {
				        Marketplace m = new Marketplace();
				        m.setID(rs.getInt("id"));
				        m.setCreated(rs.getTimestamp("created"));
				        m.setTitle(rs.getString("title"));
				        m.setDescription(rs.getString("description"));
				        m.setOrgaUnitID(rs.getInt("orgaunit_id"));

				        // Hinzufügen des neuen Objekts zum Ergebnisvektor
				        result.addElement(m);
				      }
				    }
				    catch (SQLException e2) {
				      e2.printStackTrace();
				    }

		// Ergebnisvektor zurückgeben
		return result;
		}
*/		
		/**
		 * Loeschen eines Marketplace-Objektes aus der Datenbank.
		 * 
		 * @param m
		 * @author Theresa
		 */
		public void delete(Marketplace m) {
			Connection con = DBConnection.connection();

		    try {
		    	Statement stmt = con.createStatement();
		    	stmt.executeUpdate("DELETE FROM marketplace WHERE marketplace.id= " + m.getID());
		    	
		    }
		    catch (SQLException e2) {
					  e2.printStackTrace();		
		    }
			
		}

		/**
		 * Auslesen eines Marktplatzes mit vorgegebener MarketplaceID. Durch die Eindeutigkeit der ID 
		 * wird genau ein Objekt zurück gegeben. 
		 * 
		 * @param marketplaceID
		 * @return
		 * @author Theresa
		 */
		public Marketplace findByID(Integer marketplaceID) {
	    	Connection con = DBConnection.connection();
			
			try {						
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM marketplace WHERE id = " + marketplaceID);		
				/*
				 * es kann maximal nur ein Tupel zurueckgegeben werden, da ID Primaerschluessel ist, 
				 * dann wird geprueft, ob für diese ID ein Tupel in der DB vorhanden ist
				 */
				if (rs.next()) {
					Marketplace m = new Marketplace();
					m.setID(rs.getInt("id"));
					m.setCreated(rs.getTimestamp("created"));
					m.setTitle(rs.getString("title"));
					m.setDescription(rs.getString("description"));
					
					return m;
				}			
			}
			catch (SQLException e2) {
				e2.printStackTrace();
				return null;
			}
			return null;
		}	
	
}
