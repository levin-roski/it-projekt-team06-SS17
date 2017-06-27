package de.worketplace.team06.client;

import com.google.gwt.user.client.ui.HTML;

import de.worketplace.team06.shared.ReportGeneratorAsync;
import de.worketplace.team06.shared.report.HTMLReportWriter;

public abstract class ReportView extends View {
	protected ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
	protected HTMLReportWriter writer = new HTMLReportWriter();

	protected void append(String text) {
		HTML html = new HTML(text);
		this.add(html);
	}
}