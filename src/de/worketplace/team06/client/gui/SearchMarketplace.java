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

	// private static class Marketplace {
	// private final String title;
	// private final String owner;
	// private final String description;
	//
	// public Marketplace(String title, String owner, String description) {
	// this.title = title;
	// this.owner = owner;
	// this.description = description;
	// }
	// }
	//
	// private static final List<Marketplace> MARKETPLACE = Arrays.asList(
	// new Marketplace("Stuttgart", "Patrick Strepp", "Hier steht eine
	// Beschreibung des Marktplatzes Stuttgart"),
	// new Marketplace("Karlsruhe", "Dominik Florschütz", "Hier steht eine
	// Beschreibung des Marktplatzes Karlsruhe"),
	// new Marketplace("Heilbronn", "Tobias Müller", "Hier steht eine
	// Beschreibung des Marktplatzes Heilbronn"));

	final CellTable<Marketplace> allMarketplacesTable = new CellTable<Marketplace>();

	public void run() {

//		allMarketplacesTable.setPageSize(3);
		
		final SingleSelectionModel<Marketplace> allMarketplaceSsm = new SingleSelectionModel<Marketplace>();

		allMarketplacesTable.setSelectionModel(allMarketplaceSsm);

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

		final CellTable<Marketplace> table = new CellTable<Marketplace>();

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
//		allMarketplacesTable.setRowData(MARKETPLACE);
		final VerticalPanel root = new VerticalPanel();
		root.add(createHeadline("Alle Marktplätze"));
		root.add(allMarketplacesTable);

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

	RootPanel.get("content").add(root);
}}