package de.worketplace.team06.client.gui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.View;
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
	/*
	 * Grid mit 9 Zeilen und 2 Spalten für das Formular bereitstellen.
	 */
	protected final Grid form = new Grid(9, 2);

	/**
	 * Im Konstruktor kann eine selektierter Marktplatz übergeben werden, der
	 * dann bearbeitet und gelöscht werden kann. null übergeben, falls ein neuer
	 * Marktplatz erstellt werden soll.
	 * 
	 * @param entryPoint
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

		form.setWidth("100%");
		form.setWidget(0, 0, typeLabel);
		form.setWidget(0, 1, typeInput);
		form.setWidget(1, 0, descriptionLabel);
		form.setWidget(1, 1, descriptionInput);
		final VerticalPanel root = new VerticalPanel();
		root.add(ClientsideSettings.getBreadcrumbs());
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
			final Button saveButton = new Button("Speichern");
			final Button deleteButton = new Button("Nutzer entfernen");

			switch (toChangeOrgaUnit.getType()) {
			case "Person":
				worketplaceAdministration.getPersonByGoogleID(toChangeOrgaUnit.getGoogleID(),
						new AsyncCallback<Person>() {
							@Override
							public void onFailure(Throwable caught) {
							}

							@Override
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
															@Override
															public void onFailure(Throwable caught) {
																Window.alert(
																		"Es trat ein Fehler beim Löschen auf, bitte versuchen Sie es erneut");
															}

															@Override
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
											toChangePerson.setFirstName(firstnameInput.getText());
											toChangePerson.setLastName(lastnameInput.getText());
											toChangePerson.setStreet(streetInput.getText());
											try {
												toChangePerson.setZipcode(Integer.parseInt(zipcodeInput.getText()));
											} catch (Exception e) {
												toChangePerson.setZipcode(null);
											}
											toChangePerson.setCity(cityInput.getText());
											worketplaceAdministration.savePerson(toChangePerson,
													new AsyncCallback<Void>() {
														@Override
														public void onFailure(Throwable caught) {
															Window.alert(
																	"Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
														}

														@Override
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
				worketplaceAdministration.getTeamByGoogleID(toChangeOrgaUnit.getGoogleID(), new AsyncCallback<Team>() {
					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(final Team toChangeTeam) {
						Label nameLabel = new Label("Team-Name");
						form.setWidget(2, 0, nameLabel);
						final TextBox nameInput = new TextBox();
						nameInput.setText(toChangeTeam.getName());
						form.setWidget(2, 1, nameInput);

						Label countLabel = new Label("Anzahl Mitglieder");
						form.setWidget(3, 0, countLabel);
						final TextBox countInput = new TextBox();
						countInput.setText(toChangeTeam.getMembercount().toString());
						form.setWidget(3, 1, countInput);

						deleteButton.addClickHandler(new ClickHandler() {
							public void onClick(ClickEvent event) {
								final boolean confirmDelete = Window
										.confirm("Möchten Sie Ihren Nutzer wirklich löschen?");
								if (confirmDelete) {
									try {
										worketplaceAdministration.deleteTeam(toChangeTeam, new AsyncCallback<Void>() {
											@Override
											public void onFailure(Throwable caught) {
												Window.alert(
														"Es trat ein Fehler beim Löschen auf, bitte versuchen Sie es erneut");
											}

											@Override
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
								} else if (nameInput.getText().length() == 0) {
									Window.alert("Bitte füllen Sie das Feld Name");
								} else {
									toChangeTeam.setDescription(descriptionInput.getText());
									toChangeTeam.setName(nameInput.getText());
									try {
										toChangeTeam.setMembercount(Integer.parseInt(countInput.getText()));
									} catch (Exception e) {
										toChangeTeam.setMembercount(null);
									}
									worketplaceAdministration.saveTeam(toChangeTeam, new AsyncCallback<Void>() {
										@Override
										public void onFailure(Throwable caught) {
											Window.alert(
													"Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
										}

										@Override
										public void onSuccess(Void result) {
											Window.alert("Der Nutzer wurde erfolgreich geändert");
										}
									});
								}
							}
						});
						form.setWidget(4, 1, saveButton);
						form.setWidget(5, 1, deleteButton);
					}
				});
				break;

			case "Organisation":
				worketplaceAdministration.getOrganisationByGoogleID(toChangeOrgaUnit.getGoogleID(),
						new AsyncCallback<Organisation>() {
							@Override
							public void onFailure(Throwable caught) {
							}

							@Override
							public void onSuccess(final Organisation toChangeOrganisation) {
								Label nameLabel = new Label("Vorname");
								form.setWidget(2, 0, nameLabel);
								final TextBox nameInput = new TextBox();
								nameInput.setText(toChangeOrganisation.getName());
								form.setWidget(2, 1, nameInput);

								Label streetLabel = new Label("Nachname");
								form.setWidget(3, 0, streetLabel);
								final TextBox streetInput = new TextBox();
								streetInput.setText(toChangeOrganisation.getStreet());
								form.setWidget(3, 1, streetInput);

								Label zipcodeLabel = new Label("Postleitzahl");
								form.setWidget(4, 0, zipcodeLabel);
								final TextBox zipcodeInput = new TextBox();
								zipcodeInput.setText(toChangeOrganisation.getZipcode().toString());
								form.setWidget(4, 1, zipcodeInput);

								Label cityLabel = new Label("Stadt");
								form.setWidget(5, 0, cityLabel);
								final TextBox cityInput = new TextBox();
								cityInput.setText(toChangeOrganisation.getCity());
								form.setWidget(5, 1, cityInput);

								deleteButton.addClickHandler(new ClickHandler() {
									public void onClick(ClickEvent event) {
										final boolean confirmDelete = Window
												.confirm("Möchten Sie Ihren Nutzer wirklich löschen?");
										if (confirmDelete) {
											try {
												worketplaceAdministration.deleteOrganisation(toChangeOrganisation,
														new AsyncCallback<Void>() {
															@Override
															public void onFailure(Throwable caught) {
																Window.alert(
																		"Es trat ein Fehler beim Löschen auf, bitte versuchen Sie es erneut");
															}

															@Override
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
										} else if (nameInput.getText().length() == 0) {
											Window.alert("Bitte füllen Sie das Feld Vorname");
										} else if (streetInput.getText().length() == 0) {
											Window.alert("Bitte füllen Sie das Feld Nachname");
										} else {
											toChangeOrganisation.setDescription(descriptionInput.getText());
											toChangeOrganisation.setName(nameInput.getText());
											toChangeOrganisation.setStreet(streetInput.getText());
											try {
												toChangeOrganisation
														.setZipcode(Integer.parseInt(zipcodeInput.getText()));
											} catch (Exception e) {
												toChangeOrganisation.setZipcode(null);
											}
											toChangeOrganisation.setCity(cityInput.getText());
											worketplaceAdministration.saveOrganisation(toChangeOrganisation,
													new AsyncCallback<Void>() {
														@Override
														public void onFailure(Throwable caught) {
															Window.alert(
																	"Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
														}

														@Override
														public void onSuccess(Void result) {
															Window.alert("Der Nutzer wurde erfolgreich geändert");
														}
													});
										}
									}
								});
								form.setWidget(6, 1, saveButton);
								form.setWidget(7, 1, deleteButton);
							}
						});
				break;
			}

		} else {
			RootPanel.get("navigation").add(new HTML("<img src=\"/logo.png\" class=\"registration-logo\">"));
			if (addHeadline != null) {
				root.add(addHeadline);
			}

			typeInput.addChangeHandler(new ChangeHandler() {
				public void onChange(ChangeEvent event) {
					form.clearCell(2, 0);
					form.clearCell(2, 1);
					form.clearCell(3, 0);
					form.clearCell(3, 1);
					form.clearCell(4, 0);
					form.clearCell(4, 1);
					form.clearCell(5, 0);
					form.clearCell(5, 1);
					form.clearCell(6, 0);
					form.clearCell(6, 1);
					form.clearCell(7, 0);
					form.clearCell(7, 1);

					final Button saveButton = new Button("Nutzer anlegen");
					final Button logoutButton = new Button("Logout");
					logoutButton.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							final boolean confirm = Window.confirm(
									"Möchten Sie sich wirklich abmelden? Ihre aktuellen Eingaben könnten verloren gehen.");
							if (confirm) {
								Window.Location.replace(ClientsideSettings.getLoginInfo().getLogoutUrl());
							}
						}
					});

					String type = typeInput.getSelectedValue();
					switch (type) {
					case "Person":
						Label firstnameLabel = new Label("Vorname");
						form.setWidget(2, 0, firstnameLabel);
						final TextBox firstnameInput = new TextBox();
						form.setWidget(2, 1, firstnameInput);

						Label lastnameLabel = new Label("Nachname");
						form.setWidget(3, 0, lastnameLabel);
						final TextBox lastnameInput = new TextBox();
						form.setWidget(3, 1, lastnameInput);

						Label streetLabel = new Label("Straße");
						form.setWidget(4, 0, streetLabel);
						final TextBox streetInput = new TextBox();
						form.setWidget(4, 1, streetInput);

						Label zipcodeLabel = new Label("Postleitzahl");
						form.setWidget(5, 0, zipcodeLabel);
						final TextBox zipcodeInput = new TextBox();
						form.setWidget(5, 1, zipcodeInput);

						Label cityLabel = new Label("Stadt");
						form.setWidget(6, 0, cityLabel);
						final TextBox cityInput = new TextBox();
						form.setWidget(6, 1, cityInput);

						saveButton.addClickHandler(new ClickHandler() {
							public void onClick(ClickEvent event) {
								if (descriptionInput.getText().length() == 0) {
									Window.alert("Bitte beschreiben Sie Ihren Nutzer genauer");
								} else if (firstnameInput.getText().length() == 0) {
									Window.alert("Bitte füllen Sie das Feld Vorname");
								} else if (lastnameInput.getText().length() == 0) {
									Window.alert("Bitte füllen Sie das Feld Nachname");
								} else {
									int zipcode = 0;
									try {
										zipcode = Integer.parseInt(zipcodeInput.getText());
									} catch (Exception e) {
									}
									worketplaceAdministration.createPerson(firstnameInput.getText(),
											lastnameInput.getText(), streetInput.getText(), zipcode,
											cityInput.getText(), descriptionInput.getText(),
											ClientsideSettings.getLoginInfo().getGoogleId(),
											new AsyncCallback<Person>() {
												@Override
												public void onFailure(Throwable caught) {
													Window.alert(
															"Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
												}

												@Override
												public void onSuccess(Person result) {
													runAfterInsert();
												}
											});
								}
							}
						});
						form.setWidget(7, 1, saveButton);
						form.setWidget(8, 1, logoutButton);
						break;

					case "Team":
						Label nameLabel = new Label("Team-Name");
						form.setWidget(2, 0, nameLabel);
						final TextBox nameInput = new TextBox();
						form.setWidget(2, 1, nameInput);

						Label countLabel = new Label("Anzahl Mitglieder");
						form.setWidget(3, 0, countLabel);
						final TextBox countInput = new TextBox();
						form.setWidget(3, 1, countInput);

						saveButton.addClickHandler(new ClickHandler() {
							public void onClick(ClickEvent event) {
								if (descriptionInput.getText().length() == 0) {
									Window.alert("Bitte beschreiben Sie Ihren Nutzer genauer");
								} else if (nameInput.getText().length() == 0) {
									Window.alert("Bitte füllen Sie das Feld Name");
								} else {
									int count = 0;
									try {
										count = Integer.parseInt(countInput.getText());
									} catch (Exception e) {
									}
									worketplaceAdministration.createTeam(nameInput.getText(),
											descriptionInput.getText(), count,
											ClientsideSettings.getLoginInfo().getGoogleId(), new AsyncCallback<Team>() {
												@Override
												public void onFailure(Throwable caught) {
													Window.alert(
															"Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
												}

												@Override
												public void onSuccess(Team result) {
													runAfterInsert();
												}
											});
								}
							}
						});
						form.setWidget(4, 1, saveButton);
						form.setWidget(5, 1, logoutButton);
						break;

					case "Organisation":
						Label nameLabel1 = new Label("Vorname");
						form.setWidget(2, 0, nameLabel1);
						final TextBox nameInput1 = new TextBox();
						form.setWidget(2, 1, nameInput1);

						Label streetLabel1 = new Label("Nachname");
						form.setWidget(3, 0, streetLabel1);
						final TextBox streetInput1 = new TextBox();
						form.setWidget(3, 1, streetInput1);

						Label zipcodeLabel1 = new Label("Postleitzahl");
						form.setWidget(4, 0, zipcodeLabel1);
						final TextBox zipcodeInput1 = new TextBox();
						form.setWidget(4, 1, zipcodeInput1);

						Label cityLabel1 = new Label("Stadt");
						form.setWidget(5, 0, cityLabel1);
						final TextBox cityInput1 = new TextBox();
						form.setWidget(5, 1, cityInput1);

						saveButton.addClickHandler(new ClickHandler() {
							public void onClick(ClickEvent event) {
								if (descriptionInput.getText().length() == 0) {
									Window.alert("Bitte beschreiben Sie Ihren Nutzer genauer");
								} else if (nameInput1.getText().length() == 0) {
									Window.alert("Bitte füllen Sie das Feld Vorname");
								} else if (streetInput1.getText().length() == 0) {
									Window.alert("Bitte füllen Sie das Feld Nachname");
								} else {
									int zipcode = 0;
									try {
										zipcode = Integer.parseInt(zipcodeInput1.getText());
									} catch (Exception e) {
									}
									worketplaceAdministration.createOrganisation(nameInput1.getText(),
											descriptionInput.getText(), streetInput1.getText(), zipcode,
											cityInput1.getText(), ClientsideSettings.getLoginInfo().getGoogleId(),
											new AsyncCallback<Organisation>() {
												@Override
												public void onFailure(Throwable caught) {
													Window.alert(
															"Es trat ein Fehler beim Speichern auf, bitte versuchen Sie es erneut");
												}

												@Override
												public void onSuccess(Organisation result) {
													runAfterInsert();
												}
											});
								}
							}
						});
						form.setWidget(6, 1, saveButton);
						form.setWidget(7, 1, logoutButton);
						break;
					}
				}
			});
			DomEvent.fireNativeEvent(Document.get().createChangeEvent(), typeInput);
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

	protected void runAfterInsert() {
		Window.alert("Ihr Nutzer wurde erfolgreich erstellt");
		History.newItem("Startseite");
		Window.Location.reload();
	}

	@Override
	public String returnTokenName() {
		return "Mein-Nutzer";
	}
}