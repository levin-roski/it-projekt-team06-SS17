package de.worketplace.team06.client.gui;

import java.util.Vector;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.DataLoading;
import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.shared.bo.Application;
import de.worketplace.team06.shared.bo.Call;
import de.worketplace.team06.shared.bo.Marketplace;
import de.worketplace.team06.shared.bo.Project;

public class Overview extends Page implements DataLoading {
	private WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
			.getWorketplaceAdministration();
	private final CellTable<Project> myProjectsTable = new CellTable<Project>();
	private final CellTable<Call> myCallsTable = new CellTable<Call>();
	private final CellTable<Application> myApplicationsTable = new CellTable<Application>();
	private final CellTable<Application> applicationsTable = new CellTable<Application>();

	public Overview() {
		// erstellen der Tabelle Meine Projekte

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
				// m1 = allMarketplaceSsm.getSelectedObject();
				// Page page = new SearchProject(m1);
				// RootPanel.get("Anzeige").clear();
				// RootPanel.get("Anzeige").add(page);
				Window.alert("Element geklickt");
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
		TextColumn<Project> projectsCounterColumn = new TextColumn<Project>() {
			@Override
			public String getValue(Project object) {
				return object.getDescription();
			}
		};
		myProjectsTable.addColumn(projectsCounterColumn, "Offene Ausschreibungen");

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
		myCallsTable.addColumn(callsCounterColumn, "Anzahl Projekte");

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

		VerticalPanel overviewVerticalPanel = new VerticalPanel();
		overviewVerticalPanel.add(new HTML("Mein Bereich"));
		overviewVerticalPanel.add(new HTML("Meine Projekte"));
		overviewVerticalPanel.add(myProjectsTable);
		overviewVerticalPanel.add(new HTML("Ausshreibungen von mir"));
		overviewVerticalPanel.add(myCallsTable);
		overviewVerticalPanel.add(new HTML("Bewerbungen von mir"));
		overviewVerticalPanel.add(myApplicationsTable);
		overviewVerticalPanel.add(new HTML("Mitarbeiter Beteiligungen an meinen Projekten"));
		overviewVerticalPanel.add(applicationsTable);
		overviewVerticalPanel.add(new HTML("Meine Projekt-Beteiligungen"));
		overviewVerticalPanel.add(myProjectApplicationsTable);

		this.add(overviewVerticalPanel);
		loadData();
	}

	@Override
	public void loadData() {
		final Vector<Call> myCalls = new Vector<Call>();
		final Vector<Application> applicationsToMe = new Vector<Application>();
		worketplaceAdministration.getProjectsForLeader(ClientsideSettings.getCurrentUser(), new AsyncCallback<Vector<Project>>() {
			@Override
			public void onFailure(Throwable caught) {
			}
			@Override
			public void onSuccess(Vector<Project> results) {
				myProjectsTable.setRowData(0, results);
				myProjectsTable.setRowCount(results.size(), true);
				for(Project project : results) {
					worketplaceAdministration.getCallsFor(project, new AsyncCallback<Vector<Call>>() {
						@Override
						public void onFailure(Throwable caught) {
						}
						@Override
						public void onSuccess(Vector<Call> results2) {
							myCalls.addAll(results2);
							for (Call call : results2) {
								worketplaceAdministration.getApplicationsFor(call, new AsyncCallback<Vector<Application>>() {
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
		worketplaceAdministration.getApplicationsFor(ClientsideSettings.getCurrentUser(), new AsyncCallback<Vector<Application>>() {
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
}
