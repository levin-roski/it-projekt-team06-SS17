package de.worketplace.team06.client;

import com.google.gwt.user.client.History;

import de.worketplace.team06.client.gui.MarketplaceOverView;
import de.worketplace.team06.client.gui.MarketplaceView;
import de.worketplace.team06.client.gui.ProjectView;
import de.worketplace.team06.shared.WorketplaceAdministrationAsync;

/**
 * Superklasse für View-Widgets, die in der GUI immer links gerdendert werden.
 * 
 * @author Roski
 */
public abstract class View extends Page {
	// WorketplaceAdministrationAsync für die Nutzung in allen Subklassen
	protected WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
			.getWorketplaceAdministration();

	public View() {
		this.setWidth("100%");
		loadToken();
	}

	/**
	 * In dieser Methode werden die Breadcrumbs für diese View in den
	 * ClientsideSettings gesetzt
	 */
	public abstract void setBreadcrumb();

	/**
	 * Views enthalten Tabellen, in der Methode loadData werden diese Tabellen
	 * befüllt. Diese Methode kann auch aufgerufen werden, um die Daten der
	 * Tablle neu zu befüllen
	 */
	public abstract void loadData();

	/**
	 * Gibt den Token für diese View zurück, diese kann in der Navigation
	 * hinzugefügt werden, um diese View anzuzeigen.
	 */
	public abstract String returnTokenName();

	/**
	 * Fügt der Navigation ein neues Token hinzu, was das Rendern einer anderen
	 * View hervorruft.
	 */
	public void loadToken() {
		if (!(History.getToken().equals(this.returnTokenName()))) {
			if (this instanceof MarketplaceOverView && ClientsideSettings.getCurrentMarketplaceId() != 0) {
			} else if (this instanceof MarketplaceView && ClientsideSettings.getCurrentProjectId() != 0) {
			} else if (this instanceof ProjectView && ClientsideSettings.getCurrentCallId() != 0) {
			} else {
				History.newItem(this.returnTokenName());
			}
		}
	}
}