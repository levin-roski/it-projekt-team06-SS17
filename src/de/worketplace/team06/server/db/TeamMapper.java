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
	     * @param orgaUnit 
	     * @return
	     */
	    public Team update(Team t) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * @param orgaUnit
	     */
	    public Team findById (int id){
	    	return null;
	    }
	    
	    
	    public void delete(Team t) {
	        // TODO implement here
	    }

	    /**
	     * @return
	     */
	    public Vector<Team> findAll() {
	        // TODO implement here
	        return null;
	    }

		public Team findByGoogleID(String googleId) {
			// TODO Auto-generated method stub
			return null;
		}

	    /**
	     * @param orgaUnit 
	     * @return
	     */
	  
}
	
