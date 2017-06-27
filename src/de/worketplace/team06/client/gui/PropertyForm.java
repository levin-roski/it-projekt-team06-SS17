package de.worketplace.team06.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.worketplace.team06.client.Callback;
import de.worketplace.team06.client.Form;
import de.worketplace.team06.shared.bo.PartnerProfile;
import de.worketplace.team06.shared.bo.Property;

/**
 * Formular für die Darstellung, Bearbeitung und Löschung eines selektierten
 * Marktplatzes. Falls kein selektierter Marktplatz beim Initialisieren
 * übergeben wird, ist das Formular leer, bereit für die Erstellung eines neuen
 * Marktplatzes.
 * 
 * @author Roski
 */
public class PropertyForm extends Form {
	private Label nameLabel = new Label("Eigenschaften-Name");
	private TextBox nameInput = new TextBox();
	private Label valueLabel = new Label("Wert");
	private TextBox valueInput = new TextBox();
	private boolean shouldUpdate = false;
	private Property toChangeProperty;
	private HorizontalPanel changeHeadline;
	private HorizontalPanel addHeadline;

	/**
	 * Im Konstruktor kann eine selektierter Marktplatz übergeben werden, der
	 * dann bearbeitet und gelöscht werden kann. null übergeben, falls ein neuer
	 * Marktplatz erstellt werden soll.
	 * 
	 * @param pToChangeProperty
	 *            Property, der im Formular angezeigt werden soll
	 * @param pHeadline
	 *            Falls true wird dem Formular eine Überschrift vorangehängt
	 */
	public PropertyForm(Property pToChangeProperty, final boolean pHeadline) {
		if (pToChangeProperty != null) {
			shouldUpdate = true;
			this.toChangeProperty = pToChangeProperty;
		}
		if (pHeadline) {
			changeHeadline = createHeadline("Eigenschaft bearbeiten", true);
			addHeadline = createHeadline("Eigenschaft hinzufügen", true);
		}
	}

	/**
	 * Im Konstruktor kann eine selektierte Eigenschaft übergeben werden, die
	 * dann bearbeitet und gelöscht werden kann. null übergeben, falls eine neue
	 * Eigenschaft erstellt werden soll.
	 * 
	 * @param pToChangeProperty
	 *            Property, die im Formular angezeigt werden soll
	 * @param pHeadline
	 *            Falls true wird dem Formular eine Überschrift vorangehängt
	 * @param pClosingHeadline
	 *            Falls true wird dem Formular eine Überschrift mit Button, der
	 *            das aktuelle Item schließt, vorangehängt
	 */
	public PropertyForm(Property pToChangeProperty, final boolean pHeadline, final boolean pClosingHeadline,
			final Callback editCallback, final Callback deleteCallback, final PartnerProfile addToPartnerProfile) {
		this(pToChangeProperty, pHeadline);
		if (pClosingHeadline) {
			changeHeadline = createHeadlineWithCloseButton("Eigenschaft bearbeiten", true);
			addHeadline = createHeadlineWithCloseButton("Eigenschaft hinzufügen", true);
		}

		/*
		 * Grid mit 3 Zeilen und 2 Spalten für das Formular bereitstellen.
		 * Danach nötige Panels einfügen und diesem Widget hinzufügen.
		 */
		Grid form = new Grid(3, 2);
		form.setWidth("100%");
		form.setWidget(0, 0, nameLabel);
		form.setWidget(0, 1, nameInput);
		form.setWidget(1, 0, valueLabel);
		form.setWidget(1, 1, valueInput);
		final VerticalPanel root = new VerticalPanel();
		this.add(root);
		/*
		 * Falls eine selektierte Eigenschaft übergeben wurde und jetzt
		 * dargestellt werden soll
		 */
		if (shouldUpdate) {
			if (changeHeadline != null) {
				root.add(changeHeadline);
			}
			nameInput.setText(toChangeProperty.getName());
			valueInput.setText(toChangeProperty.getValue());
			final Button saveButton = new Button("Änderungen speichern");
			saveButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (nameInput.getText().length() == 0) {
						Window.alert("Bitte vergeben Sie einen Namen");
					} else if (valueInput.getText().length() == 0) {
						Window.alert("Bitte vergeben Sie einen Wert");
					} else {
						toChangeProperty.setName(nameInput.getText());
						toChangeProperty.setValue(valueInput.getText());
						worketplaceAdministration.saveProperty(toChangeProperty, new AsyncCallback<Void>() {
							public void onFailure(Throwable caught) {
								Window.alert("Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
							}

							public void onSuccess(Void result) {
								Window.alert("Die Eigenschaft wurde erfolgreich geändert");
								if (editCallback != null) {
									editCallback.run();
								} else {
									renderFormSuccess();
								}
							}
						});
					}
				}
			});
			final VerticalPanel panel = new VerticalPanel();
			panel.add(saveButton);
			final Button deleteButton = new Button("Diese Eigenschaft entfernen");
			deleteButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					final boolean confirmDelete = Window.confirm("Möchten Sie die Eigenschaft wirklich löschen?");
					if (confirmDelete) {
						worketplaceAdministration.deleteProperty(toChangeProperty, new AsyncCallback<Void>() {
							public void onFailure(Throwable caught) {
								Window.alert("Es trat ein Fehler beim Löschen auf, bitte versuchen Sie es erneut");
							}

							public void onSuccess(Void result) {
								Window.alert("Die Eigenschaft wurde erfolgreich gelöscht");
								if (deleteCallback != null) {
									deleteCallback.run();
								} else {
									renderFormSuccess();
								}
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
			final Button saveButton = new Button("Neue Eigenschaft anlegen");
			saveButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (nameInput.getText().length() == 0) {
						Window.alert("Bitte vergeben Sie einen Namen");
					} else if (valueInput.getText().length() == 0) {
						Window.alert("Bitte vergeben Sie einen Wert");
					} else {
						worketplaceAdministration.createProperty(addToPartnerProfile, nameInput.getText(), valueInput.getText(),
								new AsyncCallback<Property>() {
									public void onFailure(Throwable caught) {
										Window.alert(
												"Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
									}

									public void onSuccess(Property result) {
										Window.alert("Die Eigenschaft \"" + result.getName() + "\" wurde erstellt");
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