package de.worketplace.team06.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

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

/**
 * Das asynchrone Gegenstück des Interface {@link WorketplaceAdministration}. Es wird
 * semiautomatisch durch das Google Plugin erstellt und gepflegt. Daher erfolgt
 * hier keine weitere Dokumentation. Für weitere Informationen siehe das
 * synchrone Interface {@link WorketplaceAdministration}.
 * 
 * @author thies
 */

public interface WorketplaceAdministrationAsync {

	void applyFor(Call call, OrgaUnit applicantOrgaUnit, Date createDate, String applicationText,
			AsyncCallback<Application> callback);

	void checkExistence(int userID, AsyncCallback<Boolean> callback);

	void createCall(Project project, Person projectLeaderPerson, PartnerProfile partnerProfile, String title,
			String description, Date deadline, AsyncCallback<Call> callback);

	void createEnrollment(Project project, OrgaUnit orgaUnit, Rating rating, Date startDate, Date endDate, int period,
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

	void getAllApplications(AsyncCallback<Vector<Application>> callback);

	void getAllCalls(AsyncCallback<Vector<Call>> callback);

	void getAllMarketplaces(AsyncCallback<Vector<Marketplace>> callback);

	void getAllProjects(AsyncCallback<Vector<Project>> callback);

	void getAllProperties(AsyncCallback<Vector<Property>> callback);

	void getApplicationsFor(OrgaUnit orgaUnit, AsyncCallback<Vector<Application>> callback);

	void getApplicationsForProjectsFor(OrgaUnit orgaUnit, AsyncCallback<Vector<Application>> callback);

	void getMarketplacesFor(OrgaUnit orgaUnit, AsyncCallback<Vector<Marketplace>> callback);

	void getPartnerProfileFor(OrgaUnit orgaUnit, AsyncCallback<PartnerProfile> callback);

	void getProjectsFor(OrgaUnit orgaUnit, AsyncCallback<Vector<Project>> callback);

	void init(AsyncCallback<Void> callback);

	void rateApplication(Application application, Double rating, String ratingStatemant,
			AsyncCallback<Rating> callback);

	void saveApplication(Application application, AsyncCallback<Void> callback);

	void saveCall(Call call, AsyncCallback<Void> callback);

	void saveEnrollment(Enrollment enrollment, AsyncCallback<Void> callback);

	void saveMarketplace(Marketplace marketplace, AsyncCallback<Void> callback);

	void saveOrganisation(Organisation organisation, AsyncCallback<Void> callback);

	void savePartnerProfileFor(OrgaUnit orgaUnit, PartnerProfile partnerProfile, AsyncCallback<Void> callback);

	void savePerson(Person person, AsyncCallback<Void> callback);

	void saveProject(Project project, AsyncCallback<Void> callback);

	void saveProperty(Property property, AsyncCallback<Void> callback);

	void saveRating(Rating rating, AsyncCallback<Void> callback);

	void saveTeam(Team team, AsyncCallback<Void> callback);

}
