package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.worketplace.team06.shared.bo.Project;

/**
 * Die Mapper-Klasse ProjectMapper bildet Project-Objekte als Datensätze
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
 * @see PropertyMapper
 * @see RatingMapper
 * @see TeamMapper
 * 
 * @author Strepp
 * @author Vocke
 * @author Thies
 */

 public class ProjectMapper {
	 /**
	     * Die Klasse ProjectMapper wird nur einmal instanziiert. Man spricht hierbei
	     * von einem sogenannten <b>Singleton</b>.
	     * <p>
	     * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	     * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	     * einzige Instanz dieser Klasse.
	     * 
	     */
	private static ProjectMapper projectMapper = null;
	
	/**
	 * Darstellung von Datum wird durch diese Methode vereinfacht und vereinheitlicht. 
	 * wird aufgerufen, wenn Startdatum und Enddatum neu angelegt oder verändert werden.
	 * 
	 * @author Theresa
	 */
	
	 
	/**
	   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   * 
	   */
	protected ProjectMapper(){
		
	}
	/**
	 * Diese Methode stellt sicher, dass die Singleton-Eigenschaft gegeben ist. Sie sorgt
	 * dafür, dass nur eine einzige Instanz der ProjectMapper-Klasse existiert. 
	 * ProjectMapper sollte nicht über den New-Operator, sondern über den 
	 * Aufruf dieser statischen Methode aufgerufen werden.
	 * 
	 * @return ProjectMapper
	 */
	public static ProjectMapper projectMapper(){
		if (projectMapper == null){
			projectMapper = new ProjectMapper();
		}
		return projectMapper; 
	}
	/**
	 * Auslesen eines Project-Objektes anhand übergebener ProjectID. Durch die Eindeutigkeit der ID 
	 * wird genau ein Objekt zurück gegeben. 
	 * 
	 * @param proj
	 * @return Project-Objekt, das der übergebenen ID entspricht
	 */
	public Project findByID(Integer projectID) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt= con.createStatement();
		  	ResultSet rs = stmt.executeQuery("SELECT id, title, description, projectleader_id, "
		  			+ "start_date, end_date, marketplace_id "
		  			+ "FROM project WHERE ID=" + projectID);
		  	/*
			 * es kann maximal nur ein Tupel zurueckgegeben werden, da ID Primaerschluessel ist, 
			 * dann wird geprueft, ob für diese ID ein Tupel in der DB vorhanden ist
			 */
		  	if (rs.next()){
		  		Project proj = new Project();
		  		proj.setID(rs.getInt("id"));
		  		proj.setTitle(rs.getString("title"));
		  		proj.setDescription(rs.getString("description"));
		  		proj.setProjectLeaderID(rs.getInt("projectleader_id"));
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
  
	/**
	 * Auslesen aller Project-Objekte in der Datenbank.
	 * 
	 * @return Vektor <Project>
	 */
    public Vector<Project> findAll() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Connection con = DBConnection.connection();
        Vector<Project> result = new Vector<Project>();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT * FROM project");
        	
        	while (rs.next()){
        		Project proj = new Project();
        		proj.setID(rs.getInt("id"));
        		proj.setTitle(rs.getString("title"));
        		proj.setDescription(rs.getString("description"));
        		proj.setProjectLeaderID(rs.getInt("projectleader_id"));
        		proj.setStartDate(sdf.parse(rs.getString("start_date")));
        		proj.setEndDate(sdf.parse(rs.getString("end_date")));
        		proj.setCreated(rs.getTimestamp("created"));
        		proj.setMarketplaceID(rs.getInt("marketplace_id"));
        		result.addElement(proj);
        	}
        }
        catch (SQLException | ParseException e){
        	e.printStackTrace();
        }
        return result ;
    }
    
    /**
     * Einfuegen eines Project-Objekt in die Datenbank. Dabei wird auch der Primaerschluessel
     * des uebergebenen Objektes geprueft und ggf. berichtigt.
     * 
     * @param proj
     * @return Project
     * @throws  
     */
    public Project insert (Project proj) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Connection con = DBConnection.connection();
        
        try {
        	String startdate = sdf.format(proj.getStartDate());
        	String enddate = sdf.format(proj.getEndDate());
        	
        	Statement stmt = con.createStatement();
        	/*
			 * Abfragen des hoechsten Primaerschluesselwertes, die aktuelle ID 
			 * wird dann um 1 erhoeht und an an proj vergeben
			*/
        	ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM project ");
        	
        	if (rs.next()){
        	proj.setID(rs.getInt("maxid") + 1);
        	stmt = con.createStatement();
			//Einfuegeoption, damit das neue Project-Tupel in die Datenbank eingefuegt werden kann.
        	stmt.executeUpdate("INSERT INTO project (id, created, title, description, projectleader_id, start_date, end_date, marketplace_id) " 
        	+ "VALUES (" 
        	+ proj.getID() + ",'" 
        	+ proj.getCreated() + "','" 
        	+ proj.getTitle() + "','" 
        	+ proj.getDescription() + "','"
        	+ proj.getProjectLeaderID() + "','"
        	+ startdate + "','"
        	+ enddate + "','"
        	+ proj.getMarketplaceID() + "')");
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        try{
        	String startdate = sdf.format(proj.getStartDate());
        	String enddate = sdf.format(proj.getEndDate());
        	
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("UPDATE project " 
        	+ "SET title=\"" + proj.getTitle() + "\", " 
        	+ "SET description=\"" + proj.getDescription() + "\", "
        	+ "SET projectleader_id=\"" + proj.getProjectLeaderID() + "\", "
        	+ "SET start_date=\"" + startdate + "\", "
        	+ "SET end_date=\"" + enddate + "\" "
        	+ "WHERE id=" + proj.getID());
        }
        
        catch (SQLException e){
        	e.printStackTrace();
        } 
        return proj;
    }
    
    /**
     * Loeschen eines Project-Objektes aus der Datenbank.
     * 
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
	 * Auslesen eines Project-Objekts anhand der übergebenen MarketplaceID.
	 * 
	 * @param marketplaceID
	 * @return Vektor<Project>
	 */
	public Vector<Project> findByMarketplaceID(Integer marketplaceID) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Connection con = DBConnection.connection();
		
		Vector<Project> result = new Vector<Project>();
		
		try {
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, created, title, description, projectleader_id, "
					+ "start_date, end_date, marketplace_id "
					+ "FROM project WHERE marketplace_id = " + marketplaceID + " ORDER BY id");
			
			while (rs.next()){
				Project proj = new Project();
				proj.setID(rs.getInt("id"));
				proj.setTitle(rs.getString("title"));
        		proj.setDescription(rs.getString("description"));
        		proj.setProjectLeaderID(rs.getInt("projectleader_id"));
        		proj.setStartDate(sdf.parse(rs.getString("start_date")));
        		proj.setEndDate(sdf.parse(rs.getString("end_date")));
        		proj.setCreated(rs.getTimestamp("created"));
        		proj.setMarketplaceID(rs.getInt("marketplace_id"));
        		
        		result.add(proj);	
			}
		} catch (SQLException | ParseException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * Auslesen der Project-Objekte aus der Datenbank anhand der übergebenen ProjectLeader-ID.
	 * 
	 * @param projectOwnerID
	 * @return Vector<Project> 
	 */
	
	public Vector<Project> findByProjectLeaderID(Integer projectLeaderID) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Connection con = DBConnection.connection();
		
		Vector<Project> result = new Vector<Project>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, title, description, projectleader_id, "
					+ "start_date, end_date, created, marketplace_id "
					+ " FROM project WHERE projectleader_id ='" + projectLeaderID + "'ORDER BY id");
			
			while (rs.next()){
				Project proj = new Project();
				proj.setID(rs.getInt("id"));
				proj.setTitle(rs.getString("title"));
        		proj.setDescription(rs.getString("description"));
        		proj.setProjectLeaderID(rs.getInt("projectleader_id"));
        		proj.setStartDate(sdf.parse(rs.getString("start_date")));
        		proj.setEndDate(sdf.parse(rs.getString("end_date")));
        		proj.setCreated(rs.getTimestamp("created"));
        		proj.setMarketplaceID(rs.getInt("marketplace_id"));
        		
        		result.add(proj);	
			}
		} catch (SQLException | ParseException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}

	
}
