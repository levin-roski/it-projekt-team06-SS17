package de.worketplace.team06.server;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


import de.worketplace.team06.shared.Editor;
import de.worketplace.team06.shared.bo.Application;
import de.worketplace.team06.shared.bo.Call;
import de.worketplace.team06.shared.bo.Enrollment;
import de.worketplace.team06.shared.bo.Marketplace;
import de.worketplace.team06.shared.bo.OrgaUnit;
import de.worketplace.team06.shared.bo.Organisation;
import de.worketplace.team06.shared.bo.PartnerProfile;
import de.worketplace.team06.shared.bo.Person;
import de.worketplace.team06.shared.bo.Project;
import de.worketplace.team06.shared.bo.Property;
import de.worketplace.team06.shared.bo.Rating;
import de.worketplace.team06.shared.bo.Team;

public class EditorImpl extends RemoteServiceServlet implements Editor{

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;

	public void init() throws IllegalArgumentException{
		/*this.cMapper = CustomerMapper.customerMapper();
		this.ouMapper = OrgaUnitMapper.orgaUnitMapper();*/
		
	}
	
	

	/**
	 *  
	 */
	@Override
	public boolean checkExistence(int userID) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 *  
	 */
	@Override
	public void savePerson(Person person) throws IllegalArgumentException {
		// TODO Hannes testet was in dieser Methode
		
	}

	/**
	 *  
	 */
	@Override
	public void saveTeam(Team team) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  
	 */
	@Override
	public void saveOrganisation(Organisation organisation) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  
	 */
	@Override
	public PartnerProfile getPartnerProfileFor(OrgaUnit orgaUnit) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  
	 */
	@Override
	public void savePartnerProfileFor(OrgaUnit orgaUnit, PartnerProfile partnerProfile)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  
	 */
	@Override
	public Vector<Marketplace> getAllMarketplaces() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  
	 */
	@Override
	public Vector<Marketplace> getMarketplacesFor(OrgaUnit orgaUnit) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  
	 */
	@Override
	public Vector<Project> getAllProjects() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  
	 */
	@Override
	public Vector<Project> getProjectsFor(OrgaUnit orgaUnit) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  
	 */
	@Override
	public Vector<Call> getAllCalls() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  
	 */
	@Override
	public Vector<Application> getAllApplications() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  
	 */
	@Override
	public Vector<Application> getApplicationsFor(OrgaUnit orgaUnit) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  
	 */
	@Override
	public Vector<Application> getApplicationsForProjectsFor(OrgaUnit orgaUnit) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  
	 */
	@Override
	public Project createProject(Marketplace marketplace, String title, String description, Person projectLeaderPerson,
			OrgaUnit projectOwnerOrgaUnit, Date startDate, Date endDate) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  
	 */
	@Override
	public void saveProject(Project project) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  
	 */
	@Override
	public void deleteProject(Project project) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  
	 */
	@Override
	public Marketplace createMarketplace(String title) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  
	 */
	@Override
	public void saveMarketplace(Marketplace marketplace) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  
	 */
	@Override
	public void deleteMarketplace(Marketplace marketplace) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  
	 */
	@Override
	public Call createCall(Project project, Person projectLeaderPerson, PartnerProfile partnerProfile, String title,
			String description, Date deadline) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  
	 */
	@Override
	public void saveCall(Call call) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  
	 */
	@Override
	public void deleteCall(Call call) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  
	 */
	@Override
	public Application applyFor(Call call, OrgaUnit applicantOrgaUnit, Date createDate, String applicationText)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  
	 */
	@Override
	public void saveApplication(Application application) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  
	 */
	@Override
	public void deleteApplication(Application application) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  
	 */
	@Override
	public Rating rateApplication(Application application, Double rating, String ratingStatemant)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  
	 */
	@Override
	public void saveRating(Rating rating) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  
	 */
	@Override
	public void deleteRating(Rating rating) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  
	 */
	@Override
	public Enrollment createEnrollment(Project project, OrgaUnit orgaUnit, Rating rating, Date startDate, Date endDate,
			int period) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  
	 */
	@Override
	public void saveEnrollment(Enrollment enrollment) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  
	 */
	@Override
	public void deleteEnrollment(Enrollment enrollment) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  
	 */
	@Override
	public Property createProperty(PartnerProfile partnerProfile, String name, String value)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  
	 */
	@Override
	public void saveProperty(Property property) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  
	 */
	@Override
	public Vector<Property> getAllProperties() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
