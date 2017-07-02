package de.worketplace.team06.client.gui.report;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.ReportView;

public class HomeReportView extends ReportView {
	public HomeReportView() {
		this.add(ClientsideSettings.getBreadcrumbs());
		VerticalPanel vp = new VerticalPanel();
		SimplePanel sp = new SimplePanel();
		vp.add(createHeadline("Report Generator - Startseite", true));
		vp.add(sp);
		sp.add(new HTML(
				"<p class=\"homereport\">Im oberen Navigationsmenü können Sie verschiedene Reports für die Auswertungen von Worketplace generieren.</p>"));
		this.add(vp);
	}

	@Override
	public void loadData() {
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setFirstBreadcrumb(this, "Startseite");
	}

	@Override
	public String returnTokenName() {
		return "Startseite";
	}
}
