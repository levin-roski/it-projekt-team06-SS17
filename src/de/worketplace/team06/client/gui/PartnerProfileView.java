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

import de.worketplace.team06.client.Callback;
import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.View;
import de.worketplace.team06.shared.bo.PartnerProfile;
import de.worketplace.team06.shared.bo.Property;

public class PartnerProfileView extends View {
	// erstellen der Tabelle Projekte
	final CellTable<Property> propertyTable = new CellTable<Property>();
	PartnerProfile currentPartnerProfile;

	public PartnerProfileView(PartnerProfile pCurrentPartnerProfile) {
		super();
		
		currentPartnerProfile = pCurrentPartnerProfile;
		final VerticalPanel root = new VerticalPanel();
		root.add(ClientsideSettings.getBreadcrumbs());
		root.setWidth("100%");
		root.add(createHeadline("Marktplatz-Details", true));

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
				Property selectedProperty = propertySsm.getSelectedObject();
				mainPanel.setView(new PropertyForm(selectedProperty));
			}
		});

		// hinzufügen der Tabellenspaltennamen sowie hinzufügen der zugehörigen
		// Daten aus der Datenbank
		TextColumn<Property> projectsTitleColumn = new TextColumn<Property>() {
			@Override
			public String getValue(Property object) {
				return object.getTitle();
			}
		};
		propertyTable.addColumn(projectsTitleColumn, "Name");

		// Muss eigentlich Int (bzw. Row counter) wiedergeben
		// TextColumn<Property> projectsCounterColumn = new TextColumn<Property>()
		// {
		// @Override
		// public String getValue(Property object) {
		// // TODO Anzahl offene Ausschreibungen
		// return object.getDescription();
		// }
		// };
		// projectTable.addColumn(projectsCounterColumn, "Anzahl Offene
		// Ausschreibungen");

		TextColumn<Property> projectsDescriptionColumn = new TextColumn<Property>() {
			@Override
			public String getValue(Property object) {
				return object.getDescription();
			}
		};
		propertyTable.addColumn(projectsDescriptionColumn, "Beschreibung");

		TextColumn<Property> projectsStartColumn = new TextColumn<Property>() {
			@Override
			public String getValue(Property object) {
				return simpleDateFormat.format(object.getStartDate());
			}
		};
		propertyTable.addColumn(projectsStartColumn, "Start");

		TextColumn<Property> projectsEndColumn = new TextColumn<Property>() {
			@Override
			public String getValue(Property object) {
				return simpleDateFormat.format(object.getEndDate());
			}
		};
		propertyTable.addColumn(projectsEndColumn, "Ende");

		root.add(createSecondHeadline("Projekte auf diesem Marktplatz"));
		root.add(propertyTable);
		propertyTable.setWidth("100%", true);

		final Button newButton = new Button("Neues Projekt hinzufügen");
		newButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainPanel.setForm(new PropertyForm(null, false, true, null, null, currentPartnerProfile));
			}
		});
		root.add(newButton);

		this.add(root);
		loadData();
	}

	@Override
	public void loadData() {
		worketplaceAdministration.getPropertysFor(currentPartnerProfile, new AsyncCallback<Vector<Property>>() {
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
		ClientsideSettings.setSecondBreadcrumb(this, "Marktplatz-Details");
	}
}