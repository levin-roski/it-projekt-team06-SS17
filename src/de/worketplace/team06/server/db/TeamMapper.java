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
		   * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
		   * neue Instanzen dieser Klasse zu erzeugen.
		   */
		
		protected TeamMapper(){
			 /**
			   * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
			   * neue Instanzen dieser Klasse zu erzeugen.
			   */
		}
		
		public static TeamMapper teamMapper(){
			if (teamMapper == null){
				teamMapper = new TeamMapper();
			}
			return teamMapper; 
		}

	    /**
	     * @param orgaUnit 
	     * @return
	     * @throws  
	     */
	    public Team insert (Team t) {
	    	Connection con = DBConnection.connection();
			
			
			try {
				
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM orgaunit ");			
				if (rs.next()) {
					
					t.setID(rs.getInt("maxid") + 1);
			
					con.setAutoCommit(false);
					stmt = con.createStatement();
					stmt.executeUpdate("INSERT INTO orgaunit (id, created, googleID, description, type) " + "VALUES (" + t.getID() + ",'" + t.getCreated() + "','" + t.getGoogleID() +  "','" + t.getDescription() +  "','" + t.getType() + "')");
					stmt.executeUpdate("INSERT INTO team (id, created, teamName, membercount) " + "VALUES (" + t.getID() + ",'" + t.getCreated() + "','" + t.getName() + "','" + t.getMembercount() + "')");
					con.commit();
				}
			}
			
			catch (SQLException e2) {
				try {
					System.out.println("Die SQL Transaktion konnte nicht vollständig ausgeführt werden. Es wird versucht die Transaktion rückgängig zu machen!");
					con.rollback();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  finally {
					  e2.printStackTrace();
				}	
			}
			return t;
	    }
	    
		/**
		 * Auslesen eines Teams mithilfe einer GoogleID.
		 */
		public Team findByGoogleID(String googleID) {
			Connection con = DBConnection.connection();
			
			try {						
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM orgaunit INNER JOIN team ON orgaunit.id = team.id WHERE orgaunit.googleID = '" + googleID + "'");		
				
				if (rs.next()) {
					Team t = new Team();
					t.setID(rs.getInt("id"));
					t.setCreated(rs.getTimestamp("created"));
					t.setGoogleID(googleID);
					t.setDescription(rs.getString("description"));
					t.setPartnerProfileID(rs.getInt("partnerprofileID"));
					t.setType(rs.getString("type"));
					
					t.setName(rs.getString("teamName"));
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
	     * @param orgaUnit 
	     * @return
	     */
		public void update(Team t) {
			Connection con = DBConnection.connection();

		    try {
		    	Statement stmt = con.createStatement();
		    	//Updaten eines Teams
		    	stmt.executeUpdate("UPDATE orgaunit, team SET"
		    						+ " orgaunit.description='" + t.getDescription() +
		    						"', orgaunit.partnerprofileID= " + t.getPartnerProfileID() +
		    						", team.teamName= '" + t.getName() +
		    						"', team.membercount= " + t.getMembercount() + 
		    						" WHERE orgaunit.id= " + t.getID() + 
		    						" AND team.id= " + t.getID()); 	
		    }
		    catch (SQLException e2) {
					  e2.printStackTrace();		
		    }
			
		}
		
		/*
		 * Wird findbyID benötigt ?  Klären! 
		 */

	    /**
	     * @param orgaUnit
	     */

	    public Team findById (int id){

	    	Connection con = DBConnection.connection();
	    	
	    	try{
	    		Statement stmt= con.createStatement();
	    		ResultSet rs = stmt.executeQuery("SELECT id, created FROM Team " + "WHERE id= " + id);
	    		
	    		if (rs.next()) {
	    			Team t = new Team();
	    			t.setID(rs.getInt("id"));
	    			t.setCreated(rs.getTimestamp("created"));
	    		}	
	    	}
	    	catch (SQLException e){
	    		e.printStackTrace();
	    	}
	    	return null ;
	    
	    
	    /**
	     * Löschen eines Teams aus der Datenbank
	     * @param team
	     */
	    public void delete(Team t) {
			Connection con = DBConnection.connection();

		    try {
		    	Statement stmt = con.createStatement();
		    	//Löschen des Teams aus der Tabelle orgaunit und Team
		    	stmt.executeUpdate("DELETE orgaunit, team FROM orgaunit INNER JOIN team ON orgaunit.id = team.id WHERE orgaunit.id= " + t.getID());
		    	
		    }
		    catch (SQLException e2) {
					  e2.printStackTrace();		
		    }
			
		}	

	    /*
	     * Wird findAll benötigt ? Klären ! 
	     */
	    /**
	     * @return
	     */
	    public Vector<Team> findAll() {
	        // TODO implement here
	        return null;
	    }



	    /**
	     * @param orgaUnit 
	     * @return
	     */

	  
	    public Vector<Team> findAll() {
	        Connection con = DBConnection.connection();
	        Vector<Team> result = new Vector<Team>();
	        
	        try{
	        	Statement stmt = con.createStatement();
	        	
	        	ResultSet rs = stmt.executeQuery("SELECT * FROM Team ");
	        	
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
	    
	    
	    
	   /** public Team insert (Team t) {
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
	       catch (SQLException e){
	    	e.printStackTrace();
	    }
	    
	    return t;
	    } 
	    */ 
	    
	   /** public Team update(Team t) {
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
	    */

	    /**public void delete(Team t) {
	        Connection con = DBConnection.connection();
	        
	        try {
	        	Statement stmt = con.createStatement();
	        	
	        	stmt.executeUpdate("DELETE * FROM team " + "WHERE id=" + t.getID());
	        }
	        catch (SQLException e){
	        	e.printStackTrace();
	        }
	    }
	    */
	    
	    public Vector<Team> findByTeamName(String teamName) {
			Connection con = DBConnection.connection();
			
			Vector<Team> result = new Vector<Team>();
			
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT id, name "
						+ " FROM team WHERE teamName ='" + teamName + "'ORDER BY id");
				
				while (rs.next()){
					Team t = new Team();
					t.setID(rs.getInt("id"));
					t.setTeamName(rs.getString("teamName"));
	        	
	        		result.add(t);	
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			
			return result;
		}
	    

}
	
