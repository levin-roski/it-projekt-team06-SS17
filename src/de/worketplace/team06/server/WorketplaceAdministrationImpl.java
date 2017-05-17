package de.worketplace.team06.server;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.worketplace.team06.shared.*;
//import de.worketplace.team06.shared.WorketplaceAdministration;
import de.worketplace.team06.shared.bo.*;
import de.hdm.thies.bankProjekt.server.db.CustomerMapper;
import de.worketplace.team06.server.db.*;



public class WorketplaceAdministrationImpl extends RemoteServiceServlet implements WorketplaceAdministration{

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObjekt "Application" mit der Datenbank
	 * abgleicht.
	 */
	private ApplicationMapper appMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Call" mit der Datenbank
	 * abgleicht.
	 */
	private CallMapper callMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Enrollment" mit der Datenbank
	 * abgleicht.
	 */
	private EnrollmentMapper enrollMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Marketplace" mit der Datenbank
	 * abgleicht.
	 */
	private MarketplaceMapper marketMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Organisation" mit der Datenbank
	 * abgleicht.
	 */
	private OrganisationMapper orgaMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Partner" mit der Datenbank
	 * abgleicht.
	 */
	private PartnerProfileMapper partnerMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Person" mit der Datenbank
	 * abgleicht.
	 */
	private PersonMapper personMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Project" mit der Datenbank
	 * abgleicht.
	 */
	private ProjectMapper projectMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Property" mit der Datenbank
	 * abgleicht.
	 */
	private PropertyMapper propertyMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Rating" mit der Datenbank
	 * abgleicht.
	 */
	private RatingMapper ratingMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Team" mit der Datenbank
	 * abgleicht.
	 */
	private TeamMapper teamMapper = null;
	

	

	public void init() throws IllegalArgumentException{
		/*this.cMapper = CustomerMapper.customerMapper();
		this.ouMapper = OrgaUnitMapper.orgaUnitMapper();*/
		
	}
	
	public OrgaUnit getTestUnit() throws IllegalArgumentException {
		OrgaUnit test = new OrgaUnit();
		test.setName("Hans");
		return test;
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
