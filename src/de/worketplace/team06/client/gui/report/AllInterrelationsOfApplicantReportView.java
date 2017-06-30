package de.worketplace.team06.client.gui.report;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.ReportView;
import de.worketplace.team06.shared.report.AllInterrelationsOfApplicantReport;

public class AllInterrelationsOfApplicantReportView extends ReportView {
	public AllInterrelationsOfApplicantReportView() {
		// TODO Auswahl welchen Bewerbers hinzufügen! Und OnChange dann für
		// diesen Bewerber anzeigen
		reportGenerator.createAllInterrelationsOfApplicantReport(ClientsideSettings.getCurrentUser(),
				new AsyncCallback<AllInterrelationsOfApplicantReport>() {
					public void onFailure(Throwable caught) {
						Window.alert("Der Report konnte nicht geladen werden, bitte versuchen Sie es erneut");
					}

					public void onSuccess(AllInterrelationsOfApplicantReport result) {
						writer.process(result);
						AllInterrelationsOfApplicantReportView.this.append(writer.getReportText());
					}
				});
	}

	@Override
	public void loadData() {
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setFirstBreadcrumb(this, "Verflechtungen meines Bewerbers");
	}

	@Override
	public String returnTokenName() {
		return "Verflechtungen-meines-Bewerbers";
	}
}
