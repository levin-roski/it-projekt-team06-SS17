package de.worketplace.team06.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.gui.EditorNavigation;
import de.worketplace.team06.client.gui.MarketplaceForm;
import de.worketplace.team06.client.gui.SearchMarketplace;
import de.worketplace.team06.client.gui.testRpcGetAllMarketplaces;
import de.worketplace.team06.shared.LoginService;
import de.worketplace.team06.shared.LoginServiceAsync;
import de.worketplace.team06.shared.bo.LoginInfo;
import de.worketplace.team06.shared.bo.OrgaUnit;
import de.worketplace.team06.shared.bo.Person;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Worketplace implements EntryPoint {
	private WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
			.getWorketplaceAdministration();
	private LoginInfo loginInfo = null;
	private Logger console = Logger.getLogger("");

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Person ou = new Person();
		ou.setID(7);
		ou.setGoogleID("G3000");
		ClientsideSettings.setCurrentUser(ou);
		final EditorNavigation navigationMenu = new EditorNavigation();
		navigationMenu.run();

		final MarketplaceForm form = new MarketplaceForm(null);
		form.run();

		final testRpcGetAllMarketplaces testRpcGetAllMarketplaces = new testRpcGetAllMarketplaces();
		// testRpcGetAllMarketplaces.run();

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