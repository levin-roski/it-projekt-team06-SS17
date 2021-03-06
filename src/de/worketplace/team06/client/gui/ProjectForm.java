package de.worketplace.team06.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.Form;
import de.worketplace.team06.shared.bo.Marketplace;
import de.worketplace.team06.shared.bo.Person;
import de.worketplace.team06.shared.bo.Project;

/**
 * Formular für die Darstellung, Bearbeitung und Löschung eines selektierten
 * Projektes. Falls kein selektierter Projekt beim Initialisieren übergeben
 * wird, ist das Formular leer, bereit für die Erstellung eines neuen Projektes.
 * 
 * @author Roski
 */
public class ProjectForm extends Form {
	private Label nameLabel = new Label("Name");
	private TextBox nameInput = new TextBox();
	private Label beschreibungLabel = new Label("Beschreibung");
	private TextArea beschreibungInput = new TextArea();
	private Label startDateLabel = new Label("Startdatum");
	private DateBox startDateInput = new DateBox();
	private Label endDateLabel = new Label("Enddatum");
	private DateBox endDateInput = new DateBox();
	private Boolean shouldUpdate = false;
	private Project toChangeProject;
	private Person projectLeader;
	private HorizontalPanel changeHeadline;
	private HorizontalPanel addHeadline;
	private Button saveButton;
	private Button deleteButton;

	/**
	 * Im Konstruktor kann ein selektiertes Projekt übergeben werden, welches
	 * dann bearbeitet und gelöscht werden kann. null übergeben, falls ein neues
	 * Projekt erstellt werden soll.
	 * 
	 * @param pToChangeProject
	 *            Projekt, das im Formular angezeigt werden soll
	 * @param pHeadline
	 *            Falls true wird dem Formular eine Überschrift vorangehängt
	 */
	public ProjectForm(Project pToChangeProject, final boolean pHeadline) {
		if (pToChangeProject != null) {
			toChangeProject = pToChangeProject;
			shouldUpdate = true;
		}
		if (pHeadline) {
			changeHeadline = createHeadline("Projekt bearbeiten", true);
			addHeadline = createHeadline("Projekt hinzufügen", true);
		}
		startDateInput.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd.MM.yyyy")));
		endDateInput.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd.MM.yyyy")));
	}

	/**
	 * Im Konstruktor kann eine selektiertes Projekt übergeben werden, welches
	 * dann bearbeitet und gelöscht werden kann. null übergeben, falls ein neues
	 * Projekt erstellt werden soll.
	 * 
	 * @param pToChangeProject
	 *            Project, das im Formular angezeigt werden soll
	 * @param pHeadline
	 *            Falls true wird dem Formular eine Überschrift vorangehängt
	 * @param pClosingHeadline
	 *            Falls true wird dem Formular eine Überschrift mit Button, der
	 *            das aktuelle Item schließt, vorangehängt
	 */
	public ProjectForm(Project pToChangeProject, final boolean pHeadline, final boolean pClosingHeadline,
			final Marketplace addToMarketplace) {
		this(pToChangeProject, pHeadline);
		if (pClosingHeadline) {
			changeHeadline = createHeadlineWithCloseButton("Projekt bearbeiten", true);
			addHeadline = createHeadlineWithCloseButton("Projekt hinzufügen", true);
		}

		/*
		 * Grid mit 6 Zeilen und 2 Spalten für das Formular bereitstellen.
		 * Danach nötige Panels einfügen und diesem Widget hinzufügen.
		 */
		final Grid form = new Grid(5, 2);
		form.setWidth("100%");
		form.setWidget(0, 0, nameLabel);
		form.setWidget(0, 1, nameInput);
		form.setWidget(1, 0, beschreibungLabel);
		form.setWidget(1, 1, beschreibungInput);
		form.setWidget(2, 0, startDateLabel);
		form.setWidget(2, 1, startDateInput);
		form.setWidget(3, 0, endDateLabel);
		form.setWidget(3, 1, endDateInput);
		final VerticalPanel root = new VerticalPanel();
		this.add(root);
		/*
		 * Falls ein selektierter Projekt übergeben wurde und jetzt dargestellt
		 * werden soll
		 */
		if (shouldUpdate) {
			if (changeHeadline != null) {
				root.add(changeHeadline);
			}
			nameInput.setEnabled(false);
			beschreibungInput.setEnabled(false);
			startDateInput.setEnabled(false);
			endDateInput.setEnabled(false);

			class RpcWrapper {
				protected Timer t;

				public RpcWrapper() {
					t = new Timer() {
						public void run() {
							t.cancel();
							worketplaceAdministration.getPersonByID(toChangeProject.getProjectLeaderID(),
									new AsyncCallback<Person>() {
										public void onFailure(Throwable caught) {
											Window.alert("Es trat ein Fehler beim auslesen des Projektleiters auf");
										}

										public void onSuccess(Person result) {
											projectLeader = result;

											nameInput.setText(toChangeProject.getTitle());
											beschreibungInput.setText(toChangeProject.getDescription());
											startDateInput.setValue(toChangeProject.getStartDate());
											endDateInput.setValue(toChangeProject.getEndDate());

											saveButton = new Button("Speichern");
											saveButton.addClickHandler(new ClickHandler() {
												public void onClick(ClickEvent event) {
													if (nameInput.getText().length() == 0) {
														Window.alert("Bitte vergeben Sie einen Namen");
													} else if (beschreibungInput.getText().length() == 0) {
														Window.alert("Bitte beschreiben Sie Ihr Projekt genauer");
													} else if (startDateInput.getValue() == null) {
														Window.alert("Bitte geben Sie ein Startdatum ein");
													} else if (endDateInput.getValue() == null) {
														Window.alert("Bitte geben Sie ein Enddatum ein");
													} else {
														toChangeProject.setTitle(nameInput.getText());
														toChangeProject.setDescription(beschreibungInput.getText());
														toChangeProject.setStartDate(startDateInput.getValue());
														toChangeProject.setEndDate(endDateInput.getValue());
														worketplaceAdministration.saveProject(toChangeProject,
																new AsyncCallback<Void>() {
																	public void onFailure(Throwable caught) {
																		Window.alert(
																				"Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
																	}

																	public void onSuccess(Void result) {
																		Window.alert(
																				"Das Projekt wurde erfolgreich geändert");
																		renderFormSuccess();
																	}
																});
													}
												}
											});
											final HorizontalPanel panel = new HorizontalPanel();
											panel.add(saveButton);
											saveButton.setEnabled(false);
											saveButton.setVisible(false);
											deleteButton = new Button("Projekt entfernen");
											deleteButton.addClickHandler(new ClickHandler() {
												public void onClick(ClickEvent event) {
													final boolean confirmDelete = Window
															.confirm("Möchten Sie das Projekt wirklich löschen?");
													if (confirmDelete) {
														worketplaceAdministration.deleteProject(toChangeProject,
																new AsyncCallback<Void>() {
																	public void onFailure(Throwable caught) {
																		Window.alert(
																				"Es trat ein Fehler beim Löschen auf, bitte versuchen Sie es erneut");
																	}

																	public void onSuccess(Void result) {
																		Window.alert(
																				"Das Projekt wurde erfolgreich gelöscht");
																		History.newItem("Marktplatz-Details"
																				+ ClientsideSettings
																						.getCurrentMarketplaceId());
																	}
																});
													}
												}
											});
											panel.add(deleteButton);
											deleteButton.setEnabled(false);
											deleteButton.setVisible(false);
											form.setWidget(4, 1, panel);
											if (ClientsideSettings.getCurrentUser().getID() == projectLeader.getID()) {
												nameInput.setEnabled(true);
												beschreibungInput.setEnabled(true);
												startDateInput.setEnabled(true);
												endDateInput.setEnabled(true);
												saveButton.setEnabled(true);
												saveButton.setVisible(true);
												if (deleteButton instanceof Button) {
													deleteButton.setEnabled(true);
													deleteButton.setVisible(true);
												}
											}
											if (ClientsideSettings.isCurrentProjectLeader() && shouldUpdate) {
												nameInput.setEnabled(true);
												beschreibungInput.setEnabled(true);
												startDateInput.setEnabled(true);
												endDateInput.setEnabled(true);
												saveButton.setEnabled(true);
												saveButton.setVisible(true);
												if (deleteButton instanceof Button) {
													deleteButton.setEnabled(true);
													deleteButton.setVisible(true);
												}
											}
										}
									});
						}
					};
					// Schedule the timer to check if all RPC calls finished
					// each 400 milliseconds
					t.scheduleRepeating(400);
				}
			}
			new RpcWrapper();
		} else {
			if (!(currentUser instanceof Person)) {
				Window.alert(
						"Sie können kein Projekt anlegen, da Sie einen Firmen-, oder Teamnutzer besitzen. Nur Personen Nutzer können neue Projekte anlegen.");
				mainPanel.closeForm();
			}
			if (addHeadline != null) {
				root.add(addHeadline);
			}
			saveButton = new Button("Projekt anlegen");
			saveButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (nameInput.getText().length() == 0) {
						Window.alert("Bitte vergeben Sie einen Namen");
					} else if (beschreibungInput.getText().length() == 0) {
						Window.alert("Bitte beschreiben Sie Ihr Projekt genauer");
					} else if (startDateInput.getValue() == null) {
						Window.alert("Bitte geben Sie ein Startdatum ein");
					} else if (endDateInput.getValue() == null) {
						Window.alert("Bitte geben Sie ein Enddatum ein");
					} else {
						worketplaceAdministration.createProject(addToMarketplace, nameInput.getText(),
								beschreibungInput.getText(), (Person) currentUser, startDateInput.getValue(),
								endDateInput.getValue(), new AsyncCallback<Project>() {
									public void onFailure(Throwable caught) {
										Window.alert(
												"Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
									}

									public void onSuccess(Project result) {
										renderFormSuccess();
										Window.alert("Das Projekt " + result.getTitle()
												+ " wurde erstellt und dem Marktplatz " + addToMarketplace.getTitle()
												+ " hinzugefügt");
									}
								});
					}
				}

			});
			form.setWidget(4, 1, saveButton);
		}
		root.add(form);
	}
}