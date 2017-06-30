package de.worketplace.team06.client.gui.report;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.ReportView;
import de.worketplace.team06.shared.report.AllEnrollmentsOfApplicantReport;

public class AllEnrollmentsOfApplicantReportView extends ReportView {
	public AllEnrollmentsOfApplicantReportView() {
		// TODO Auswahl welchen Bewerbers hinzufügen! Und OnChange dann für
		// diesen Bewerber anzeigen
		reportGenerator.createAllEnrollmentsOfApplicantReport(ClientsideSettings.getCurrentUser(),
				new AsyncCallback<AllEnrollmentsOfApplicantReport>() {
					public void onFailure(Throwable caught) {
						Window.alert("Der Report konnte nicht geladen werden, bitte versuchen Sie es erneut");
					}

					public void onSuccess(AllEnrollmentsOfApplicantReport result) {
						writer.process(result);
						AllEnrollmentsOfApplicantReportView.this.append(writer.getReportText());
					}
				});
	}

	@Override
	public void loadData() {
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setFirstBreadcrumb(this, "Beteiligungen meines Bewerbers");
	}

	@Override
	public String returnTokenName() {
		return "Beteiligungen-meines-Bewerbers";
	}
}
