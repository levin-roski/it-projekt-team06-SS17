package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import de.worketplace.team06.shared.bo.Project;
import de.worketplace.team06.shared.bo.Team;

 public class ProjectMapper {
	 /**
	     * Die Klasse ProjectMapper wird nur einmal instantiiert. Man spricht hierbei
	     * von einem sogenannten <b>Singleton</b>.
	     * <p>
	     * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	     * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	     * einzige Instanz dieser Klasse.
	     * 
	     * @author Thies
	     * @author Theresa
	     */
	private static ProjectMapper projectMapper = null;
	/**
	   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   * 
	   * @author Thies 
	   */
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 /**
	   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected ProjectMapper(){
		
	}
	/**
	 * Diese Methode stellt sicher, dass die Singleton-Eigenschaft gegeben ist. Sie sorgt
	 * dafür, dass nur eine einzige Instanz der ProjectMapper-Klasse existiert. 
	 * ProjectMapper sollte nicht über den New-Operator, sondern über den 
	 * Aufruf dieser statischen Methode.
	 * 
	 * @return ProjectMapper
	 * @author Thies
	 * @author Theresa
	 */
	public static ProjectMapper projectMapper(){
		if (projectMapper == null){
			projectMapper = new ProjectMapper();
		}
		return projectMapper; 
	}
	/**
	 * Suchen eines Projects mit vorgegebener ProjectID. Duch die Eindeutigkeit der ID, 
	 * wird genau ein Objekt zurück gegeben. 
	 * 
	 * @param proj
	 * @return Project-Objekt, das der übergebenen ID entspricht
	 */
	public Project findByID(int projectID) {
		
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt= con.createStatement();
		  	ResultSet rs = stmt.executeQuery("SELECT id, title, description, projectleader_id, "
		  			+ "projectowner_id, start_date, end_date, marketplace_id "
		  			+ "FROM project WHERE ID=" + projectID);
		  	/**
			 * es kann maximal nur ein Tupel zurueckgegeben werden, da ID Primaerschluessel ist, 
			 * dann wird geprueft, ob für diese ID ein Tupel in der DB vorhanden ist
			 */
		  	if (rs.next()){
		  		Project proj = new Project();
		  		proj.setID(rs.getInt("id"));
		  		proj.setTitle(rs.getString("title"));
		  		proj.setDescription(rs.getString("description"));
		  		proj.setProjectLeaderID(rs.getInt("projectleader_id"));
		  		proj.setProjectOwnerID(rs.getInt("projectowner_id"));
		  		proj.setStartDate(sdf.parse(rs.getString("start_date")));
        		proj.setEndDate(sdf.parse(rs.getString("end_date")));
		  		proj.setMarketplaceID(rs.getInt("marketplace_id"));
		  		
		  		return proj;
		  	}
		  	
		} catch (SQLException | ParseException e2){
			e2.printStackTrace();
		}
		
		return null;
	}
  
    public Vector<Project> findAll() {
        Connection con = DBConnection.connection();
        Vector<Project> result = new Vector<Project>();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT id, title, description, "
        			+ "projectleader_id, projectowner_id, start_date, end_date, created,  "
        	+ "FROM team ");
        	
        	while (rs.next()){
        		Project proj = new Project();
        		proj.setID(rs.getInt("id"));
        		proj.setTitle(rs.getString("title"));
        		proj.setDescription(rs.getString("description"));
        		proj.setProjectLeaderID(rs.getInt("projectleader_id"));
        		proj.setProjectOwnerID(rs.getInt("projectowner_id"));
        		proj.setStartDate(sdf.parse(rs.getString("start_date")));
        		proj.setEndDate(sdf.parse(rs.getString("end_date")));
        		proj.setCreated(rs.getTimestamp("created"));
        		
        		result.addElement(proj);
        	}
        }
        catch (SQLException | ParseException e){
        	e.printStackTrace();
        }
        return result ;
    }
    
    /**
     * Einfuegen eines Project-Objektes in die Datenbank. Dabei wird auch der Primaerschluessel
     * des uebergebenen Objektes geprueft und ggf. berichtigt.
     * 
     * @param proj
     * @return Project
     * @throws  
     * @author Thies 
     * @author Theresa
     */
    public Project insert (Project proj) {
        Connection con = DBConnection.connection();
        
        try {
        	String startdate = sdf.format(proj.getStartDate());
        	String enddate = sdf.format(proj.getEndDate());
        	
        	Statement stmt = con.createStatement();
        	/** 
			 * Abfragen des hoechsten Primaerschluesselwertes, die aktuelle ID 
			 *wird dann um 1 erhoet und an an proj vergeben
			*/
        	ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM project ");
        	
        	if (rs.next()){
        	proj.setID(rs.getInt("maxid") + 1);
        	stmt = con.createStatement();
        	
        	/**
			 * Einfuegeoption, damit das neue Team-Tupel in die Datenbank eingefuegt werden kann
			 */
        	stmt.executeUpdate("INSERT INTO project (id, title, description, projectleader_id, projectowner_id, start_date, end_date, marketplace_id) " 
        	+ "VALUES (" 
        	+ proj.getID() + ", " 
        	+ proj.getTitle() + "','" 
        	+ proj.getDescription() + "','"
        	+ proj.getProjectLeaderID() + "','"
        	+ proj.getProjectOwnerID() + "','"
        	+ startdate + "','"
        	+ enddate + "','"
        	+ proj.getMarketplaceID() + "','"         // noch hinzugefügt, damit das Projekt einem Marktplatz zugewiesen werden kann 
        	+ "')");
        	}
        }
       catch (SQLException e){
    	e.printStackTrace();
    }
    
    return proj;
    } 
    /**
     * Methode ermoeglicht, dass ein Project-Objekt in der Datenbank aktualisiert werden kann.
     * 
     * @param proj
     * @return Project
     */
    public Project update(Project proj) {
        Connection con = DBConnection.connection();
        
        try{
        	String startdate = sdf.format(proj.getStartDate());
        	String enddate = sdf.format(proj.getEndDate());
        	
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("UPDATE project " 
        	+ "SET title=\"" + proj.getTitle() + "\", " 
        	+ "SET description=\"" + proj.getDescription() + "\", "
        	+ "SET projectleader_id=\"" + proj.getProjectLeaderID() + "\", "
        	+ "SET projectowner_id=\"" + proj.getProjectOwnerID() + "\", "
        	+ "SET start_date=\"" + startdate + "\", "
        	+ "SET end_date=\"" + enddate + "\", "
        	+ "WHERE id=" + proj.getID());
        }
        
        catch (SQLException e){
        	e.printStackTrace();
        } 
        return proj;
    }
    
    /**
     * Loeschen eines Project-Objektes aus der Datenbank
     * @param proj
     */
    public void delete(Project proj) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("DELETE FROM project WHERE id=" + proj.getID());
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
    }
    /**
	 * Diese Methode findet ein Project-Objekt, anhand der übergebenen Marketplace-ID 
	 * 
	 * @param marketplaceID
	 * @return Project-Objekt 
	 */
	public Vector<Project> findByMarketplaceID(int marketplaceID) {
		
		Connection con = DBConnection.connection();
		
		Vector<Project> result = new Vector<Project>();
		
		try {
			
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, title, description, projectleader_id, projectowner_id, "
					+ "start_date, end_date, marketplace_id "
					+ " FROM project WHERE marketplace_id ='" + marketplaceID + "'ORDER BY id");
			
			while (rs.next()){
				Project proj = new Project();
				proj.setID(rs.getInt("id"));
				proj.setTitle(rs.getString("title"));
        		proj.setDescription(rs.getString("description"));
        		proj.setProjectLeaderID(rs.getInt("projectleader_id"));
        		proj.setProjectOwnerID(rs.getInt("projectowner_id"));
        		proj.setStartDate(sdf.parse(rs.getString("start_date")));
        		proj.setEndDate(sdf.parse(rs.getString("end_date")));
        		proj.setCreated(rs.getTimestamp("created"));
        		proj.setMarketplaceID(rs.getInt("marektplace_id"));
        		
        		result.add(proj);	
			}
		} catch (SQLException | ParseException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}
	
	
	public Vector<Project> findByProjectOwnerID(int projectOwnerID) {
		Connection con = DBConnection.connection();
		
		Vector<Project> result = new Vector<Project>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, title, description, projectleader_id, projectowner_id, "
					+ "start_date, end_date, marketplace_id "
					+ " FROM project WHERE projectowner_id ='" + projectOwnerID + "'ORDER BY id");
			
			while (rs.next()){
				Project proj = new Project();
				proj.setID(rs.getInt("id"));
				proj.setTitle(rs.getString("title"));
        		proj.setDescription(rs.getString("description"));
        		proj.setProjectLeaderID(rs.getInt("projectleader_id"));
        		proj.setProjectOwnerID(rs.getInt("projectowner_id"));
        		proj.setStartDate(sdf.parse(rs.getString("start_date")));
        		proj.setEndDate(sdf.parse(rs.getString("end_date")));
        		proj.setCreated(rs.getTimestamp("created"));
        		proj.setMarketplaceID(rs.getInt("marektplace_id"));
        		
        		result.add(proj);	
			}
		} catch (SQLException | ParseException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}

	
	public Vector<Project> findByProjectLeaderID(int projectLeaderID) {
		Connection con = DBConnection.connection();
		
		Vector<Project> result = new Vector<Project>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, title, description, projectleader_id, projectowner_id, "
					+ "start_date, end_date, marketplace_id "
					+ " FROM project WHERE projectleader_id ='" + projectLeaderID + "'ORDER BY id");
			
			while (rs.next()){
				Project proj = new Project();
				proj.setID(rs.getInt("id"));
				proj.setTitle(rs.getString("title"));
        		proj.setDescription(rs.getString("description"));
        		proj.setProjectLeaderID(rs.getInt("projectleader_id"));
        		proj.setProjectOwnerID(rs.getInt("projectowner_id"));
        		proj.setStartDate(sdf.parse(rs.getString("start_date")));
        		proj.setEndDate(sdf.parse(rs.getString("end_date")));
        		proj.setCreated(rs.getTimestamp("created"));
        		proj.setMarketplaceID(rs.getInt("marektplace_id"));
        		
        		result.add(proj);	
			}
		} catch (SQLException | ParseException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}

	
}
