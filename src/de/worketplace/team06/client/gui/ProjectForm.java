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

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.shared.bo.Project;

/**
 * Formular für die Darstellung, Bearbeitung und Löschung eines
 * selektierten Projektes. Falls kein selektierter Projekt beim
 * Initialisieren übergeben wird, ist das Formular leer, bereit für die
 * Erstellung eines neuen Projektes.
 * 
 * @author Roski
 */
public class ProjectForm extends Page {
	private WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
			.getWorketplaceAdministration();
	private Label nameLabel = new Label("Name");
	private TextBox nameInput = new TextBox();
	private Label beschreibungLabel = new Label("Beschreibung");
	private TextBox beschreibungInput = new TextBox();
	private Label startDateLabel = new Label("Startdatum");
	private TextBox startDateInput = new TextBox();
	private Label endDateLabel = new Label("Enddatum");
	private TextBox endDateInput = new TextBox();
	private Label projectleaderIdLabel = new Label ("Projektleiter");
	private Label marketplaceIdLabel = new Label ("zugehöriger Marktplatz");
	private Boolean shouldUpdate = false;
	private Project toChangeProject;
	private HorizontalPanel changeHeadline;
	private HorizontalPanel addHeadline;

	/**
	 * Im Konstruktor kann ein selektiertes Projekt übergeben werden, welches
	 * dann bearbeitet und gelöscht werden kann. null übergeben, falls ein neues 
	 * Projekt erstellt werden soll.
	 * 
	 * @param pToChangeProject Project, das im Formular angezeigt werden soll
	 * @param pHeadline Falls true wird dem Formular eine Überschrift vorangehängt
	 */
	public ProjectForm (Project pToChangeProject, final boolean pHeadline) {
		if (pToChangeProject != null) {
			shouldUpdate = true;
			this.toChangeProject = pToChangeProject;
		}
		if (pHeadline) {
			changeHeadline = createHeadline("Projekt bearbeiten", true);
			addHeadline = createHeadline("Projekt hinzufügen", true);
		}
	}
	/**
	 * Im Konstruktor kann eine selektiertes Projekt übergeben werden, welches
	 * dann bearbeitet und gelöscht werden kann. null übergeben, falls ein neues
	 * Projekt erstellt werden soll.
	 * 
	 * @param pToChangeProject Project, das im Formular angezeigt werden soll
	 * @param pHeadline Falls true wird dem Formular eine Überschrift vorangehängt
	 * @param pClosingHeadline Falls true wird dem Formular eine Überschrift mit Button, der das aktuelle Item schließt, vorangehängt
	 */
	public ProjectForm(Project pToChangeProject, final boolean pHeadline, final boolean pClosingHeadline) {
		this(pToChangeProject, pHeadline);
		if (pClosingHeadline) {
			changeHeadline = createHeadlineWithCloseButton("Projekt bearbeiten", true);
			addHeadline = createHeadlineWithCloseButton("Projekt hinzufügen", true);
		}
		

		/*
		 * Grid mit 3 Zeilen und 2 Spalten für das Formular bereitstellen.
		 * Danach nötige Panels einfügen und diesem Widget hinzufügen.
		 */
		Grid form = new Grid(3, 2);
		form.setWidth("100%");
		form.setWidget(0, 0, nameLabel);
		form.setWidget(0, 1, nameInput);
		form.setWidget(1, 0, beschreibungLabel);
		form.setWidget(1, 1, beschreibungInput);
		form.setWidget(1, 0, startDateLabel);
		form.setWidget(1, 1, startDateInput);
		form.setWidget(1, 0, endDateLabel);
		form.setWidget(1, 1, endDateInput);
		form.setWidget(1, 0, projectleaderIdLabel);
		form.setWidget(1, 0, marketplaceIdLabel);
		final VerticalPanel root = new VerticalPanel();
		this.add(root);
		/*
		 * Falls ein selektierter Projekt übergeben wurde und jetzt dargestellt werden soll
		 */
		if (shouldUpdate) {
			if (changeHeadline != null) {
				root.add(changeHeadline);
			}
			nameInput.setText(toChangeProject.getTitle());
			beschreibungInput.setText(toChangeProject.getDescription());
			final Button saveButton = new Button("Änderungen speichern");
			saveButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (nameInput.getText().length() == 0) {
						Window.alert("Bitte vergeben Sie einen Namen");
					} else if (beschreibungInput.getText().length() == 0) {
						Window.alert("Bitte beschreiben Sie Ihr Projekt genauer");
					} else if (startDateInput.getText().length() == 0) {
						Window.alert("Bitte geben Sie ein Startdatum ein");
					} else if (endDateInput.getText().length() == 0) {
						Window.alert("Bitte geben Sie ein Enddatum ein");
					} else {
						toChangeProject.setTitle(nameInput.getText());
						toChangeProject.setDescription(beschreibungInput.getText());
						toChangeProject.setStartDate(startDateInput.getText());
						toChangeProject.setEndDate(endDateInput.getText());
						worketplaceAdministration.saveProject(toChangeProject, new AsyncCallback<Void>() {
							public void onFailure(Throwable caught) {
								Window.alert("Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
							}

							public void onSuccess(Void result) {
								Window.alert("Das Projekt wurde erfolgreich geändert");
								renderFormSuccess();
							}
						});
					}
				}
			});
			final VerticalPanel panel = new VerticalPanel();
			panel.add(saveButton);
			final Button deleteButton = new Button("Dieses Projekt entfernen");
			deleteButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					final boolean confirmDelete = Window.confirm("Möchten Sie das Projekt wirklich löschen?");
					if (confirmDelete) {
						worketplaceAdministration.deleteProject(toChangeProject, new AsyncCallback<Void>() {
							public void onFailure(Throwable caught) {
								Window.alert("Es trat ein Fehler beim Löschen auf, bitte versuchen Sie es erneut");
							}

							public void onSuccess(Void result) {
								Window.alert("Das Projekt wurde erfolgreich gelöscht");
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
			final Button saveButton = new Button("Neues Projekt anlegen");
			saveButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (nameInput.getText().length() == 0) {
						Window.alert("Bitte vergeben Sie einen Namen");
					} else if (beschreibungInput.getText().length() == 0) {
						Window.alert("Bitte beschreiben Sie Ihr Projekt genauer");
					} else if (startDateInput.getText().length() == 0) {
						Window.alert("Bitte geben Sie ein Startdatum ein");
					} else if (endDateInput.getText().length() == 0) {
						Window.alert("Bitte geben Sie ein Enddatum ein"); 
					} else {
						worketplaceAdministration.createProject(nameInput.getText(), beschreibungInput.getText(), new AsyncCallback<Project>() {
									public void onFailure(Throwable caught) {
										Window.alert(
												"Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
									}

									public void onSuccess(Project result) {
										Window.alert("Das Projekt \"" + result.getTitle() + 
												"von " + result.getProjectLeaderID() 
												+ "im Marktplatz " + result.getMarketplaceID() 
												+ "\" wurde erstellt");
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