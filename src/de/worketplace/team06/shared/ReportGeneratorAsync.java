package de.worketplace.team06.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.worketplace.team06.shared.bo.*;
import de.worketplace.team06.shared.report.AllApplicationsForCallsOfUserReport;
import de.worketplace.team06.shared.report.AllCallsMatchingWithUserReport;
import de.worketplace.team06.shared.report.AllCallsReport;

/**
 * Das asynchrone Gegenstück des Interface {@link ReportGenerator}. Es wird
 * semiautomatisch durch das Google Plugin erstellt und gepflegt. Daher erfolgt
 * hier keine weitere Dokumentation. Für weitere Informationen siehe das
 * synchrone Interface {@link ReportGenerator}.
 * 
 * @author thies
 */
public interface ReportGeneratorAsync {

	void createAllCallsReport(OrgaUnit o, AsyncCallback<AllCallsReport> callback);

	void createAllCallsMatchingWithUserReport(OrgaUnit o, AsyncCallback<AllCallsMatchingWithUserReport> callback);

	void createAllApplicationsForCallsOfUserReport(OrgaUnit o,
			AsyncCallback<AllApplicationsForCallsOfUserReport> callback);

}
