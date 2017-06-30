package de.worketplace.team06.client.gui.report;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.ReportView;
import de.worketplace.team06.shared.report.FanInFanOutOfUserReport;

public class FanInFanOutOfUserReportView extends ReportView {
	public FanInFanOutOfUserReportView() {
		this.add(ClientsideSettings.getBreadcrumbs());
		reportGenerator.createFanInFanOutOfUserReport(ClientsideSettings.getCurrentUser(), new AsyncCallback<FanInFanOutOfUserReport>() {
			public void onFailure(Throwable caught) {
				Window.alert("Der Report konnte nicht geladen werden, bitte versuchen Sie es erneut");
			}

			public void onSuccess(FanInFanOutOfUserReport result) {
				writer.process(result);
				FanInFanOutOfUserReportView.this.append(writer.getReportText());
			}
		});
	}

	@Override
	public void loadData() {
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setFirstBreadcrumb(this, "Fan-In-Fan-Out-Analyse");
	}

	@Override
	public String returnTokenName() {
		return "Fan-In-Fan-Out-Analyse";
	}
}
