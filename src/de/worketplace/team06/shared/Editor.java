package de.worketplace.team06.shared;


import java.util.*;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


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
@RemoteServiceRelativePath("worketplaceadministration")
public interface Editor extends RemoteService {


    /**
     * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
     * RPC zusätzlich zum No Argument Constructor der implementierenden Klasse
     * {@link EditorImpl} notwendig. Bitte diese Methode direkt nach der
     * Instantiierung aufrufen.
     * 
     * @throws IllegalArgumentException
     * @author Thies
     */
    public void init() throws IllegalArgumentException;

    /**
     * Abfragen ob der User existiert.
     * 
     * @param userID die zu abfragende ID eines Users
     * @return
     */
    public boolean checkExistence(int userID) throws IllegalArgumentException;

    /**
     * Speichern eines Objekts vom Typ Person (Subklasse von OrgaUnit)
     * 
     * @param person die zu speichernde Person
     */
    public void savePerson(Person person) throws IllegalArgumentException;

    /**
     * Speichern eines Objekts vom Typ Team (Subklasse von OrgaUnit)
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
     * Abfragen des Partnerprofils einer Organisationseinheit. 
     * 
     * @param orgaUnit 
     * @return
     */
    public PartnerProfile getPartnerProfileFor(OrgaUnit orgaUnit) throws IllegalArgumentException;

    /**
     * @param orgaUnit 
     * @param partnerProfile
     */
    public void savePartnerProfileFor(OrgaUnit orgaUnit, PartnerProfile partnerProfile) throws IllegalArgumentException;

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
     * @param orgaUnit 
     * @return
     */
    public Vector<Project> getProjectsFor(OrgaUnit orgaUnit) throws IllegalArgumentException;

    /**
     * @return
     */
    public Vector<Call> getAllCalls() throws IllegalArgumentException;

    /**
     * @return
     */
    public Vector<Application> getAllApplications() throws IllegalArgumentException;

    /**
     * @param orgaUnit 
     * @return
     */
    public Vector<Application> getApplicationsFor(OrgaUnit orgaUnit) throws IllegalArgumentException;

    /**
     * @param orgaUnit 
     * @return
     */
    public Vector<Application> getApplicationsForProjectsFor(OrgaUnit orgaUnit) throws IllegalArgumentException;

    /**
     * @param marketplace 
     * @param title 
     * @param description 
     * @param projectLeaderPerson 
     * @param projectOwnerOrgaUnit 
     * @param startDate 
     * @param endDate 
     * @return
     */
    public Project createProject(Marketplace marketplace, String title, String description, Person projectLeaderPerson, OrgaUnit projectOwnerOrgaUnit, Date startDate, Date endDate) throws IllegalArgumentException;

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
    public Marketplace createMarketplace(String title) throws IllegalArgumentException;

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
    public Call createCall(Project project, Person projectLeaderPerson, PartnerProfile partnerProfile, String title, String description, Date deadline) throws IllegalArgumentException;

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
    public Application applyFor(Call call, OrgaUnit applicantOrgaUnit,Date createDate , String applicationText) throws IllegalArgumentException;

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
    public Rating rateApplication(Application application, Double rating, String ratingStatemant) throws IllegalArgumentException;

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
    public Enrollment createEnrollment(Project project, OrgaUnit orgaUnit, Rating rating, Date startDate, Date endDate, int period) throws IllegalArgumentException;

    /**
     * @param enrollment
     */
    public void saveEnrollment(Enrollment enrollment) throws IllegalArgumentException;

    /**
     * @param enrollment
     */
    public void deleteEnrollment(Enrollment enrollment) throws IllegalArgumentException;

    /**
     * @param partnerProfile 
     * @param name 
     * @param value 
     * @return
     */
    public Property createProperty(PartnerProfile partnerProfile, String name, String value) throws IllegalArgumentException;

    /**
     * @param property
     */
    public void saveProperty(Property property) throws IllegalArgumentException;

    /**
     * @return
     */
    public Vector<Property> getAllProperties() throws IllegalArgumentException;

}