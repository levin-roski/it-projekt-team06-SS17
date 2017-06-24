package de.worketplace.team06.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.worketplace.team06.client.Callback;
import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.DataLoading;
import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.shared.bo.Call;
import de.worketplace.team06.shared.bo.Enrollment;
import de.worketplace.team06.shared.bo.Marketplace;

public class ProjectView extends Page implements DataLoading {
	private WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
			.getWorketplaceAdministration();
	// erstellen der Tabelle Ausschreibungen
	final CellTable<Call> callTable = new CellTable<Call>();
	// erstellen der Tabelle Beteiligungen
	final CellTable<Enrollment> enrollmentTable = new CellTable<Enrollment>();

	public ProjectView(final Marketplace currentMarketplace) {
		final VerticalPanel root = new VerticalPanel();
		root.setWidth("100%");
		root.add(createHeadline("Projekt", true));
		root.add(new MarketplaceForm(currentMarketplace, false, false, null, new Callback() {
			@Override
			public void run() {
				ClientsideSettings.getMainPanel().setView(new MarketplaceOverview());
			}
		}));
		
		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Call> callSsm = new SingleSelectionModel<Call>();

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		callTable.setSelectionModel(callSsm);

		// hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der
		// Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		callSsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// Marketplace m1 = allProjectsSsm.getSelectedObject();
				// ClientsideSettings.getMainPanel().setItem(new
				// MarketplaceForm(m1, false, true));
			}
		});

		TextColumn<Call> callsTitleColumn = new TextColumn<Call>() {
			@Override
			public String getValue(Call object) {
				return object.getTitle();
			}
		};
		callTable.addColumn(callsTitleColumn, "Name");

		root.add(createSecondHeadline("Ausschreibungen"));
		root.add(callTable);
		callTable.setWidth("100%", true);

		final Button newButton = new Button("Neue Ausschreibung hinzufügen");
		newButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				MainPanel tmpMainPanel = ClientsideSettings.getMainPanel();
				tmpMainPanel.setForm(new MarketplaceForm(null, false, true, null, null));
			}
		});
		root.add(newButton);

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Enrollment> enrollmentSsm = new SingleSelectionModel<Enrollment>();

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		enrollmentTable.setSelectionModel(enrollmentSsm);

		// hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der
		// Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		enrollmentSsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// Marketplace m1 = allProjectsSsm.getSelectedObject();
				// ClientsideSettings.getMainPanel().setItem(new
				// MarketplaceForm(m1, false, true));
			}
		});

		TextColumn<Enrollment> enrollmentTitleColumn = new TextColumn<Enrollment>() {
			@Override
			public String getValue(Enrollment object) {
				return String.valueOf(object.getOrgaUnitID());
			}
		};
		enrollmentTable.addColumn(enrollmentTitleColumn, "Name");

		TextColumn<Enrollment> timePeriodColumn = new TextColumn<Enrollment>() {
			@Override
			public String getValue(Enrollment object) {
				return String.valueOf(object.getStartDate());
//				return String.valueOf(object.getEndDate());
			}
		};
		enrollmentTable.addColumn(timePeriodColumn, "Von - Bis");

		TextColumn<Enrollment> workloadColumn = new TextColumn<Enrollment>() {
			@Override
			public String getValue(Enrollment object) {
				return String.valueOf(object.getWorkload());
			}
		};
		enrollmentTable.addColumn(workloadColumn, "Workload");

		root.add(createSecondHeadline("Beteiligungen"));
		root.add(enrollmentTable);
		enrollmentTable.setWidth("100%", true);

		this.add(root);
	}

	@Override
	public void loadData() {
//		 worketplaceAdministration.getEnrollmentFor(project, new
//		 AsyncCallback<Vector<Enrollment>>() {
//		 @Override
//		 public void onFailure(Throwable caught) {
//		 // TODO
//		 }
//		
//		 public void onSuccess(Vector<Enrollment> results) {
//		 enrollmentTable.setRowData(0, results);
//		 enrollmentTable.setRowCount(results.size(), true);
//		 }
//		 });
		
		// worketplaceAdministration.getAllCalls(new
		// AsyncCallback<Vector<Call>>() {
		// @Override
		// public void onFailure(Throwable caught) {
		// // TODO
		// }
		//
		// public void onSuccess(Vector<Call> results) {
		// callTable.setRowData(0, results);
		// callTable.setRowCount(results.size(), true);
		// }
		// });
	}
}