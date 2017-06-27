package de.worketplace.team06.client;

import de.worketplace.team06.shared.WorketplaceAdministrationAsync;

public abstract class View extends Page {
	protected WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
			.getWorketplaceAdministration();
	public View() {
		this.setBreadcrumb();
	}

	public abstract void setBreadcrumb();

	public abstract void loadData();
}