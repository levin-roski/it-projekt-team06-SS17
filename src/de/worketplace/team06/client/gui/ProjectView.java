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
import de.worketplace.team06.shared.bo.Project;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ProjectView extends Page {
	private WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
			.getWorketplaceAdministration();

	// erstellen der Tabelle Meine Marktplätze
	final CellTable<Project> allProjectsTable = new CellTable<Project>();

	public ProjectView() {

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Project> allProjectsSsm = new SingleSelectionModel<Project>();

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		allProjectsTable.setSelectionModel(allProjectsSsm);

		// hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der
		// Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		allProjectsSsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
//				Marketplace m1 = allProjectsSsm.getSelectedObject();
//				ClientsideSettings.getMainPanel().setItem(new MarketplaceForm(m1, false, true));
			}
		});


		TextColumn<Project> projectsTitleColumn = new TextColumn<Project>() {
			@Override
			public String getValue(Project object) {
				return object.getTitle();
			}
		};
		allProjectsTable.addColumn(projectsTitleColumn, "Name");

		// TextColumn<Project> ownerColumn = new TextColumn<Project>() {
		// @Override
		// public String getValue(Project object) {
		// return object.getOrgaUnitID();
		// }
		// };
		// allMarketplacesTable.addColumn(ownerColumn, "Inhaber");

		TextColumn<Project> descriptionColumn = new TextColumn<Project>() {
			@Override
			public String getValue(Project object) {
				return object.getDescription();
			}
		};
		allProjectsTable.addColumn(descriptionColumn, "Beschreibung");

		allProjectsTable.setWidth("100%", true);
		// allMarketplacesTable.setRowData(MARKETPLACE);
		final VerticalPanel root = new VerticalPanel();
		root.add(createHeadline("Alle Marktplätze", true));
		root.add(allProjectsTable);

		final Button newButton = new Button("Neues Projekt hinzufügen");
		newButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				MainPanel tmpMainPanel = ClientsideSettings.getMainPanel();
				tmpMainPanel.setItem(new MarketplaceForm(null, false, true));
			}
		});
		root.add(newButton);

		worketplaceAdministration.getAllProjects (new AsyncCallback<Vector<Project>>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO
			}

			public void onSuccess(Vector<Project> results) {
				allProjectsTable.setRowData(0, results);
				allProjectsTable.setRowCount(results.size(), true);
			}
		});

		this.add(root);
	}
}
