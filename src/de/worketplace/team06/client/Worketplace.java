package de.worketplace.team06.client;

import java.util.logging.Level;
import java.util.logging.Logger;

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

import de.worketplace.team06.client.gui.EditorNavigation;
import de.worketplace.team06.client.gui.MainPanel;
import de.worketplace.team06.client.gui.MarketplaceOverview;
import de.worketplace.team06.client.gui.MarketplaceView;
import de.worketplace.team06.client.gui.MyOverview;
import de.worketplace.team06.client.gui.OrgaUnitFormView;
import de.worketplace.team06.client.gui.ProjectView;
import de.worketplace.team06.shared.LoginService;
import de.worketplace.team06.shared.LoginServiceAsync;
import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.shared.bo.LoginInfo;
import de.worketplace.team06.shared.bo.Marketplace;
import de.worketplace.team06.shared.bo.OrgaUnit;
import de.worketplace.team06.shared.bo.Person;
import de.worketplace.team06.shared.bo.Project;

/**
 * Entry-Point-Klasse des Projekts <b>Worketplace</b>. Diese enhtählt die
 * Funktionalitäten des Editors.
 */
public class Worketplace implements EntryPoint {
	private WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
			.getWorketplaceAdministration();
	private LoginInfo loginInfo = null;
	private Logger console = Logger.getLogger("");
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

		worketplaceAdministration.getPersonByGoogleID("G3002", new AsyncCallback<Person>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(Person result) {
				ClientsideSettings.setCurrentUser(result);
				renderUrlToken(null);
			}
		});

		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				renderUrlToken(event.getValue());
			}
		});

		/*
		 * Navigationsleiste des Editors
		 */
		final EditorNavigation navigationMenu = new EditorNavigation();
		RootPanel.get("navigation").add(navigationMenu);
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL() + "Worketplace.html", new AsyncCallback<LoginInfo>() {
			public void onFailure(Throwable error) {
				console.log(Level.SEVERE, "Login Anfrage konnte nicht ausgef�hrt werden");
			}

			public void onSuccess(LoginInfo result) {
				loginInfo = result;
				if (loginInfo.isLoggedIn()) {
					worketplaceAdministration.checkExistence(loginInfo.getGoogleId(),
							new CheckExistenceLoginInfoCallback());
				} else {
					// TODO Login aufrufen (nicht eingeloggt)
					// Window.alert("Login aufrufen");
				}
			}
		});
	}

	private void renderUrlToken(String historyToken) {
		if (historyToken == null) {
			historyToken = History.getToken();
		}
		// Parse the history token
		try {
			if (historyToken.substring(0, 12).equals("Marktplaetze")) {
				mainPanel.setView(new MarketplaceOverview());
			} else if(historyToken.equals("Mein-Nutzer")) {
				mainPanel.setView(new OrgaUnitFormView());
			} else if (historyToken.substring(0, 18).equals("Marktplatz-Details")) {
				worketplaceAdministration.getMarketplaceByID(Integer.parseInt(historyToken.substring(18)),
						new AsyncCallback<Marketplace>() {
							@Override
							public void onFailure(Throwable caught) {
								mainPanel.setView(new MyOverview());
							}

							@Override
							public void onSuccess(Marketplace result) {
								ClientsideSettings.setCurrentMarketplaceId(result.getID());
								ClientsideSettings.setFirstBreadcrumb(new MarketplaceOverview(), "Marktplätze");
								mainPanel.setView(new MarketplaceView(result));
							}
						});
			} else if (historyToken.substring(0, 15).equals("Projekt-Details")) {
				// Split projectID to ids[0] and marketplaceID to ids[1]
				final String[] ids = historyToken.substring(15).split("-");

				class RpcWrapper {
					protected MarketplaceOverview tokenMov;
					protected MarketplaceView tokenMv;
					protected ProjectView tokenPv;
					protected int checkRpcTimerCounter = 1;
					protected Timer t;

					public RpcWrapper(final String[] ids) {
						tokenMov = new MarketplaceOverview();

						Worketplace.this.worketplaceAdministration.getMarketplaceByID(Integer.parseInt(ids[1]),
								new AsyncCallback<Marketplace>() {
									@Override
									public void onFailure(Throwable caught) {
									}

									@Override
									public void onSuccess(Marketplace result) {
										ClientsideSettings.setCurrentMarketplaceId(result.getID());
										RpcWrapper.this.tokenMv = new MarketplaceView(result);
									}
								});
						t = new Timer() {
							public void run() {
								RpcWrapper.this.checkRpcTimerCounter++;
								if (RpcWrapper.this.tokenMov instanceof MarketplaceOverview && RpcWrapper.this.tokenMv instanceof MarketplaceView) {
									Worketplace.this.worketplaceAdministration.getProjectByID(Integer.parseInt(ids[0]),
											new AsyncCallback<Project>() {
												@Override
												public void onFailure(Throwable caught) {
													Worketplace.this.mainPanel.setView(new MyOverview());
												}

												@Override
												public void onSuccess(Project result) {
													ClientsideSettings.setCurrentProjectId(result.getID());
													RpcWrapper.this.tokenPv = new ProjectView(result);
													Worketplace.this.mainPanel.setView(tokenPv);
												}
											});
									RpcWrapper.this.t.cancel();
									RpcWrapper.this.checkRpcTimerCounter = 1;
								} else if (RpcWrapper.this.checkRpcTimerCounter == 25) {
									RpcWrapper.this.t.cancel();
									RpcWrapper.this.checkRpcTimerCounter = 1;
									Worketplace.this.mainPanel.setView(new MyOverview());
								}
							}
						};
						// Schedule the timer to check if all RPC calls finished
						// each 400 milliseconds
						t.scheduleRepeating(400);
					}
				}
				RpcWrapper rw = new RpcWrapper(ids);
			} else if (historyToken.substring(0, 15).equals("calls")) {
				// TODO calls aus Token rendern
			} else {
				mainPanel.setView(new MyOverview());
			}
		} catch (IndexOutOfBoundsException e) {
			mainPanel.setView(new MyOverview());
		}
		// TODO Auch tiefere Strukturen wie ProjectView hinzufügen!
	}

	class CheckExistenceLoginInfoCallback implements AsyncCallback<Boolean> {
		public void onFailure(Throwable caught) {
			// TODO Fehlermeldung �berlegen
		}

		public void onSuccess(Boolean userStatus) {
			if (userStatus) {
				worketplaceAdministration.getOrgaUnitFor(loginInfo, new AsyncCallback<OrgaUnit>() {
					public void onFailure(Throwable caught) {
						Window.alert(
								"Ihr Nutzeraccount konnte nicht abgerufen werden, bitte kontaktieren Sie den technischen Support");
					}

					@Override
					public void onSuccess(OrgaUnit result) {
						ClientsideSettings.setCurrentUser(result);
						// TODO Editor initialisieren mit z.B. loadEditor
					}
				});
			} else {
				// Window.alert( "Für diese Email existiert kein Nutzer." + "
				// Bitte erstelle ein neues Nutzerporofil");

				// TODO Usererstellung hier ausf�hren, bzw. Formular f�r
				// Usereinstellungen aufrufen
			}
		}
	}

	/**
	 * Helfermethode für die onFailure Methoden, In der Methode handleError
	 * werden alle Fehler für den Editor gepflegt.
	 * 
	 * @param error
	 */
	private void handleError(Throwable error) {
		// Window.alert(error.getMessage());
		if (error instanceof NotLoggedInException) {
			Window.Location.replace(loginInfo.getLogoutUrl());
		}
		if (error instanceof UserChangedException) {
			Window.Location.replace(loginInfo.getLogoutUrl());
		}
	}
}