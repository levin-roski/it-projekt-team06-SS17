package de.worketplace.team06.client.gui.report;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;

import de.worketplace.team06.client.Callback;
import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.ReportView;
import de.worketplace.team06.shared.bo.OrgaUnit;
import de.worketplace.team06.shared.report.AllInterrelationsOfAllApplicantsOfUserReport;

public class AllInterrelationsOfAllApplicantsOfUserReportView extends ReportView {
	public AllInterrelationsOfAllApplicantsOfUserReportView() {
		this.add(ClientsideSettings.getBreadcrumbs());
		final HTMLPanel report = new HTMLPanel("");
		if (ClientsideSettings.getCurrentUser().getType() == "Person") {
			this.add(getAllApplicantsOfCurrentUserInput(new Callback() {
				@Override
				public <T> void runOnePar(final T parameter) {
					class RpcWrapper {
						protected Timer t;
						protected OrgaUnit selectedOrgaUnit;

						public RpcWrapper() {
							worketplaceAdministration.getOrgaUnitById(
									Integer.parseInt(((ListBox) parameter).getSelectedValue()),
									new AsyncCallback<OrgaUnit>() {
										@Override
										public void onFailure(Throwable caught) {
										}

										@Override
										public void onSuccess(OrgaUnit result) {
											selectedOrgaUnit = result;
										}
									});
							t = new Timer() {
								public void run() {
									if (selectedOrgaUnit instanceof OrgaUnit) {
										reportGenerator.createAllInterrelationsOfAllApplicantsOfUserReport(
												selectedOrgaUnit,
												new AsyncCallback<AllInterrelationsOfAllApplicantsOfUserReport>() {
													public void onFailure(Throwable caught) {
														Window.alert(
																"Der Report konnte nicht geladen werden, bitte versuchen Sie es erneut");
													}

													public void onSuccess(
															AllInterrelationsOfAllApplicantsOfUserReport result) {
														report.clear();
														writer.process(result);
														report.add(new HTML(writer.getReportText()));
													}
												});
										RpcWrapper.this.t.cancel();
									}
								}
							};
							// Schedule the timer to check if all RPC calls
							// finished
							// each 400 milliseconds
							t.scheduleRepeating(400);
						};
					}
					new RpcWrapper();
				};

				@Override
				public void run() {
				}
			}));
		} else {
			this.add(new HTML("<h2>Dieser Bericht ist nur für Personen zugänglich</h2>"));
		}
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
