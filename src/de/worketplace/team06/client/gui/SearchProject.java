package de.worketplace.team06.client.gui;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.shared.bo.Marketplace;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SearchProject extends Page {
	private WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
			.getWorketplaceAdministration();

	// erstellen der Tabelle Meine Marktplätze
	final CellTable<Marketplace> allMarketplacesTable = new CellTable<Marketplace>();

	public SearchProject() {

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Marketplace> allMarketplaceSsm = new SingleSelectionModel<Marketplace>();

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		allMarketplacesTable.setSelectionModel(allMarketplaceSsm);

		// hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der
		// Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		allMarketplaceSsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				Marketplace m1 = allMarketplaceSsm.getSelectedObject();
				ClientsideSettings.getMainPanel().setItem(new MarketplaceForm(m1, false, true));
			}
		});

		// final CellTable<Marketplace> table = new CellTable<Marketplace>();

		TextColumn<Marketplace> titleColumn = new TextColumn<Marketplace>() {
			@Override
			public String getValue(Marketplace object) {
				return object.getTitle();
			}
		};
		allMarketplacesTable.addColumn(titleColumn, "Name");

		// TextColumn<Marketplace> ownerColumn = new TextColumn<Marketplace>() {
		// @Override
		// public String getValue(Marketplace object) {
		// return object.getOrgaUnitID();
		// }
		// };
		// allMarketplacesTable.addColumn(ownerColumn, "Inhaber");

		TextColumn<Marketplace> descriptionColumn = new TextColumn<Marketplace>() {
			@Override
			public String getValue(Marketplace object) {
				return object.getDescription();
			}
		};
		allMarketplacesTable.addColumn(descriptionColumn, "Beschreibung");

		allMarketplacesTable.setWidth("100%", true);
		// allMarketplacesTable.setRowData(MARKETPLACE);
		final VerticalPanel root = new VerticalPanel();
		root.add(createHeadline("Alle Marktplätze", true));
		root.add(allMarketplacesTable);

		final Button newButton = new Button("Neuen Marktplatz hinzufügen");
		newButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				MainPanel tmpMainPanel = ClientsideSettings.getMainPanel();
				tmpMainPanel.setItem(new MarketplaceForm(null, false, true));
			}
		});
		root.add(newButton);

		worketplaceAdministration.getAllMarketplaces(new AsyncCallback<Vector<Marketplace>>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO
			}

			public void onSuccess(Vector<Marketplace> results) {
				allMarketplacesTable.setRowData(0, results);
				allMarketplacesTable.setRowCount(results.size(), true);
			}
		});

		this.add(root);
	}
}
