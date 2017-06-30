package de.worketplace.team06.client.gui.report;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.ReportView;
import de.worketplace.team06.shared.report.AllCallsMatchingWithUserReport;

public class AllCallsMatchingWithUserReportView extends ReportView {
	public AllCallsMatchingWithUserReportView() {
		this.add(ClientsideSettings.getBreadcrumbs());
		reportGenerator.createAllCallsMatchingWithUserReport(ClientsideSettings.getCurrentUser(), new AsyncCallback<AllCallsMatchingWithUserReport>() {
			public void onFailure(Throwable caught) {
				Window.alert("Der Report konnte nicht geladen werden, bitte versuchen Sie es erneut");
			}

			public void onSuccess(AllCallsMatchingWithUserReport result) {
				writer.process(result);
				AllCallsMatchingWithUserReportView.this.append(writer.getReportText());
			}
		});
	}

	@Override
	public void loadData() {
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setFirstBreadcrumb(this, "Passende Ausschreibungen zu meinem Partnerprofil");
	}

	@Override
	public String returnTokenName() {
		return "Passende-Ausschreibungen-zu-meinem-Partnerprofil";
	}
}
