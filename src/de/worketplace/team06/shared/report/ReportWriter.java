package de.worketplace.team06.shared.report;

/**
 * 
 * @author Toby
 *
 */
public abstract class ReportWriter {

	public abstract void process(AllCalls r);
	
	public abstract void process(AllCallsMatchingWithUser r);
	
	public abstract void process(AllApplicationsForCallsOfUser r);
	
	public abstract void process(AllApplicationsOfUserToCalls r);
	
}
