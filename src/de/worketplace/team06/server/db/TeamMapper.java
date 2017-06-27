package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import de.worketplace.team06.shared.bo.*;
/**
 * Die Mapper-Klasse TeamMapper bildet Team-Objekte als Datensätze
 * in einer relationalen Datenbank ab. Durch die Bereitstellung verschiedener Methoden
 * können mit deren Hilfe Objekte erzeugt, verändert, gelöscht und ausgelesen werden.
 * Das Mapping erfolgt bidirektional: Objekte können in Datensätze und Datensätze in 
 * Objekte umgewandelt werden.
 * 
 * @see ApplicationMapper
 * @see CallMapper
 * @see EnrollmentMapper
 * @see MarketplaceMapper
 * @see OrganisationMapper
 * @see OrgaUnitMapper
 * @see PartnerProfileMapper
 * @see PersonMapper
 * @see ProjectMapper
 * @see PropertyMapper
 * @see RatingMapper
 * 
 * @author Patrick
 * @author Theresa
 */

	public class TeamMapper {
	    /**
	     * Die Klasse TeamMapper wird nur einmal instantiiert. Man spricht hierbei
	     * von einem sogenannten <b>Singleton</b>.
	     * <p>
	     * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	     * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	     * einzige Instanz dieser Klasse.
	     * 
	     * @author Thies
	     * @author Theresa
	     */
		private static TeamMapper teamMapper = null;

		 /**
		   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
		   * neue Instanzen dieser Klasse zu erzeugen.
		   * 
		   * @author Thies 
		   */
		
		protected TeamMapper(){

		}
		
		/**
		 * Diese Methode stellt sicher, dass die Singleton-Eigenschaft gegeben ist. Sie sorgt
		 * dafür, dass nur eine einzige Instanz der TeamMapper-Klasse existiert. 
		 * TeamMapper sollte nicht über den New-Operator, sondern über den 
		 * Aufruf dieser statischen Methode ausgeführt werden.
		 * 
		 * @return TeamMapper
		 * @author Thies
		 * @author Theresa
		 */
		
		public static TeamMapper teamMapper(){
			if (teamMapper == null){
				teamMapper = new TeamMapper();
			}
			return teamMapper; 
		}

	    /**
	     * Einfuegen eines Team-Objektes in die Datenbank. Dabei wird auch der Primaerschluessel
	     * des uebergebenen Objektes geprueft und ggf. berichtigt.
	     * 
	     * @param orgaUnit
	     * @return Team 
	     * @throws  
	     * @author Thies 
	     * @author Theresa
	     */
	    public Team insert (Team t) {
	    	Connection con = DBConnection.connection();
			
			
			try {
				
				Statement stmt = con.createStatement();
				
				/*
				 * Abfragen des hoechsten Primaerschluesselwertes, die aktuelle ID 
				 * wird dann um 1 erhoeht und an an t vergeben
				*/
				ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM orgaunit ");			
				if (rs.next()) {
					
					t.setID(rs.getInt("maxid") + 1);
			
					con.setAutoCommit(false);
					stmt = con.createStatement();
					//Einfuegeoption, damit das neue Team-Tupel in die Datenbank eingefuegt werden kann
					stmt.executeUpdate("INSERT INTO orgaunit (id, created, google_id, description, type) " + "VALUES (" + t.getID() + ",'" + t.getCreated() + "','" + t.getGoogleID() +  "','" + t.getDescription() +  "','" + t.getType() + "')");
					stmt.executeUpdate("INSERT INTO team (id, created, team_name, membercount) " + "VALUES (" + t.getID() + ",'" + t.getCreated() + "','" + t.getName() + "','" + t.getMembercount() + "')");
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
			
			return t;
	    }
	    
		/**
		 * Diese Methode findet ein Team-Objekt, anhand der übergebenen Google-ID. 
		 * 
		 * @param googleID 
		 * @return Team-Objekt 
		 * @author Theresa
		 */
	    
		public Team findByGoogleID(String googleID) {
			Connection con = DBConnection.connection();
			
			try {						
				Statement stmt = con.createStatement();
				// Tabellen orgaunit und team verbinden, da jedes Team auch eine Organisationseinheit ist.
				ResultSet rs = stmt.executeQuery("SELECT * FROM orgaunit INNER JOIN team ON orgaunit.id = team.id WHERE orgaunit.google_id = '" + googleID + "'");		
				
				if (rs.next()) {
					Team t = new Team();
					t.setID(rs.getInt("id"));
					t.setCreated(rs.getTimestamp("created"));
					t.setGoogleID(googleID);
					t.setDescription(rs.getString("description"));
					t.setPartnerProfileID(rs.getInt("partnerprofile_id"));
					t.setType(rs.getString("type"));
					
					t.setName(rs.getString("team_name"));
					t.setMembercount(rs.getInt("membercount"));
					return t;
				}			
			}
			catch (SQLException e2) {
				e2.printStackTrace();
				return null;
			}
			return null;
		}	

	    /**
	     * Methode ermoeglicht, dass ein Team-Objekt in der Datenbank aktualisiert werden kann.
	     * 
	     * @param orgaUnit 
	     * @return Team
	     * @author Theresa
	     */
		public void update(Team t) {
			Connection con = DBConnection.connection();

		    try {
		    	Statement stmt = con.createStatement();
		    	stmt.executeUpdate("UPDATE orgaunit, team SET"
		    						+ " orgaunit.description='" + t.getDescription() +
		    						"', orgaunit.partnerprofile_id= " + t.getPartnerProfileID() +
		    						", team.team_name= '" + t.getName() +
		    						"', team.membercount= " + t.getMembercount() + 
		    						" WHERE orgaunit.id= " + t.getID() + 
		    						" AND team.id= " + t.getID()); 	
		    }
		    catch (SQLException e2) {
					  e2.printStackTrace();		
		    }
			
		}
	
		/**
		 * Suchen eines Teams mit vorgegebener TeamID. Durch die Eindeutigkeit der ID 
		 * wird genau ein Objekt zurück gegeben. 
		 * 
		 * @param ouid
		 * @return Team-Objekt, das der übergebenen ID entspricht
		 * @author Theresa
		 */
		
		public Team findByID(Integer ouid) {
			Connection con = DBConnection.connection();
			
			try {						
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM orgaunit INNER JOIN team ON orgaunit.id = team.id WHERE orgaunit.id = " + ouid);		
				
				/*
				 * es kann maximal nur ein Tupel zurueckgegeben werden, da ID Primaerschluessel ist, 
				 * dann wird geprueft, ob für diese ID ein Tupel in der DB vorhanden ist
				 */
				
				if (rs.next()) {
					Team t = new Team();
					t.setID(rs.getInt("id"));
					t.setCreated(rs.getTimestamp("created"));
					t.setGoogleID(rs.getString("google_id"));
					t.setDescription(rs.getString("description"));
					t.setPartnerProfileID(rs.getInt("partnerprofile_id"));
					t.setType(rs.getString("type"));
					
					t.setName(rs.getString("team_name"));
					t.setMembercount(rs.getInt("membercount"));
					return t;
				}			
			}
			catch (SQLException e2) {
				e2.printStackTrace();
				return null;
			}
			return null;
		}	    

	    /**
	     * Loeschen eines Team-Objektes aus der Datenbank.
	     * 
	     * @param team
	     * @author Theresa
	     */
	    public void delete(Team t) {
			Connection con = DBConnection.connection();

		    try {
		    	Statement stmt = con.createStatement();
		    	//Loeschen des Teams aus den Tabellen orgaunit und Team
		    	stmt.executeUpdate("DELETE orgaunit, team FROM orgaunit INNER JOIN team ON orgaunit.id = team.id WHERE orgaunit.id= " + t.getID());
		    	
		    }
		    catch (SQLException e2) {
					  e2.printStackTrace();		
		    }
			
		}	
}
	
