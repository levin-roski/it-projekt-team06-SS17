/**
 * 
 */
package de.worketplace.team06.shared;

import java.util.*;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.worketplace.team06.shared.bo.*;
import de.worketplace.team06.shared.report.AllApplicationsForCallsOfUserReport;
import de.worketplace.team06.shared.report.AllCallsMatchingWithUserReport;
import de.worketplace.team06.shared.report.AllCallsReport;

/**
 * @author Toby
 *
 */
@RemoteServiceRelativePath("worketplaceAdmin")
public interface ReportGenerator extends RemoteService {

	public AllCallsReport createAllCallsReport(OrgaUnit o) throws IllegalArgumentException;

	public AllCallsMatchingWithUserReport createAllCallsMatchingWithUserReport(OrgaUnit o) throws IllegalArgumentException;

	public AllApplicationsForCallsOfUserReport createAllApplicationsForCallsOfUserReport(OrgaUnit o)
			throws IllegalArgumentException;

	public OrgaUnit getOrgaUnitFor(LoginInfo loginInfo) throws IllegalArgumentException;

}