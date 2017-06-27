package de.worketplace.team06.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.worketplace.team06.client.gui.AllCallsReportView;
import de.worketplace.team06.client.gui.EditorNavigation;
import de.worketplace.team06.client.gui.MainPanel;
import de.worketplace.team06.client.gui.MarketplaceOverview;
import de.worketplace.team06.client.gui.MarketplaceView;
import de.worketplace.team06.client.gui.MyOverview;
import de.worketplace.team06.client.gui.OrgaUnitFormView;
import de.worketplace.team06.client.gui.ProjectView;
import de.worketplace.team06.shared.LoginService;
import de.worketplace.team06.shared.LoginServiceAsync;
import de.worketplace.team06.shared.ReportGeneratorAsync;
import de.worketplace.team06.shared.bo.LoginInfo;
import de.worketplace.team06.shared.bo.Marketplace;
import de.worketplace.team06.shared.bo.OrgaUnit;
import de.worketplace.team06.shared.bo.Project;

/**
 * Entry-Point-Klasse des Projekts <b>Worketplace</b>. Diese enhtählt die
 * Funktionalitäten des Report Generators.
 */
public class ReportGenerator implements EntryPoint {
	private ReportGeneratorAsync reportAdministration = ClientsideSettings.getReportGenerator();
	private MainPanel mainPanel;

	/**
	 * Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	 * zusichert, benötigen wir eine Methode
	 * <code>public void onModuleLoad()</code>. Diese ist das GWT-Pendant der
	 * <code>main()</code>-Methode normaler Java-Applikationen.
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
					reportAdministration.getOrgaUnitFor(ClientsideSettings.getLoginInfo(),
							new AsyncCallback<OrgaUnit>() {
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
										mainPanel.setView(new OrgaUnitFormView(ReportGenerator.this));
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
		RootPanel.get("navigation").add(new EditorNavigation());

		renderUrlToken(null);
	}

	private void renderUrlToken(String historyToken) {
		if (historyToken == null) {
			historyToken = History.getToken();
		}
		// Parse the history token
		try {
//			if (historyToken.substring(0, 12).equals("Marktplaetze")) {
//				mainPanel.setView(new MarketplaceOverview());
//			} else if (historyToken.equals("Mein-Nutzer")) {
//				mainPanel.setView(new OrgaUnitFormView(null));
//			}
		} catch (IndexOutOfBoundsException e) {
			Window.alert("Startseite aufrufen");
			mainPanel.setView(new AllCallsReportView());
		}
		// TODO Auch tiefere Strukturen wie ProjectView hinzufügen!
	}
}