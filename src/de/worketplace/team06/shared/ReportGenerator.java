/**
 * 
 */
package de.worketplace.team06.shared;

import java.util.*;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.worketplace.team06.client.WindowAlertException;
import de.worketplace.team06.shared.bo.*;
import de.worketplace.team06.shared.report.AllCallsReport;

/**
 * @author Toby
 *
 */
@RemoteServiceRelativePath("worketplaceAdmin")
public interface ReportGenerator extends RemoteService {

	public AllCallsReport createAllCallsReport(OrgaUnit o) throws IllegalArgumentException;

}