package de.worketplace.team06.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.worketplace.team06.client.gui.MainPanel;
import de.worketplace.team06.client.gui.OrgaUnitFormView;
import de.worketplace.team06.client.gui.UserNavigationReport;
import de.worketplace.team06.client.gui.report.AllApplicationsForCallsOfUserReportView;
import de.worketplace.team06.client.gui.report.AllApplicationsOfApplicantReportView;
import de.worketplace.team06.client.gui.report.AllCallsMatchingWithUserReportView;
import de.worketplace.team06.client.gui.report.AllCallsOfUserReportView;
import de.worketplace.team06.client.gui.report.AllCallsReportView;
import de.worketplace.team06.client.gui.report.AllEnrollmentsOfApplicantReportView;
import de.worketplace.team06.client.gui.report.AllInterrelationsOfAllApplicantsOfUserReportView;
import de.worketplace.team06.client.gui.report.AllInterrelationsOfApplicantReportView;
import de.worketplace.team06.client.gui.report.FanInFanOutOfUserReportView;
import de.worketplace.team06.client.gui.report.FanInOfApplicationsOfUserReportView;
import de.worketplace.team06.client.gui.report.FanOutOfCallsOfUserReportView;
import de.worketplace.team06.client.gui.report.HomeReportView;
import de.worketplace.team06.client.gui.report.ReportNavigation;
import de.worketplace.team06.shared.LoginService;
import de.worketplace.team06.shared.LoginServiceAsync;
import de.worketplace.team06.shared.ReportGeneratorAsync;
import de.worketplace.team06.shared.bo.LoginInfo;
import de.worketplace.team06.shared.bo.OrgaUnit;

/**
 * Entry-Point-Klasse des Projekts <b>Worketplace</b>. Diese enhtählt die
 * Funktionalitäten des Report Generators.
 * 
 * @author Roski
 */
public class Report implements EntryPoint {
	private ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
	private MainPanel mainPanel;

	/**
	 * Durch Interface <code>EntryPoint</code> nötig. Wird ausgeführt, sobald
	 * per URL angesprochen. <code>public void onModuleLoad()</code>. Diese ist
	 * das GWT-Pendant der <code>main()</code>-Methode normaler
	 * Java-Applikationen.
	 */
	@Override
	public void onModuleLoad() {
		mainPanel = new MainPanel(Unit.PCT);
		ClientsideSettings.setMainPanel(mainPanel);
		RootLayoutPanel rp = RootLayoutPanel.get();
		rp.add(mainPanel);

		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL() + "report.html", new AsyncCallback<LoginInfo>() {
			public void onFailure(Throwable error) {
			}

			public void onSuccess(LoginInfo result) {
				ClientsideSettings.setLoginInfo(result);
				if (result.isLoggedIn()) {
					reportGenerator.getOrgaUnitFor(ClientsideSettings.getLoginInfo(), new AsyncCallback<OrgaUnit>() {
						public void onFailure(Throwable caught) {
							Window.alert(
									"Ihr Nutzer konnte nicht abgerufen werden, bitte kontaktieren Sie den technischen Support");
						}

						@Override
						public void onSuccess(OrgaUnit result) {
							if (result instanceof OrgaUnit) {
								ClientsideSettings.setCurrentUser(result);
								renderApplicationForLoggedIn();
							} else {
								mainPanel.setView(new OrgaUnitFormView());
							}
						}
					});
				} else {
					Window.Location.replace(result.getLoginUrl());
				}
			}
		});
	}

	public void renderApplicationForLoggedIn() {
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				renderUrlToken(event.getValue());
			}
		});

		/*
		 * Navigationsleiste des Editors
		 */
		HorizontalPanel fullNav = new HorizontalPanel();
		ReportNavigation editNav = new ReportNavigation();
		editNav.setStyleName("editNav");
		fullNav.add(editNav);
		UserNavigationReport userNav = new UserNavigationReport();
		userNav.setStyleName("userNav");
		fullNav.add(userNav);
		RootPanel.get("navigation").add(fullNav);

		renderUrlToken(null);
	}

	private void renderUrlToken(String historyToken) {
		if (historyToken == null) {
			historyToken = History.getToken();
		}
		// Die Tokens der Navigation rendern
		try {
			if (historyToken.equals("Startseite")) {
				mainPanel.setView(new HomeReportView());
			} else if (historyToken.equals("Mein-Nutzer")) {
				mainPanel.setView(new OrgaUnitFormView());
			} else if (historyToken.equals("Alle-Ausschreibungen")) {
				mainPanel.setView(new AllCallsReportView());
			} else if (historyToken.equals("Passende-Ausschreibungen-zu-meinem-Partnerprofil")) {
				mainPanel.setView(new AllCallsMatchingWithUserReportView());
			} else if (historyToken.equals("Anzahl-meiner-Bewerbungen-nach-Status-(Fan-In)")) {
				mainPanel.setView(new FanInOfApplicationsOfUserReportView());
			} else if (historyToken.equals("Fan-In-Fan-Out-Analyse")) {
				mainPanel.setView(new FanInFanOutOfUserReportView());
			} else if (historyToken.equals("Meine-Ausschreibungen")) {
				mainPanel.setView(new AllCallsOfUserReportView());
			} else if (historyToken.equals("Anzahl-meiner-Ausschreibungen-nach-Status-(Fan-Out)")) {
				mainPanel.setView(new FanOutOfCallsOfUserReportView());
			} else if (historyToken.equals("Bewerbungen-auf-meine-Ausschreibungen")) {
				mainPanel.setView(new AllApplicationsForCallsOfUserReportView());
			} else if (historyToken.equals("Bewerbungen-meines-Bewerbers")) {
				mainPanel.setView(new AllApplicationsOfApplicantReportView());
			} else if (historyToken.equals("Beteiligungen-meines-Bewerbers")) {
				mainPanel.setView(new AllEnrollmentsOfApplicantReportView());
			} else if (historyToken.equals("Verflechtungen-meines-Bewerbers")) {
				mainPanel.setView(new AllInterrelationsOfApplicantReportView());
			} else if (historyToken.equals("Verflechtungen-saemtlicher-meiner-Bewerber")) {
				mainPanel.setView(new AllInterrelationsOfAllApplicantsOfUserReportView());
			} else {
				mainPanel.setView(new HomeReportView());
			}
		} catch (IndexOutOfBoundsException e) {
			mainPanel.setView(new HomeReportView());
		}
	}
}