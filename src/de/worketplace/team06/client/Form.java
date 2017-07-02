package de.worketplace.team06.client;

import java.util.Vector;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;

import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.shared.bo.Person;

public abstract class Form extends Page {
	protected WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
			.getWorketplaceAdministration();
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
	
	protected HorizontalPanel createHeadline(final String text) {
		HorizontalPanel hp = new HorizontalPanel();
		HTML sh = new HTML();
			sh.setHTML("<h1>"+text+"</h1>");
		hp.add(sh);
		return hp;
	}

	protected void renderFormSuccess() {
		ClientsideSettings.getCurrentView().loadData();
		mainPanel.closeForm();
	}
	
	protected ListBox getAllPersonsListBox(final Callback callback) {
		final ListBox allUsers = new ListBox();
		worketplaceAdministration.getAllPersons(new AsyncCallback<Vector<Person>>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(Vector<Person> results) {
				for (Person person : results) {
					allUsers.addItem(person.getFirstName() + " " + person.getLastName(), String.valueOf(person.getID()));
				}
			}
		});
		allUsers.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				if (callback instanceof Callback) {
					callback.run();
				}
			}
		});
		return allUsers;
	}
}