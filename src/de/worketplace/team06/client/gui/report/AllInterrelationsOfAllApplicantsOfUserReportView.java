package de.worketplace.team06.client.gui.report;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.ReportView;
import de.worketplace.team06.shared.report.AllInterrelationsOfAllApplicantsOfUserReport;

public class AllInterrelationsOfAllApplicantsOfUserReportView extends ReportView {
	public AllInterrelationsOfAllApplicantsOfUserReportView() {
		reportGenerator.createAllInterrelationsOfAllApplicantsOfUserReport(ClientsideSettings.getCurrentUser(), new AsyncCallback<AllInterrelationsOfAllApplicantsOfUserReport>() {
			public void onFailure(Throwable caught) {
				Window.alert("Der Report konnte nicht geladen werden, bitte versuchen Sie es erneut");
			}

			public void onSuccess(AllInterrelationsOfAllApplicantsOfUserReport result) {
				writer.process(result);
				AllInterrelationsOfAllApplicantsOfUserReportView.this.append(writer.getReportText());
			}
		});
	}

	@Override
	public void loadData() {
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setFirstBreadcrumb(this, "Verflechtungen sämtlicher meiner Bewerber");
	}

	@Override
	public String returnTokenName() {
		return "Verflechtungen-saemtlicher-meiner-Bewerber";
	}
}
