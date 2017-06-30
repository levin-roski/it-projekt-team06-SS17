/**
 * 
 */
package de.worketplace.team06.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.worketplace.team06.shared.bo.*;
import de.worketplace.team06.shared.report.*;


/**
 * @author Toby
 *
 */
@RemoteServiceRelativePath("reportGenerator")
public interface ReportGenerator extends RemoteService {

	public void init() throws IllegalArgumentException;
	
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
	
	public AllInterrelationsOfAllApplicantsOfUserReport createAllInterrelationsOfAllApplicantsOfUserReport(OrgaUnit o)
			throws IllegalArgumentException;
	
	public FanInOfApplicationsOfUserReport createFanInOfApplicationsOfUserReport(OrgaUnit o) throws IllegalArgumentException;
	
	public FanOutOfCallsOfUserReport createFanOutOfCallsOfUserReport(OrgaUnit o) throws IllegalArgumentException;

	public FanInFanOutOfUserReport createFanInFanOutOfUserReport(OrgaUnit o) throws IllegalArgumentException;
	
	public OrgaUnit getOrgaUnitFor(LoginInfo loginInfo) throws IllegalArgumentException;

	public Vector<OrgaUnit> getAllApplicantsForAllCallsFrom(Person person);


}