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

import de.worketplace.team06.client.Callback;
import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.View;
import de.worketplace.team06.shared.bo.Call;
import de.worketplace.team06.shared.bo.Enrollment;
import de.worketplace.team06.shared.bo.Project;

public class ProjectView extends View {
	// erstellen der Tabelle Ausschreibungen
	private final CellTable<Call> callTable = new CellTable<Call>();
	// erstellen der Tabelle Beteiligungen
	private final CellTable<Enrollment> enrollmentTable = new CellTable<Enrollment>();
	private Project currentProject;

	public ProjectView(Project pCurrentProject) {
		currentProject = pCurrentProject;
		if (currentProject.getProjectLeaderID() == ClientsideSettings.getCurrentUser().getID()) {
			ClientsideSettings.setIsCurrentProjectLeader(true);
		} else {
			ClientsideSettings.setIsCurrentProjectLeader(false);
		}
		final VerticalPanel root = new VerticalPanel();
		root.setWidth("100%");
		root.add(ClientsideSettings.getBreadcrumbs());
		root.add(createHeadline("Projekt-Details", true));
		root.add(new ProjectForm(currentProject, false, false, null, new Callback() {
			@Override
			public void run() {
				mainPanel.setView(new MarketplaceOverView());
			}

			@Override
			public <T> void runOnePar(T parameter) {
			}
		}, null));

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
				Call selectedCall = callSsm.getSelectedObject();
				ClientsideSettings.setCurrentCallId(selectedCall.getID());
				History.newItem(
						"Ausschreibungs-Details" + selectedCall.getID() + "-" + ClientsideSettings.getCurrentProjectId()
								+ "-" + ClientsideSettings.getCurrentMarketplaceId());
			}
		});

		// hinzufügen der Tabellenspaltennamen sowie hinzufügen der zugehörigen
		// Daten aus der Datenbank
		TextColumn<Call> projectsAndCallsColumn = new TextColumn<Call>() {
			@Override
			public String getValue(Call object) {
				return object.getTitle();
			}
		};
		callTable.addColumn(projectsAndCallsColumn, "Titel");

		TextColumn<Call> descriptionColumn = new TextColumn<Call>() {
			@Override
			public String getValue(Call object) {
				return object.getDescription();
			}
		};
		callTable.addColumn(descriptionColumn, "Beschreibung");

		TextColumn<Call> deadlineColumn = new TextColumn<Call>() {
			@Override
			public String getValue(Call object) {
				try {
					return simpleDateFormat.format(object.getDeadline());
				} catch (Exception e) {
					return "Kein Datum gesetzt";
				}
			}
		};
		callTable.addColumn(deadlineColumn, "Bewerbungsfrist");

		TextColumn<Call> statusColumn = new TextColumn<Call>() {
			@Override
			public String getValue(Call object) {
				return object.getStatusString();
			}
		};
		callTable.addColumn(statusColumn, "Status");

		root.add(createSecondHeadline("Ausschreibungen"));
		root.add(callTable);
		callTable.setWidth("100%", true);

		if (ClientsideSettings.isCurrentProjectLeader()) {
			final Button newButton = new Button("Ausschreibung hinzufügen");
			newButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					mainPanel.setForm(new CallForm(null, false, true, null, null));
				}
			});
			root.add(newButton);
		}

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		final SingleSelectionModel<Enrollment> enrollmentSsm = new SingleSelectionModel<Enrollment>();

		// erstellen eines SingleSelectionModels -> macht, dass immer nur ein
		// Item zur selben Zeit ausgewählt sein kann
		enrollmentTable.setSelectionModel(enrollmentSsm);

		// hinzufügen eines SelectionChangeHandler -> wenn eine Zeile der
		// Tabelle gedrückt wird soll die neue Tabelle geöffnet werden
		if (ClientsideSettings.isCurrentProjectLeader()) {
			enrollmentSsm.addSelectionChangeHandler(new Handler() {
				@Override
				public void onSelectionChange(SelectionChangeEvent event) {
					if (enrollmentSsm.getSelectedObject() != null) {
						Enrollment selectedEnrollment = enrollmentSsm.getSelectedObject();
						mainPanel.setForm(new EnrollmentForm(selectedEnrollment, false, true));
						enrollmentSsm.clear();
					}
				}
			});
		}

		// hinzufügen der Tabellenspaltennamen sowie hinzufügen der zugehörigen
		// Daten aus der Datenbank
		TextColumn<Enrollment> enrollmentStartColumn = new TextColumn<Enrollment>() {
			@Override
			public String getValue(Enrollment object) {
				try {
					return simpleDateFormat.format(object.getStartDate());
				} catch (Exception e) {
					return "Kein Datum gesetzt";
				}
			}
		};
		enrollmentTable.addColumn(enrollmentStartColumn, "Startdartum");

		TextColumn<Enrollment> enrollmentEndColumn = new TextColumn<Enrollment>() {
			@Override
			public String getValue(Enrollment object) {
				try {
					return simpleDateFormat.format(object.getEndDate());
				} catch (Exception e) {
					return "Kein Datum gesetzt";
				}
			}
		};
		enrollmentTable.addColumn(enrollmentEndColumn, "Startdartum");

		TextColumn<Enrollment> enrollmentPeriodColumn = new TextColumn<Enrollment>() {
			@Override
			public String getValue(Enrollment object) {
				return String.valueOf(object.getWorkload());
			}
		};
		enrollmentTable.addColumn(enrollmentPeriodColumn, "Workload (in Tagen)");

		root.add(createSecondHeadline("Beteiligungen"));
		root.add(enrollmentTable);
		enrollmentTable.setWidth("100%", true);

		this.add(root);
		loadData();
	}

	@Override
	public void loadData() {
		worketplaceAdministration.getEnrollmentFor(currentProject, new AsyncCallback<Vector<Enrollment>>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Vector<Enrollment> result) {
				enrollmentTable.setRowData(0, result);
				enrollmentTable.setRowCount(result.size(), true);
			}
		});

		worketplaceAdministration.getCallsFor(currentProject, new AsyncCallback<Vector<Call>>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Vector<Call> results) {
				callTable.setRowData(0, results);
				callTable.setRowCount(results.size(), true);
			}
		});
	}

	@Override
	public void setBreadcrumb() {
		ClientsideSettings.setThirdBreadcrumb(this, "Projekt-Details");
	}

	@Override
	public String returnTokenName() {
		return "Projekt-Details" + ClientsideSettings.getCurrentProjectId() + "-"
				+ ClientsideSettings.getCurrentMarketplaceId();
	}
}