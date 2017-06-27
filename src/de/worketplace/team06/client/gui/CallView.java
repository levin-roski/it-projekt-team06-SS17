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
import de.worketplace.team06.shared.bo.Call;
import de.worketplace.team06.shared.bo.Marketplace;
import de.worketplace.team06.shared.bo.PartnerProfile;
import de.worketplace.team06.shared.bo.Project;

public class CallView extends View {
	private WorketplaceAdministrationAsync worketplaceAdministration = ClientsideSettings
			.getWorketplaceAdministration();
	
	// erstellen der Tabelle Projekte
	final CellTable<PartnerProfile> partnerProfileTable = new CellTable<PartnerProfile>();
	Call currentCall;

	public CallView(Call pCurrentCall) {
		super();
		currentCall = pCurrentCall;
		final VerticalPanel root = new VerticalPanel();
		root.add(ClientsideSettings.getBreadcrumbs());
		root.setWidth("100%");
		root.add(createHeadline("Ausschreibungs-Details", true));
		root.add(new CallForm(currentCall, false, false, null, null));

//		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
//		// Item zur selben Zeit ausgewählt sein kann
//		final SingleSelectionModel<Project> partnerProfileSsm = new SingleSelectionModel<Project>();
//
//		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
//		// Item zur selben Zeit ausgewählt sein kann
//		partnerProfileTable.setSelectionModel(partnerProfileSsm);
//
//		// hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der
//		// Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
//		partnerProfileSsm.addSelectionChangeHandler(new Handler() {
//			@Override
//			public void onSelectionChange(SelectionChangeEvent event) {
//				Project selectedProject = partnerProfileSsm.getSelectedObject();
//				ClientsideSettings.setCurrentProjectId(selectedProject.getID());
//				mainPanel.setView(new ProjectView(selectedProject));
//			}
//		}); TODO

		// hinzufügen der Tabellenspaltennamen sowie hinzufügen der zugehörigen
		// Daten aus der Datenbank
		TextColumn<Project> projectsTitleColumn = new TextColumn<Project>() {
			@Override
			public String getValue(Project object) {
				return object.getTitle();
			}
		};
		partnerProfileTable.addColumn(projectsTitleColumn, "Name");

		// Muss eigentlich Int (bzw. Row counter) wiedergeben
//		TextColumn<Project> projectsCounterColumn = new TextColumn<Project>() {
//			@Override
//			public String getValue(Project object) {
//				// TODO Anzahl offene Ausschreibungen
//				return object.getDescription();
//			}
//		};
//		projectTable.addColumn(projectsCounterColumn, "Anzahl Offene Ausschreibungen");

		TextColumn<PartnerProfile> partnerProfileAttributesColumn = new TextColumn<PartnerProfile>() {
			@Override
			public String getValue(PartnerProfile object) {
//				return object.get(); TODO
			}
		};
		partnerProfileTable.addColumn(partnerProfileAttributesColumn, "Eigenschaft");

		TextColumn<PartnerProfile> partnerProfileValueColumn = new TextColumn<PartnerProfile>() {
			@Override
			public String getValue(PartnerProfile object) {
//				return object.get(); TODO
			}
		};
		partnerProfileTable.addColumn(partnerProfileValueColumn, "Wert");


		root.add(createSecondHeadline("Partnerprofil dieser Ausschreibung"));
		root.add(partnerProfileTable);
		partnerProfileTable.setWidth("100%", true);

		final Button firstButton = new Button("Eigenschaft hinzufügen");
		firstButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
//				mainPanel.setForm(new CallForm(null, false, true, null, null, currentCall)); TODO
			}
		});
		root.add(firstButton);
		
		final Button secondButton = new Button("Auf Ausschreibung bewerben");
		secondButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
//				mainPanel.setForm(new CallForm(null, false, true, null, null, currentCall)); TODO
			}
		});
		root.add(secondButton);

		this.add(root);
		loadData();
	}

	@Override
	public void loadData() {
		worketplaceAdministration.getPartnerProfileFor(currentCall, new AsyncCallback<Vector<Call>>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Vector<Call> results) {
				partnerProfileTable.setRowData(0, results);
				partnerProfileTable.setRowCount(results.size(), true);
			}
		});
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setSecondBreadcrumb(this, "Ausschreibungs-Details");		
	}
}