package de.worketplace.team06.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;

import de.worketplace.team06.client.ClientsideSettings;

public abstract class Page extends HorizontalPanel {
	private MainPanel mainPanel = ClientsideSettings.getMainPanel();
	
	protected HTML createHeadline(final String text, boolean isFirstPageElement) {
		HTML sh = new HTML();
		sh.setHTML("<h1 class=\"first-element\">"+text+"</h1>");
		return sh;
	}
	
	protected HorizontalPanel createHeadlineWithCloseButton(final String text, boolean isFirstPageElement) {
		HorizontalPanel hp = new HorizontalPanel();
		HTML sh = new HTML();
		sh.setHTML("<h1 class=\"first-element\">"+text+"</h1>");
		Button closeButton = new Button("X");
		closeButton.setStyleName("close-button");
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainPanel.closeItem();
			}
		});
		hp.add(closeButton);
		hp.add(sh);
		return hp;
	}
}