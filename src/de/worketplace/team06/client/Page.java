package de.worketplace.team06.client;

import javax.swing.text.html.HTML;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.worketplace.team06.client.gui.MainPanel;
import de.worketplace.team06.shared.bo.OrgaUnit;

public abstract class Page extends VerticalPanel {
	protected OrgaUnit currentUser = ClientsideSettings.getCurrentUser();
	protected MainPanel mainPanel = ClientsideSettings.getMainPanel();
	protected final DateTimeFormat simpleDateFormat = DateTimeFormat.getFormat("yyyy-MM-dd");

	protected HorizontalPanel createHeadline(final String text, boolean isFirstPageElement) {
		HorizontalPanel hp = new HorizontalPanel();
		HTML sh = new HTML();
		if (isFirstPageElement) {
			sh.setHTML("<h1 class=\"first-element\">" + text + "</h1>");
		} else {
			sh.setHTML("<h1>" + text + "</h1>");
		}
		hp.add(sh);
		return hp;
	}

	protected HorizontalPanel createSecondHeadline(final String text) {
		HorizontalPanel hp = new HorizontalPanel();
		HTML sh = new HTML();
		sh.setHTML("<h2>" + text + "</h2>");
		hp.add(sh);
		return hp;
	}
}