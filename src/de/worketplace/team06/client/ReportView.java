package de.worketplace.team06.client;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

import de.worketplace.team06.shared.ReportGeneratorAsync;
import de.worketplace.team06.shared.report.HTMLReportWriter;

public abstract class ReportView extends View {
	protected ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
	protected HTMLReportWriter writer = new HTMLReportWriter();

	protected void append(String text) {
		HTML html = new HTML(text);
		this.add(html);
	}
	
	protected ListBox getAllApplicantsOfCurrentUserInput(final Callback callback) {
		ListBox allUsers = new ListBox();
//		reportGenerator. TODO hier RPC Call und alle Daten holen
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