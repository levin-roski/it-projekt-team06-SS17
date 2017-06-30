package de.worketplace.team06.client;

import com.google.gwt.user.client.History;

import de.worketplace.team06.client.gui.MarketplaceOverView;
import de.worketplace.team06.client.gui.MarketplaceView;
import de.worketplace.team06.client.gui.ProjectView;
import de.worketplace.team06.shared.WorketplaceAdministrationAsync;

public abstract class View extends Page {
	protected WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
			.getWorketplaceAdministration();

	public View() {
		setBreadcrumb();
		loadToken();
	}

	public abstract void setBreadcrumb();

	public abstract void loadData();

	public abstract String returnTokenName();
	
	public void loadToken() {
		if (!(History.getToken().equals(this.returnTokenName()))) {
			if (this instanceof MarketplaceOverView
					&& ClientsideSettings.getCurrentMarketplaceId() != 0) {
			} else if (this instanceof MarketplaceView
					&& ClientsideSettings.getCurrentProjectId() != 0) {
			} else if (this instanceof ProjectView
					&& ClientsideSettings.getCurrentCallId() != 0) {
			} else {
				History.newItem(this.returnTokenName());
			}
		}
	}
}