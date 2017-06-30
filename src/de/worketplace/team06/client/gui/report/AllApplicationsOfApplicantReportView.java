package de.worketplace.team06.client.gui.report;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.ReportView;
import de.worketplace.team06.shared.report.AllApplicationsOfApplicantReport;

public class AllApplicationsOfApplicantReportView extends ReportView {
	public AllApplicationsOfApplicantReportView() {
		this.add(ClientsideSettings.getBreadcrumbs());
		// TODO Auswahl welchen Bewerbers hinzufügen! Und OnChange dann für
		// diesen Bewerber anzeigen
		reportGenerator.createAllApplicationsOfApplicantReport(ClientsideSettings.getCurrentUser(),
				new AsyncCallback<AllApplicationsOfApplicantReport>() {
					public void onFailure(Throwable caught) {
						Window.alert("Der Report konnte nicht geladen werden, bitte versuchen Sie es erneut");
					}

					public void onSuccess(AllApplicationsOfApplicantReport result) {
						writer.process(result);
						AllApplicationsOfApplicantReportView.this.append(writer.getReportText());
					}
				});
	}

	@Override
	public void loadData() {
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setFirstBreadcrumb(this, "Bewerbungen meines Bewerbers");
	}

	@Override
	public String returnTokenName() {
		return "Bewerbungen-meines-Bewerbers";
	}
}
