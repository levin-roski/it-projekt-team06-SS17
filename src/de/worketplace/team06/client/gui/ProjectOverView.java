package de.worketplace.team06.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.View;
import de.worketplace.team06.shared.bo.Project;

public class ProjectOverView extends View {
	// erstellen der Tabelle Meine Projekte
	private final CellTable<Project> allProjectsTable = new CellTable<Project>();

	public ProjectOverView() {
		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Project> AllProjectSsm = new SingleSelectionModel<Project>();

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		allProjectsTable.setSelectionModel(AllProjectSsm);

		// hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der
		// Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		AllProjectSsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				if (AllProjectSsm.getSelectedObject() != null) {
					Project selectedProject = AllProjectSsm.getSelectedObject();
					ClientsideSettings.setCurrentProjectId(selectedProject.getID());
					History.newItem("Projekt-Details" + selectedProject.getID() + "-"
							+ ClientsideSettings.getCurrentMarketplaceId());
					AllProjectSsm.clear();
				}
			}
		});

		TextColumn<Project> titleColumn = new TextColumn<Project>() {
			@Override
			public String getValue(Project object) {
				return object.getTitle();
			}
		};
		allProjectsTable.addColumn(titleColumn, "Name");

		TextColumn<Project> descriptionColumn = new TextColumn<Project>() {
			@Override
			public String getValue(Project object) {
				return object.getDescription();
			}
		};
		allProjectsTable.addColumn(descriptionColumn, "Beschreibung");

		allProjectsTable.setWidth("100%", true);
		final VerticalPanel root = new VerticalPanel();
		root.add(ClientsideSettings.getBreadcrumbs());
		root.add(createHeadline("Alle Projekte", true));
		root.add(allProjectsTable);

		final Button newButton = new Button("Projekt hinzufügen");
		newButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainPanel.setForm(new ProjectForm(null, false));
			}
		});
		root.add(newButton);

		this.add(root);

		loadData();
	}

	@Override
	public void loadData() {
		worketplaceAdministration.getAllProjects(new AsyncCallback<Vector<Project>>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Vector<Project> results) {
				allProjectsTable.setRowData(0, results);
				allProjectsTable.setRowCount(results.size(), true);
			}
		});
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setFirstBreadcrumb(this, "Projekte");
	}

	@Override
	public String returnTokenName() {
		return "Projekte";
	}
}
