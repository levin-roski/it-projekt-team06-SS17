package de.worketplace.team06.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.gui.EditorNavigation;
import de.worketplace.team06.client.gui.MainPanel;
import de.worketplace.team06.client.gui.MarketplaceForm;
import de.worketplace.team06.client.gui.MyOverview;
import de.worketplace.team06.client.gui.ProjectView;
import de.worketplace.team06.client.gui.MarketplaceOverview;
import de.worketplace.team06.shared.LoginService;
import de.worketplace.team06.shared.LoginServiceAsync;
import de.worketplace.team06.shared.bo.LoginInfo;
import de.worketplace.team06.shared.bo.OrgaUnit;
import de.worketplace.team06.shared.bo.Person;

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

		Person ou = new Person();
		ou.setID(7);
		ou.setGoogleID("G3000");
		ClientsideSettings.setCurrentUser(ou);

		// if (ClientsideSettings.getBreadcrumbFirstLevel() != null) {
		// if (ClientsideSettings.getBreadcrumbSecondLevel() != null) {
		// if (ClientsideSettings.getBreadcrumbThirdLevel() != null) {
		// if (ClientsideSettings.getBreadcrumbFourthLevel() != null) {
		// mainPanel.setView(ClientsideSettings.getBreadcrumbFourthLevel());
		// } else {
		// mainPanel.setView(ClientsideSettings.getBreadcrumbThirdLevel());
		// }
		// } else {
		// mainPanel.setView(ClientsideSettings.getBreadcrumbSecondLevel());
		// }
		// } else {
		// mainPanel.setView(ClientsideSettings.getBreadcrumbFirstLevel());
		// }
		// } else {
		// mainPanel.setView(new MyOverview());
		// }

		mainPanel.setView(new MyOverview());

		RootLayoutPanel rp = RootLayoutPanel.get();
		rp.add(mainPanel);

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