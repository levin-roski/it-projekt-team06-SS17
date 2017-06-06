package de.worketplace.team06.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.shared.LoginService;
import de.worketplace.team06.shared.LoginServiceAsync;
import de.worketplace.team06.shared.bo.OrgaUnit;
import de.worketplace.team06.shared.bo.Person;
import de.worketplace.team06.shared.bo.LoginInfo;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Worketplace implements EntryPoint {
	private WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings.getWorketplaceAdministration();
	private LoginInfo loginInfo = null;
	private Logger console = Logger.getLogger("");

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL() + "Worketplace.html", new AsyncCallback<LoginInfo>() {
			public void onFailure(Throwable error) {
				console.log(Level.SEVERE, "Login Anfrage konnte nicht ausgeführt werden");
			}

			public void onSuccess(LoginInfo result) {
				loginInfo = result;
				if (loginInfo.isLoggedIn()) {
					worketplaceAdministration.checkExistence(loginInfo.getGoogleId(), new CheckExistenceLoginInfoCallback());
				} else {
//					TODO Login aufrufen (nicht eingeloggt)
					Window.alert("Login aufrufen");
				}
			}
		});
	}

	
	class CheckExistenceLoginInfoCallback implements AsyncCallback<Boolean> {
		public void onFailure(Throwable caught) {
//			TODO Fehlermeldung überlegen
		}
		
		public void onSuccess(Boolean userStatus) {
			if (userStatus) {
//				TODO Editor initialisieren mit z.B. loadEditor
			} else {
				Window.alert( "Für diese Email existiert kein Nutzer." + " Bitte erstelle ein neues Nutzerporofil");

//				TODO Usererstellung hier ausführen, bzw. Formular für Usereinstellungen aufrufen
			}
		}
	}
	
	/**
	 * Helfermethode fÃ¼r die onFailure Methoden, In der Methode handleError werden alle Fehler fÃ¼r den Editor gepflegt. 
	 * @param error
	 */
	private void handleError(Throwable error) {
	    Window.alert(error.getMessage());
	    if (error instanceof NotLoggedInException) {
	      Window.Location.replace(loginInfo.getLogoutUrl());
	    }
	    if (error instanceof UserChangedException) {
	    	Window.Location.replace(loginInfo.getLogoutUrl());
	    }
	  }
}