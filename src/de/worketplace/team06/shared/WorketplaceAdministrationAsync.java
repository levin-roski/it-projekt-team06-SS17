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

	void checkExistence(int userID, AsyncCallback<Boolean> callback);

	void createCall(Project project, Person projectLeaderPerson, PartnerProfile partnerProfile, String title,
			String description, Date deadline, AsyncCallback<Call> callback);

	void createEnrollment(Project project, OrgaUnit orgaUnit, Rating rating, Date startDate, Date endDate, int workload,
			AsyncCallback<Enrollment> callback);

	void createMarketplace(String title, AsyncCallback<Marketplace> callback);

	void createProject(Marketplace marketplace, String title, String description, Person projectLeaderPerson,
			OrgaUnit projectOwnerOrgaUnit, Date startDate, Date endDate, AsyncCallback<Project> callback);

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

	void getMarketplacesFor(OrgaUnit orgaUnit, AsyncCallback<Vector<Marketplace>> callback);

	void getProjectsFor(OrgaUnit orgaUnit, AsyncCallback<Vector<Project>> callback);

	void init(AsyncCallback<Void> callback);

	void rateApplication(Application application, Double rating, String ratingStatemant,
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

	void createPartnerProfileFor(Call call, Vector<Property> propertyList, AsyncCallback<PartnerProfile> callback);

	void createPartnerProfileFor(OrgaUnit orgaunit, Vector<Property> propertyList,
			AsyncCallback<PartnerProfile> callback);

	void getPartnerProfileFor(Call call, AsyncCallback<PartnerProfile> callback);

	void getPartnerProfileFor(OrgaUnit orgaunit, AsyncCallback<PartnerProfile> callback);

	void getProjectsFor(Marketplace marketplace, AsyncCallback<Vector<Project>> callback);

	void getCallsFor(Project project, AsyncCallback<Vector<Call>> callback);

	void getEnrollmentFor(Project project, AsyncCallback<Vector<Enrollment>> callback);
	
	void getEnrollmentFor(OrgaUnit orgaUnit, AsyncCallback<Vector<Enrollment>> callback);

	
}
