package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import de.worketplace.team06.shared.bo.Project;
import de.worketplace.team06.shared.bo.Team;

 class ProjectMapper {

	private static ProjectMapper projectMapper = null;
	 /**
	   * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected ProjectMapper(){
		
	}
	
	public static ProjectMapper projecteMapper(){
		if (projectMapper == null){
			projectMapper = new ProjectMapper();
		}
		return projectMapper; 
	}
   
    public Project findById (int id){
    	Connection con = DBConnection.connection();
    	
    	try{
    		Statement stmt= con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT id, created FROM Team " + "WHERE id= " + id);
    		
    		if (rs.next()) {
    			Project proj = new Project();
    			proj.setID(rs.getInt("id"));
    			proj.setTitle(rs.getString("Title"));
    			proj.setDescription(rs.getString("description"));
    			proj.setProjectLeaderID(rs.getInt("projectLeaderID"));
    			proj.setProjectOwnerID(rs.getInt("projectOwnerID"));
    			proj.setStartDate(rs.getDate("startDate"));
    			proj.setEndDate(rs.getDate("endDate"));
    			proj.setCreated(rs.getTimestamp("created"));
    		}	
    	}
    	catch (SQLExpetion e){
    		e.printStackTrace();
    	}
    	return rs;
    }
  
    public Vector<Project> findAll() {
        Connection con = DBConnection.connection();
        Vector<Project> result = new Vector<Project>();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT id, title, description, "
        			+ "projectLeaderID, projectOwnerID, startDate, endDate, created "
        	+ "FROM Team ");
        	
        	while (rs.next()){
        		Project proj = new Project();
        		proj.setID(rs.getInt("id"));
        		proj.setTitle(rs.getString("title"));
        		proj.setDescription(rs.getString("description"));
        		proj.setProjectLeaderID(rs.getInt("projectLeaderID"));
        		proj.setProjectOwnerID(rs.getInt("projectOwnerID"));
        		proj.setStartDate(rs.getDate("startDate"));
        		proj.setEndDate(rs.getDate("endDate"));
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
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM project ");
        	
        	if (rs.next()){
        	proj.setID(rs.getInt("maxid") + 1);
        	stmt = con.createStatement();
        	
        	stmt.executeUpdate("INSERT INTO team (id, title, description, projectLeaderID, projectOwnerID, startDate, endDate) " 
        	+ "VALUES (" 
        	+ proj.getID() + ", " 
        	+ proj.getTitle() + "','" 
        	+ proj.getDescription() + "','"
        	+ proj.getProjectLeaderID() + "','"
        	+ proj.getProjectOwnerID() + "','"
        	+ proj.getStartDate() + "','"
        	+ proj.getEndDate() + "','"
        	+ "')");
        	}
        }
       catch (SQLExpetion e){
    	e.printStackTrace();
    }
    
    return proj;
    } 
    
    public Project update(Project proj) {
        Connection con = DBConnection.connection();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("UPDATE project " 
        	+ "SET title=\"" + proj.getTitle() + "\", " 
        	+ "SET description=\"" + proj.getDescription() + "\", "
        	+ "SET projectLeaderID=\"" + proj.getProjectLeaderID() + "\", "
        	+ "SET projectOwnerID=\"" + proj.getProjectOwnerID() + "\", "
        	+ "SET startDate=\"" + proj.getStartDate() + "\", "
        	+ "SET endDate=\"" + proj.getEndDate() + "\", "
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
        	
        	stmt.executeUpdate("DELETE FROM project " + "WHERE id=" + proj.getID());
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
    }

	
}
