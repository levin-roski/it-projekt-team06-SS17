package de.worketplace.team06.shared.report;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * 
 * @author Toby
 *
 */
public abstract class ReportWriter {

	public abstract void process(AllCallsReport r);
	
	public abstract void process(AllCallsOfUserReport r);
	
	public abstract void process(AllCallsMatchingWithUserReport r);
	
	public abstract void process(AllApplicationsForCallsOfUserReport r);
	
	public abstract void process(AllApplicationsOfUserToCallsReport r);
	
	public abstract void process(AllApplicationsOfApplicantReport r);
	
	public abstract void process(AllEnrollmentsOfApplicantReport r);
	
	public abstract void process(AllInterrelationsOfApplicantReport r);
	
	public abstract void process(AllInterrelationsOfAllApplicantsOfUserReport r);
	
	public abstract void process(FanInOfApplicationsOfUserReport r);
	
	public abstract void process(FanOutOfCallsOfUserReport r);
	
	public abstract void process(FanInFanOutOfUserReport r);
	
}
