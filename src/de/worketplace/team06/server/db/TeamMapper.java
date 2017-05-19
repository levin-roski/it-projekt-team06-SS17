package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import de.worketplace.team06.shared.bo.*;
	/**
	 * 
	 */
	public class TeamMapper {
	    /**
	     * Default constructor
	     */
		private static TeamMapper teamMapper = null;
		 /**
		   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
		   * neue Instanzen dieser Klasse zu erzeugen.
		   */
		
		protected TeamMapper(){
			
		}
		
		public static TeamMapper teamMapper(){
			if (teamMapper == null){
				teamMapper = new TeamMapper();
			}
			return teamMapper; 
		}
	   
	    public Team findById (int id){
	    	Connection con = DBConnection.connection();
	    	
	    	try{
	    		Statement stmt= con.createStatement();
	    		ResultSet rs = stmt.executeQuery("SELECT id, created FROM Team " + "WHERE id= " + id);
	    		
	    		if (rs.next()) {
	    			Team t = new Team();
	    			t.setID(rs.getInt("id"));
	    			t.setCreated(rs.getTimestamp("created"));
	    		 
	    			reutrn t;
	    		}	
	    	}
	    	catch (SQLExpetion e){
	    		e.printStackTrace();
	    		return null;
	    	}
	    }
	  
	    public Vector<Team> findAll() {
	        Connection con = DBConnection.connection();
	        Vector<Team> result = new Vector<Team>();
	        
	        try{
	        	Statement stmt = con.createStatement();
	        	
	        	ResultSet rs = stmt.executeQuery("SELECT id, created "+ "FROM Team ");
	        	
	        	while (rs.next()){
	        		Team t = new Team();
	        		t.setID(rs.getInt("id"));
	        		t.setCreated(rs.getTimestamp("created"));
	        		
	        		result.addElement(t);
	        	}
	        }
	        catch (SQLException e){
	        	e.printStackTrace();
	        }
	        return result ;
	    }
	    
	    
	    
	    public Team insert (Team t) {
	        Connection con = DBConnection.connection();
	        
	        try {
	        	Statement stmt = con.createStatement();
	        	
	        	ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM team ");
	        	
	        	if (rs.next()){
	        	t.setID(rs.getInt("maxid") + 1);
	        	stmt = con.createStatement();
	        	
	        	stmt.executeUpdate("INSERT INTO team (id, teamName) " + "VALUES (" + t.getID() + ", " + t.getTeamName() + "','" + "')");
	        	}
	        }
	       catch (SQLExpetion e){
	    	e.printStackTrace();
	    }
	    
	    return t;
	    } 
	    
	    public Team update(Team t) {
	        Connection con = DBConnection.connection();
	        
	        try{
	        	Statement stmt = con.createStatement();
	        	
	        	stmt.executeUpdate("UPDATE team " + "SET teamName=\"" + t.getTeamName() + "\", " + "WHERE id=" + t.getID());
	        }
	        
	        catch (SQLException e){
	        	e.printStackTrace();
	        } 
	        return t;
	    }

	    public void delete(Team t) {
	        Connection con = DBConnection.connection();
	        
	        try {
	        	Statement stmt = con.createStatement();
	        	
	        	stmt.executeUpdate("DELETE FROM team " + "WHERE id=" + c.getID());
	        }
	        catch (SQLException e){
	        	e.printStackTrace();
	        }
	    }

}
	
