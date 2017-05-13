package de.worketplace.team06.client;

import com.google.gwt.core.client.EntryPoint;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.shared.WorketplaceAdministrationAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Editor implements EntryPoint {
	WorketplaceAdministrationAsync worketplaceAdministration = null;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		if (worketplaceAdministration == null) {
			worketplaceAdministration = ClientsideSettings.getWorketplaceAdministration();
		}
	}
}