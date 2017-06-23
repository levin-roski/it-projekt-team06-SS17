package de.worketplace.team06.server;

import de.worketplace.team06.server.report.*;
import de.worketplace.team06.shared.bo.Person;
import de.worketplace.team06.shared.report.*;

/**
 * Diese Klasse dient zum Testen einzelner Methoden für die Erstellung von Reports
 * und der Ausgabe innerhalb der Console
 * @author Toby
 *
 */
public class TestReportG {
	
	

	public static void main(String[] args) {
		ReportGeneratorImpl reportGen = new ReportGeneratorImpl();
		reportGen.init();

		ShowAllCallsReport(reportGen);
	
	}
	
	public static void ShowAllCallsReport(ReportGeneratorImpl gen){
		Person p = new Person();
		p.setGoogleID("G3000");
		//PlainTextReportWriter writer = new PlainTextReportWriter();
		HTMLReportWriter writer = new HTMLReportWriter();
		writer.process(gen.createAllCallsReport(p));
		System.out.println(writer.getReportText());
	}

}
