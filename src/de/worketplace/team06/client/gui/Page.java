package de.worketplace.team06.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;

import de.worketplace.team06.client.ClientsideSettings;

public abstract class Page extends HorizontalPanel {
	protected MainPanel mainPanel = ClientsideSettings.getMainPanel();
	protected final DateTimeFormat simpleDateFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
	
	protected HorizontalPanel createHeadline(final String text, boolean isFirstPageElement) {
		HorizontalPanel hp = new HorizontalPanel();
		HTML sh = new HTML();
		if (isFirstPageElement) {
			sh.setHTML("<h1 class=\"first-element\">"+text+"</h1>");
		} else {
			sh.setHTML("<h1>"+text+"</h1>");
		}
		hp.add(sh);
		return hp;
	}
	
	protected HorizontalPanel createHeadlineWithCloseButton(final String text, boolean isFirstPageElement) {
		HorizontalPanel hp = new HorizontalPanel();
		HTML sh = new HTML();
		if (isFirstPageElement) {
			sh.setHTML("<h1 class=\"first-element\">"+text+"</h1>");			
		} else {
			sh.setHTML("<h1>"+text+"</h1>");
		}
		Button closeButton = new Button("X");
		closeButton.setStyleName("close-button");
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainPanel.closeForm();
			}
		});
		hp.add(closeButton);
		hp.add(sh);
		return hp;
	}
	
	protected HorizontalPanel createSecondHeadline(final String text) {
		HorizontalPanel hp = new HorizontalPanel();
		HTML sh = new HTML();
		sh.setHTML("<h2>"+text+"</h2>");
		hp.add(sh);
		return hp;
	}
	
	protected void renderFormSuccess() {
		ClientsideSettings.getCurrentView().loadData();
		mainPanel.closeForm();
	}
}