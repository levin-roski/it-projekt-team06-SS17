package de.worketplace.team06.client.gui;

import java.util.Vector;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.View;
import de.worketplace.team06.shared.bo.Application;
import de.worketplace.team06.shared.bo.Call;
import de.worketplace.team06.shared.bo.Enrollment;
import de.worketplace.team06.shared.bo.Person;
import de.worketplace.team06.shared.bo.Project;

public class MyOverView extends View {
	private final CellTable<Project> myProjectsTable = new CellTable<Project>();
	private final CellTable<Call> myCallsTable = new CellTable<Call>();
	private final CellTable<Application> myApplicationsTable = new CellTable<Application>();
	private final CellTable<Application> applicationsToMeTable = new CellTable<Application>();
	private final CellTable<Enrollment> enrollmentsToMeTable = new CellTable<Enrollment>();
	private final CellTable<Enrollment> myEnrollmentsTable = new CellTable<Enrollment>();

	public MyOverView() {
		setBreadcrumb();
		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Project> myProjectsSsm = new SingleSelectionModel<Project>();

		// Das SingleSelectionModel wird der Tabelle Meine Marktplätze
		// hinzugefügt
		myProjectsTable.setSelectionModel(myProjectsSsm);

		// hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der
		// Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		myProjectsSsm.addSelectionChangeHandler(new Handler() {
			/**
			 * @param event
			 */
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				if (myProjectsSsm.getSelectedObject() != null) {
					Project selectedProject = myProjectsSsm.getSelectedObject();
					History.newItem(
							"Projekt-Details" + selectedProject.getID() + "-" + selectedProject.getMarketplaceID());
					myProjectsSsm.clear();
				}
			}
		});

		// hinzufügen der Tabellenspaltennamen sowie hinzufügen der zugehörigen
		// Daten aus der Datenbank
		TextColumn<Project> projectsTitleColumn = new TextColumn<Project>() {
			@Override
			public String getValue(Project object) {
				return object.getTitle();
			}
		};
		myProjectsTable.addColumn(projectsTitleColumn, "Name");

		TextColumn<Project> projectsDescriptionColumn = new TextColumn<Project>() {
			@Override
			public String getValue(Project object) {
				return object.getDescription();
			}
		};
		myProjectsTable.addColumn(projectsDescriptionColumn, "Beschreibung");

		TextColumn<Project> projectsStartColumn = new TextColumn<Project>() {
			@Override
			public String getValue(Project object) {
				try {
					return simpleDateFormat.format(object.getStartDate());
				} catch (Exception e) {
					return "Kein Datum gesetzt";
				}
			}
		};
		myProjectsTable.addColumn(projectsStartColumn, "Startdatum");

		TextColumn<Project> projectsEndColumn = new TextColumn<Project>() {
			@Override
			public String getValue(Project object) {
				try {
					return simpleDateFormat.format(object.getEndDate());

				} catch (Exception e) {
					return "Kein Datum gesetzt";
				}
			}
		};
		myProjectsTable.addColumn(projectsEndColumn, "Enddatum");

		myProjectsTable.setWidth("100%", true);

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Call> myCallsSsm = new SingleSelectionModel<Call>();

		// Das SingleSelectionModel wird der Tabelle Meine Marktplätze
		// hinzugefügt
		myCallsTable.setSelectionModel(myCallsSsm);

		// hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der
		// Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		myCallsSsm.addSelectionChangeHandler(new Handler() {

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				if (myCallsSsm.getSelectedObject() != null) {
					final Call selectedCall = myCallsSsm.getSelectedObject();
					worketplaceAdministration.getProjectByID(selectedCall.getProjectID(), new AsyncCallback<Project>() {
						@Override
						public void onFailure(Throwable caught) {
						}

						@Override
						public void onSuccess(Project results) {
							myCallsSsm.clear();
							History.newItem("Ausschreibungs-Details" + selectedCall.getID() + "-" + results.getID()
									+ "-" + results.getMarketplaceID());
						}
					});
				}
			}
		});

		// hinzufügen der Tabellenspaltennamen sowie hinzufügen der zugehörigen
		// Daten aus der Datenbank
		TextColumn<Call> projectsAndCallsColumn = new TextColumn<Call>() {
			@Override
			public String getValue(Call object) {
				return object.getTitle();
			}
		};
		myCallsTable.addColumn(projectsAndCallsColumn, "Titel");

		TextColumn<Call> descriptionColumn = new TextColumn<Call>() {
			@Override
			public String getValue(Call object) {
				return object.getDescription();
			}
		};
		myCallsTable.addColumn(descriptionColumn, "Beschreibung");

		TextColumn<Call> deadlineColumn = new TextColumn<Call>() {
			@Override
			public String getValue(Call object) {
				try {
					return simpleDateFormat.format(object.getDeadline());
				} catch (Exception e) {
					return "Kein Datum gesetzt";
				}
			}
		};
		myCallsTable.addColumn(deadlineColumn, "Bewerbungsfrist");

		TextColumn<Call> statusColumn = new TextColumn<Call>() {
			@Override
			public String getValue(Call object) {
				return object.getStatusString();
			}
		};
		myCallsTable.addColumn(statusColumn, "Status");

		myCallsTable.setWidth("100%", true);

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Application> myApplicationsSsm = new SingleSelectionModel<Application>();

		// Das SingleSelectionModel wird der Tabelle Meine Marktplätze
		// hinzugefügt
		myApplicationsTable.setSelectionModel(myApplicationsSsm);

		// hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der
		// Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		myApplicationsSsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				if (myApplicationsSsm.getSelectedObject() != null) {
					Application selectedApplication = myApplicationsSsm.getSelectedObject();
					mainPanel.setForm(new ApplicationForm(selectedApplication, false, true, null));
					myApplicationsSsm.clear();
				}
			}
		});

		// hinzufügen der Tabellenspaltennamen sowie hinzufügen der zugehörigen
		// Daten aus der Datenbank
		TextColumn<Application> bewerbungstextColumn = new TextColumn<Application>() {
			@Override
			public String getValue(Application object) {
				return object.getText();
			}
		};
		myApplicationsTable.addColumn(bewerbungstextColumn, "Bewerbungstext");

		// Muss eigentlich Int (bzw. Row counter) wiedergeben
		TextColumn<Application> applicationsCounterColumn = new TextColumn<Application>() {
			@Override
			public String getValue(Application object) {
				return object.getStatusString();
			}
		};
		myApplicationsTable.addColumn(applicationsCounterColumn, "Status");

		myApplicationsTable.setWidth("100%", true);

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Application> applicationsToMeSsm = new SingleSelectionModel<Application>();

		// Das SingleSelectionModel wird der Tabelle Meine Marktplätze
		// hinzugefügt
		applicationsToMeTable.setSelectionModel(applicationsToMeSsm);

		// hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der
		// Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		applicationsToMeSsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				if (applicationsToMeSsm.getSelectedObject() != null) {
					Application selectedApplication = applicationsToMeSsm.getSelectedObject();
					mainPanel.setForm(new RateApplicationForm(null, false, true, selectedApplication));
					applicationsToMeSsm.clear();
				}
			}
		});

		// hinzufügen der Tabellenspaltennamen sowie hinzufügen der zugehörigen
		// Daten aus der Datenbank
		TextColumn<Application> applicationTextColumn = new TextColumn<Application>() {
			@Override
			public String getValue(Application object) {
				return object.getText();
			}
		};
		applicationsToMeTable.addColumn(applicationTextColumn, "Bewerbungstext");

		TextColumn<Application> applicationStatusColumn = new TextColumn<Application>() {
			@Override
			public String getValue(Application object) {
				return object.getStatusString();
			}
		};
		applicationsToMeTable.addColumn(applicationStatusColumn, "Status");

		applicationsToMeTable.setWidth("100%", true);

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Enrollment> myEnrollmentsSsm = new SingleSelectionModel<Enrollment>();

		// Das SingleSelectionModel wird der Tabelle Meine Marktplätze
		// hinzugefügt
		myEnrollmentsTable.setSelectionModel(myEnrollmentsSsm);

		// hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der
		// Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		myEnrollmentsSsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				if (myEnrollmentsSsm.getSelectedObject() != null) {
					Enrollment selectedEnrollment = myEnrollmentsSsm.getSelectedObject();
					mainPanel.setForm(new EnrollmentForm(selectedEnrollment, false, true));
					myEnrollmentsSsm.clear();
				}
			}
		});

		// hinzufügen der Tabellenspaltennamen sowie hinzufügen der zugehörigen
		// Daten aus der Datenbank
		TextColumn<Enrollment> enrollmentDescriptionColumn = new TextColumn<Enrollment>() {
			@Override
			public String getValue(Enrollment object) {
				return object.getJobdescription();
			}
		};
		myEnrollmentsTable.addColumn(enrollmentDescriptionColumn, "Tätigkeit");

		TextColumn<Enrollment> enrollmentStartColumn = new TextColumn<Enrollment>() {
			@Override
			public String getValue(Enrollment object) {
				try {
					return simpleDateFormat.format(object.getStartDate());
				} catch (Exception e) {
					return "Kein Datum gesetzt";
				}
			}
		};
		myEnrollmentsTable.addColumn(enrollmentStartColumn, "Startdartum");

		TextColumn<Enrollment> enrollmentEndColumn = new TextColumn<Enrollment>() {
			@Override
			public String getValue(Enrollment object) {
				try {
					return simpleDateFormat.format(object.getEndDate());
				} catch (Exception e) {
					return "Kein Datum gesetzt";
				}
			}
		};
		myEnrollmentsTable.addColumn(enrollmentEndColumn, "Enddartum");

		TextColumn<Enrollment> enrollmentPeriodColumn = new TextColumn<Enrollment>() {
			@Override
			public String getValue(Enrollment object) {
				return String.valueOf(object.getWorkload());
			}
		};
		myEnrollmentsTable.addColumn(enrollmentPeriodColumn, "Workload (in Tagen)");

		myEnrollmentsTable.setWidth("100%", true);

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Enrollment> enrollmentsToMeSsm = new SingleSelectionModel<Enrollment>();

		// Das SingleSelectionModel wird der Tabelle Meine Marktplätze
		// hinzugefügt
		enrollmentsToMeTable.setSelectionModel(enrollmentsToMeSsm);

		// hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der
		// Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		enrollmentsToMeSsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				if (enrollmentsToMeSsm.getSelectedObject() != null) {
					Enrollment selectedEnrollment = enrollmentsToMeSsm.getSelectedObject();
					mainPanel.setForm(new EnrollmentForm(selectedEnrollment, false, true));
					enrollmentsToMeSsm.clear();
				}
			}
		});

		// hinzufügen der Tabellenspaltennamen sowie hinzufügen der zugehörigen
		// Daten aus der Datenbank
		TextColumn<Enrollment> enrollmentToMeDescriptionColumn = new TextColumn<Enrollment>() {
			@Override
			public String getValue(Enrollment object) {
				return object.getJobdescription();
			}
		};
		enrollmentsToMeTable.addColumn(enrollmentToMeDescriptionColumn, "Tätigkeit");

		TextColumn<Enrollment> enrollmentToMeStartColumn = new TextColumn<Enrollment>() {
			@Override
			public String getValue(Enrollment object) {
				try {
					return simpleDateFormat.format(object.getStartDate());
				} catch (Exception e) {
					return "Kein Datum gesetzt";
				}
			}
		};
		enrollmentsToMeTable.addColumn(enrollmentToMeStartColumn, "Startdartum");

		TextColumn<Enrollment> enrollmentToMeEndColumn = new TextColumn<Enrollment>() {
			@Override
			public String getValue(Enrollment object) {
				try {
					return simpleDateFormat.format(object.getEndDate());
				} catch (Exception e) {
					return "Kein Datum gesetzt";
				}
			}
		};
		enrollmentsToMeTable.addColumn(enrollmentToMeEndColumn, "Enddartum");

		TextColumn<Enrollment> enrollmentToMePeriodColumn = new TextColumn<Enrollment>() {
			@Override
			public String getValue(Enrollment object) {
				return String.valueOf(object.getWorkload());
			}
		};
		enrollmentsToMeTable.addColumn(enrollmentToMePeriodColumn, "Workload (in Tagen)");

		enrollmentsToMeTable.setWidth("100%", true);

		VerticalPanel root = new VerticalPanel();
		root.add(ClientsideSettings.getBreadcrumbs());
		root.add(createHeadline("Mein Bereich", true));
		if (ClientsideSettings.getCurrentUser() instanceof Person) {
			root.add(createSecondHeadline("Meine Projekte"));
			root.add(myProjectsTable);
		}
		if (ClientsideSettings.getCurrentUser() instanceof Person) {
			root.add(createSecondHeadline("Meine Ausschreibungen"));
			root.add(myCallsTable);
		}
		root.add(createSecondHeadline("Ausgehende Bewerbungen"));
		root.add(myApplicationsTable);
		if (ClientsideSettings.getCurrentUser() instanceof Person) {
			root.add(createSecondHeadline("Eingehende Bewerbungen"));
			root.add(applicationsToMeTable);
		}
		root.add(createSecondHeadline("Meine Projekt-Beteiligungen"));
		root.add(myEnrollmentsTable);
		if (ClientsideSettings.getCurrentUser() instanceof Person) {
			root.add(createSecondHeadline("Beteiligungen an meinen Projekten"));
			root.add(enrollmentsToMeTable);
		}

		this.add(root);
		loadData();
	}

	@Override
	public void loadData() {
		worketplaceAdministration.getProjectsForLeader(ClientsideSettings.getCurrentUser(),
				new AsyncCallback<Vector<Project>>() {
					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(Vector<Project> results) {
						myProjectsTable.setRowData(0, results);
						myProjectsTable.setRowCount(results.size(), true);
					}
				});
		try {
			worketplaceAdministration.getCallsFor((Person) ClientsideSettings.getCurrentUser(),
					new AsyncCallback<Vector<Call>>() {
						@Override
						public void onFailure(Throwable caught) {
						}

						@Override
						public void onSuccess(Vector<Call> results) {
							myCallsTable.setRowData(0, results);
							myCallsTable.setRowCount(results.size(), true);
						}
					});
		} catch (Exception e) {
		}
		try {
			worketplaceAdministration.getApplicationsForProjectLeader((Person) ClientsideSettings.getCurrentUser(),
					new AsyncCallback<Vector<Application>>() {
						@Override
						public void onFailure(Throwable caught) {
						}

						@Override
						public void onSuccess(Vector<Application> results) {
							applicationsToMeTable.setRowData(0, results);
							applicationsToMeTable.setRowCount(results.size(), true);
						}
					});
		} catch (Exception e) {
		}
		worketplaceAdministration.getApplicationsFor(ClientsideSettings.getCurrentUser(),
				new AsyncCallback<Vector<Application>>() {
					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(Vector<Application> results) {
						myApplicationsTable.setRowData(0, results);
						myApplicationsTable.setRowCount(results.size(), true);
					}
				});
		worketplaceAdministration.getEnrollmentFor(ClientsideSettings.getCurrentUser(),
				new AsyncCallback<Vector<Enrollment>>() {
					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(Vector<Enrollment> results) {
						myEnrollmentsTable.setRowData(0, results);
						myEnrollmentsTable.setRowCount(results.size(), true);
					}
				});
		try {
			worketplaceAdministration.getEnrollmentsForProjectLeader((Person) ClientsideSettings.getCurrentUser(),
					new AsyncCallback<Vector<Enrollment>>() {
						@Override
						public void onFailure(Throwable caught) {
						}

						@Override
						public void onSuccess(Vector<Enrollment> results) {
							enrollmentsToMeTable.setRowData(0, results);
							enrollmentsToMeTable.setRowCount(results.size(), true);
						}
					});
		} catch (Exception e) {
		}
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setFirstBreadcrumb(this, "Mein Bereich");
	}

	@Override
	public String returnTokenName() {
		return "Startseite";
	}
}
