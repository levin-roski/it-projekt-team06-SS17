package de.worketplace.team06.client.gui;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.ReportView;
import de.worketplace.team06.shared.report.FanInOfApplicationsOfUserReport;

public class FanInOfApplicationsOfUserReportView extends ReportView {
	public FanInOfApplicationsOfUserReportView() {
		this.add(ClientsideSettings.getBreadcrumbs());
		reportGenerator.createFanInOfApplicationsOfUserReport(ClientsideSettings.getCurrentUser(), new AsyncCallback<FanInOfApplicationsOfUserReport>() {
			public void onFailure(Throwable caught) {
				Window.alert("Der Report konnte nicht geladen werden, bitte versuchen Sie es erneut");
			}

			public void onSuccess(FanInOfApplicationsOfUserReport result) {
				writer.process(result);
				FanInOfApplicationsOfUserReportView.this.append(writer.getReportText());
			}
		});
	}

	@Override
	public void loadData() {
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setFirstBreadcrumb(this, "Anzahl meiner Bewerbungen nach Status (Fan-In)");
	}
}
