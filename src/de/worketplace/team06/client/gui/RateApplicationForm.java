package de.worketplace.team06.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.worketplace.team06.client.Callback;
import de.worketplace.team06.client.Form;
import de.worketplace.team06.shared.bo.Application;
import de.worketplace.team06.shared.bo.Marketplace;

/**
 * Formular für die Bewertung einer Bewerbung. 
 * 
 * @author Roski
 */
public class RateApplicationForm extends Form {
	private Label nameLabel = new Label("Bewertung");
	private ListBox ratingList = new ListBox();
	private Label descriptionLabel = new Label("Beschreibung");
	private TextBox descriptionInput = new TextBox();
	private boolean shouldUpdate = false;
	private Application toChangeApplication;
	private HorizontalPanel changeHeadline;
	private HorizontalPanel addHeadline;

	/**
	 * Im Konstruktor kann eine Bewerbung übergeben werden, die
	 * dann bewertet werden kann. null übergeben, falls ein neuer
	 * Marktplatz erstellt werden soll.
	 * 
	 * @param pToChangeApplication
	 *            Marketplace, der im Formular angezeigt werden soll
	 * @param pHeadline
	 *            Falls true wird dem Formular eine Überschrift vorangehängt
	 */
	public RateApplicationForm(Application pToChangeApplication, final boolean pHeadline) {
		if (pToChangeApplication != null) {
			shouldUpdate = true;
			this.toChangeApplication = pToChangeApplication;
		}
		if (pHeadline) {
			changeHeadline = createHeadline("Bewerbung bearbeiten", true);
			addHeadline = createHeadline("Bewerbung hinzufügen", true);
		}
	}

	/**
	 * Im Konstruktor kann eine selektierter Marktplatz übergeben werden, der
	 * dann bearbeitet und gelöscht werden kann. null übergeben, falls ein neuer
	 * Marktplatz erstellt werden soll.
	 * 
	 * @param pToChangeApplication
	 *            Marketplace, der im Formular angezeigt werden soll
	 * @param pHeadline
	 *            Falls true wird dem Formular eine Überschrift vorangehängt
	 * @param pClosingHeadline
	 *            Falls true wird dem Formular eine Überschrift mit Button, der
	 *            das aktuelle Item schließt, vorangehängt
	 */
	public RateApplicationForm(Application pToChangeApplication, final boolean pHeadline, final boolean pClosingHeadline,
			final Callback editCallback, final Callback deleteCallback) {
		this(pToChangeApplication, pHeadline);
		if (pClosingHeadline) {
			changeHeadline = createHeadlineWithCloseButton("Bewerbung bearbeiten", true);
			addHeadline = createHeadlineWithCloseButton("Bewerbung hinzufügen", true);
		}

		/*
		 * Grid mit 3 Zeilen und 2 Spalten für das Formular bereitstellen.
		 * Danach nötige Panels einfügen und diesem Widget hinzufügen.
		 */
		Grid form = new Grid(3, 2);
		form.setWidth("100%");
		form.setWidget(0, 0, nameLabel);
		form.setWidget(0, 1, ratingList);
		form.setWidget(1, 0, descriptionLabel);
		form.setWidget(1, 1, descriptionInput);
		final VerticalPanel root = new VerticalPanel();
		this.add(root);
		/*
		 * Falls ein selektierter Marktplatz übergeben wurde und jetzt
		 * dargestellt werden soll
		 */
		if (shouldUpdate) {
			if (changeHeadline != null) {
				root.add(changeHeadline);
			}
			ratingList.setText(toChangeApplication.getTitle());
			descriptionInput.setText(toChangeApplication.getDescription());
			final Button saveButton = new Button("Speichern");
			saveButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (ratingList.getText().length() == 0) {
						Window.alert("Bitte vergeben eine Bewertung");
					} else if (descriptionInput.getText().length() == 0) {
						Window.alert("Bitte beschreiben Sie Ihre Bewertung genauer");
					} else {
						toChangeApplication.setTitle(ratingList.getText());
						toChangeApplication.setDescription(descriptionInput.getText());
						worketplaceAdministration.saveMarketplace(toChangeApplication, new AsyncCallback<Void>() {
							public void onFailure(Throwable caught) {
								Window.alert("Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
							}

							public void onSuccess(Void result) {
								Window.alert("Die Bewerbung wurde erfolgreich bewertet");
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
			final Button deleteButton = new Button("Diese Bewertung entfernen");
			deleteButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					final boolean confirmDelete = Window.confirm("Möchten Sie die Bewertung wirklich löschen?");
					if (confirmDelete) {
						worketplaceAdministration.deleteMarketplace(toChangeApplication, new AsyncCallback<Void>() {
							public void onFailure(Throwable caught) {
								Window.alert("Es trat ein Fehler beim Löschen auf, bitte versuchen Sie es erneut");
							}

							public void onSuccess(Void result) {
								Window.alert("Die Bewertung wurde erfolgreich gelöscht");
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
			final Button saveButton = new Button("Bewertung anlegen");
			saveButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (ratingList.getText().length() == 0) {
						Window.alert("Bitte vergeben Sie einen Namen");
					} else if (descriptionInput.getText().length() == 0) {
						Window.alert("Bitte beschreiben Sie Ihren Marktplatz genauer");
					} else {
						worketplaceAdministration.createMarketplace(ratingList.getText(), descriptionInput.getText(),
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
		ratingList.setFocus(true);
	}
}