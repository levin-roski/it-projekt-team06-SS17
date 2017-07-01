package de.worketplace.team06.client.gui.report;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.ReportView;
import de.worketplace.team06.shared.report.AllCallsReport;

public class AllCallsOfUserReportView extends ReportView {
	public AllCallsOfUserReportView() {
		this.add(ClientsideSettings.getBreadcrumbs());
		reportGenerator.createAllCallsOfUserReport(ClientsideSettings.getCurrentUser(), new AsyncCallback<AllCallsReport>() {
			public void onFailure(Throwable caught) {
				Window.alert("Der Report konnte nicht geladen werden, bitte versuchen Sie es erneut");
			}

			public void onSuccess(AllCallsReport result) {
				writer.process(result);
				AllCallsOfUserReportView.this.append(writer.getReportText());
			}
		});
	}

	@Override
	public void loadData() {
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setFirstBreadcrumb(this, "Meine Ausschreibungen");
	}

	@Override
	public String returnTokenName() {
		return "Meine-Ausschreibungen";
	}
}
