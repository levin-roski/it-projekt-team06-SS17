package de.worketplace.team06.client.gui;

import com.google.gwt.user.client.Window;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.ReportView;

public class AllCallsReportView extends ReportView {
	public AllCallsReportView() {
		Window.alert("Seite aufgerufen");
	}

	@Override
	public void loadData() {
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setFirstBreadcrumb(this, "Marktplätze");
	}
}
