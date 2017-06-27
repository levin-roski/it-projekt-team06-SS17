package de.worketplace.team06.client.gui;

import java.util.Vector;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.View;
import de.worketplace.team06.shared.bo.Application;
import de.worketplace.team06.shared.bo.Call;
import de.worketplace.team06.shared.bo.Project;

public class MyOverview extends View {
	private final CellTable<Project> myProjectsTable = new CellTable<Project>();
	private final CellTable<Call> myCallsTable = new CellTable<Call>();
	private final CellTable<Application> myApplicationsTable = new CellTable<Application>();
	private final CellTable<Application> applicationsTable = new CellTable<Application>();

	public MyOverview() {
		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Project> myProjectsSsm = new SingleSelectionModel<Project>();

		// Das SingleSelectionModel wird der Tabelle Meine Marktplätze
		// hinzugefügt
		myProjectsTable.setSelectionModel(myProjectsSsm);

		// hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der
		// Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		myProjectsSsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				if (myProjectsSsm.getSelectedObject() != null) {
					Project selectedProject = myProjectsSsm.getSelectedObject();
					mainPanel.setForm(new ProjectForm(selectedProject, false, true, null, null, null));
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

		// Muss eigentlich Int (bzw. Row counter) wiedergeben
//		TextColumn<Project> projectsCounterColumn = new TextColumn<Project>() {
//			@Override
//			public String getValue(Project object) {
//				// TODO Anzahl offene Ausschreibungen
//				return object.getDescription();
//			}
//		};
//		myProjectsTable.addColumn(projectsCounterColumn, "Anzahl Offene Ausschreibungen");

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
				return simpleDateFormat.format(object.getStartDate());
			}
		};
		myProjectsTable.addColumn(projectsStartColumn, "Start");

		TextColumn<Project> projectsEndColumn = new TextColumn<Project>() {
			@Override
			public String getValue(Project object) {
				return simpleDateFormat.format(object.getEndDate());
			}
		};
		myProjectsTable.addColumn(projectsEndColumn, "Ende");

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
				// m1 = allMarketplaceSsm.getSelectedObject();
				// Page page = new SearchProject(m1);
				// RootPanel.get("Anzeige").clear();
				// RootPanel.get("Anzeige").add(page);
				Window.alert("Element geklickt");
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
		myCallsTable.addColumn(projectsAndCallsColumn, "Projekt & Ausschreibung");

		// Muss eigentlich Int (bzw. Row counter) wiedergeben
		TextColumn<Call> callsCounterColumn = new TextColumn<Call>() {
			@Override
			public String getValue(Call object) {
				return object.getDescription();
			}
		};
		myCallsTable.addColumn(callsCounterColumn, "Anzahl Bewerbungen");

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
				return String.valueOf(object.getDeadline());
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
				// m1 = allMarketplaceSsm.getSelectedObject();
				// Page page = new SearchProject(m1);
				// RootPanel.get("Anzeige").clear();
				// RootPanel.get("Anzeige").add(page);
				Window.alert("Element geklickt");
			}
		});

		// hinzufügen der Tabellenspaltennamen sowie hinzufügen der zugehörigen
		// Daten aus der Datenbank
		TextColumn<Application> projectsAndRoleColumn = new TextColumn<Application>() {
			@Override
			public String getValue(Application object) {
				return object.getText();
			}
		};
		myApplicationsTable.addColumn(projectsAndRoleColumn, "Projekt & Rolle");

		// Muss eigentlich Int (bzw. Row counter) wiedergeben
		TextColumn<Application> applicationsCounterColumn = new TextColumn<Application>() {
			@Override
			public String getValue(Application object) {
				return object.getStatusString();
			}
		};
		myApplicationsTable.addColumn(applicationsCounterColumn, "Bewertung");

		myApplicationsTable.setWidth("100%", true);

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Application> applicationsSsm = new SingleSelectionModel<Application>();

		// Das SingleSelectionModel wird der Tabelle Meine Marktplätze
		// hinzugefügt
		applicationsTable.setSelectionModel(applicationsSsm);

		// hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der
		// Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		applicationsSsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// m1 = allMarketplaceSsm.getSelectedObject();
				// Page page = new SearchProject(m1);
				// RootPanel.get("Anzeige").clear();
				// RootPanel.get("Anzeige").add(page);
				Window.alert("Element geklickt");
			}
		});

		applicationsTable.setWidth("100%", true);

		// erstellen der Tabelle Meine Projekt-Beteiligungen
		final CellTable<Application> myProjectApplicationsTable = new CellTable<Application>();

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Application> myProjectApplicationsSsm = new SingleSelectionModel<Application>();

		// Das SingleSelectionModel wird der Tabelle Meine Marktplätze
		// hinzugefügt
		applicationsTable.setSelectionModel(myProjectApplicationsSsm);

		// hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der
		// Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		myProjectApplicationsSsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// m1 = allMarketplaceSsm.getSelectedObject();
				// Page page = new SearchProject(m1);
				// RootPanel.get("Anzeige").clear();
				// RootPanel.get("Anzeige").add(page);
				Window.alert("Element geklickt");
			}
		});

		myProjectApplicationsTable.setWidth("100%", true);

		// worketplaceAdministration.getAllMarketplaces(new
		// AsyncCallback<Vector<Marketplace>>() {
		// @Override
		// public void onFailure(Throwable caught) {
		// Window.alert("Hat nicht funtioniert!");
		// }
		//
		// public void onSuccess(Vector<Marketplace> results) {
		// myMarketplacesTable.setRowData(0, results);
		// myMarketplacesTable.setRowCount(results.size(), true);
		// Window.alert("Funktioniert!");
		// }
		// });

		VerticalPanel root = new VerticalPanel();
		root.add(ClientsideSettings.getBreadcrumbs());
		root.add(createHeadline("Mein Bereich", true));
		root.add(createSecondHeadline("Meine Projekte"));
		root.add(myProjectsTable);
		root.add(createSecondHeadline("Ausshreibungen von mir"));
		root.add(myCallsTable);
		root.add(createSecondHeadline("Bewerbungen von mir"));
		root.add(myApplicationsTable);
		root.add(createSecondHeadline("Mitarbeiter-Beteiligungen an meinen Projekten"));
		root.add(applicationsTable);
		root.add(createSecondHeadline("Meine Projekt-Beteiligungen"));
		root.add(myProjectApplicationsTable);

		this.add(root);
		loadData();
	}

	@Override
	public void loadData() {
		final Vector<Call> myCalls = new Vector<Call>();
		final Vector<Application> applicationsToMe = new Vector<Application>();
		worketplaceAdministration.getProjectsForLeader(ClientsideSettings.getCurrentUser(),
				new AsyncCallback<Vector<Project>>() {
					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(Vector<Project> results) {
						myProjectsTable.setRowData(0, results);
						myProjectsTable.setRowCount(results.size(), true);
						for (Project project : results) {
							worketplaceAdministration.getCallsFor(project, new AsyncCallback<Vector<Call>>() {
								@Override
								public void onFailure(Throwable caught) {
								}

								@Override
								public void onSuccess(Vector<Call> results2) {
									myCalls.addAll(results2);
									for (Call call : results2) {
										worketplaceAdministration.getApplicationsFor(call,
												new AsyncCallback<Vector<Application>>() {
													@Override
													public void onFailure(Throwable caught) {
													}

													@Override
													public void onSuccess(Vector<Application> results3) {
														applicationsToMe.addAll(results3);
													}
												});
									}
								}
							});
						}
					}
				});
		myCallsTable.setRowData(0, myCalls);
		myCallsTable.setRowCount(myCalls.size(), true);
		applicationsTable.setRowData(0, applicationsToMe);
		applicationsTable.setRowCount(applicationsToMe.size(), true);
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
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setFirstBreadcrumb(this, "Mein Bereich");
	}
}
