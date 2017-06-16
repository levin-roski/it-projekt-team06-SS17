package de.worketplace.team06.server.db;

import java.sql.*;
import java.util.Vector;
import de.worketplace.team06.shared.bo.*;

public class OrgaUnitMapper {
	
	private static OrgaUnitMapper orgaUnitMapper = null;
	
	protected OrgaUnitMapper() {
		
	}

	public static OrgaUnitMapper orgaUnitMapper() {
		if (orgaUnitMapper == null) {
			orgaUnitMapper = new OrgaUnitMapper();
		}
		
		return orgaUnitMapper;
	}

	/*
	 * METHODE findByGoogleID: Vorerst auskommentiert, da alternative Lösung gefunden.
	 * 
	public OrgaUnit findByGoogleID(String googleID) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("Select id, created, google_id, description, partnerprofileID, type FROM orgaunit " + "WHERE id = " + googleID);
			
			String type = rs.getType(); 
	        
	        switch(type){ 
	        case "Person": 
	        	Person p = this.personMapper.findById(ou.getID());
	        	return p;
	        	break;
	        case "Team": 
	        	Team t = this.teamMapper.findById(ou.getID());
	            return t;
	        	break; 
	        case "Organisation": 
	        	Organisation o = this.orgaMapper.findById(ou.getID());
	        	return o;
	            break; 
	        }
			
			
			if (rs.next()) {
				Person p = new Person();
				a.setId(rs.getInt("id"));
				a.setCreated(rs.getTimestamp("created"));
				a.setText(rs.getString("text"));
				a.setCallId(rs.getCallId("call_id"));
				a.setPersonId(rs.getPersonId("person_id"));
				a.setTeamId(rs.getTeamId("team_id"));
				a.setOrganisationId(rs.getOrganisationId("organisation_id"));
				return p;
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}
	*/
	
	public String findTypeByGoogleID(String googleID){
		
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select id, created, google_id, description, partnerprofile_id, type FROM orgaunit " + "WHERE google_id = " + googleID);		
			String type = rs.getString("type");
		
			return type;
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		
	}

	public String findTypeByID(int ouid) {
		
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select type FROM orgaunit " + "WHERE id = " + ouid);		
			String type = rs.getString("type");
		
			return type;
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
	}

	public Integer findID(String googleID) {
		
		Connection con = DBConnection.connection();
		int checkID;
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select id FROM orgaunit " + "WHERE google_id = " + googleID);
			checkID = rs.getInt("id");
			return checkID;
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
	}
	
	public void update(OrgaUnit ou) {
		Connection con = DBConnection.connection();

	    try {
	    	Statement stmt = con.createStatement();
	    	stmt.executeUpdate("UPDATE orgaunit, person SET"
	    						+ " orgaunit.description='" + ou.getDescription() +
	    						"', orgaunit.partnerprofile_id= " + ou.getPartnerProfileID() +
	    						" WHERE orgaunit.id= " + ou.getID()); 
	    						
	    }
	    catch (SQLException e2) {
				  e2.printStackTrace();		
	    }
		
	}	
	
}
