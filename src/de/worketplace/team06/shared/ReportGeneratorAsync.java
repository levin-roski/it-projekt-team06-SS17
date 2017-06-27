package de.worketplace.team06.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.worketplace.team06.shared.bo.*;
import de.worketplace.team06.shared.report.AllApplicationsForCallsOfUserReport;
import de.worketplace.team06.shared.report.AllApplicationsOfApplicantReport;
import de.worketplace.team06.shared.report.AllApplicationsOfUserToCallsReport;
import de.worketplace.team06.shared.report.AllCallsMatchingWithUserReport;
import de.worketplace.team06.shared.report.AllCallsReport;
import de.worketplace.team06.shared.report.AllEnrollmentsOfApplicantReport;
import de.worketplace.team06.shared.report.AllInterrelationsOfApplicantReport;

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
	
	void getOrgaUnitFor(LoginInfo loginInfo, AsyncCallback<OrgaUnit> callback);

}
