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

import de.worketplace.team06.client.Form;
import de.worketplace.team06.shared.bo.Enrollment;

/**
 * Formular für die Darstellung, Bearbeitung und Löschung einer selektierten
 * Beteiligung. Falls keine selektierte Beteiligung beim Initialisieren
 * übergeben wird, ist das Formular leer, bereit für die Erstellung eines neuen
 * Beteiligunges.
 * 
 * @author Roski
 */
public class EnrollmentForm extends Form {
	private Label startDateLabel = new Label("Startdatum");
	private TextBox startDateInput = new TextBox();
	private Label periodLabel = new Label("Dauer");
	private TextBox periodInput = new TextBox();
	private Label endDateLabel = new Label("Enddatum");
	private TextBox endDateInput = new TextBox();
	private Label orgaUnitIdLabel = new Label("Organisationseinheit");
	private Label projectIdLabel = new Label("Projekt");
	private Label ratingIdLabel = new Label("Bewertung");
	private Enrollment toChangeEnrollment;
	private HorizontalPanel changeHeadline;

	/**
	 * Im Konstruktor kann eine selektierte Beteiligung übergeben werden, die
	 * dann bearbeitet und gelöscht werden kann. null übergeben, falls eine neue
	 * Beteiligung erstellt werden soll.
	 * 
	 * @param pToChangeEnrollment
	 *            Enrollment, die im Formular angezeigt werden soll
	 * @param pHeadline
	 *            Falls true wird dem Formular eine Überschrift vorangehängt
	 */
	public EnrollmentForm(Enrollment pToChangeEnrollment, final boolean pHeadline) {
		toChangeEnrollment = pToChangeEnrollment;
		if (pHeadline) {
			changeHeadline = createHeadline("Beteiligung bearbeiten", true);
		}
	}

	/**
	 * Im Konstruktor kann eine selektierte Beteiligung übergeben werden, die
	 * dann bearbeitet und gelöscht werden kann. null übergeben, falls ein neuer
	 * Beteiligung erstellt werden soll.
	 * 
	 * @param pToChangeEnrollment
	 *            Enrollment, die im Formular angezeigt werden soll
	 * @param pHeadline
	 *            Falls true wird dem Formular eine Überschrift vorangehängt
	 * @param pClosingHeadline
	 *            Falls true wird dem Formular eine Überschrift mit Button, der
	 *            das aktuelle Item schließt, vorangehängt
	 */
	public EnrollmentForm(Enrollment pToChangeEnrollment, final boolean pHeadline, final Boolean pClosingHeadline) {
		this(pToChangeEnrollment, pHeadline);
		if (pClosingHeadline) {
			changeHeadline = createHeadlineWithCloseButton("Beteiligung bearbeiten", true);
		}

		/*
		 * Grid mit 3 Zeilen und 2 Spalten für das Formular bereitstellen.
		 * Danach nötige Panels einfügen und diesem Widget hinzufügen.
		 */
		Grid form = new Grid(3, 2);
		form.setWidth("100%");
		form.setWidget(0, 0, startDateLabel);
		form.setWidget(0, 1, startDateInput);
		form.setWidget(1, 0, periodLabel);
		form.setWidget(1, 1, periodInput);
		form.setWidget(1, 0, endDateLabel);
		form.setWidget(1, 1, endDateInput);
		form.setWidget(1, 0, orgaUnitIdLabel);
		form.setWidget(1, 0, projectIdLabel);
		form.setWidget(1, 0, ratingIdLabel);

		final VerticalPanel root = new VerticalPanel();
		this.add(root);
		/*
		 * Falls eine selektierte Beteiligung übergeben wurde und jetzt
		 * dargestellt werden soll
		 */

		if (changeHeadline != null) {
			root.add(changeHeadline);
		}
		startDateInput.setValue(toChangeEnrollment.getStartDate().toString());
		periodInput.setValue(toChangeEnrollment.getWorkload().toString());
		endDateInput.setValue(toChangeEnrollment.getEndDate().toString());
		final Button saveButton = new Button("Änderungen speichern");
		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (startDateInput.getText().length() == 0) {
					Window.alert("Bitte vergeben Sie ein Startdatum");
				} else if (periodInput.getText().length() == 0) {
					Window.alert("Bitte geben Sie eine Dauer an");
				} else if (endDateInput.getText().length() == 0) {
					Window.alert("Bitte geben Sie ein Enddatum an");
				} else {
					toChangeEnrollment.setStartDate(startDateInput.getValue());
					toChangeEnrollment.setWorkload(periodInput.getValue());
					toChangeEnrollment.setEndDate(endDateInput.getValue());

					worketplaceAdministration.saveEnrollment(toChangeEnrollment, new AsyncCallback<Void>() {
						public void onFailure(Throwable caught) {
							Window.alert("Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
						}

						public void onSuccess(Void result) {
							Window.alert("Die Beteiligung wurde erfolgreich geändert");
							renderFormSuccess();
						}
					});
				}
			}
		});
		final VerticalPanel panel = new VerticalPanel();
		panel.add(saveButton);
		final Button deleteButton = new Button("Diese Beteiligung entfernen");
		deleteButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				final boolean confirmDelete = Window.confirm("Möchten Sie die Beteiligung wirklich löschen?");
				if (confirmDelete) {
					worketplaceAdministration.deleteEnrollment(toChangeEnrollment, new AsyncCallback<Void>() {
						public void onFailure(Throwable caught) {
							Window.alert("Es trat ein Fehler beim Löschen auf, bitte versuchen Sie es erneut");
						}

						public void onSuccess(Void result) {
							Window.alert("Die Beteiligung wurde erfolgreich gelöscht");
							renderFormSuccess();
						}
					});
				}
			}
		});
		panel.add(deleteButton);
		form.setWidget(2, 1, panel);
		root.add(form);
	}
}