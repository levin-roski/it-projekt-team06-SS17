package de.worketplace.team06.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.Form;
import de.worketplace.team06.shared.bo.Application;
import de.worketplace.team06.shared.bo.Call;
import de.worketplace.team06.shared.bo.Enrollment;
import de.worketplace.team06.shared.bo.Rating;

/**
 * Formular für die Darstellung, Bearbeitung und Löschung einer selektierten
 * Bewerbung. Falls keine selektierte Bewerbung beim Initialisieren übergeben
 * wird, ist das Formular leer, bereit für die Erstellung einer neuen Bewerbung.
 * 
 * @author Patrick
 */
public class ApplicationForm extends Form {
	private Label textLabel = new Label("Ihr Bewerbungstext");
	private TextArea textInput = new TextArea();
	private Label ratingLabel = new Label();
	private DoubleBox ratingInput = new DoubleBox();
	private Label ratingTextLabel = new Label();
	private TextBox ratingTextInput = new TextBox();
	private boolean shouldUpdate = false;
	private Application toChangeApplication;
	private HorizontalPanel ratingHeadline;
	private HorizontalPanel changeHeadline;
	private HorizontalPanel addHeadline;

	/**
	 * Im Konstruktor kann eine selektierte Bewerbung übergeben werden, die dann
	 * bearbeitet und gelöscht werden kann. null wird übergeben, falls eine neue
	 * Bewerbung erstellt werden soll.
	 * 
	 * @param pToChangeApplication
	 *            Application, die im Formular angezeigt werden soll
	 * @param pHeadline
	 *            Falls true wird dem Formular eine Überschrift vorangehängt
	 */
	public ApplicationForm(Application pToChangeApplication, final boolean pHeadline) {
		if (pToChangeApplication != null) {
			shouldUpdate = true;
			this.toChangeApplication = pToChangeApplication;
		}
		if (pHeadline) {
			changeHeadline = createHeadline("Bewerbung bearbeiten", true);
			addHeadline = createHeadline("Auf Ausschreibung bewerben", true);
		}
	}

	/**
	 * Im Konstruktor kann eine selektierte Bewerbung übergeben werden, die dann
	 * bearbeitet und gelöscht werden kann. null übergeben, falls eine neue
	 * Bewerbung erstellt werden soll.
	 * 
	 * @param pToChangeApplication
	 *            Application, die im Formular angezeigt werden soll
	 * @param pHeadline
	 *            Falls true wird dem Formular eine Überschrift vorangehängt
	 * @param pClosingHeadline
	 *            Falls true wird dem Formular eine Überschrift mit Button, der
	 *            das aktuelle Item schließt, vorangehängt
	 */
	public ApplicationForm(Application pToChangeApplication, final boolean pHeadline, final boolean pClosingHeadline, final Call currentCall) {
		this(pToChangeApplication, pHeadline);
		if (pClosingHeadline) {
			changeHeadline = createHeadlineWithCloseButton("Bewerbung bearbeiten", true);
			addHeadline = createHeadlineWithCloseButton("Auf Ausschreibung bewerben", true);
		}
		
		ratingHeadline = createHeadline("Bewertung der Bewerbung");

		/*
		 * Grid mit 4 Zeilen und 1 Spalte für das Formular bereitstellen. Danach
		 * nötige Panels einfügen und diesem Widget hinzufügen.
		 */
		Grid form = new Grid(3, 1);
		form.setWidth("100%");
		form.setWidget(0, 0, textLabel);
		form.setWidget(1, 0, textInput);
		textInput.setSize("100%", "150px");
		
		Grid ratingform = new Grid(3, 2);
		ratingform.setWidget(0, 0, ratingHeadline);
		ratingform.setWidget(1, 0, ratingLabel);
		ratingform.setWidget(1, 1, ratingInput);
		ratingform.setWidget(2, 0, ratingTextLabel);
		ratingform.setWidget(2, 1, ratingTextInput);
		
		
		
		final VerticalPanel root = new VerticalPanel();
		this.add(root);
		
		
		/*
		 * Falls eine selektierte Bewerbung übergeben wurde und jetzt
		 * dargestellt werden soll
		 */
		if (shouldUpdate) {
			if (changeHeadline != null) {
				root.add(changeHeadline);
			}
			textInput.setText(toChangeApplication.getText());
			final Button saveButton = new Button("Speichern");
			saveButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (textInput.getText().length() == 0) {
						Window.alert("Bitte geben Sie einen Bewerbungstext ein");
					} else {
						toChangeApplication.setText(textInput.getText());
						worketplaceAdministration.saveApplication(toChangeApplication, new AsyncCallback<Void>() {
							public void onFailure(Throwable caught) {
								Window.alert("Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
							}

							public void onSuccess(Void result) {
								renderFormSuccess();
								Window.alert("Die Bewerbung wurde erfolgreich geändert");
							}
						});
					}
				}
			});
			final VerticalPanel panel = new VerticalPanel();
			panel.add(saveButton);
			final Button deleteButton = new Button("Bewerbung löschen");
			deleteButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					final boolean confirmDelete = Window.confirm("Möchten Sie die Bewerbung wirklich löschen?");
					if (confirmDelete) {
						worketplaceAdministration.deleteApplication(toChangeApplication, new AsyncCallback<Void>() {
							public void onFailure(Throwable caught) {
								Window.alert("Es trat ein Fehler beim Löschen auf, bitte versuchen Sie es erneut");
							}

							public void onSuccess(Void result) {
								renderFormSuccess();
								Window.alert("Die Bewerbung wurde erfolgreich gelöscht");
							}
						});
					}
				}
			});
			panel.add(deleteButton);
			form.setWidget(2, 0, panel);
			ratingHeadline.setVisible(false);
			ratingLabel.setVisible(false);
			ratingInput.setVisible(false);
			ratingTextLabel.setVisible(false);
			ratingTextInput.setVisible(false);
		} else {
			if (addHeadline != null) {
				root.add(addHeadline);
			}
			final Button saveButton = new Button("Bewerben");
			saveButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (textInput.getText().length() == 0) {
						Window.alert("Bitte geben Sie einen Bewerbungstext ein");
					} else {
						worketplaceAdministration.applyFor(currentCall, ClientsideSettings.getCurrentUser(),
								textInput.getText(), new AsyncCallback<Application>() {
									public void onFailure(Throwable caught) {
										Window.alert(
												"Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
									}

									public void onSuccess(Application result) {
										Window.alert("Die Bewerbung auf die Ausschreibung \"" + currentCall.getTitle()
												+ "\" wurde erstellt");
										Window.Location.reload();
									}
								});
					}
				}
			});
			form.setWidget(2, 0, saveButton);
			
		}
		root.add(form);
		root.add(ratingHeadline);
		root.add(ratingform);
		
		
		worketplaceAdministration.getRatingFor(pToChangeApplication,
				new AsyncCallback<Rating>() {
					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(Rating result) {
						if(result != null){
						ratingLabel.setVisible(true);
						ratingInput.setVisible(true);
						ratingTextLabel.setVisible(true);
						ratingTextInput.setVisible(true);
						ratingLabel.setText("Bewertung");
						ratingInput.setValue(Double.valueOf(String.valueOf(result.getRating())));
						ratingInput.setEnabled(false);
						ratingTextLabel.setText("Bewertungstext");
						ratingTextInput.setValue(result.getRatingStatement());
						ratingTextInput.setEnabled(false);
						ratingHeadline.setVisible(true);
						}
					}
				});
	}
}