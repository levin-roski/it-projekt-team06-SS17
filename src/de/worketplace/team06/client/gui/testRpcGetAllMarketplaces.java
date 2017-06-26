package de.worketplace.team06.client.gui;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.View;
import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.shared.bo.Marketplace;

public class testRpcGetAllMarketplaces extends View {
	public testRpcGetAllMarketplaces () {
		final Label searchMarketplace = new Label("Alle Marktplätze");

		// Create a Flex Table
		final FlexTable flexTable = new FlexTable();
		FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
		flexTable.addStyleName("flexTable");
		flexTable.setWidth("100%");
		flexTable.setCellSpacing(5);
		flexTable.setCellPadding(3);

		// Header-Zeile der Tabelle festlegen.
		flexTable.setText(0, 0, "Name");
		flexTable.setText(0, 1, "Inhaber");
		flexTable.setText(0, 2, "Beschreibung");

		// Callback um alle Nutzer abzurufen
		worketplaceAdministration.getAllMarketplaces(new AsyncCallback<Vector<Marketplace>>() {
			public void onFailure(Throwable caught) {
//				TODO Fehler ausgeben
			}
			public void onSuccess(Vector<Marketplace> results) {
				int row = flexTable.getRowCount();
				for (Marketplace n : results) {
					row++;
					final String MarketplaceID = String.valueOf(n.getID());
					flexTable.setText(row, 0, n.getTitle());
//					flexTable.setText(row, 1, n.getOrgaUnitID());
					flexTable.setText(row, 2, n.getDescription());
				}
			}
		});
		final VerticalPanel root = new VerticalPanel();
		root.add(flexTable);
		
		this.add(root);
	}

	@Override
	public void setBreadcrumb() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadData() {
		// TODO Auto-generated method stub
		
	}
}
