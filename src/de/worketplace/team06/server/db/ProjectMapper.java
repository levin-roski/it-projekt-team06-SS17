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

	private static ProjectMapper projectMapper = null;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 /**
	   * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected ProjectMapper(){
		
	}
	
	public static ProjectMapper projectMapper(){
		if (projectMapper == null){
			projectMapper = new ProjectMapper();
		}
		return projectMapper; 
	}
   
	public Project findByID(int projectID) throws ParseException {
		
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt= con.createStatement();
		  	ResultSet rs = stmt.executeQuery("SELECT id, title, description, projectleader_id, "
		  			+ "projectowner_id, start_date, end_date, marketplace_id "
		  			+ "FROM project WHERE ID=" + projectID);
		  	
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
		  	
		} catch (SQLException e2){
			e2.printStackTrace();
		}
		
		return null;
	}
  
    public Vector<Project> findAll() throws ParseException {
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
        catch (SQLException e){
        	e.printStackTrace();
        }
        return result ;
    }
    
    
    public Project insert (Project proj) {
        Connection con = DBConnection.connection();
        
        try {
        	String startdate = sdf.format(proj.getStartDate());
        	String enddate = sdf.format(proj.getEndDate());
        	
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM project ");
        	
        	if (rs.next()){
        	proj.setID(rs.getInt("maxid") + 1);
        	stmt = con.createStatement();
        	
        	
        	stmt.executeUpdate("INSERT INTO project (id, title, description, projectleader_id, projectowner_id, start_date, end_date, marketplace_id) " 
        	+ "VALUES (" 
        	+ proj.getID() + ", " 
        	+ proj.getTitle() + "','" 
        	+ proj.getDescription() + "','"
        	+ proj.getProjectLeaderID() + "','"
        	+ proj.getProjectOwnerID() + "','"
        	+ startdate + "','"
        	+ enddate + "','"
        	+ proj.getMarketplaceID() + "','"         // noch hinzugef�gt, damit das Projekt einem Marktplatz zugewiesen werden kann 
        	+ "')");
        	}
        }
       catch (SQLException e){
    	e.printStackTrace();
    }
    
    return proj;
    } 
    
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
     
	public Vector<Project> findByMarketplaceID(int marketplaceID) throws ParseException {
		
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
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}
	
	
	public Vector<Project> findByProjectOwnerID(int projectOwnerID) throws ParseException {
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
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}

	
	public Vector<Project> findByProjectLeaderID(int projectLeaderID) throws ParseException {
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
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}

	
}
