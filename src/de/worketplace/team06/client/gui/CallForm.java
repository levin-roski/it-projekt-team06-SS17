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

import de.worketplace.team06.client.Callback;
import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.Form;
import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.shared.bo.Call;
import de.worketplace.team06.shared.bo.Marketplace;
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
	private TextBox callerInputFirstName = new TextBox();
	private TextBox callerInputLastName = new TextBox();
	private Boolean shouldUpdate = false;
	private Call toChangeCall;
	private Boolean permissionToChange = false;
	private HorizontalPanel changeHeadline;
	private HorizontalPanel addHeadline;

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
	
	public CallForm(final Call pToChangeCall, final boolean pHeadline, final Boolean pClosingHeadline,
			final Callback editCallback, final Callback deleteCallback, final Project currentProject) {
		this(pToChangeCall, pHeadline);
		if (pClosingHeadline) {
			changeHeadline = createHeadlineWithCloseButton("Ausschreibung bearbeiten", true);
			addHeadline = createHeadlineWithCloseButton("Ausschreibung hinzufügen", true);
		}

		/*
		 * Grid mit 6 Zeilen und 2 Spalten für das Formular bereitstellen.
		 * Danach nötige Panels einfügen und diesem Widget hinzufügen.
		 */
		Grid form = new Grid(7, 2);
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
		form.setWidget(4, 1, callerInputFirstName);
		form.setWidget(5, 1, callerInputLastName);
		final VerticalPanel root = new VerticalPanel();
		this.add(root);
		
		
		if (toChangeCall != null){
		worketplaceAdministration.getProjectByID(pToChangeCall.getProjectID(), new AsyncCallback<Project>() {
			public void onFailure(Throwable caught) {
				Window.alert("Es trat ein Fehler beim abrufen des Projektleiters auf. ");
			}

			public void onSuccess(Project result) {
				
				if(result.getProjectLeaderID() == ClientsideSettings.getCurrentUser().getID()) {
					permissionToChange = true;
				}
				else{
					permissionToChange = false;
				}
				
			}
		});
		}
		else{
			if(currentProject.getProjectLeaderID() == ClientsideSettings.getCurrentUser().getID()){
				permissionToChange = true;
			}
			else{
				permissionToChange = false;
			}
		}
		
		
		

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
			statusInput.addItem("Laufend");
			statusInput.addItem("Erfolgreich");
			statusInput.addItem("Abgelehnt");
			statusInput.setVisibleItemCount(toChangeCall.getStatus());
			
			worketplaceAdministration.getPersonByID(toChangeCall.getCallerID(), new AsyncCallback<Person>() {
				public void onFailure(Throwable caught) {
					Window.alert("Es trat ein Fehler beim abrufen der Ausschreibenden Person auf. ");
				}
				public void onSuccess(Person result) {
					callerInputFirstName.setValue(result.getFirstName());
					callerInputLastName.setValue(result.getLastName());
					callerInputFirstName.isReadOnly();
					callerInputLastName.isReadOnly();
				}
			});
			
			
			
			final Button saveButton = new Button("Änderungen speichern");
			if(permissionToChange == true){
			
			saveButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (titleInput.getText().length() == 0) {
						Window.alert("Bitte vergeben Sie einen Titel ein");
					} else if (descriptionInput.getText().length() == 0) {
						Window.alert("Bitte vergeben Sie eine Beschreibung");
					} else if (deadlineInput.getValue() == null){
						Window.alert("Bitte vergeben Sie eine Bewerbungsfrist");
					} 
					else {
						// TODO ergänzen
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
			});}
			
			final VerticalPanel panel = new VerticalPanel();
			
			if (permissionToChange == true){
			panel.add(saveButton);
			final Button deleteButton = new Button("Diese Ausschreibung entfernen");
			deleteButton.addClickHandler(new ClickHandler() {
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
			panel.add(deleteButton);
			}
			
			form.setWidget(6, 1, panel);
		} else {
			if (addHeadline != null) {
				root.add(addHeadline);
			}
			final Button saveButton = new Button("Neue Ausschreibung anlegen");
			if (permissionToChange == true){
			saveButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (titleInput.getText().length() == 0) {
						Window.alert("Bitte vergeben Sie einen Titel");
					} else if (descriptionInput.getText().length() == 0) {
						Window.alert("Bitte vergeben Sie eine Beschreibung");	
					} else if (deadlineInput.getValue() == null){
						Window.alert("Bitte vergeben Sie eine Bewerbungsfrist");
					} 
					else {
						// TODO ergänzen
						toChangeCall.setTitle(titleInput.getText());
						toChangeCall.setDescription(descriptionInput.getText());
						toChangeCall.setDeadline(deadlineInput.getValue());
						toChangeCall.setStatus(statusInput.getSelectedIndex());
						worketplaceAdministration.createCall(currentProject, callerPerson, title, description, deadline, new AsyncCallback<Call>(){
							public void onFailure(Throwable caught) {
								Window.alert("Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
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
			form.setWidget(6, 1, saveButton);
			}
		}
		if (toChangeCall == null){
			statusLabel.setVisible(false);
			statusInput.setVisible(false);
			callerIDLabel.setVisible(false);
			callerInputFirstName.setVisible(false);
			callerInputLastName.setVisible(false);
			
		}
		if(permissionToChange == false){
			titleInput.setEnabled(false);
			descriptionInput.setEnabled(false);
			deadlineInput.setEnabled(false);
			statusInput.setEnabled(false);
			callerInputFirstName.setEnabled(false);
			callerInputLastName.setEnabled(false);

		}
		root.add(form);
		titleInput.setFocus(true);
	}
}
