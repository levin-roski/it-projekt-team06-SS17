package de.worketplace.team06.client;

import de.worketplace.team06.shared.ReportGeneratorAsync;
import de.worketplace.team06.shared.report.HTMLReportWriter;

public abstract class ReportView extends View {
	protected ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
	protected HTMLReportWriter writer = new HTMLReportWriter();
}