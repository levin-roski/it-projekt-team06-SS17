package de.worketplace.team06.client.gui;

import javax.security.auth.callback.TextInputCallback;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
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

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.View;
import de.worketplace.team06.shared.bo.Marketplace;
import de.worketplace.team06.shared.bo.OrgaUnit;
import de.worketplace.team06.shared.bo.Organisation;
import de.worketplace.team06.shared.bo.Person;
import de.worketplace.team06.shared.bo.Team;

/**
 * Formular für die Darstellung und Bearbeitung der aktuellen OrgaUnit. Falls
 * keine OrgaUnit beim Initialisieren in den ClientsideSettings verfügbar ist,
 * bleibt das Formular leer, bereit für die Erstellung eines neuen Users. Im
 * Anschluss wird dann der geänderte oder hinzugefügte User den
 * ClientsideSettings hinzugefügt.
 *
 * @author Roski
 */
public class OrgaUnitFormView extends View {
	private Label typeLabel = new Label("Nutzertyp");
	private ListBox typeInput = new ListBox();
	private Label descriptionLabel = new Label("Beschreibung");
	private TextBox descriptionInput = new TextBox();
	private boolean shouldUpdate = false;
	private OrgaUnit toChangeOrgaUnit;
	private HorizontalPanel changeHeadline;
	private HorizontalPanel addHeadline;

	/**
	 * Im Konstruktor kann eine selektierter Marktplatz übergeben werden, der
	 * dann bearbeitet und gelöscht werden kann. null übergeben, falls ein neuer
	 * Marktplatz erstellt werden soll.
	 * 
	 * @param pClosingHeadline
	 *            Falls true wird dem Formular eine Überschrift mit Button, der
	 *            das aktuelle Item schließt, vorangehängt
	 */
	public OrgaUnitFormView() {
		changeHeadline = createHeadline("Meinen Nutzer bearbeiten", true);
		addHeadline = createHeadline("Meinen Nutzer erstellen", true);

		if (ClientsideSettings.getCurrentUser() != null) {
			toChangeOrgaUnit = ClientsideSettings.getCurrentUser();
			shouldUpdate = true;
		} else {
			shouldUpdate = false;
		}
		typeInput.addItem("Person");
		typeInput.addItem("Team");
		typeInput.addItem("Organisation");

		/*
		 * Grid mit 8 Zeilen und 2 Spalten für das Formular bereitstellen.
		 * Danach nötige Panels einfügen und diesem Widget hinzufügen.
		 */
		final Grid form = new Grid(8, 2);
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
			History.newItem("Mein-Nutzer");
			if (changeHeadline != null) {
				root.add(changeHeadline);
			}
			int indexToFind = -1;
			for (int i = 0; i < typeInput.getItemCount(); i++) {
				if (typeInput.getItemText(i).equals(toChangeOrgaUnit.getType())) {
					indexToFind = i;
					break;
				}
			}
			typeInput.setSelectedIndex(indexToFind);

			typeInput.setEnabled(false);
			descriptionInput.setText(toChangeOrgaUnit.getDescription());
			final Button saveButton = new Button("Änderungen speichern");
			final Button deleteButton = new Button("Diesen Nutzer entfernen");

			switch (toChangeOrgaUnit.getType()) {
			case "Person":
				worketplaceAdministration.getPersonByGoogleID(toChangeOrgaUnit.getGoogleID(),
						new AsyncCallback<Person>() {
							public void onFailure(Throwable caught) {
							}

							public void onSuccess(final Person toChangePerson) {
								Label firstnameLabel = new Label("Vorname");
								form.setWidget(2, 0, firstnameLabel);
								final TextBox firstnameInput = new TextBox();
								firstnameInput.setText(toChangePerson.getFirstName());
								form.setWidget(2, 1, firstnameInput);

								Label lastnameLabel = new Label("Nachname");
								form.setWidget(3, 0, lastnameLabel);
								final TextBox lastnameInput = new TextBox();
								lastnameInput.setText(toChangePerson.getLastName());
								form.setWidget(3, 1, lastnameInput);

								Label streetLabel = new Label("Straße");
								form.setWidget(4, 0, streetLabel);
								final TextBox streetInput = new TextBox();
								streetInput.setText(toChangePerson.getStreet());
								form.setWidget(4, 1, streetInput);

								Label zipcodeLabel = new Label("Postleitzahl");
								form.setWidget(5, 0, zipcodeLabel);
								final TextBox zipcodeInput = new TextBox();
								zipcodeInput.setText(toChangePerson.getZipcode().toString());
								form.setWidget(5, 1, zipcodeInput);

								Label cityLabel = new Label("Stadt");
								form.setWidget(6, 0, cityLabel);
								final TextBox cityInput = new TextBox();
								cityInput.setText(toChangePerson.getCity());
								form.setWidget(6, 1, cityInput);

								deleteButton.addClickHandler(new ClickHandler() {
									public void onClick(ClickEvent event) {
										final boolean confirmDelete = Window
												.confirm("Möchten Sie Ihren Nutzer wirklich löschen?");
										if (confirmDelete) {
											try {
												worketplaceAdministration.deletePerson(toChangePerson,
														new AsyncCallback<Void>() {
															public void onFailure(Throwable caught) {
																Window.alert(
																		"Es trat ein Fehler beim Löschen auf, bitte versuchen Sie es erneut");
															}

															public void onSuccess(Void result) {
																Window.alert("Der Nutzer wurde erfolgreich gelöscht");
															}
														});

											} catch (Exception e) {
											}
										}
									}
								});
								saveButton.addClickHandler(new ClickHandler() {
									public void onClick(ClickEvent event) {
										if (descriptionInput.getText().length() == 0) {
											Window.alert("Bitte beschreiben Sie Ihren Nutzer genauer");
										} else if (firstnameInput.getText().length() == 0) {
											Window.alert("Bitte füllen Sie das Feld Vorname");
										} else if (lastnameInput.getText().length() == 0) {
											Window.alert("Bitte füllen Sie das Feld Nachname");
										} else {
											toChangePerson.setDescription(descriptionInput.getText());
											worketplaceAdministration.savePerson(toChangePerson,
													new AsyncCallback<Void>() {
														public void onFailure(Throwable caught) {
															Window.alert(
																	"Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
														}

														public void onSuccess(Void result) {
															Window.alert("Der Nutzer wurde erfolgreich geändert");
														}
													});
										}
									}
								});
								form.setWidget(7, 1, saveButton);
								form.setWidget(8, 1, deleteButton);
							}
						});
				break;

			case "Team":

				break;

			case "Organisation":

				break;
			}

		} else {
			// if (addHeadline != null) {
			// root.add(addHeadline);
			// }
			// final Button saveButton = new Button("Neuen Marktplatz anlegen");
			// saveButton.addClickHandler(new ClickHandler() {
			// public void onClick(ClickEvent event) {
			// if (nameInput.getText().length() == 0) {
			// Window.alert("Bitte vergeben Sie einen Namen");
			// } else if (descriptionInput.getText().length() == 0) {
			// Window.alert("Bitte beschreiben Sie Ihren Marktplatz genauer");
			// } else {
			// worketplaceAdministration.createMarketplace(nameInput.getText(),
			// descriptionInput.getText(),
			// new AsyncCallback<Marketplace>() {
			// public void onFailure(Throwable caught) {
			// Window.alert(
			// "Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es
			// erneut");
			// }
			//
			// public void onSuccess(Marketplace result) {
			// Window.alert("Der Marktplatz \"" + result.getTitle() + "\" wurde
			// erstellt");
			// renderFormSuccess();
			// }
			// });
			// }
			// }
			// });
			// form.setWidget(2, 1, saveButton);
		}
		root.add(form);
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setFirstBreadcrumb(this, "Mein Nutzer");
	}

	@Override
	public void loadData() {
	}
}