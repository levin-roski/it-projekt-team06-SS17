package de.worketplace.team06.shared;


import java.util.*;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.worketplace.team06.client.WindowAlertException;
import de.worketplace.team06.shared.bo.*;

/**
 * <p>
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Verwaltung des worketplace.
 * </p>
 * <p>
 * Neben den Business Objekten existiert diese Klasse um die Verflechtungen der Business 
 * Objekte darzustellen. Des Weiteren wird die eigentliche Applikationslogik in dieser 
 * Klasse realisiert. Diese Klasse wird benötigt damit andere Subklassen von
 * {@link BusinessObject} nicht zu sehr mtieinander gekoppelt werden. In der vorliegenden 
 * Klasse wird das Wissen darüber, wie einzelene "Daten"-Objekte koexistieren gekapselt.
 * 
 * <p>
 * <code>@RemoteServiceRelativePath("bankadministration")</code> ist bei der
 * Adressierung des aus der zugehörigen Impl-Klasse entstehenden
 * Servlet-Kompilats behilflich. Es gibt im Wesentlichen einen Teil der URL des
 * Servlets an.
 * </p>
 * 
 * @author Thies, Johannes Müller
 */
@RemoteServiceRelativePath("worketplaceAdmin")
public interface WorketplaceAdministration extends RemoteService {
	public Person getTestUnit();

    /**
     * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
     * RPC zusätzlich zum No Argument Constructor der implementierenden Klasse
     * {@link WorketplaceAdministrationImpl} notwendig. Bitte diese Methode direkt nach der
     * Instantiierung aufrufen.
     * 
     * @throws IllegalArgumentException
     * @author Thies
     */
    public void init() throws IllegalArgumentException;

    /**
     * Abfragen ob der User existiert.
     * 
     * @param googleID die zu abfragende ID eines Users
     * @return
     */
    public boolean checkExistence(String googleID) throws IllegalArgumentException;

    /**
     * Speichern eines Objekts vom Typ Person (Subklasse von OrgaUnit)
     * 
     * @param person die zu speichernde Person
     */
    public void savePerson(Person person) throws IllegalArgumentException;

    /**
     * Speichern eines Objekts vom Typ Team (Subklasse von OrgaUnit)
     * 
     *
     * @param team das zu speichernde Team
     */
    public void saveTeam(Team team) throws IllegalArgumentException;

    /**
     * Speichern eines Objekts vom Typ Organisation (Subklasse von OrgaUnit)
     * 
     * @param organisation die zu speichernde Organisation
     */
    public void saveOrganisation(Organisation organisation) throws IllegalArgumentException;

    /**
     * Create Methoden für die PartnerProfile. Team, Organisation und Person wurden in separate Tabellen ausgelagert.
     * Deshalb ist es notwendig für jedes Subklasse von OrgaUnit(Team, Organisation, Person) eine create-Methode zu
     * erstellen. Einer Ausschreibung (Call) kann ebenfalls ein Partnerprofil angehängt werden. Deshalb muss auch hierfür 
     * eine Create-Methode erstellt werden. 
     * 
     */
    public PartnerProfile createPartnerProfileFor(Call call) throws IllegalArgumentException;
    
    public PartnerProfile createPartnerProfileFor(OrgaUnit orgaunit)
			throws IllegalArgumentException;
    
    /**
     * Abfragen der Partnerprofile. 
     * 
     * @param
     * @return
     */
    public PartnerProfile getPartnerProfileFor(Call call) throws IllegalArgumentException;
    
    public PartnerProfile getPartnerProfileFor(OrgaUnit orgaunit) throws IllegalArgumentException;

    /**
     * Änderungen des Partnerprofils in der Datenbank speichern. 
     * 
     * @param PartnerProfile des Partnerprofils, dass gespeichert werden soll. 
     * @return void
     */
    public void savePartnerProfileFor(PartnerProfile partnerProfile) throws IllegalArgumentException;
    
    
    /**
     * @return
     */
    public Vector<Marketplace> getAllMarketplaces() throws IllegalArgumentException;

    /**
     * @param orgaUnit 
     * @return
     */
    public Vector<Marketplace> getMarketplacesFor(OrgaUnit orgaUnit) throws IllegalArgumentException;

    /**
     * @return
     */
    public Vector<Project> getAllProjects() throws IllegalArgumentException;

 

    /**
     * @param Marketplace
     * @return
     */
    public Vector<Project> getProjectsFor(Marketplace marketplace) throws IllegalArgumentException;
    
    /**
     * @return
     */
    public Vector<Call> getAllCalls() throws IllegalArgumentException;

    /**
     * @return
     */
    //public Vector<Application> getAllApplications() throws IllegalArgumentException;
    
    
    /**
     * @param orgaUnit 
     * @return
     */
    public Vector<Application> getApplicationsFor(OrgaUnit orgaUnit) throws IllegalArgumentException;

    /**
     * @param marketplace 
     * @param title 
     * @param description 
     * @param projectLeaderPerson 
     * @param startDate 
     * @param endDate 
     * @return
     */
    public Project createProject(Marketplace marketplace, String title, String description, Person projectLeaderPerson, Date startDate, Date endDate) throws IllegalArgumentException;

    /**
     * @param project
     */
    public void saveProject(Project project) throws IllegalArgumentException;

    /**
     * @param project
     */
    public void deleteProject(Project project) throws IllegalArgumentException;

    /**
     * @param title 
     * @return
     */
   

    /**
     * @param marketplace
     */
    public void saveMarketplace(Marketplace marketplace) throws IllegalArgumentException;

    /**
     * @param marketplace
     */
    public void deleteMarketplace(Marketplace marketplace) throws IllegalArgumentException;

    /**
     * @param project 
     * @param projectLeaderPerson 
     * @param partnerProfile 
     * @param title 
     * @param description 
     * @param deadline 
     * @return
     */


    /**
     * @param call
     */
    public void saveCall(Call call) throws IllegalArgumentException;

    /**
     * @param call
     */
    public void deleteCall(Call call) throws IllegalArgumentException;

    /**
     * @param call 
     * @param applicantOrgaUnit 
     * @param createDate Date 
     * @param applicationText 
     * @return
     */
    public Application applyFor(Call call, OrgaUnit applicantOrgaUnit, String applicationText) throws IllegalArgumentException;

    /**
     * @param application
     */
    public void saveApplication(Application application) throws IllegalArgumentException;

    /**
     * @param application
     */
    public void deleteApplication(Application application) throws IllegalArgumentException;

    /**
     * @param application 
     * @param rating 
     * @param ratingStatemant 
     * @return
     */
    public Rating rateApplication(Application application, Float rating, String ratingStatemant) throws IllegalArgumentException;

    /**
     * @param rating
     */
    public void saveRating(Rating rating) throws IllegalArgumentException;

    /**
     * @param rating
     */
    public void deleteRating(Rating rating) throws IllegalArgumentException;

    /**
     * @param project 
     * @param orgaUnit 
     * @param rating 
     * @param startDate 
     * @param endDate 
     * @param period 
     * @return
     */
    public Enrollment createEnrollment(Project project, OrgaUnit orgaUnit, Rating rating, Date startDate, Date endDate, Integer workload) throws IllegalArgumentException;

    /**
     * @param enrollment
     */
    public void saveEnrollment(Enrollment enrollment) throws IllegalArgumentException;

    /**
     * @param enrollment
     */
    public void deleteEnrollment(Enrollment enrollment) throws IllegalArgumentException;

    /**
     * @param property
     */
    public void saveProperty(Property property) throws IllegalArgumentException;

    /**
     * @return
     */
    public Vector<Property> getAllPropertiesFor(PartnerProfile partnerprofile);

    /**
     * @return
     */
	public Vector<Call> getCallsFor(Project project) throws IllegalArgumentException;

    /**
     * @return
     */
	public Vector<Application> getApplicationsFor(Call call) throws IllegalArgumentException;

	/**
	 * 
	 * @param project
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Enrollment> getEnrollmentFor(Project project) throws IllegalArgumentException;

	/**
	 * 
	 * @param orgaUnit
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Enrollment> getEnrollmentFor(OrgaUnit orgaUnit) throws IllegalArgumentException;


	public Vector<Project> getProjectsForLeader(OrgaUnit orgaUnit) throws IllegalArgumentException;

//	public Vector<Project> getProjectsForOwner(OrgaUnit orgaUnit) throws IllegalArgumentException;
	
	public Project getProjectByID(Integer projectID) throws IllegalArgumentException;
	
	public Call getCallByID(Integer callID) throws IllegalArgumentException;
	
	public OrgaUnit getOrgaUnitFor(LoginInfo loginInfo) throws IllegalArgumentException;

	public Marketplace createMarketplace(String title, String description, OrgaUnit o) throws IllegalArgumentException;

	public void deletePerson(Person person) throws IllegalArgumentException, WindowAlertException;

	public void deleteTeam(Team team) throws IllegalArgumentException, WindowAlertException;

	public void deleteOrganisation(Organisation organisation) throws IllegalArgumentException, WindowAlertException;

	public Marketplace getMarketplaceByID(Integer marketplaceID) throws IllegalArgumentException;

	public Property createProperty(PartnerProfile partnerProfile, String name, String value) throws IllegalArgumentException;

	public Call createCall(Project project, Person callerPerson, String title, String description, Date deadline)
			throws IllegalArgumentException;
	
	public Person createPerson(String firstName, String lastName, String street, Integer zipcode, String city, String description,
			String googleID);
	
	public Team createTeam(String name, String description, Integer membercount, String googleID) throws IllegalArgumentException;
	
	public Organisation createOrganisation(String name, String description, String street, Integer zipcode, String city,
			String googleID);

	public void deletePartnerProfile(PartnerProfile p) throws IllegalArgumentException;
	
	public Rating getRatingFor(Application application) throws IllegalArgumentException;
	
	public Rating getRatingFor(Enrollment enrollment) throws IllegalArgumentException;

	public OrgaUnit getOrgaUnit() throws IllegalArgumentException;

	public void setOrgaUnit(OrgaUnit ou) throws IllegalArgumentException;

}