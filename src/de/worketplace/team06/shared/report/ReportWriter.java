package de.worketplace.team06.shared.report;

/**
 * 
 * @author Toby
 *
 */
public abstract class ReportWriter {

	public abstract void process(AllCallsReport r);
	
	public abstract void process(AllCallsMatchingWithUserReport r);
	
	public abstract void process(AllApplicationsForCallsOfUserReport r);
	
	public abstract void process(AllApplicationsOfUserToCallsReport r);
	
}
