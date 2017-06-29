package de.worketplace.team06.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.worketplace.team06.shared.bo.*;
import de.worketplace.team06.shared.report.*;

/**
 * Das asynchrone Gegenstück des Interface {@link ReportGenerator}. Es wird
 * semiautomatisch durch das Google Plugin erstellt und gepflegt. Daher erfolgt
 * hier keine weitere Dokumentation. Für weitere Informationen siehe das
 * synchrone Interface {@link ReportGenerator}.
 * 
 * @author thies
 */
public interface ReportGeneratorAsync {
	
	void init(AsyncCallback<Void> callback);

	void createAllCallsReport(AsyncCallback<AllCallsReport> callback);
	
	void createAllCallsOfUserReport(OrgaUnit o, AsyncCallback<AllCallsReport> callback);

	void createAllCallsMatchingWithUserReport(OrgaUnit o, AsyncCallback<AllCallsMatchingWithUserReport> callback);

	void createAllApplicationsForCallsOfUserReport(OrgaUnit o,
			AsyncCallback<AllApplicationsForCallsOfUserReport> callback);
	
	void createAllApplicationsOfUserToCallsReport(OrgaUnit o,
			AsyncCallback<AllApplicationsOfUserToCallsReport> callback);

	void createAllApplicationsOfApplicantReport(OrgaUnit applicant, AsyncCallback<AllApplicationsOfApplicantReport> callback);

	void createAllEnrollmentsOfApplicantReport(OrgaUnit applicant, AsyncCallback<AllEnrollmentsOfApplicantReport> callback);

	void createAllInterrelationsOfApplicantReport(OrgaUnit applicant,
			AsyncCallback<AllInterrelationsOfApplicantReport> callback);

	void createAllInterrelationsOfAllApplicantsOfUserReport(OrgaUnit o,
			AsyncCallback<AllInterrelationsOfAllApplicantsOfUserReport> callback);
	
	void createFanInOfApplicationsOfUserReport(OrgaUnit o, AsyncCallback<FanInOfApplicationsOfUserReport> callback);
	
	void createFanOutOfCallsOfUserReport(OrgaUnit o, AsyncCallback<FanOutOfCallsOfUserReport> callback);

	void createFanInFanOutOfUserReport(OrgaUnit o, AsyncCallback<FanInFanOutOfUserReport> callback);
	
	void getOrgaUnitFor(LoginInfo loginInfo, AsyncCallback<OrgaUnit> callback);

}
