package de.worketplace.team06.client.gui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;

public abstract class Page extends HorizontalPanel {
	protected HTML createHeadline(final String text) {
		HTML sh = new HTML();
		sh.setHTML("<h1>"+text+"</h1>");
		return sh;
	}
}