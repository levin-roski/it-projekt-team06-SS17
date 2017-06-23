package de.worketplace.team06.client.gui;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.shared.bo.Marketplace;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SearchMarketplace extends Page {

	private WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
			.getWorketplaceAdministration();

	public void run() {
		//erstellen der Tabelle Meine Marktplätze
		final CellTable<Marketplace> allMarketplacesTable = new CellTable<Marketplace>();
		
		//erstellen eines SingleSelectionModels -> macht, dass immer nur ein Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Marketplace> allMarketplaceSsm = new SingleSelectionModel<Marketplace>();

		//Das SingleSelectionModel wird der Tabelle Meine Marktplätze hinzugefügt
		allMarketplacesTable.setSelectionModel(allMarketplaceSsm);

		//hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		allMarketplaceSsm.addSelectionChangeHandler(new Handler() {
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

		TextColumn<Marketplace> titleColumn = new TextColumn<Marketplace>() {
			@Override
			public String getValue(Marketplace object) {
				return object.getTitle();
			}
		};
		allMarketplacesTable.addColumn(titleColumn, "Name");

//		TextColumn<Marketplace> ownerColumn = new TextColumn<Marketplace>() {
//			@Override
//			public String getValue(Marketplace object) {
//				return object.getOrgaUnitID();
//			}
//		};
//		allMarketplacesTable.addColumn(ownerColumn, "Inhaber");

		TextColumn<Marketplace> descriptionColumn = new TextColumn<Marketplace>() {
			@Override
			public String getValue(Marketplace object) {
				return object.getDescription();
			}
		};
		allMarketplacesTable.addColumn(descriptionColumn, "Beschreibung");
		
		allMarketplacesTable.setWidth("100%", true);
		final VerticalPanel allMarketplacesPanel = new VerticalPanel();
		allMarketplacesPanel.add(createHeadline("Alle Marktplätze"));
		allMarketplacesPanel.add(allMarketplacesTable);

		worketplaceAdministration.getAllMarketplaces(new AsyncCallback<Vector<Marketplace>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Hat nicht funtioniert!");
			}
			
			public void onSuccess(Vector<Marketplace> results) {
				allMarketplacesTable.setRowData(0, results);
				allMarketplacesTable.setRowCount(results.size(), true);
				Window.alert("Funktioniert!");
			}
		});

	RootPanel.get("content").add(allMarketplacesPanel);
}}