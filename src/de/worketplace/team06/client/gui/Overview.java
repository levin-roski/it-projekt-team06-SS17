package de.worketplace.team06.client.gui;

import java.util.Vector;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.shared.bo.Application;
import de.worketplace.team06.shared.bo.Call;
import de.worketplace.team06.shared.bo.Marketplace;
import de.worketplace.team06.shared.bo.Project;

public class Overview extends Page {
	private WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
			.getWorketplaceAdministration();

	public void run() {
		
		//erstellen der Tabelle Meine Projekte	
		final CellTable<Project> myProjectsTable = new CellTable<Project>();
		
		//erstellen eines SingleSelectionModels -> macht, dass immer nur ein Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Project> myProjectsSsm = new SingleSelectionModel<Project>();
		
		//Das SingleSelectionModel wird der Tabelle Meine Marktplätze hinzugefügt
		myProjectsTable.setSelectionModel(myProjectsSsm);
		
		//hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		myProjectsSsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
//				m1 = allMarketplaceSsm.getSelectedObject();
//				Page page = new SearchProject(m1);
//				RootPanel.get("Anzeige").clear();
//				RootPanel.get("Anzeige").add(page);
				Window.alert("Element geklickt");
			}
		});


		//hinzufügen der Tabellenspaltennamen sowie hinzufügen der zugehörigen Daten aus der Datenbank
		TextColumn<Project> projectsTitleColumn = new TextColumn<Project>() {
			@Override
			public String getValue(Project object) {
				return object.getTitle();
			}
		};
		myProjectsTable.addColumn(projectsTitleColumn, "Name");

		//Muss eigentlich Int (bzw. Row counter) wiedergeben
		TextColumn<Project> projectsCounterColumn = new TextColumn<Project>() {
			@Override
			public String getValue(Project object) {
				return object.getDescription();
			}
		};
		myProjectsTable.addColumn(projectsCounterColumn, "Offene Ausschreibungen");
		
		myProjectsTable.setWidth("100%", true);
		
		
		//erstellen der Tabelle Ausschreibungen von mir	
		final CellTable<Call> myCallsTable = new CellTable<Call>();
		
		//erstellen eines SingleSelectionModels -> macht, dass immer nur ein Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Call> myCallsSsm = new SingleSelectionModel<Call>();
		
		//Das SingleSelectionModel wird der Tabelle Meine Marktplätze hinzugefügt
		myCallsTable.setSelectionModel(myCallsSsm);
		
		//hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		myCallsSsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
//				m1 = allMarketplaceSsm.getSelectedObject();
//				Page page = new SearchProject(m1);
//				RootPanel.get("Anzeige").clear();
//				RootPanel.get("Anzeige").add(page);
				Window.alert("Element geklickt");
			}
		});


		//hinzufügen der Tabellenspaltennamen sowie hinzufügen der zugehörigen Daten aus der Datenbank
		TextColumn<Call> projectsAndCallsColumn = new TextColumn<Call>() {
			@Override
			public String getValue(Call object) {
				return object.getTitle();
			}
		};
		myCallsTable.addColumn(projectsAndCallsColumn, "Projekt & Ausschreibung");

		//Muss eigentlich Int (bzw. Row counter) wiedergeben
		TextColumn<Call> callsCounterColumn = new TextColumn<Call>() {
			@Override
			public String getValue(Call object) {
				return object.getDescription();
			}
		};
		myCallsTable.addColumn(callsCounterColumn, "Anzahl Projekte");
		
		myCallsTable.setWidth("100%", true);
		
		
		//erstellen der Tabelle Bewerbungen von mir	
		final CellTable<Application> myApplicationsTable = new CellTable<Application>();
		
		//erstellen eines SingleSelectionModels -> macht, dass immer nur ein Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Application> myApplicationsSsm = new SingleSelectionModel<Application>();
		
		//Das SingleSelectionModel wird der Tabelle Meine Marktplätze hinzugefügt
		myApplicationsTable.setSelectionModel(myApplicationsSsm);
		
		//hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		myApplicationsSsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
//				m1 = allMarketplaceSsm.getSelectedObject();
//				Page page = new SearchProject(m1);
//				RootPanel.get("Anzeige").clear();
//				RootPanel.get("Anzeige").add(page);
				Window.alert("Element geklickt");
			}
		});


		//hinzufügen der Tabellenspaltennamen sowie hinzufügen der zugehörigen Daten aus der Datenbank
		TextColumn<Application> projectsAndRoleColumn = new TextColumn<Application>() {
			@Override
			public String getValue(Application object) {
				return object.getText();
			}
		};
		myApplicationsTable.addColumn(projectsAndRoleColumn, "Projekt & Rolle");

		//Muss eigentlich Int (bzw. Row counter) wiedergeben
		TextColumn<Application> applicationsCounterColumn = new TextColumn<Application>() {
			@Override
			public String getValue(Application object) {
				return object.getStatusString();
			}
		};
		myApplicationsTable.addColumn(applicationsCounterColumn, "Bewertung");
		
		myApplicationsTable.setWidth("100%", true);
		
		
		//erstellen der Tabelle Mitarbeiter-Beteiligungen an meinen Projekten	
		final CellTable<Application> applicationsTable = new CellTable<Application>();
		
		//erstellen eines SingleSelectionModels -> macht, dass immer nur ein Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Application> applicationsSsm = new SingleSelectionModel<Application>();
		
		//Das SingleSelectionModel wird der Tabelle Meine Marktplätze hinzugefügt
		applicationsTable.setSelectionModel(applicationsSsm);
		
		//hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		applicationsSsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
//				m1 = allMarketplaceSsm.getSelectedObject();
//				Page page = new SearchProject(m1);
//				RootPanel.get("Anzeige").clear();
//				RootPanel.get("Anzeige").add(page);
				Window.alert("Element geklickt");
			}
		});


		applicationsTable.setWidth("100%", true);
		
		
		//erstellen der Tabelle Meine Projekt-Beteiligungen	
		final CellTable<Application> myProjectApplicationsTable = new CellTable<Application>();
		
		//erstellen eines SingleSelectionModels -> macht, dass immer nur ein Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Application> myProjectApplicationsSsm = new SingleSelectionModel<Application>();
		
		//Das SingleSelectionModel wird der Tabelle Meine Marktplätze hinzugefügt
		applicationsTable.setSelectionModel(myProjectApplicationsSsm);
		
		//hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		myProjectApplicationsSsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
//				m1 = allMarketplaceSsm.getSelectedObject();
//				Page page = new SearchProject(m1);
//				RootPanel.get("Anzeige").clear();
//				RootPanel.get("Anzeige").add(page);
				Window.alert("Element geklickt");
			}
		});


		myProjectApplicationsTable.setWidth("100%", true);


//		worketplaceAdministration.getAllMarketplaces(new AsyncCallback<Vector<Marketplace>>() {
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("Hat nicht funtioniert!");
//			}
//			
//			public void onSuccess(Vector<Marketplace> results) {
//				myMarketplacesTable.setRowData(0, results);
//				myMarketplacesTable.setRowCount(results.size(), true);
//				Window.alert("Funktioniert!");
//			}
//		});
		
		
		VerticalPanel overviewVerticalPanel = new VerticalPanel();
		overviewVerticalPanel.add(new HTML ("Mein Bereich"));
		overviewVerticalPanel.add(new HTML ("Meine Projekte"));
		overviewVerticalPanel.add(myProjectsTable);
		overviewVerticalPanel.add(new HTML ("Ausshreibungen von mir"));
		overviewVerticalPanel.add(myCallsTable);
		overviewVerticalPanel.add(new HTML ("Bewerbungen von mir"));
		overviewVerticalPanel.add(myApplicationsTable);
		overviewVerticalPanel.add(new HTML ("Mitarbeiter Beteiligungen an meinen Projekten"));
		overviewVerticalPanel.add(applicationsTable);
		overviewVerticalPanel.add(new HTML ("Meine Projekt-Beteiligungen"));
		overviewVerticalPanel.add(myProjectApplicationsTable);
		

		

		RootPanel.get("content").add(overviewVerticalPanel);
	}
	
}
