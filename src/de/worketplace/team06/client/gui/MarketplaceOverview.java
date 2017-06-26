package de.worketplace.team06.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.View;
import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.shared.bo.Marketplace;

public class MarketplaceOverview extends View {
	private WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
			.getWorketplaceAdministration();
	// erstellen der Tabelle Meine Marktplätze
	private final CellTable<Marketplace> allMarketplacesTable = new CellTable<Marketplace>();

	public MarketplaceOverview() {
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
				Marketplace currentMarketplace = allMarketplaceSsm.getSelectedObject();
				ClientsideSettings.setCurrentMarketplaceId(currentMarketplace.getID());
				mainPanel.setView(new MarketplaceView(currentMarketplace));
			}
		});

		TextColumn<Marketplace> titleColumn = new TextColumn<Marketplace>() {
			@Override
			public String getValue(Marketplace object) {
				return object.getTitle();
			}
		};
		allMarketplacesTable.addColumn(titleColumn, "Name");

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
		root.add(ClientsideSettings.getBreadcrumbs());
		root.add(createHeadline("Alle Marktplätze", true));
		root.add(allMarketplacesTable);

		final Button newButton = new Button("Neuen Marktplatz hinzufügen");
		newButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainPanel.setForm(new MarketplaceForm(null, false, true, null, null));
			}
		});
		root.add(newButton);

		this.add(root);

		loadData();
	}

	@Override
	public void loadData() {
		worketplaceAdministration.getAllMarketplaces(new AsyncCallback<Vector<Marketplace>>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Vector<Marketplace> results) {
				allMarketplacesTable.setRowData(0, results);
				allMarketplacesTable.setRowCount(results.size(), true);
			}
		});
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setFirstBreadcrumb(this, "Marktplätze");		
	}
}
