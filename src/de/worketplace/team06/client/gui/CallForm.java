package de.worketplace.team06.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Timer;
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

import de.worketplace.team06.client.Callback;
import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.Form;
import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.shared.bo.Call;
import de.worketplace.team06.shared.bo.Person;
import de.worketplace.team06.shared.bo.Project;

/**
 * Formular für die Darstellung, Bearbeitung und Löschung einer selektierten
 * Ausschreibung. Falls keine selektierte Ausschreibung beim Initialisieren
 * übergeben wird, ist das Formular leer, bereit für die Erstellung einer neuen
 * Ausschreibung.
 * 
 * @author Roski, Müller
 */
public class CallForm extends Form {
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
	private Label callerFirstNameLabel = new Label("Vorname");
	protected TextBox callerInputFirstName = new TextBox();
	private Label callerLastNameLabel = new Label("Nachname");
	protected TextBox callerInputLastName = new TextBox();
	private boolean shouldUpdate = false;
	private Call toChangeCall;
	protected Person projectLeader;
	protected Project project;
	private HorizontalPanel changeHeadline;
	private HorizontalPanel addHeadline;
	Button changeDeleteButton;
	Button changeSaveButton;

	/**
	 * Im Konstruktor kann eine selektierte Ausschreibung übergeben werden, die
	 * dann bearbeitet und gelöscht werden kann. null übergeben, falls eine neue
	 * Ausschreibung erstellt werden soll.
	 * 
	 * @param pToChangeCall
	 *            Call, die im Formular angezeigt werden soll
	 * @param pHeadline
	 *            Falls true wird dem Formular eine Überschrift vorangehängt
	 */
	public CallForm(Call pToChangeCall, final boolean pHeadline) {
		if (pToChangeCall != null) {
			shouldUpdate = true;
			toChangeCall = pToChangeCall;
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
	 * @param pToChangeCall
	 *            Call, die im Formular angezeigt werden soll
	 * @param pHeadline
	 *            Falls true wird dem Formular eine Überschrift vorangehängt
	 * @param pClosingHeadline
	 *            Falls true wird dem Formular eine Überschrift mit Button, der
	 *            das aktuelle Item schließt, vorangehängt
	 */

	public CallForm(final Call pToChangeCall, final boolean pHeadline, final boolean pClosingHeadline,
			final Callback editCallback, final Callback deleteCallback) {
		this(pToChangeCall, pHeadline);
		if (pClosingHeadline) {
			changeHeadline = createHeadlineWithCloseButton("Ausschreibung bearbeiten", true);
			addHeadline = createHeadlineWithCloseButton("Ausschreibung hinzufügen", true);
		}

		if (pToChangeCall != null) {
			getObjects(pToChangeCall);
		}

		/*
		 * Grid mit 8 Zeilen und 2 Spalten für das Formular bereitstellen.
		 * Danach nötige Panels einfügen und diesem Widget hinzufügen.
		 */
		Grid form = new Grid(8, 2);
		form.setWidth("100%");
		form.setWidget(0, 0, titleLabel);
		form.setWidget(0, 1, titleInput);
		titleInput.setEnabled(false);
		form.setWidget(1, 0, descriptionLabel);
		form.setWidget(1, 1, descriptionInput);
		descriptionInput.setEnabled(false);
		form.setWidget(2, 0, deadlineLabel);
		form.setWidget(2, 1, deadlineInput);
		deadlineInput.setEnabled(false);
		form.setWidget(3, 0, statusLabel);
		form.setWidget(3, 1, statusInput);
		statusInput.setEnabled(false);
		statusInput.addItem("Laufend");
		statusInput.addItem("Erfolgreich");
		statusInput.addItem("Abgelehnt");
		form.setWidget(4, 1, callerIDLabel);
		callerIDLabel.setStyleName("bold");
		form.setWidget(5, 0, callerFirstNameLabel);
		form.setWidget(5, 1, callerInputFirstName);
		callerInputFirstName.setEnabled(false);
		form.setWidget(6, 0, callerLastNameLabel);
		form.setWidget(6, 1, callerInputLastName);
		callerInputLastName.setEnabled(false);
		final VerticalPanel root = new VerticalPanel();
		this.add(root);

		/*
		 * Falls eine selektiertee Ausschreibung übergeben wurde und jetzt
		 * dargestellt werden soll
		 * 
		 */
		if (shouldUpdate) {
			if (changeHeadline != null) {
				root.add(changeHeadline);
			}
			titleInput.setText(toChangeCall.getTitle());
			descriptionInput.setText(toChangeCall.getDescription());
			deadlineInput.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT)));
			deadlineInput.setValue(toChangeCall.getDeadline());
			statusInput.setVisibleItemCount(toChangeCall.getStatus());

			changeSaveButton= new Button("Änderungen speichern");
			changeSaveButton.setVisible(false);
			changeSaveButton.setEnabled(false);

			changeSaveButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (titleInput.getText().length() == 0) {
						Window.alert("Bitte vergeben Sie einen Titel ein");
					} else if (descriptionInput.getText().length() == 0) {
						Window.alert("Bitte vergeben Sie eine Beschreibung");
					} else if (deadlineInput.getValue() == null) {
						Window.alert("Bitte vergeben Sie eine Bewerbungsfrist");
					} else {
						toChangeCall.setTitle(titleInput.getText());
						toChangeCall.setDescription(descriptionInput.getText());
						toChangeCall.setDeadline(deadlineInput.getValue());
						toChangeCall.setStatus(statusInput.getSelectedIndex());
						worketplaceAdministration.saveCall(toChangeCall, new AsyncCallback<Void>() {
							public void onFailure(Throwable caught) {
								Window.alert("Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
							}

							public void onSuccess(Void result) {
								Window.alert("Die Ausschreibung wurde erfolgreich geändert");
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
			panel.add(changeSaveButton);
			
			changeDeleteButton = new Button("Diese Ausschreibung entfernen");
			changeDeleteButton.setVisible(false);
			changeDeleteButton.setEnabled(false);
			changeDeleteButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					final boolean confirmDelete = Window.confirm("Möchten Sie die Ausschreibung löschen?"
							+ "Es werden damit alle zugehörigen Bewerbungen und deren Bewertungen gelöscht.");
					if (confirmDelete) {
						worketplaceAdministration.deleteCall(toChangeCall, new AsyncCallback<Void>() {
							public void onFailure(Throwable caught) {
								Window.alert("Es trat ein Fehler beim Löschen auf, bitte versuchen Sie es erneut");
							}

							public void onSuccess(Void result) {
								Window.alert("Die Ausschreibung wurde erfolgreich gelöscht");
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
			panel.add(changeDeleteButton);

			form.setWidget(7, 1, panel);
		} else {
			if (addHeadline != null) {
				root.add(addHeadline);
			}
			final Button saveButton = new Button("Neue Ausschreibung anlegen");

			saveButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (titleInput.getText().length() == 0) {
						Window.alert("Bitte vergeben Sie einen Titel");
					} else if (descriptionInput.getText().length() == 0) {
						Window.alert("Bitte vergeben Sie eine Beschreibung");
					} else if (deadlineInput.getValue() == null) {
						Window.alert("Bitte vergeben Sie eine Bewerbungsfrist");
					} else {
						worketplaceAdministration.createCall(project, (Person) ClientsideSettings.getCurrentUser(),
								titleInput.getValue(), descriptionInput.getValue(), deadlineInput.getValue(),
								new AsyncCallback<Call>() {
									public void onFailure(Throwable caught) {
										Window.alert(
												"Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
									}

									public void onSuccess(Call result) {
										Window.alert("Die Ausschreibung wurde erfolgreich geändert");
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
			form.setWidget(4, 1, saveButton);
			form.getWidget(5, 0).setVisible(false);
			form.getWidget(5, 1).setVisible(false);
			form.getWidget(6, 0).setVisible(false);
			form.getWidget(6, 1).setVisible(false);
		}
		class RpcWrapper {
			protected int checkRpcTimerCounter = 1;
			protected Timer t;
			protected int checkRpcTimerCounter2 = 1;
			protected Timer t2;

			public RpcWrapper() {
				worketplaceAdministration.getProjectByID(toChangeCall.getProjectID(), new AsyncCallback<Project>() {
					public void onFailure(Throwable caught) {
						Window.alert("Es trat ein Fehler beim abrufen des Projekts auf");
					}

					public void onSuccess(Project result) {
						project = result;
					}
				});
				
				t = new Timer() {
					public void run() {
						RpcWrapper.this.checkRpcTimerCounter++;
						if (project instanceof Project) {
							RpcWrapper.this.t.cancel();
							RpcWrapper.this.checkRpcTimerCounter = 1;
							worketplaceAdministration.getPersonByID(project.getProjectLeaderID(), new AsyncCallback<Person>() {
								public void onFailure(Throwable caught) {
									Window.alert("Es trat ein Fehler beim abrufen des Projektleiters auf");
								}

								public void onSuccess(Person result) {
									projectLeader = result;
									callerInputFirstName.setValue(result.getFirstName());
									callerInputLastName.setValue(result.getLastName());
								}
							});
						} else if (RpcWrapper.this.checkRpcTimerCounter == 25) {
							RpcWrapper.this.t.cancel();
							RpcWrapper.this.checkRpcTimerCounter = 1;
						}
					}
				};
				// Schedule the timer to check if all RPC calls finished
				// each 400 milliseconds
				t.scheduleRepeating(400);
				
				t2 = new Timer() {
					public void run() {
						RpcWrapper.this.checkRpcTimerCounter2++;
						if (project instanceof Project && projectLeader instanceof Person) {
							RpcWrapper.this.t2.cancel();
							RpcWrapper.this.checkRpcTimerCounter2 = 1;
							if (project.getProjectLeaderID() == ClientsideSettings.getCurrentUser().getID()) {
								titleInput.setEnabled(true);
								descriptionInput.setEnabled(true);
								deadlineInput.setEnabled(true);
								statusInput.setEnabled(true);
								changeSaveButton.setVisible(true);
								changeSaveButton.setEnabled(true);
								changeDeleteButton.setVisible(true);
								changeDeleteButton.setEnabled(true);
							}
						} else if (RpcWrapper.this.checkRpcTimerCounter2 == 25) {
							RpcWrapper.this.t2.cancel();
							RpcWrapper.this.checkRpcTimerCounter2 = 1;
						}
					}
				};
				// Schedule the timer to check if all RPC calls finished
				// each 400 milliseconds
				t2.scheduleRepeating(400);
			}
		}
		new RpcWrapper();
		root.add(form);
		titleInput.setFocus(true);
	}

	// Async Call um Projekt und Person als Variablen zu setzen.
	private void getObjects(Call pToChangeCall) {
		
	}
}
