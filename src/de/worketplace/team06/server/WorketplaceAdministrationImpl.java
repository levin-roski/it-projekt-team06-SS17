package de.worketplace.team06.server;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.worketplace.team06.shared.*;
//import de.worketplace.team06.shared.WorketplaceAdministration;
import de.worketplace.team06.shared.bo.*;
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

		//DB Mapper initialisieren
		this.appMapper = ApplicationMapper.applicationMapper();
		this.callMapper = CallMapper.callMapper();
		this.enrollMapper = EnrollmentMapper.enrollmentMapper();
		this.marketMapper = MarketplaceMapper.marketplaceMapper();
		this.orgaMapper = OrganisationMapper.organisationMapper();
		this.partnerMapper = PartnerProfileMapper.partnerProfileMapper();
		this.personMapper = PersonMapper.personMapper();
		this.projectMapper = ProjectMapper.projecteMapper();
		this.propertyMapper = PropertyMapper.propertyMapper();
		this.ratingMapper = RatingMapper.ratingMapper();
		this.teamMapper = TeamMapper.teamMapper();
		
		
	}
	
	public Person getTestUnit() throws IllegalArgumentException {
		Person test = new Person();
		test.setFirstName("Hans");
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
	 *  Methode zum erstellen einer Person. Es werden alle Attribute bis auf die partnerprofileID 
	 *  gesetzt. Die Partnerprofile id kann zu einem spätzeren Zeitpunkt über die savePerson Methode
	 *  gespeichert werden. 
	 *  
	 */
	public Person createPerson(String firstName, String lastName, String street, int zipcode, String city, String description, String googleID) throws IllegalArgumentException {
		Person p = new Person();
		Date created = new Date();
		
		p.setCreated(created);
		p.setDescription(description);
		p.setGoogleID(googleID);
		
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setStreet(street);
		p.setZipcode(zipcode);
		p.setCity(city);
		
		
		/**
		 *  Setzen einer vorläufigen ID. 
		 *  Der korrekte bzw vortlaufende Primärschlüssel (=id) wird über eine Datenbankabfrage in der
		 *  personMapper.insert Methode generiert. (Die Id muss mit den Datensätzen in der Datenbank
		 *  konsistent sein) 
		 */
		p.setID(1);
		
		return this.personMapper.insert(p);
	}
	
	/**
	 *  
	 */
	@Override
	public void savePerson(Person person) throws IllegalArgumentException {
		this.personMapper.update(person);
	}

	/**
	 *  
	 */
	public Team createTeam(String description, String googleID, String name, int membercount) throws IllegalArgumentException {
		
		Team t = new Team();
		Date created = new Date();
		
		t.setCreated(created);
		t.setDescription(description);
		t.setGoogleID(googleID);
		
		t.setName(name);
		t.setMembercount(membercount);
		
		/**
		 *  Siehe createPerson
		 */
		t.setID(1);
		
		return this.teamMapper.insert(t);
	}
	
	/**
	 *  
	 */
	@Override
	public void saveTeam(Team team) throws IllegalArgumentException {
		this.teamMapper.update(team);
	}

	/**
	 *  
	 */
	public Organisation createOrganisation(String description, String googleID, String name, String street, int zipcode, String city) throws IllegalArgumentException {
		
		Organisation o = new Organisation();
		Date created = new Date();
		
		o.setCreated(created);
		o.setDescription(description);
		o.setGoogleID(googleID);
		
		o.setName(name);
		o.setStreet(street);
		o.setZipcode(zipcode);
		o.setCity(city);
		
		/**
		 *  Siehe createPerson
		 */
		o.setID(1);
		
		return this.orgaMapper.insert(o);
	}
	
	/**
	 *  
	 */
	@Override
	public void saveOrganisation(Organisation organisation) throws IllegalArgumentException {
		this.orgaMapper.update(organisation);
		
	}
	@Override
	public PartnerProfile createPartnerProfileFor(Call call, Vector<Property> propertyList)
			throws IllegalArgumentException {
		
		PartnerProfile profile = new PartnerProfile();
		Date created = new Date();
		
		profile.setCreated(created);
		profile.setLastedit(created);
	
		profile.setPropertyList(propertyList);
		profile.setID(1);
		
		profile = this.partnerMapper.insert(profile);
		call.setPartnerProfileID(profile.getID());
		this.callMapper.update(call);
		
		return profile;
	}

	@Override
	public PartnerProfile createPartnerProfileFor(OrgaUnit orgaunit, Vector<Property> propertyList)
			throws IllegalArgumentException {
		
		PartnerProfile profile = new PartnerProfile();
		Date created = new Date();
		
		profile.setCreated(created);
		profile.setLastedit(created);
	
		profile.setPropertyList(propertyList);
		profile.setID(1);
		
		profile = this.partnerMapper.insert(profile);
		orgaunit.setPartnerProfileID(profile.getID()); 
		//OrgaUnitMapper
		//this..update(orgaunit);
		
		return profile;
	}

	@Override
	public PartnerProfile getPartnerProfileFor(Call call) throws IllegalArgumentException {
		return this.partnerMapper.findPartnerProfileByID(call.getPartnerProfileID());
	}

	@Override
	public PartnerProfile getPartnerProfileFor(OrgaUnit orgaunit) throws IllegalArgumentException {
		return this.partnerMapper.findPartnerProfileByID(orgaunit.getPartnerProfileID());
	}


	@Override
	public void savePartnerProfileFor(PartnerProfile partnerProfile) throws IllegalArgumentException {
		this.partnerMapper.update(partnerProfile);
		
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

	/**
	 *  
	 */
	@Override
	public Vector<Marketplace> getAllMarketplaces() throws IllegalArgumentException {
		//***WICHTIG*** @DB-Team: Methode muss noch deklariert werden.
		//Auslesen aller Marktpl�tze aus der DB
		return this.marketMapper.findAll();
	}

	/**
	 *  
	 */
	@Override
	public Vector<Marketplace> getMarketplacesFor(OrgaUnit orgaUnit) throws IllegalArgumentException {
		//***WICHTIG*** @DB-Team: Methode muss noch deklariert werden.
		return this.marketMapper.findById(orgaUnit.getID());
	}

	/**
	 *  
	 */
	@Override
	public Vector<Project> getAllProjects() throws IllegalArgumentException {
		//Auslesen aller Projekte aus der DB
		return this.projectMapper.findAll();
	}

	/**
	 *  
	 */
	@Override
	public Vector<Project> getProjectsFor(OrgaUnit orgaUnit) throws IllegalArgumentException {
		//***WICHTIG*** Nochmals pr�fen...
		//Auslesen aller Projekte f�r eine OrgaUnit aus der DB
		int findID = orgaUnit.getID();
		return this.orgaMapper.findById(findID);
	}

	/**
	 *  
	 */
	@Override
	public Vector<Call> getAllCalls() throws IllegalArgumentException {
		//***WICHTIG*** @DB-Team: Methode muss noch deklariert werden.
		//Auslesen aller Calls aus der DB
		return this.callMapper.findAll();
	}

	/**
	 *  
	 */
	@Override
	public Vector<Application> getAllApplications() throws IllegalArgumentException {
		//Auslesen aller Bewerbungen aus der DB
		return this.appMapper.findAll();
	}

	/**
	 *  
	 */
	@Override
	public Vector<Application> getApplicationsFor(OrgaUnit orgaUnit) throws IllegalArgumentException {
		//***WICHTIG*** Nochmals pr�fen...
		//Auslesen aller Bewerbungen f�r eine OrgaUnit aus der DB
		int findID = orgaUnit.getID();
		return this.appMapper.findByOrganisationApplicant(findID);
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
		
		Project p = new Project();
		p.setTitle(title);
		p.setDescription(description);
		p.setProjectLeaderID(projectLeaderPerson.getID());
		p.setProjectOwnerID(projectOwnerOrgaUnit.getID());
		p.setStartDate(startDate);
		p.setEndDate(endDate);
		
		//Setzen einer vorlaueufigen ID
		p.setID(1);
		
		//Objekt in der DB speichern
		return this.projectMapper.insert(p);
	}

	/**
	 *  Speichern eines Projekts.
	 */
	@Override
	public void saveProject(Project project) throws IllegalArgumentException {
		this.projectMapper.update(project);
		
	}

	/**
	 *  
	 */
	@Override
	public void deleteProject(Project project) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/**
	 *  Erstellen eines Marktplatzes
	 */
	// Wir ben�tigen f�r den Marktplatz ein CreateDate oder nicht? Ggf. beim speichern in der Datenbank erst eintragen...
	@Override
	public Marketplace createMarketplace(String title) throws IllegalArgumentException {
		Marketplace m = new Marketplace();
		m.setTitle(title);
		//m.setCreated();
		
		//Setzen einer vorlaueufigen ID
		m.setID(1);
		
		//Objekt in der DB speichern
		return this.marketMapper.insert(m);
	}

	/**
	 *  
	 */
	@Override
	public void saveMarketplace(Marketplace marketplace) throws IllegalArgumentException {
		this.marketMapper.update(marketplace);
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
		Application a = new Application();
		a.setCreated(created);
		a.setApplicationText(applicationText);
		
		//***WICHTIG*** Hier muss noch das BO angepasst werden.
		//a.setCall(call.getID());
		//a.setOrgaUnit(applicantOrgaUnit.getID());
		
		//Setzen einer vorlaueufigen ID
		a.setID(1);
		
		//Speichern einer ausgehenden Bewerbung in der Datenbank.
		return this.appMapper.insert(a);
	}

	/**
	 *  
	 */
	@Override
	public void saveApplication(Application application) throws IllegalArgumentException {
		this.appMapper.update(application);
		
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
		Enrollment e = new Enrollment();
		//***WICHTIG*** Hier muss noch das BO angepasst werden.
		//e.setProject(project.getID());
		//e.setOrgaUnit(orgaUnit.getID());
		//e.setRating(rating.getID());
		e.setCreated(startDate);
		e.setEndDate(endDate);
		e.setPeriod(period);
		
		//Setzen einer vorlauefigen ID
		e.setID(1);
		
		return this.enrollMapper.insert(e);
		
	}

	/**
	 *  
	 */
	@Override
	public void saveEnrollment(Enrollment enrollment) throws IllegalArgumentException {
		this.enrollMapper.update(enrollment);
				
	}

	/**
	 *  
	 */
	@Override
	public void deleteEnrollment(Enrollment enrollment) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

}
