package de.worketplace.team06.client.gui;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.shared.bo.Call;
import de.worketplace.team06.shared.bo.Marketplace;

/**
 * Formular für die Darstellung, Bearbeitung und Löschung einer
 * selektierten Ausschreibung. Falls keine selektierte Ausschreibung beim
 * Initialisieren übergeben wird, ist das Formular leer, bereit für die
 * Erstellung einer neuen Ausschreibung.
 * 
 * @author Roski, Müller
 */


public class CallForm {
	private WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
			.getWorketplaceAdministration();
	
	
	
	private Label titleLabel = new Label("Titel");
	private TextBox titleInput = new TextBox();
	private Label descriptionLabel = new Label("Beschreibung");
	private TextBox descriptionInput = new TextBox();
	private Label deadlineLabel = new Label("Bewerbungsfrist");
	private DateBox deadlineInput = new DateBox();
	private Label statusLabel = new Label("Status");
	private ListBox statusInput = new ListBox();
	private Label callerIDLabel = new Label("Ausschreibender");
	private ListBox callerOutput = new ListBox();
	private Boolean shouldUpdate = false;
	private Call toChangeCall;
	private HorizontalPanel changeHeadline;
	private HorizontalPanel addHeadline;
	
	/**
	 * Im Konstruktor kann eine selektierte Ausschreibung übergeben werden, die
	 * dann bearbeitet und gelöscht werden kann. null übergeben, falls eine neue
	 * Ausschreibung erstellt werden soll.
	 * 
	 * @param pToChangeCall Call, die im Formular angezeigt werden soll
	 * @param pHeadline Falls true wird dem Formular eine Überschrift vorangehängt
	 */
	
	public CallForm (Call pToChangeCall, final boolean pHeadline) {
		if (pToChangeCall != null) {
			shouldUpdate = true;
			this.toChangeCall = pToChangeCall;
		}
		if (pHeadline) {
			changeHeadline = createHeadline("Ausschreibung bearbeiten", true);
			addHeadline = createHeadline("Ausschreibung hinzufügen", true);
		}		
	}
	
	/**
	 * Im Konstruktor kann eine selektierte Ausschreibung übergeben werden, die
	 * dann bearbeitet und gelöscht werden kann. null übergeben, falls eine neue
	 * Ausschreibungen erstellt werden soll.
	 * 
	 * @param pToChangeCall Call, die im Formular angezeigt werden soll
	 * @param pHeadline Falls true wird dem Formular eine Überschrift vorangehängt
	 * @param pClosingHeadline Falls true wird dem Formular eine Überschrift mit Button, der das aktuelle Item schließt, vorangehängt
	 */
	
	public CallForm(Call pToChangeCall, final boolean pHeadline, final Boolean pClosingHeadline) {
		this(pToChangeCall, pHeadline);
		if (pClosingHeadline) {
			changeHeadline = createHeadlineWithCloseButton("Ausschreibung bearbeiten", true);
			addHeadline = createHeadlineWithCloseButton("Ausschreibung hinzufügen", true);
		}
		

		/*
		 * Grid mit 5 Zeilen und 2 Spalten für das Formular bereitstellen.
		 * Danach nötige Panels einfügen und diesem Widget hinzufügen.
		 */
		Grid form = new Grid(5, 2);
		form.setWidth("100%");
		form.setWidget(0, 0, titleLabel);
		form.setWidget(0, 1, titleInput);
		form.setWidget(1, 0, descriptionLabel);
		form.setWidget(1, 1, descriptionInput);
		form.setWidget(2, 0, deadlineLabel);
		form.setWidget(2, 1, deadlineInput);
		form.setWidget(3, 0, statusLabel);
		form.setWidget(3, 1, statusInput);
		form.setWidget(4, 0, callerIDLabel);
		form.setWidget(4, 1, callerOutput);
		final VerticalPanel root = new VerticalPanel();
		this.add(root);
		
		/*
		 * Falls eine selektiertee Ausschreibung übergeben wurde und jetzt dargestellt werden soll
		 */
		if (shouldUpdate) {
			if (changeHeadline != null) {
				root.add(changeHeadline);
			}
			titleInput.setText(toChangeCall.getTitle());
			descriptionInput.setText(toChangeCall.getDescription());
			deadlineInput.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT)));
			deadlineInput.setValue(toChangeCall.getDeadline());
			statusInput.addItem("Abgelehnt");
			statusInput.addItem("Laufend");
			statusInput.addItem("Erfolgreich");
			statusInput.setVisibleItemCount(1);
			statusInput.set
			
			final Button saveButton = new Button("Änderungen speichern");
			saveButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (nameInput.getText().length() == 0) {
						Window.alert("Bitte vergeben Sie einen Namen");
					} else if (beschreibungInput.getText().length() == 0) {
						Window.alert("Bitte beschreiben Sie Ihren Marktplatz genauer");
					} else {
						toChangeMarketplace.setTitle(nameInput.getText());
						toChangeMarketplace.setDescription(beschreibungInput.getText());
						worketplaceAdministration.saveMarketplace(toChangeMarketplace, new AsyncCallback<Void>() {
							public void onFailure(Throwable caught) {
								Window.alert("Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
							}

							public void onSuccess(Void result) {
								Window.alert("Der Marktplatz wurde erfolgreich geändert");
							}
						});
					}
				}
			});
			final VerticalPanel panel = new VerticalPanel();
			panel.add(saveButton);
			final Button deleteButton = new Button("Diesen Marktplatz entfernen");
			deleteButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					final boolean confirmDelete = Window.confirm("Möchten Sie den Marktplatz wirklich löschen?");
					if (confirmDelete) {
						worketplaceAdministration.deleteMarketplace(toChangeMarketplace, new AsyncCallback<Void>() {
							public void onFailure(Throwable caught) {
								Window.alert("Es trat ein Fehler beim Löschen auf, bitte versuchen Sie es erneut");
							}

							public void onSuccess(Void result) {
								Window.alert("Der Marktplatz wurde erfolgreich gelöscht");
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
					} else if (beschreibungInput.getText().length() == 0) {
						Window.alert("Bitte beschreiben Sie Ihren Marktplatz genauer");
					} else {
						worketplaceAdministration.createMarketplace(nameInput.getText(), beschreibungInput.getText(), new AsyncCallback<Marketplace>() {
									public void onFailure(Throwable caught) {
										Window.alert(
												"Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
									}

									public void onSuccess(Marketplace result) {
										Window.alert("Der Marktplatz \"" + result.getTitle() + "\" wurde erstellt");
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
