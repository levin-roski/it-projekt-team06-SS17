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
import de.worketplace.team06.shared.bo.PartnerProfile;
import de.worketplace.team06.shared.bo.Property;

public class OrgaUnitPartnerProfileView extends View {
	// erstellen der Tabelle Projekte
	final CellTable<Property> propertyTable = new CellTable<Property>();
	PartnerProfile currentPartnerProfile;

	public OrgaUnitPartnerProfileView() {
		setBreadcrumb();
		worketplaceAdministration.getPartnerProfileFor(ClientsideSettings.getCurrentUser(), new AsyncCallback<PartnerProfile>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(PartnerProfile result) {
				currentPartnerProfile = result;
				loadData();
			}
		});
		final VerticalPanel root = new VerticalPanel();
		root.add(ClientsideSettings.getBreadcrumbs());
		root.setWidth("100%");
		root.add(createHeadline("Ihr Partnerprofil", true));

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Property> propertySsm = new SingleSelectionModel<Property>();

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		propertyTable.setSelectionModel(propertySsm);

		// hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der
		// Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		propertySsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				if (propertySsm.getSelectedObject() != null) {
					Property selectedProperty = propertySsm.getSelectedObject();
					mainPanel.setForm(new PropertyForm(selectedProperty, false, true, currentPartnerProfile));
					propertySsm.clear();
				}
			}
		});

		// hinzufügen der Tabellenspaltennamen sowie hinzufügen der zugehörigen
		// Daten aus der Datenbank
		TextColumn<Property> propertyTitleColumn = new TextColumn<Property>() {
			@Override
			public String getValue(Property object) {
				return object.getName();
			}
		};
		propertyTable.addColumn(propertyTitleColumn, "Eigenschaft");

		TextColumn<Property> propertyValueColumn = new TextColumn<Property>() {
			@Override
			public String getValue(Property object) {
				return object.getValue();
			}
		};
		propertyTable.addColumn(propertyValueColumn, "Wert");

		root.add(propertyTable);
		propertyTable.setWidth("100%", true);

		final Button newButton = new Button("Eigenschaft hinzufügen");
		newButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainPanel.setForm(new PropertyForm(null, false, true, currentPartnerProfile));
			}
		});
		root.add(newButton);

		this.add(root);
	}

	@Override
	public void loadData() {
		worketplaceAdministration.getAllPropertiesFor(currentPartnerProfile, new AsyncCallback<Vector<Property>>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Vector<Property> results) {
				propertyTable.setRowData(0, results);
				propertyTable.setRowCount(results.size(), true);
			}
		});
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setFirstBreadcrumb(this, "Mein Partnerprofil");
	}
	
	@Override
	public String returnTokenName() {
		return "Mein-Partnerprofil";
	}
}