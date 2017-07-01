package de.worketplace.team06.client.gui.report;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.ReportView;
import de.worketplace.team06.shared.report.FanOutOfCallsOfUserReport;

public class FanOutOfCallsOfUserReportView extends ReportView {
	public FanOutOfCallsOfUserReportView() {
		this.add(ClientsideSettings.getBreadcrumbs());
		reportGenerator.createFanOutOfCallsOfUserReport(ClientsideSettings.getCurrentUser(), new AsyncCallback<FanOutOfCallsOfUserReport>() {
			public void onFailure(Throwable caught) {
				Window.alert("Der Report konnte nicht geladen werden, bitte versuchen Sie es erneut");
			}

			public void onSuccess(FanOutOfCallsOfUserReport result) {
				writer.process(result);
				FanOutOfCallsOfUserReportView.this.append(writer.getReportText());
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
