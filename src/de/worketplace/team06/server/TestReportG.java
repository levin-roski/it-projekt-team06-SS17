package de.worketplace.team06.server;

import de.worketplace.team06.server.report.*;
import de.worketplace.team06.shared.bo.*;
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

		ShowReport(reportGen);
	
	}
	
	public static void ShowReport(ReportGeneratorImpl gen){
		LoginInfo login = new LoginInfo();
		login.setGoogleId("G3000");
		login.setEmailAddress("test@mail.de");
		login.setNickname("Testuser");
		
		OrgaUnit o = gen.getOrgaUnitFor(login);
		
		if (o instanceof Person){
			Person p = (Person) o;
		} else if (o instanceof Team){
			Team t = (Team) o;
		} else if (o instanceof Organisation){
			Organisation org = (Organisation) o;
		}
		
		PlainTextReportWriter writer = new PlainTextReportWriter();
		//HTMLReportWriter writer = new HTMLReportWriter();
		//writer.process(gen.createAllCallsReport());
		//writer.process(gen.createAllCallsOfUserReport(testuser));
		//writer.process(gen.createAllApplicationsForCallsOfUserReport(o));
		
		writer.process(gen.createAllInterrelationsOfApplicantReport(o));
		//writer.process(gen.createAllInterrelationsOfAllApplicantsOfUserReport(o));
		
		System.out.println(writer.getReportText());
	}

}
