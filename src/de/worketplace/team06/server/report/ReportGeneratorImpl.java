package de.worketplace.team06.server.report;

import java.sql.Timestamp;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.worketplace.team06.server.WorketplaceAdministrationImpl;
import de.worketplace.team06.shared.ReportGenerator;
import de.worketplace.team06.shared.WorketplaceAdministration;
import de.worketplace.team06.shared.bo.*;
import de.worketplace.team06.shared.report.*;


/**
 * @author Toby
 *
 */
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private WorketplaceAdministration wpadmin = null;
	
	public ReportGeneratorImpl() throws IllegalArgumentException {
	
	}
	
	/**
	 * Methode zur Initalisierung der WorketplaceAdministration
	 */
	@Override
	public void init() throws IllegalArgumentException {
	    WorketplaceAdministrationImpl impl = new WorketplaceAdministrationImpl();
	    impl.init();
	    this.wpadmin = impl;
	}
	
	protected WorketplaceAdministration getWorketplaceAdministration() {
		return this.wpadmin;
	}
	
	/**
	 * Methode zum Hinzufügen eines Impressums zum entsprechenden Report
	 * @param r
	 */
	/*
	 * Impressum hinzufügen (optional)
	protected void addImprint (Report r){
		
		CompositeParagraph imprint = new CompositeParagraph();
		
		User user = getUser();

        switch(orgaUnit.getType()){ 
        case "Person": 
        	Person p = wpadmin.getPersonByGoogleID(orgaUnit.getGoogleID());
        	imprint.addSubParagraph(new SimpleParagraph(p.getFirstName() + " " + p.getLastName()));
        	imprint.addSubParagraph(new SimpleParagraph(p.getStreet()));
        	imprint.addSubParagraph(new SimpleParagraph(p.getZipcode() + " " + p.getCity()));
        	break;
		case "Team": 
			Team t = wpadmin.getTeamByGoogleID(orgaUnit.getGoogleID());
        	imprint.addSubParagraph(new SimpleParagraph(t.getName()));
            break;
		case "Organisation": 
			Organisation o = wpadmin.getOrganisationByGoogleID(orgaUnit.getGoogleID());
        	imprint.addSubParagraph(new SimpleParagraph(o.getName()));
        	imprint.addSubParagraph(new SimpleParagraph(o.getStreet()));
        	imprint.addSubParagraph(new SimpleParagraph(o.getZipcode() + " " + o.getCity()));
        	break;
        }
		
		r.setImprint(imprint);
		
	}
	*/
	
	/**
	 * Methode zum Generieren eines Reports für alle Ausschreibungen
	 */
	@Override
	public AllCallsReport createAllCallsReport() throws IllegalArgumentException {
		
		//Erstellung einer Instanz des Reports
		AllCallsReport report = new AllCallsReport();
		
		//Setzen des Reporttitels und dem Generierungsdatum
		report.setTitle("Alle Ausschreibungen");
		report.setCreated(new Timestamp(System.currentTimeMillis()));
		
		Row headline = new Row();
		
		//Kopfzeile mit den Überschriften der einzelnen Spalten im Report erstellen
		headline.addColumn(new Column("Titel"));
		headline.addColumn(new Column("Beschreibung"));
		headline.addColumn(new Column("Projekt"));
		headline.addColumn(new Column("Deadline"));
		headline.addColumn(new Column("Status"));
		
		//Kopfzeile dem Report hinzufügen
		report.addRow(headline);
		
		//Relevanten Daten in den Vektor laden und Zeile für Zeile dem Report hinzufügen
		Vector<Call> calls = wpadmin.getAllCalls();
		for (Call c : calls){
			Project p = wpadmin.getProjectByID(c.getProjectID());
			Row rowToAdd = new Row();
			rowToAdd.addColumn(new Column(c.getTitle()));
			rowToAdd.addColumn(new Column(c.getDescription()));
			rowToAdd.addColumn(new Column(p.getTitle()));
			rowToAdd.addColumn(new Column(c.getDeadline().toString()));
			rowToAdd.addColumn(new Column(c.getStatusString()));
			report.addRow(rowToAdd);
		}
		
		return report;
	}
	
	
	/**
	 * Methode zum Generieren eines Reports für alle Ausschreibungen der übergebenen Organisationseinheit
	 */
	@Override
	public AllCallsReport createAllCallsOfUserReport(OrgaUnit o) throws IllegalArgumentException {
		
		//Erstellung einer Instanz des Reports
		AllCallsReport report = new AllCallsReport();
		
		//Setzen des Reporttitels und dem Generierungsdatum
		report.setTitle("Alle eigenen Ausschreibungen");
		report.setCreated(new Timestamp(System.currentTimeMillis()));
		
		Row headline = new Row();
		
		//Kopfzeile mit den Überschriften der einzelnen Spalten im Report erstellen
		headline.addColumn(new Column("Titel"));
		headline.addColumn(new Column("Beschreibung"));
		headline.addColumn(new Column("Projekt"));
		headline.addColumn(new Column("Deadline"));
		headline.addColumn(new Column("Status"));
		
		//Kopfzeile dem Report hinzufügen
		report.addRow(headline);
		
		//Relevanten Daten in den Vektor laden und Zeile für Zeile dem Report hinzufügen
		Vector<Project> projects = wpadmin.getProjectsForLeader(o);
		for (Project p : projects){
			Vector<Call> calls = wpadmin.getCallsFor(p);
			for (Call c : calls){
				Row rowToAdd = new Row();
				rowToAdd.addColumn(new Column(c.getTitle()));
				rowToAdd.addColumn(new Column(c.getDescription()));
				rowToAdd.addColumn(new Column(p.getTitle()));
				rowToAdd.addColumn(new Column(c.getDeadline().toString()));
				rowToAdd.addColumn(new Column(c.getStatusString()));
				report.addRow(rowToAdd);
			}
		}
		
		return report;
	}
	
	
	
	/**
	 * 
	 */
	@Override
	public AllCallsMatchingWithUserReport createAllCallsMatchingWithUserReport(OrgaUnit o) throws IllegalArgumentException {
		//TODO: Überlegung, wie die Partnerprofile verglichen werden. Ggf. DB "like"
		return null;
	}
	
	
	/**
	 * Methode zum Generieren eines Reports für alle eingehenden Bewerbungen auf Ausschreibungen, die dem übergebenenen Benutzer zugeordnet sind.
	 */
	@Override
	public AllApplicationsForCallsOfUserReport createAllApplicationsForCallsOfUserReport(OrgaUnit o) throws IllegalArgumentException {
		
		//Erstellung einer Instanz des Reports
		AllApplicationsForCallsOfUserReport report = new AllApplicationsForCallsOfUserReport();
		
		//Setzen des Reporttitels und dem Generierungsdatum
		report.setTitle("Alle Bewerbungen auf Ausschreibungen des Benutzers");
		report.setCreated(new Timestamp(System.currentTimeMillis()));
		
		Row headline = new Row();
		
		//Kopfzeile mit den Überschriften der einzelnen Spalten im Report erstellen
		headline.addColumn(new Column("Bewerbungstext"));
		headline.addColumn(new Column("Status"));
		headline.addColumn(new Column("Rating"));
		headline.addColumn(new Column("Projekt"));
		
		//Kopfzeile dem Report hinzufügen
		report.addRow(headline);
		
		//Relevanten Daten in den Vektor laden und Zeile für Zeile dem Report hinzufügen
		Vector<Project> projects = wpadmin.getProjectsForLeader(o);
		for (Project p : projects){
			Vector<Call> calls = wpadmin.getCallsFor(p);
			for (Call c : calls){
				Vector<Application> applications = wpadmin.getApplicationsFor(c);
				for (Application a : applications){
					Row rowToAdd = new Row();
					rowToAdd.addColumn(new Column(a.getText()));
					rowToAdd.addColumn(new Column(a.getStatusString()));
					Rating r = wpadmin.getRatingFor(a);
					rowToAdd.addColumn(new Column(r.getRating().toString()));
					rowToAdd.addColumn(new Column(p.getTitle()));
					report.addRow(rowToAdd);
				}
			}
		}
		
		return report;
	}
	
	
	/**
	 * Methode zum Generieren eines Reports für alle ausgehenden Bewerbungen vom User auf bestehende Ausschreibungen.
	 */
	@Override
	public AllApplicationsOfUserToCallsReport createAllApplicationsOfUserToCallsReport(OrgaUnit o) throws IllegalArgumentException {
		
		//Erstellung einer Instanz des Reports
		AllApplicationsOfUserToCallsReport report = new AllApplicationsOfUserToCallsReport();
		
		//Setzen des Reporttitels und dem Generierungsdatum
		report.setTitle("Alle ausgehenden Bewerbungen des Benutzers");
		report.setCreated(new Timestamp(System.currentTimeMillis()));
		
		Row headline = new Row();
		
		//Kopfzeile mit den Überschriften der einzelnen Spalten im Report erstellen
		headline.addColumn(new Column("Ausschreibung"));
		headline.addColumn(new Column("Deadline"));
		headline.addColumn(new Column("Bewerbungstext"));
		headline.addColumn(new Column("Status"));
		headline.addColumn(new Column("Rating"));
		
		//Kopfzeile dem Report hinzufügen
		report.addRow(headline);
		
		Vector<Application> applications = wpadmin.getApplicationsFor(o);
		for(Application a : applications){
			Call c = wpadmin.getCallByID(a.getCallID());
			Row rowToAdd = new Row();
			rowToAdd.addColumn(new Column(c.getTitle()));
			rowToAdd.addColumn(new Column(c.getDeadline().toString()));
			rowToAdd.addColumn(new Column(a.getText()));
			rowToAdd.addColumn(new Column(a.getStatusString()));
			Rating r = wpadmin.getRatingFor(a);
			rowToAdd.addColumn(new Column(r.getRating().toString()));
			report.addRow(rowToAdd);
		}
		
		return report;
	}
	
	
	/**
	 * Methode zum Generieren eines Reports für alle Bewerbungen eines Bewerbers.
	 */
	@Override
	public AllApplicationsOfApplicantReport createAllApplicationsOfApplicantReport (OrgaUnit applicant) throws IllegalArgumentException {
		
		//Erstellung einer Instanz des Reports
		AllApplicationsOfApplicantReport report = new AllApplicationsOfApplicantReport();
		
		//Setzen des Reporttitels und dem Generierungsdatum
		report.setTitle("Ausgehende Bewerbungen des Bewerbers");
		report.setCreated(new Timestamp(System.currentTimeMillis()));
		
		Row headline = new Row();
		
		//Kopfzeile mit den Überschriften der einzelnen Spalten im Report erstellen
		headline.addColumn(new Column("Projekt"));
		headline.addColumn(new Column("Ausschreibung"));
		headline.addColumn(new Column("Bewerbungstext"));
		headline.addColumn(new Column("Status"));
		
		//Kopfzeile dem Report hinzufügen
		report.addRow(headline);
		
		Vector<Application> applications = wpadmin.getApplicationsFor(applicant);
		for(Application a : applications){
			Call c = wpadmin.getCallByID(a.getCallID());
			Project p = wpadmin.getProjectByID(c.getProjectID());
			Row rowToAdd = new Row();
			rowToAdd.addColumn(new Column(c.getTitle()));
			rowToAdd.addColumn(new Column(p.getTitle()));
			rowToAdd.addColumn(new Column(a.getText()));
			rowToAdd.addColumn(new Column(a.getStatusString()));
			report.addRow(rowToAdd);
		}
		
		return report;
	}
	
	
	/**
	 * Methode zum Generieren eines Reports für alle Beteiligungen eines Bewerbers.
	 */
	@Override
	public AllEnrollmentsOfApplicantReport createAllEnrollmentsOfApplicantReport (OrgaUnit applicant) throws IllegalArgumentException {
		AllEnrollmentsOfApplicantReport report = new AllEnrollmentsOfApplicantReport();
		
		//Setzen des Reporttitels und dem Generierungsdatum
		report.setTitle("Beteiligungen an Projekten des Bewerbers");
		report.setCreated(new Timestamp(System.currentTimeMillis()));
		
		Row headline = new Row();
		
		//Kopfzeile mit den Überschriften der einzelnen Spalten im Report erstellen
		headline.addColumn(new Column("Projekt"));
		headline.addColumn(new Column("Beteiligungsbeginn"));
		headline.addColumn(new Column("Beteiligungsende"));
		
		//Kopfzeile dem Report hinzufügen
		report.addRow(headline);
		
		Vector<Enrollment> enrollments = wpadmin.getEnrollmentFor(applicant);
		for(Enrollment e : enrollments){
			Project p = wpadmin.getProjectByID(e.getProjectID());
			Row rowToAdd = new Row();
			rowToAdd.addColumn(new Column(p.getTitle()));
			rowToAdd.addColumn(new Column(e.getStartDate().toString()));
			rowToAdd.addColumn(new Column(e.getEndDate().toString()));
			report.addRow(rowToAdd);
		}
		
		return report;
	}
	
	
	/**
	 * Methode zum Generieren eines Reports für alle Verflechtungen eines Bewerbers.
	 */
	@Override
	public AllInterrelationsOfApplicantReport createAllInterrelationsOfApplicantReport (OrgaUnit applicant) throws IllegalArgumentException {
		AllInterrelationsOfApplicantReport report = new AllInterrelationsOfApplicantReport();
		
		//Setzen des Reporttitels und dem Generierungsdatum
		report.setTitle("Verflechtungen des Bewerbers mit der ID " + applicant.getID().toString());
		report.setCreated(new Timestamp(System.currentTimeMillis()));
		
		report.addSubReport(createAllApplicationsOfApplicantReport(applicant));
		report.addSubReport(createAllEnrollmentsOfApplicantReport(applicant));
		
		return report;
	}
	
	
//	/**
//	 * Methode zum Generieren eines Reports für die Verflechtungen aller Bewerber des Users.
//	 */
//	@Override
//	public AllInterrelationsOfAllApplicantsOfUserReport createAllInterrelationsOfAllApplicantsOfUserReport (OrgaUnit o) throws IllegalArgumentException {
//		AllInterrelationsOfAllApplicantsOfUserReport report = new AllInterrelationsOfAllApplicantsOfUserReport();
//		
//		//Setzen des Reporttitels und dem Generierungsdatum
//		report.setTitle("Alle Verflechtungen der Bewerber");
//		report.setCreated(new Timestamp(System.currentTimeMillis()));
//		
//		Vector<Application>
//		report.addSubReport(createAllApplicationsOfApplicantReport(applicant));
//		report.addSubReport(createAllEnrollmentsOfApplicantReport(applicant));
//		
//		return report;
//	}
	
	
	@Override
	public OrgaUnit getOrgaUnitFor(LoginInfo loginInfo) throws IllegalArgumentException {
		return wpadmin.getOrgaUnitFor(loginInfo);
	}
	
}
