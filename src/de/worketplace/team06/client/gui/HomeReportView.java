package de.worketplace.team06.client.gui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.ReportView;

public class HomeReportView extends ReportView {
	public HomeReportView() {
		VerticalPanel vp = new VerticalPanel();
		SimplePanel sp = new SimplePanel();
		vp.add(createHeadline("Startseite Report Generator", true));
		vp.add(sp);
		sp.add(new HTML(
				"<p>Im oberen Navigationsmenü können Sie verschiedene Reports für die Auswertung von Worketplace generieren.</p>"));
		this.add(vp);
	}

	@Override
	public void loadData() {
	}

	@Override
	public void setBreadcrumb() {
		// TODO
		ClientsideSettings.setFirstBreadcrumb(this, "Marktplätze");
	}
}
