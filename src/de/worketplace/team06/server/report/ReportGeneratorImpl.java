package de.worketplace.team06.server.report;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
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
	public AllCallsReport createAllCallsReport(OrgaUnit o) throws IllegalArgumentException {
		
		//Erstellung einer Instanz des Reports
		AllCallsReport report = new AllCallsReport();
		
		//Setzen des Reporttitels und dem Generierungsdatum
		report.setTitle("Alle Ausschreibungen");
		report.setCreated(new Timestamp(System.currentTimeMillis()));
		
		//Generierung der Kopfdaten des Reports
		CompositeParagraph headerData = new CompositeParagraph();
		headerData.addSubParagraph(new SimpleParagraph("User: " + getNameForOrgaUnit(o)));
		headerData.addSubParagraph(new SimpleParagraph("Datum: " + report.getDateForTS()));
		headerData.addSubParagraph(new SimpleParagraph("Uhrzeit: " + report.getTimeForTS()));
		report.setHeaderData(headerData);
		
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
		
		//Generierung der Kopfdaten des Reports
		CompositeParagraph headerData = new CompositeParagraph();
		headerData.addSubParagraph(new SimpleParagraph("User: " + getNameForOrgaUnit(o)));
		headerData.addSubParagraph(new SimpleParagraph("Datum: " + report.getDateForTS()));
		headerData.addSubParagraph(new SimpleParagraph("Uhrzeit: " + report.getTimeForTS()));
		report.setHeaderData(headerData);
		
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
	 * @param o
	 * @return 
	 */
	@Override
	public AllCallsMatchingWithUserReport createAllCallsMatchingWithUserReport(OrgaUnit o) throws IllegalArgumentException {
		
		//Partnerprofil des angemeldeten Users auslesen
		PartnerProfile pp = wpadmin.createPartnerProfileFor(o);
		
		//Alle Eigenschaften des angemeldeten Users in einen Vektor einlesen
		Vector<Property> allPropsOfOu = wpadmin.getAllPropertiesFor(pp);
		
		//Alle Ausschreibungen in einen Vektor einlesen
		Vector<Call> allCalls = wpadmin.getAllCalls();
		
		//Vektor für die zutreffenden Ausschreibungen instanziieren
		Vector<Call> matchingCalls = new Vector<Call>();
		
		//Erstellung einer Instanz des Reports
		AllCallsMatchingWithUserReport report = new AllCallsMatchingWithUserReport();
		
		//Setzen des Reporttitels und dem Generierungsdatum
		report.setTitle("Alle interessanten Ausschreibungen für den Benutzer");
		report.setCreated(new Timestamp(System.currentTimeMillis()));
		
		//Generierung der Kopfdaten des Reports
		CompositeParagraph headerData = new CompositeParagraph();
		headerData.addSubParagraph(new SimpleParagraph("User: " + getNameForOrgaUnit(o)));
		headerData.addSubParagraph(new SimpleParagraph("Datum: " + report.getDateForTS()));
		headerData.addSubParagraph(new SimpleParagraph("Uhrzeit: " + report.getTimeForTS()));
		report.setHeaderData(headerData);
		
		Row headline = new Row();
		
		//Kopfzeile mit den Überschriften 
		headline.addColumn(new Column("Titel"));
		headline.addColumn(new Column("Bewerbungsfrist"));
		headline.addColumn(new Column("Projektname"));
		headline.addColumn(new Column("Status"));
		
		//Kopfzeile dem Report hinzufügen
		report.addRow(headline);
	
		/*
		 * Innerhalb der verschachtelten Schleifen werden die Eigenschaften der Ausschreibungen(PartnerProfile)
		 * mit dein Eigenschaften der OrgaUnit(PartnerProfil) verglichen und bei einem Treffer dem Vektor
		 * matchingCalls hinzugefügt. Ebenso wird bei jedem Treffer überprüft, ob die Ausschreibung bereits
		 * zum matchingCalls Vektor hinzugefügt wurde oder nicht. Wenn Ja wird nur der MatchingCount dieser
		 * Instanz erhöht. 
		 */
		for(Call c : allCalls){
			PartnerProfile tempPartnerProfile = wpadmin.getPartnerProfileFor(c);
			Vector<Property> tempProperties = wpadmin.getAllPropertiesFor(tempPartnerProfile);
			
			for (Property prop : tempProperties){
				for (Property prop2 : allPropsOfOu){
					if (prop.getValue() == prop2.getValue() && prop.getName() == prop2.getName()){
						if(!matchingCalls.contains(c)){
							matchingCalls.addElement(c);
							c.setMatchingCount(1);
						}
						else{
							int temp = c.getMatchingCount();
							temp++;
							c.setMatchingCount(temp);
						}
					}
				}
			}
		}
		
		
		/*
		 * Die Lokale Klasse bildet die Differenz der Größe MatchingCount. Die Klasse stellt eine 
		 * einfache Möglichkeit dar eine Rückgabe <0, =0 oder >0 zu bekommen. (Funktioniert 
		 * allerdings nicht mit Fließkommazahlen) Da wir die Ausgabe 
		 * reverse sortieren wollen rechnen wir call2 - call1. 
		 */
		class CallComparator implements Comparator<Call>
		{
		  @Override public int compare( Call call1, Call call2 )
		  {
		    return call2.getMatchingCount() - call1.getMatchingCount();
		  }
		}
		
		// Mit dem CallComparator-Objekt lässt sich der Vector nach dem MatchingCount sortieren
		Collections.sort(matchingCalls, new CallComparator());
		
		for (Call call : matchingCalls){
			Project proj = wpadmin.getProjectByID(call.getProjectID());
			Row rowToAdd = new Row();
			rowToAdd.addColumn(new Column(call.getTitle()));
			rowToAdd.addColumn(new Column(call.getDeadline().toString()));
			rowToAdd.addColumn(new Column(proj.getTitle()));
			rowToAdd.addColumn(new Column(call.getStatusString()));
			report.addRow(rowToAdd);
		}
		
		
		return report;
	}
	
	
	/**
	 * Methode zum Generieren eines Reports für alle eingehenden Bewerbungen auf Ausschreibungen, die dem übergebenenen Benutzer zugeordnet sind.
	 */
	@Override
	public AllApplicationsForCallsOfUserReport createAllApplicationsForCallsOfUserReport(OrgaUnit o) throws IllegalArgumentException {
		
		//Erstellung einer Instanz des Reports
		AllApplicationsForCallsOfUserReport report = new AllApplicationsForCallsOfUserReport();
		
		//Setzen des Reporttitels und dem Generierungsdatum
		report.setTitle("Alle Bewerbungen auf Ausschreibungen für " + getNameForOrgaUnit(o));
		report.setCreated(new Timestamp(System.currentTimeMillis()));
		
		//Generierung der Kopfdaten des Reports
		CompositeParagraph headerData = new CompositeParagraph();
		headerData.addSubParagraph(new SimpleParagraph("User: " + getNameForOrgaUnit(o)));
		headerData.addSubParagraph(new SimpleParagraph("Datum: " + report.getDateForTS()));
		headerData.addSubParagraph(new SimpleParagraph("Uhrzeit: " + report.getTimeForTS()));
		report.setHeaderData(headerData);
		
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
		report.setTitle("Alle ausgehenden Bewerbungen für " + getNameForOrgaUnit(o));
		report.setCreated(new Timestamp(System.currentTimeMillis()));
		
		//Generierung der Kopfdaten des Reports
		CompositeParagraph headerData = new CompositeParagraph();
		headerData.addSubParagraph(new SimpleParagraph("User: " + getNameForOrgaUnit(o)));
		headerData.addSubParagraph(new SimpleParagraph("Datum: " + report.getDateForTS()));
		headerData.addSubParagraph(new SimpleParagraph("Uhrzeit: " + report.getTimeForTS()));
		report.setHeaderData(headerData);
		
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
		report.setTitle("Ausgehende Bewerbungen für den Bewerber: " + getNameForOrgaUnit(applicant));
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
		report.setTitle("Beteiligungen an Projekten für den Bewerber: " + getNameForOrgaUnit(applicant));
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
		report.setTitle("Verflechtungen des Bewerbers: " + getNameForOrgaUnit(applicant));
		report.setCreated(new Timestamp(System.currentTimeMillis()));
		
		report.addSubReport(createAllApplicationsOfApplicantReport(applicant));
		report.addSubReport(createAllEnrollmentsOfApplicantReport(applicant));
		
		return report;
	}
	
	
	/**
	 * Methode zum Generieren eines Reports für die Verflechtungen aller Bewerber des Users.
	 */
	@Override
	public AllInterrelationsOfAllApplicantsOfUserReport createAllInterrelationsOfAllApplicantsOfUserReport (OrgaUnit o) throws IllegalArgumentException {
		AllInterrelationsOfAllApplicantsOfUserReport report = new AllInterrelationsOfAllApplicantsOfUserReport();
		
		//Setzen des Reporttitels und dem Generierungsdatum
		report.setTitle("Alle Verflechtungen der Bewerber");
		report.setCreated(new Timestamp(System.currentTimeMillis()));
		
		//Generierung der Kopfdaten des Reports
		CompositeParagraph headerData = new CompositeParagraph();
		headerData.addSubParagraph(new SimpleParagraph("User: " + getNameForOrgaUnit(o)));
		headerData.addSubParagraph(new SimpleParagraph("Datum: " + report.getDateForTS()));
		headerData.addSubParagraph(new SimpleParagraph("Uhrzeit: " + report.getTimeForTS()));
		report.setHeaderData(headerData);
		
		Vector<Project> projects = wpadmin.getProjectsForLeader(o);
		for(Project p : projects){
			Vector<Call> calls = wpadmin.getCallsFor(p);
			for(Call c : calls){
				Vector<Application> applications = wpadmin.getApplicationsFor(c);
				for(Application a : applications){
					OrgaUnit applicant = wpadmin.getOrgaUnitById(a.getOrgaUnitID());
					report.addSubReport(createAllInterrelationsOfApplicantReport(applicant));
				}
			}
		}
		
		return report;
	}
	
	/**
	 * Methode zum Generieren eines Reports für die Anzahl aller Bewerbungen aller Teilnehmer und dessen Stati (FanIn-Analyse)
	 * Die OrgaUnit wird übergeben, damit ggf. noch Header/Impressum-Daten hinzugefügt werden können.
	 */
	@Override
	public FanInOfApplicationsOfUserReport createFanInOfApplicationsOfUserReport(OrgaUnit o) throws IllegalArgumentException {
		
		FanInOfApplicationsOfUserReport report = new FanInOfApplicationsOfUserReport();
		
		//Setzen des Reporttitels und dem Generierungsdatum
		report.setTitle("FanIn: Anzahl der Bewerbungen");
		report.setCreated(new Timestamp(System.currentTimeMillis()));
		
		//Generierung der Kopfdaten des Reports
		CompositeParagraph headerData = new CompositeParagraph();
		headerData.addSubParagraph(new SimpleParagraph("User: " + getNameForOrgaUnit(o)));
		headerData.addSubParagraph(new SimpleParagraph("Datum: " + report.getDateForTS()));
		headerData.addSubParagraph(new SimpleParagraph("Uhrzeit: " + report.getTimeForTS()));
		report.setHeaderData(headerData);
		
		Row headline = new Row();
		
		//Kopfzeile mit den Überschriften der einzelnen Spalten im Report erstellen
		headline.addColumn(new Column("Name"));
		headline.addColumn(new Column("Typ"));
		headline.addColumn(new Column("laufend"));
		headline.addColumn(new Column("abgelehnt"));
		headline.addColumn(new Column("angenommen"));
		
		//Kopfzeile dem Report hinzufügen
		report.addRow(headline);
		
		Integer ongoing = 0;
		Integer assumed = 0;
		Integer rejected = 0;
		
		//Alle Organisations-Einheiten aus der Datenbank auslesen
		Vector<OrgaUnit> allOrgaUnits = new Vector<OrgaUnit>();
		allOrgaUnits = wpadmin.getAllOrgaUnits();
		
		//Relevanten Daten in den Vektor laden und Zeile für Zeile dem Report hinzufügen
		for (OrgaUnit ou : allOrgaUnits){
			Vector<Application> applications = wpadmin.getApplicationsFor(ou);
			for (Application a : applications){
				switch (a.getStatus()){
				case 0:
					ongoing++;
					break;
				case 1:
					assumed++;
					break;
				case 2:
					rejected++;
					break;
				}
			}

			Row rowToAdd = new Row();
			rowToAdd.addColumn(new Column(getNameForOrgaUnit(ou)));
			rowToAdd.addColumn(new Column(ou.getType()));
			rowToAdd.addColumn(new Column(ongoing.toString()));
			rowToAdd.addColumn(new Column(assumed.toString()));
			rowToAdd.addColumn(new Column(rejected.toString()));
			report.addRow(rowToAdd);
			
		}
		
		return report;
	}
	
	/**
	 * Methode zum Generieren eines Reports für die Anzahl aller Ausschreibungen aller Teilnehmer und dessen Stati (FanOut-Analyse)
	 * Die OrgaUnit wird übergeben, damit ggf. noch Header/Impressum-Daten hinzugefügt werden können.
	 */
	@Override
	public FanOutOfCallsOfUserReport createFanOutOfCallsOfUserReport(OrgaUnit o) throws IllegalArgumentException {
		
		FanOutOfCallsOfUserReport report = new FanOutOfCallsOfUserReport();
		
		//Setzen des Reporttitels und dem Generierungsdatum
		report.setTitle("FanOut: Anzahl der Ausschreibungen");
		report.setCreated(new Timestamp(System.currentTimeMillis()));
		
		//Generierung der Kopfdaten des Reports
		CompositeParagraph headerData = new CompositeParagraph();
		headerData.addSubParagraph(new SimpleParagraph("User: " + getNameForOrgaUnit(o)));
		headerData.addSubParagraph(new SimpleParagraph("Datum: " + report.getDateForTS()));
		headerData.addSubParagraph(new SimpleParagraph("Uhrzeit: " + report.getTimeForTS()));
		report.setHeaderData(headerData);
		
		Row headline = new Row();
		
		//Kopfzeile mit den Überschriften der einzelnen Spalten im Report erstellen
		headline.addColumn(new Column("Name"));
		headline.addColumn(new Column("Typ"));
		headline.addColumn(new Column("laufend"));
		headline.addColumn(new Column("erfolgreich besetzt"));
		headline.addColumn(new Column("laufend"));
		
		//Kopfzeile dem Report hinzufügen
		report.addRow(headline);
		
		Integer ongoing = 0;
		Integer successful = 0;
		Integer canceled = 0;
		
		//Alle Organisations-Einheiten aus der Datenbank auslesen
		Vector<OrgaUnit> allOrgaUnits = new Vector<OrgaUnit>();
		allOrgaUnits = wpadmin.getAllOrgaUnits();
		
		//Relevanten Daten in den Vektor laden und Zeile für Zeile dem Report hinzufügen
		for (OrgaUnit ou : allOrgaUnits){
			Vector<Project> projects = wpadmin.getProjectsForLeader(ou);
			for (Project p : projects){
				Vector<Call> calls = wpadmin.getCallsFor(p);
				for (Call c : calls){
					switch (c.getStatus()){
					case 0:
						ongoing++;
						break;
					case 1:
						successful++;
						break;
					case 2:
						canceled++;
						break;
					}
				}
			}

			Row rowToAdd = new Row();
			rowToAdd.addColumn(new Column(getNameForOrgaUnit(ou)));
			rowToAdd.addColumn(new Column(ou.getType()));
			rowToAdd.addColumn(new Column(ongoing.toString()));
			rowToAdd.addColumn(new Column(successful.toString()));
			rowToAdd.addColumn(new Column(canceled.toString()));
			report.addRow(rowToAdd);
		}

		
		return report;
	}
	
	/**
	 * Methode zum Generieren eines Reports für einen zusammengesetzen Report aus
	 * <code>FanInOfApplicationsOfUserReport</code> und <code>FanOutOfCallsOfUserReport</code> (FanIn-FanOut-Analyse
	 */
	@Override
	public FanInFanOutOfUserReport createFanInFanOutOfUserReport(OrgaUnit o) throws IllegalArgumentException {
		FanInFanOutOfUserReport report = new FanInFanOutOfUserReport();
		
		//Setzen des Reporttitels und dem Generierungsdatum
		report.setTitle("FanIn-FanOut-Analyse für alle Teilnehmer");
		report.setCreated(new Timestamp(System.currentTimeMillis()));
		
		//Generierung der Kopfdaten des Reports
		CompositeParagraph headerData = new CompositeParagraph();
		headerData.addSubParagraph(new SimpleParagraph("User: " + getNameForOrgaUnit(o)));
		headerData.addSubParagraph(new SimpleParagraph("Datum: " + report.getDateForTS()));
		headerData.addSubParagraph(new SimpleParagraph("Uhrzeit: " + report.getTimeForTS()));
		report.setHeaderData(headerData);
		
		//Hinzufügen der einzelnen Reports
		report.addSubReport(createFanInOfApplicationsOfUserReport(o));
		report.addSubReport(createFanOutOfCallsOfUserReport(o));
		
		return report;
	}
	
	/**
	 * Methode um die entsprechende Organisations-Einheit zu den übergebenen Login-Informationen zurückzugeben
	 * @param LoginInfo loginInfo
	 */
	@Override
	public OrgaUnit getOrgaUnitFor(LoginInfo loginInfo) throws IllegalArgumentException {
		return wpadmin.getOrgaUnitFor(loginInfo);
	}
	
	/**
	 * Methode um den Namen je nach Typ der Organisations-Einheit zurückzugeben (interner Gebrauch für Reports)
	 * @param OrgaUnit o
	 * @return String name
	 */
	private String getNameForOrgaUnit(OrgaUnit o){
		String name = "";
		String googleID = o.getGoogleID();
		
		if (o instanceof Person){
			Person p = wpadmin.getPersonByGoogleID(googleID);
			name = p.getFirstName() + " " + p.getLastName();
		} else if (o instanceof Team){
			Team t = wpadmin.getTeamByGoogleID(googleID);
			name = t.getName();
		} else if (o instanceof Organisation){
			Organisation org = wpadmin.getOrganisationByGoogleID(googleID);
			name = org.getName();
		} else {
			name = "Es muss sich wohl um ein unbekanntes Objekt ohne Namen handeln...";
		}
		
		//Zurückgeben des Namens als String
		return name;
	}
	
}
