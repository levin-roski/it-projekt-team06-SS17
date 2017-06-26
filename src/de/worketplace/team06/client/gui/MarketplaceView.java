package de.worketplace.team06.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.worketplace.team06.client.Callback;
import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.View;
import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.shared.bo.Marketplace;
import de.worketplace.team06.shared.bo.Project;

public class MarketplaceView extends View {
	private WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
			.getWorketplaceAdministration();
	// erstellen der Tabelle Projekte
	final CellTable<Project> projectTable = new CellTable<Project>();
	Marketplace currentMarketplace;

	public MarketplaceView(Marketplace pCurrentMarketplace) {
		super();
		currentMarketplace = pCurrentMarketplace;
		final VerticalPanel root = new VerticalPanel();
		root.add(ClientsideSettings.getBreadcrumbs());
		root.setWidth("100%");
		root.add(createHeadline("Marktplatz-Details", true));
		root.add(new MarketplaceForm(currentMarketplace, false, false, null, new Callback() {
			@Override
			public void run() {
				mainPanel.setView(new MarketplaceOverview());
			}
		}));

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Project> projectSsm = new SingleSelectionModel<Project>();

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		projectTable.setSelectionModel(projectSsm);

		// hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der
		// Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		projectSsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				Project selectedProject = projectSsm.getSelectedObject();
				ClientsideSettings.setCurrentProjectId(selectedProject.getID());
				mainPanel.setView(new ProjectView(selectedProject));
			}
		});

		// hinzufügen der Tabellenspaltennamen sowie hinzufügen der zugehörigen
		// Daten aus der Datenbank
		TextColumn<Project> projectsTitleColumn = new TextColumn<Project>() {
			@Override
			public String getValue(Project object) {
				return object.getTitle();
			}
		};
		projectTable.addColumn(projectsTitleColumn, "Name");

		// Muss eigentlich Int (bzw. Row counter) wiedergeben
//		TextColumn<Project> projectsCounterColumn = new TextColumn<Project>() {
//			@Override
//			public String getValue(Project object) {
//				// TODO Anzahl offene Ausschreibungen
//				return object.getDescription();
//			}
//		};
//		projectTable.addColumn(projectsCounterColumn, "Anzahl Offene Ausschreibungen");

		TextColumn<Project> projectsDescriptionColumn = new TextColumn<Project>() {
			@Override
			public String getValue(Project object) {
				return object.getDescription();
			}
		};
		projectTable.addColumn(projectsDescriptionColumn, "Beschreibung");

		TextColumn<Project> projectsStartColumn = new TextColumn<Project>() {
			@Override
			public String getValue(Project object) {
				return simpleDateFormat.format(object.getStartDate());
			}
		};
		projectTable.addColumn(projectsStartColumn, "Start");

		TextColumn<Project> projectsEndColumn = new TextColumn<Project>() {
			@Override
			public String getValue(Project object) {
				return simpleDateFormat.format(object.getEndDate());
			}
		};
		projectTable.addColumn(projectsEndColumn, "Ende");

		root.add(createSecondHeadline("Projekte auf diesem Marktplatz"));
		root.add(projectTable);
		projectTable.setWidth("100%", true);

		final Button newButton = new Button("Neues Projekt hinzufügen");
		newButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainPanel.setForm(new ProjectForm(null, false, true, null, null, currentMarketplace));
			}
		});
		root.add(newButton);

		this.add(root);
		loadData();
	}

	@Override
	public void loadData() {
		worketplaceAdministration.getProjectsFor(currentMarketplace, new AsyncCallback<Vector<Project>>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Vector<Project> results) {
				projectTable.setRowData(0, results);
				projectTable.setRowCount(results.size(), true);
			}
		});
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setSecondBreadcrumb(this, "Marktplatz-Details");		
	}
}