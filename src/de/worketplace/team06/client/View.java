package de.worketplace.team06.client;

import com.google.gwt.user.client.History;

import de.worketplace.team06.shared.WorketplaceAdministrationAsync;

public abstract class View extends Page {
	protected WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
			.getWorketplaceAdministration();
	public View() {
		this.setBreadcrumb();
		if (!(History.getToken().equals(this.returnTokenName()))) {
			History.newItem(this.returnTokenName());
		}
	}

	public abstract void setBreadcrumb();

	public abstract void loadData();
	
	public abstract String returnTokenName();
}