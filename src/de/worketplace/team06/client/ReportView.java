package de.worketplace.team06.client;

import de.worketplace.team06.shared.ReportGeneratorAsync;
import de.worketplace.team06.shared.report.HTMLReportWriter;

public abstract class ReportView extends Page {
	protected ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
	protected HTMLReportWriter writer = new HTMLReportWriter();
	
	public ReportView() {
		this.setBreadcrumb();
	}

	public abstract void setBreadcrumb();

	public abstract void loadData();
}