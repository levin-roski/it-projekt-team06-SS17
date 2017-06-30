package de.worketplace.team06.client.gui.report;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.ReportView;
import de.worketplace.team06.shared.report.AllApplicationsForCallsOfUserReport;

public class AllApplicationsForCallsOfUserReportView extends ReportView {
	public AllApplicationsForCallsOfUserReportView() {
		reportGenerator.createAllApplicationsForCallsOfUserReport(ClientsideSettings.getCurrentUser(), new AsyncCallback<AllApplicationsForCallsOfUserReport>() {
			public void onFailure(Throwable caught) {
				Window.alert("Der Report konnte nicht geladen werden, bitte versuchen Sie es erneut");
			}

			public void onSuccess(AllApplicationsForCallsOfUserReport result) {
				writer.process(result);
				AllApplicationsForCallsOfUserReportView.this.append(writer.getReportText());
			}
		});
	}

	@Override
	public void loadData() {
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setFirstBreadcrumb(this, "Bewerbungen auf meine Ausschreibungen");
	}

	@Override
	public String returnTokenName() {
		return "Bewerbungen-auf-meine-Ausschreibungen";
	}
}
