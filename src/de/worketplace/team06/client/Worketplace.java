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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.worketplace.team06.client.gui.CallOverView;
import de.worketplace.team06.client.gui.CallView;
import de.worketplace.team06.client.gui.EditorNavigation;
import de.worketplace.team06.client.gui.MainPanel;
import de.worketplace.team06.client.gui.MarketplaceOverView;
import de.worketplace.team06.client.gui.MarketplaceView;
import de.worketplace.team06.client.gui.MyOverView;
import de.worketplace.team06.client.gui.OrgaUnitFormView;
import de.worketplace.team06.client.gui.OrgaUnitPartnerProfileView;
import de.worketplace.team06.client.gui.ProjectOverView;
import de.worketplace.team06.client.gui.ProjectView;
import de.worketplace.team06.client.gui.UserNavigation;
import de.worketplace.team06.shared.LoginService;
import de.worketplace.team06.shared.LoginServiceAsync;
import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.shared.bo.Call;
import de.worketplace.team06.shared.bo.LoginInfo;
import de.worketplace.team06.shared.bo.Marketplace;
import de.worketplace.team06.shared.bo.OrgaUnit;
import de.worketplace.team06.shared.bo.Project;

/**
 * Entry-Point-Klasse des Projekts <b>Worketplace</b>. Diese enhtählt die
 * Funktionalitäten des Editors.
 */
public class Worketplace implements EntryPoint {
	private WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
			.getWorketplaceAdministration();
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
		loginService.login(GWT.getHostPageBaseURL() + "worketplace.html", new AsyncCallback<LoginInfo>() {
			public void onFailure(Throwable error) {
			}

			public void onSuccess(LoginInfo result) {
				ClientsideSettings.setLoginInfo(result);
				if (result.isLoggedIn()) {
					worketplaceAdministration.getOrgaUnitFor(ClientsideSettings.getLoginInfo(),
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
		EditorNavigation editNav = new EditorNavigation();
		editNav.setStyleName("editNav");
		fullNav.add(editNav);
		UserNavigation userNav = new UserNavigation();
		userNav.setStyleName("userNav");
		fullNav.add(userNav);
		RootPanel.get("navigation").add(fullNav);

		renderUrlToken(null);
	}

	private void renderUrlToken(String historyToken) {
		if (historyToken == null) {
			historyToken = History.getToken();
		}
		// Parse the history token
		try {
			if (historyToken.substring(0, 12).equals("Marktplaetze")) {
				mainPanel.setView(new MarketplaceOverView());
			} else if (historyToken.equals("Projekte")) {
				mainPanel.setView(new ProjectOverView());
			} else if (historyToken.equals("Ausschreibungen")) {
				mainPanel.setView(new CallOverView());
			} else if (historyToken.equals("Mein-Nutzer")) {
				mainPanel.setView(new OrgaUnitFormView());
			} else if (historyToken.equals("Mein-Partnerprofil")) {
				mainPanel.setView(new OrgaUnitPartnerProfileView());
			} else if (historyToken.substring(0, 18).equals("Marktplatz-Details")) {
				worketplaceAdministration.getMarketplaceByID(Integer.parseInt(historyToken.substring(18)),
						new AsyncCallback<Marketplace>() {
							@Override
							public void onFailure(Throwable caught) {
								mainPanel.setView(new MyOverView());
							}

							@Override
							public void onSuccess(Marketplace result) {
								ClientsideSettings.setCurrentMarketplaceId(result.getID());
								mainPanel.setView(new MarketplaceOverView());
								mainPanel.setView(new MarketplaceView(result));
							}
						});
			} else if (historyToken.substring(0, 15).equals("Projekt-Details")) {
				// Split projectID to ids[0] and marketplaceID to ids[1]
				final String[] ids = historyToken.substring(15).split("-");
				class RpcWrapper {
					protected Marketplace selectedMarketplace;
					protected int checkRpcTimerCounter = 1;
					protected Timer t;

					public RpcWrapper(final String[] ids) {
						worketplaceAdministration.getMarketplaceByID(Integer.parseInt(ids[1]),
								new AsyncCallback<Marketplace>() {
									@Override
									public void onFailure(Throwable caught) {
									}

									@Override
									public void onSuccess(Marketplace result) {
										ClientsideSettings.setCurrentMarketplaceId(result.getID());
										selectedMarketplace = result;
									}
								});
						t = new Timer() {
							public void run() {
								checkRpcTimerCounter++;
								if (selectedMarketplace instanceof Marketplace) {
									worketplaceAdministration.getProjectByID(Integer.parseInt(ids[0]),
											new AsyncCallback<Project>() {
												@Override
												public void onFailure(Throwable caught) {
													mainPanel.setView(new MyOverView());
												}

												@Override
												public void onSuccess(Project result) {
													ClientsideSettings.setCurrentProjectId(result.getID());
													new MarketplaceOverView();
													new MarketplaceView(selectedMarketplace);
													mainPanel.setView(new ProjectView(result));
												}
											});
									t.cancel();
									checkRpcTimerCounter = 1;
								} else if (RpcWrapper.this.checkRpcTimerCounter == 25) {
									t.cancel();
									checkRpcTimerCounter = 1;
									mainPanel.setView(new MyOverView());
								}
							}
						};
						// Schedule the timer to check if all RPC calls finished
						// each 400 milliseconds
						t.scheduleRepeating(400);
					}
				}
				new RpcWrapper(ids);
			} else if (historyToken.substring(0, 22).equals("Ausschreibungs-Details")) {
				// Split callID to ids[0], projectID to ids[1] and marketplaceID
				// to ids[2]
				final String[] ids = historyToken.substring(22).split("-");

				class RpcWrapper {
					protected Marketplace selectedMarketplace;
					protected Project selectedProject;
					protected int checkRpcTimerCounter = 1;
					protected Timer t;
					protected int checkRpcTimerCounter2 = 1;
					protected Timer t2;

					public RpcWrapper(final String[] ids) {
						worketplaceAdministration.getMarketplaceByID(Integer.parseInt(ids[2]),
								new AsyncCallback<Marketplace>() {
									@Override
									public void onFailure(Throwable caught) {
									}

									@Override
									public void onSuccess(Marketplace result) {
										ClientsideSettings.setCurrentMarketplaceId(result.getID());
										selectedMarketplace = result;
									}
								});
						t = new Timer() {
							public void run() {
								checkRpcTimerCounter++;
								if (selectedMarketplace instanceof Marketplace) {
									worketplaceAdministration.getProjectByID(Integer.parseInt(ids[1]),
											new AsyncCallback<Project>() {
												@Override
												public void onFailure(Throwable caught) {
													mainPanel.setView(new MyOverView());
												}

												@Override
												public void onSuccess(Project result) {
													ClientsideSettings.setCurrentProjectId(result.getID());
													selectedProject = result;
												}
											});
									RpcWrapper.this.t.cancel();
									RpcWrapper.this.checkRpcTimerCounter = 1;
									t2 = new Timer() {
										public void run() {
											RpcWrapper.this.checkRpcTimerCounter2++;
											if (selectedMarketplace instanceof Marketplace
													&& selectedProject instanceof Project) {
												worketplaceAdministration.getCallByID(Integer.parseInt(ids[0]),
														new AsyncCallback<Call>() {
															@Override
															public void onFailure(Throwable caught) {
																mainPanel.setView(new MyOverView());
															}

															@Override
															public void onSuccess(Call result) {
																ClientsideSettings.setCurrentCallId(result.getID());
																new MarketplaceOverView();
																new MarketplaceView(selectedMarketplace);
																new ProjectView(selectedProject);
																mainPanel.setView(new CallView(result));
															}
														});
												t2.cancel();
												checkRpcTimerCounter2 = 1;
											} else if (checkRpcTimerCounter2 == 25) {
												t2.cancel();
												checkRpcTimerCounter2 = 1;
												mainPanel.setView(new MyOverView());
											}
										}
									};
									// Schedule the timer to check if all RPC
									// calls finished
									// each 400 milliseconds
									t2.scheduleRepeating(400);
								} else if (checkRpcTimerCounter == 25) {
									t.cancel();
									checkRpcTimerCounter = 1;
									mainPanel.setView(new MyOverView());
								}
							}
						};
						// Schedule the timer to check if all RPC calls finished
						// each 400 milliseconds
						t.scheduleRepeating(400);
					}
				}
				new RpcWrapper(ids);
			} else {
				mainPanel.setView(new MyOverView());
			}
		} catch (IndexOutOfBoundsException e) {
			mainPanel.setView(new MyOverView());
		}
	}
}