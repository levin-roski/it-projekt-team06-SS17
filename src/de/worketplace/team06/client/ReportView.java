package de.worketplace.team06.client;

import java.util.Vector;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

import de.worketplace.team06.shared.ReportGeneratorAsync;
import de.worketplace.team06.shared.bo.OrgaUnit;
import de.worketplace.team06.shared.bo.Organisation;
import de.worketplace.team06.shared.bo.Person;
import de.worketplace.team06.shared.bo.Team;
import de.worketplace.team06.shared.report.HTMLReportWriter;

public abstract class ReportView extends View {
	protected ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
	protected HTMLReportWriter writer = new HTMLReportWriter();

	protected void append(String text) {
		HTML html = new HTML(text);
		this.add(html);
	}

	protected ListBox getAllApplicantsOfCurrentUserInput(final Callback callback) {
		final ListBox allUsers = new ListBox();
		reportGenerator.getAllApplicantsForAllCallsFrom((Person) ClientsideSettings.getCurrentUser(), new AsyncCallback<Vector<OrgaUnit>>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(Vector<OrgaUnit> result) {
				for (OrgaUnit orgaUnit : result) {
					if (orgaUnit instanceof Person) {
						allUsers.addItem(((Person) orgaUnit).getFirstName() + " " + ((Person) orgaUnit).getLastName(), String.valueOf(orgaUnit.getID()));
					} else if (orgaUnit instanceof Team) {
						allUsers.addItem("Team " + ((Team) orgaUnit).getName(), String.valueOf(orgaUnit.getID()));
					} else if (orgaUnit instanceof Organisation) {
						allUsers.addItem("Organisation " + ((Organisation) orgaUnit).getName(), String.valueOf(orgaUnit.getID()));
					}
				}
			}
		});
		allUsers.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				if (callback instanceof Callback) {
					callback.run();
				}
			}
		});
		return allUsers;
	}
}