package de.worketplace.team06.client.gui.report;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.ReportView;
import de.worketplace.team06.shared.report.FanInOfApplicationsOfUserReport;

public class FanOutOfApplicationsOfUserReportView extends ReportView {
	public FanOutOfApplicationsOfUserReportView() {
		reportGenerator.createFanInOfApplicationsOfUserReport(ClientsideSettings.getCurrentUser(), new AsyncCallback<FanInOfApplicationsOfUserReport>() {
			public void onFailure(Throwable caught) {
				Window.alert("Der Report konnte nicht geladen werden, bitte versuchen Sie es erneut");
			}

			public void onSuccess(FanInOfApplicationsOfUserReport result) {
				writer.process(result);
				FanOutOfApplicationsOfUserReportView.this.append(writer.getReportText());
			}
		});
	}

	@Override
	public void loadData() {
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setFirstBreadcrumb(this, "Anzahl meiner Ausschreibungen nach Status (Fan-Out)");
	}

	@Override
	public String returnTokenName() {
		return "Anzahl-meiner-Ausschreibungen-nach-Status-(Fan-Out)";
	}
}
