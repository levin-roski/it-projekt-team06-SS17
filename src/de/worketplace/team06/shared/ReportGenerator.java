/**
 * 
 */
package de.worketplace.team06.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.worketplace.team06.shared.bo.*;
import de.worketplace.team06.shared.report.AllApplicationsForCallsOfUserReport;
import de.worketplace.team06.shared.report.AllApplicationsOfApplicantReport;
import de.worketplace.team06.shared.report.AllApplicationsOfUserToCallsReport;
import de.worketplace.team06.shared.report.AllCallsMatchingWithUserReport;
import de.worketplace.team06.shared.report.AllCallsReport;
import de.worketplace.team06.shared.report.AllEnrollmentsOfApplicantReport;
import de.worketplace.team06.shared.report.AllInterrelationsOfApplicantReport;

/**
 * @author Toby
 *
 */
@RemoteServiceRelativePath("worketplaceAdmin")
public interface ReportGenerator extends RemoteService {

	public AllCallsReport createAllCallsReport() throws IllegalArgumentException;
	
	public AllCallsReport createAllCallsOfUserReport(OrgaUnit o) throws IllegalArgumentException;

	public AllCallsMatchingWithUserReport createAllCallsMatchingWithUserReport(OrgaUnit o) throws IllegalArgumentException;

	public AllApplicationsForCallsOfUserReport createAllApplicationsForCallsOfUserReport(OrgaUnit o)
			throws IllegalArgumentException;
	
	public AllApplicationsOfUserToCallsReport createAllApplicationsOfUserToCallsReport(OrgaUnit o)
			throws IllegalArgumentException;

	public AllApplicationsOfApplicantReport createAllApplicationsOfApplicantReport(OrgaUnit applicant) throws IllegalArgumentException;

	public AllEnrollmentsOfApplicantReport createAllEnrollmentsOfApplicantReport(OrgaUnit applicant) throws IllegalArgumentException;

	public AllInterrelationsOfApplicantReport createAllInterrelationsOfApplicantReport(OrgaUnit applicant)
			throws IllegalArgumentException;
	
	public OrgaUnit getOrgaUnitFor(LoginInfo loginInfo) throws IllegalArgumentException;


	
}