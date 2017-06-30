package de.worketplace.team06.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.Date;
import java.util.Vector;
import de.worketplace.team06.shared.bo.*;

/**
 * Das asynchrone Gegenstück des Interface {@link WorketplaceAdministration}. Es wird
 * semiautomatisch durch das Google Plugin erstellt und gepflegt. Daher erfolgt
 * hier keine weitere Dokumentation. Für weitere Informationen siehe das
 * synchrone Interface {@link WorketplaceAdministration}.
 * 
 * @author thies
 */

public interface WorketplaceAdministrationAsync {
	void getTestUnit(AsyncCallback<Person> callback);
	
	void applyFor(Call call, OrgaUnit applicantOrgaUnit, String applicationText,
			AsyncCallback<Application> callback);

//	void checkExistence(String googleID, AsyncCallback<Boolean> callback);

	

	void createEnrollment(Application app, Project project, OrgaUnit orgaUnit, Rating rating, Date startDate,
			Date endDate, Integer workload, AsyncCallback<Enrollment> callback);



	void createProject(Marketplace marketplace, String title, String description, Person projectLeaderPerson,
			Date startDate, Date endDate, AsyncCallback<Project> callback);

	void createProperty(PartnerProfile partnerProfile, String name, String value, AsyncCallback<Property> callback);

	void deleteApplication(Application application, AsyncCallback<Void> callback);

	void deleteCall(Call call, AsyncCallback<Void> callback);

	void deleteEnrollment(Enrollment enrollment, AsyncCallback<Void> callback);

	void deleteMarketplace(Marketplace marketplace, AsyncCallback<Void> callback);

	void deleteProject(Project project, AsyncCallback<Void> callback);

	void deleteRating(Rating rating, AsyncCallback<Void> callback);

	//void getAllApplications(AsyncCallback<Vector<Application>> callback);

	void getAllCalls(AsyncCallback<Vector<Call>> callback);

	void getAllMarketplaces(AsyncCallback<Vector<Marketplace>> callback);

	void getAllProjects(AsyncCallback<Vector<Project>> callback);

	void getAllPropertiesFor(PartnerProfile partnerprofile, AsyncCallback<Vector<Property>> callback);

	void getApplicationsFor(Call call, AsyncCallback<Vector<Application>> callback);
	
	void getApplicationsFor(OrgaUnit orgaUnit, AsyncCallback<Vector<Application>> callback);

	void init(AsyncCallback<Void> callback);

	void rateApplication(Application application, Float rating, String ratingStatemant,
			AsyncCallback<Rating> callback);

	void saveApplication(Application application, AsyncCallback<Void> callback);

	void saveCall(Call call, AsyncCallback<Void> callback);

	void saveEnrollment(Enrollment enrollment, AsyncCallback<Void> callback);

	void saveMarketplace(Marketplace marketplace, AsyncCallback<Void> callback);

	void saveOrganisation(Organisation organisation, AsyncCallback<Void> callback);

	void savePartnerProfileFor(PartnerProfile partnerProfile, AsyncCallback<Void> callback);

	void savePerson(Person person, AsyncCallback<Void> callback);

	void saveProject(Project project, AsyncCallback<Void> callback);

	void saveProperty(Property property, AsyncCallback<Void> callback);

	void saveRating(Rating rating, AsyncCallback<Void> callback);

	void saveTeam(Team team, AsyncCallback<Void> callback);

	void createPartnerProfileFor(Call call, AsyncCallback<PartnerProfile> callback);

	void createPartnerProfileFor(OrgaUnit orgaunit,
			AsyncCallback<PartnerProfile> callback);

	void getPartnerProfileFor(Call call, AsyncCallback<PartnerProfile> callback);

	void getPartnerProfileFor(OrgaUnit orgaunit, AsyncCallback<PartnerProfile> callback);

	void getProjectsFor(Marketplace marketplace, AsyncCallback<Vector<Project>> callback);

	void getCallsFor(Project project, AsyncCallback<Vector<Call>> callback);

	void getEnrollmentFor(Project project, AsyncCallback<Vector<Enrollment>> callback);
	
	void getEnrollmentFor(OrgaUnit orgaUnit, AsyncCallback<Vector<Enrollment>> callback);

	

	void getProjectsForLeader(OrgaUnit orgaUnit, AsyncCallback<Vector<Project>> callback);

//	void getProjectsForOwner(OrgaUnit orgaUnit, AsyncCallback<Vector<Project>> callback);

	void getProjectByID(Integer projectID, AsyncCallback<Project> callback);

	void getCallByID(Integer callID, AsyncCallback<Call> callback);

	void getOrgaUnitFor(LoginInfo loginInfo, AsyncCallback<OrgaUnit> callback);

	void createMarketplace(String title, String description, AsyncCallback<Marketplace> callback);

	void deletePerson(Person person, AsyncCallback<Void> callback);

	void deleteTeam(Team team, AsyncCallback<Void> callback);

	void deleteOrganisation(Organisation organisation, AsyncCallback<Void> callback);

	void getMarketplaceByID(Integer marketplaceID, AsyncCallback<Marketplace> callback);

	void createCall(Project project, Person callerPerson, String title, String description, Date deadline,
			AsyncCallback<Call> callback);

	void createPerson(String firstName, String lastName, String street, Integer zipcode, String city,
			String description, String googleID, AsyncCallback<Person> callback);

	void createTeam(String name, String description, Integer membercount, String googleID,
			AsyncCallback<Team> callback);

	void createOrganisation(String name, String description, String street, Integer zipcode, String city, String googleID,
			AsyncCallback<Organisation> callback);

	void deletePartnerProfile(PartnerProfile p, AsyncCallback<Void> callback);

	void getRatingFor(Application application, AsyncCallback<Rating> callback);

	void getRatingFor(Enrollment enrollment, AsyncCallback<Rating> callback);

	//Wird wahrscheinlich nicht benötigt --> void getOrgaUnit(AsyncCallback<OrgaUnit> callback);

	//Wird wahrscheinlich nicht benötigt --> void setOrgaUnit(OrgaUnit ou, AsyncCallback<Void> callback);

	void getPersonByGoogleID(String googleID, AsyncCallback<Person> callback);

	void getTeamByGoogleID(String googleID, AsyncCallback<Team> callback);

	void getOrganisationByGoogleID(String googleID, AsyncCallback<Organisation> callback);

	void getOrgaUnitById(Integer ouid, AsyncCallback<OrgaUnit> callback);

	void deleteProperty(Property p, AsyncCallback<Void> callback);

	void getOpenCallCountFor(Project p, AsyncCallback<Integer> callback);

	void getApplicationCountFor(Call c, AsyncCallback<Integer> callback);

	void getPersonByID(Integer personid, AsyncCallback<Person> callback);

	void getAllPersons(AsyncCallback<Vector<Person>> callback);

	void getAllOrgaUnits(AsyncCallback<Vector<OrgaUnit>> callback);

	void getAllTeams(AsyncCallback<Vector<Team>> callback);

	void getAllOrganisations(AsyncCallback<Vector<Organisation>> callback);

	void getAllApplicantsForAllCallsFrom(Person person, AsyncCallback<Vector<OrgaUnit>> callback);

	void getApplicationsFor(Person projectleader, AsyncCallback<Vector<Application>> callback);

	
}
