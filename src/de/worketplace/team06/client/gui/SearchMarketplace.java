package de.worketplace.team06.client.gui;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.shared.bo.Marketplace;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class SearchMarketplace {
//	private WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
//			.getWorketplaceAdministration();

	private static class Marketplace {
		private final String title;
		private final String owner;
		private final String description;
		
		public Marketplace(String title, String owner, String description) {
			this.title = title;
			this.owner = owner;
			this.description = description;
		}
	}
	
	@SuppressWarnings("deprcation")
	private static final List<Marketplace> MARKETPLACE = Arrays.asList(
			new Marketplace("Stuttgart", "Patrick Strepp", "Hier steht eine Beschreibung des Marktplatzes Stuttgart"),
			new Marketplace("Karlsruhe", "Dominik Florschütz", "Hier steht eine Beschreibung des Marktplatzes Karlsruhe"),
			new Marketplace("Heilbronn", "Tobias Müller", "Hier steht eine Beschreibung des Marktplatzes Heilbronn"));
	
	public void onModuleLoad() {
		final CellTable<Marketplace> table = new CellTable<Marketplace>();
		table.setPageSize(3);
		
		TextColumn<Marketplace> titleColumn = new TextColumn<Marketplace>() {
			@Override
			public String getValue(Marketplace object) {
				return object.title;
			}
		};
		table.addColumn(titleColumn, "Name");
		
		TextColumn<Marketplace> ownerColumn = new TextColumn<Marketplace>() {
			@Override
			public String getValue(Marketplace object) {
				return object.owner;
			}
		};
		table.addColumn(titleColumn, "Inhaber");
		
		TextColumn<Marketplace> descriptionColumn = new TextColumn<Marketplace>() {
			@Override
			public String getValue(Marketplace object) {
				return object.description;
			}
		};
		table.addColumn(titleColumn, "Beschreibung");
		
		
		
		
//		// Callback um alle Nutzer abzurufen
//		worketplaceAdministration.getAllMarketplaces(new AsyncCallback<Vector<Marketplace>>() {
//			public void onFailure(Throwable caught) {
////				TODO Fehler ausgeben
//			}
//			public void onSuccess(Vector<Marketplace> results) {
//				int row = flexTable.getRowCount();
//				for (Marketplace n : results) {
//					row++;
//					final String MarketplaceID = String.valueOf(n.getID());
//					flexTable.setText(row, 0, n.getTitle());
////					flexTable.setText(row, 1, n.getOrgaUnitID());
//					flexTable.setText(row, 2, n.getDescription());
//				}
//			}
//		});

		RootPanel.get("content").add(CellTable<Marketplace>);
	}
}