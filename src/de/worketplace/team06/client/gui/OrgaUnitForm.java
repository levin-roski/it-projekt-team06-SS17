package de.worketplace.team06.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.worketplace.team06.client.Form;
import de.worketplace.team06.shared.bo.Marketplace;
import de.worketplace.team06.shared.bo.OrgaUnit;

/**
 * Formular für die Darstellung und Bearbeitung der aktuellen OrgaUnit. Falls
 * keine OrgaUnit beim Initialisieren in den ClientsideSettings verfügbar ist,
 * bleibt das Formular leer, bereit für die Erstellung eines neuen Users. Im
 * Anschluss wird dann der geänderte oder hinzugefügte User den
 * ClientsideSettings hinzugefügt.
 *
 * @author Roski
 */
public class OrgaUnitForm extends Form {
	private Label typeLabel = new Label("Nutzertyp");
	private ListBox typeInput = new ListBox();
	private Label descriptionLabel = new Label("Beschreibung");
	private TextBox descriptionInput = new TextBox();
	private boolean shouldUpdate = false;
	private OrgaUnit toChangeOrgaUnit;
	private HorizontalPanel changeHeadline;
	private HorizontalPanel addHeadline;

	/**
	 * Im Konstruktor wird der aktuelle Nutzer .
	 * 
	 * @param pHeadline
	 *            Falls true wird dem Formular eine Überschrift vorangehängt
	 */
	public OrgaUnitForm(final boolean pHeadline) {
		if (currentUser != null) {
			toChangeOrgaUnit = currentUser;
			shouldUpdate = true;
		} else {
			shouldUpdate = false;
		}
		if (pHeadline) {
			changeHeadline = createHeadline("Meinen Nutzer bearbeiten", true);
			addHeadline = createHeadline("Meinen Nutzer erstellen", true);
		}
		typeInput.addItem("Person", "person");
		typeInput.addItem("Team", "team");
		typeInput.addItem("Organisation", "organisation");
	}

	/**
	 * Im Konstruktor kann eine selektierter Marktplatz übergeben werden, der
	 * dann bearbeitet und gelöscht werden kann. null übergeben, falls ein neuer
	 * Marktplatz erstellt werden soll.
	 * 
	 * @param pClosingHeadline
	 *            Falls true wird dem Formular eine Überschrift mit Button, der
	 *            das aktuelle Item schließt, vorangehängt
	 */
	public OrgaUnitForm(final boolean pHeadline, final boolean pClosingHeadline) {
		this(pHeadline);
		if (pClosingHeadline) {
			changeHeadline = createHeadlineWithCloseButton("Meinen Nutzer bearbeiten", true);
			addHeadline = createHeadlineWithCloseButton("Meinen Nutzer erstellen", true);
		}

		/*
		 * Grid mit 8 Zeilen und 2 Spalten für das Formular bereitstellen.
		 * Danach nötige Panels einfügen und diesem Widget hinzufügen.
		 */
		Grid form = new Grid(8, 2);
		form.setWidth("100%");
		form.setWidget(0, 0, typeLabel);
		form.setWidget(0, 1, typeInput);
		form.setWidget(1, 0, descriptionLabel);
		form.setWidget(1, 1, descriptionInput);
		final VerticalPanel root = new VerticalPanel();
		this.add(root);
		/*
		 * Falls bereits ein OrgaUnit Objekt existiert und jetzt dargestellt
		 * werden soll
		 */
		if (shouldUpdate) {
			if (changeHeadline != null) {
				root.add(changeHeadline);
			}
			descriptionInput.setText(toChangeOrgaUnit.getDescription());
			final Button saveButton = new Button("Änderungen speichern");
			saveButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (descriptionInput.getText().length() == 0) {
						Window.alert("Bitte beschreiben Sie Ihren Nutzer genauer");
					} else {
						toChangeOrgaUnit.setDescription(descriptionInput.getText());
						worketplaceAdministration.savePerson(toChangeOrgaUnit, new AsyncCallback<Void>() {
							public void onFailure(Throwable caught) {
								Window.alert("Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
							}

							public void onSuccess(Void result) {
								Window.alert("Der Marktplatz wurde erfolgreich geändert");
								renderFormSuccess();
							}
						});
					}
				}
			});
			final VerticalPanel panel = new VerticalPanel();
			if (toChangeOrgaUnit.getType() == "person") {
				// TODO

			} else if (toChangeOrgaUnit.getType() == "team") {
				// TODO
			} else {
				// TODO
			}
			panel.add(saveButton);
			final Button deleteButton = new Button("Diesen Marktplatz entfernen");
			deleteButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					final boolean confirmDelete = Window.confirm("Möchten Sie den Marktplatz wirklich löschen?");
					if (confirmDelete) {
						worketplaceAdministration.deleteMarketplace(toChangeOrgaUnit, new AsyncCallback<Void>() {
							public void onFailure(Throwable caught) {
								Window.alert("Es trat ein Fehler beim Löschen auf, bitte versuchen Sie es erneut");
							}

							public void onSuccess(Void result) {
								Window.alert("Der Marktplatz wurde erfolgreich gelöscht");
								renderFormSuccess();
							}
						});
					}
				}
			});
			panel.add(deleteButton);
			form.setWidget(2, 1, panel);
		} else {
			if (addHeadline != null) {
				root.add(addHeadline);
			}
			final Button saveButton = new Button("Neuen Marktplatz anlegen");
			saveButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (nameInput.getText().length() == 0) {
						Window.alert("Bitte vergeben Sie einen Namen");
					} else if (descriptionInput.getText().length() == 0) {
						Window.alert("Bitte beschreiben Sie Ihren Marktplatz genauer");
					} else {
						worketplaceAdministration.createMarketplace(nameInput.getText(), descriptionInput.getText(),
								new AsyncCallback<Marketplace>() {
									public void onFailure(Throwable caught) {
										Window.alert(
												"Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
									}

									public void onSuccess(Marketplace result) {
										Window.alert("Der Marktplatz \"" + result.getTitle() + "\" wurde erstellt");
										renderFormSuccess();
									}
								});
					}
				}
			});
			form.setWidget(2, 1, saveButton);
		}
		root.add(form);
		nameInput.setFocus(true);
	}
}