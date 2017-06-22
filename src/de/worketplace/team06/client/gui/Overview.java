package de.worketplace.team06.client.gui;

import java.util.Vector;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.shared.bo.Marketplace;

public class Overview extends Page {
	private WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
			.getWorketplaceAdministration();

	public void run() {
		//erstellen der Tabelle Meine Marktplätze	
		final CellTable<Marketplace> myMarketplacesTable = new CellTable<Marketplace>();
		
		final SingleSelectionModel<Marketplace> myMarketplacesSsm = new SingleSelectionModel<Marketplace>();

		myMarketplacesTable.setSelectionModel(myMarketplacesSsm);

		myMarketplacesSsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
//				m1 = allMarketplaceSsm.getSelectedObject();
//				Page page = new SearchProject(m1);
//				RootPanel.get("Anzeige").clear();
//				RootPanel.get("Anzeige").add(page);
				Window.alert("Element geklickt");
			}
		});

//		final CellTable<Marketplace> table = new CellTable<Marketplace>();

		TextColumn<Marketplace> marketplacesTitleColumn = new TextColumn<Marketplace>() {
			@Override
			public String getValue(Marketplace object) {
				return object.getTitle();
			}
		};
		myMarketplacesTable.addColumn(marketplacesTitleColumn, "Name");

		//Muss eigentlich Integer (bzw. Row counter) wiedergeben
		TextColumn<Marketplace> marketplacesCounterColumn = new TextColumn<Marketplace>() {
			@Override
			public String getValue(Marketplace object) {
				return object.getDescription();
			}
		};
		myMarketplacesTable.addColumn(marketplacesCounterColumn, "Anzahl Projekte");
		
		myMarketplacesTable.setWidth("100%", true);
		
		//erstellen der Tabelle Meine Projekte	
		final CellTable<Marketplace> myProjectsTable = new CellTable<Marketplace>();
		
		final SingleSelectionModel<Marketplace> myProjectsSsm = new SingleSelectionModel<Marketplace>();

		myProjectsTable.setSelectionModel(myProjectsSsm);

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

//		final CellTable<Marketplace> table = new CellTable<Marketplace>();

		TextColumn<Marketplace> projectsTitleColumn = new TextColumn<Marketplace>() {
			@Override
			public String getValue(Marketplace object) {
				return object.getTitle();
			}
		};
		myProjectsTable.addColumn(projectsTitleColumn, "Name");

		//Muss eigentlich Int (bzw. Row counter) wiedergeben
		TextColumn<Marketplace> projectsCounterColumn = new TextColumn<Marketplace>() {
			@Override
			public String getValue(Marketplace object) {
				return object.getDescription();
			}
		};
		myProjectsTable.addColumn(projectsCounterColumn, "Anzahl Projekte");
		
		myProjectsTable.setWidth("100%", true);


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
		
		
		// overviewPanel1 erstellt ein Panel dem 2 Tabellen zugeordnet sind die bei 50% gesplittet sind
//		Widget myMarketplacesTable = new Widget();
//		Widget myProjectsTable = new Widget();
		
		LayoutPanel overviewPanel1 = new LayoutPanel();
	
		overviewPanel1.add(createHeadline("Meine Marktplätze"));
		overviewPanel1.add(myMarketplacesTable);
		overviewPanel1.add(createHeadline("Meine Projekte"));
		overviewPanel1.add(myProjectsTable);
	
		overviewPanel1.setWidgetLeftWidth(myMarketplacesTable, 0, Unit.PCT, 50, Unit.PCT);
		overviewPanel1.setWidgetRightWidth(myProjectsTable, 0, Unit.PCT, 50, Unit.PCT);
		overviewPanel1.add(createHeadline("Alle Marktplätze"));
		

		RootPanel.get("content").add(overviewPanel1);
	}
	
}
