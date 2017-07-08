package de.worketplace.team06.client.gui;

import java.util.Vector;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.View;
import de.worketplace.team06.shared.bo.Call;
import de.worketplace.team06.shared.bo.Project;

public class CallOverView extends View {
	// erstellen der Tabelle Meine Ausschreibungen
	private final CellTable<Call> allCallsTable = new CellTable<Call>();

	public CallOverView() {
		setBreadcrumb();
		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Call> allCallSsm = new SingleSelectionModel<Call>();

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		allCallsTable.setSelectionModel(allCallSsm);

		// hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der
		// Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		allCallSsm.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				if (allCallSsm.getSelectedObject() != null) {
					final Call selectedCall = allCallSsm.getSelectedObject();
					worketplaceAdministration.getProjectByID(selectedCall.getProjectID(), new AsyncCallback<Project>() {
						@Override
						public void onFailure(Throwable caught) {
						}

						@Override
						public void onSuccess(Project results) {
							allCallSsm.clear();
							History.newItem("Ausschreibungs-Details"+selectedCall.getID()+"-"+results.getID()+"-"+results.getMarketplaceID());
						}
					});
				}
			}
		});

		TextColumn<Call> titleColumn = new TextColumn<Call>() {
			@Override
			public String getValue(Call object) {
				return object.getTitle();
			}
		};
		allCallsTable.addColumn(titleColumn, "Name");

		TextColumn<Call> descriptionColumn = new TextColumn<Call>() {
			@Override
			public String getValue(Call object) {
				return object.getDescription();
			}
		};
		allCallsTable.addColumn(descriptionColumn, "Beschreibung");

		allCallsTable.setWidth("100%", true);
		final VerticalPanel root = new VerticalPanel();
		root.add(ClientsideSettings.getBreadcrumbs());
		root.add(createHeadline("Alle Ausschreibungen", true));
		root.add(allCallsTable);

		this.add(root);

		loadData();
	}

	@Override
	public void loadData() {
		worketplaceAdministration.getAllCalls(new AsyncCallback<Vector<Call>>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Vector<Call> results) {
				allCallsTable.setRowData(0, results);
				allCallsTable.setRowCount(results.size(), true);
			}
		});
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setFirstBreadcrumb(this, "Ausschreibungen");
	}

	@Override
	public String returnTokenName() {
		return "Ausschreibungen";
	}
}
