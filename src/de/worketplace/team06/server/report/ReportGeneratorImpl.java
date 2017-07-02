package de.worketplace.team06.server.report;

import java.util.Date;
import java.text.DateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.worketplace.team06.server.WorketplaceAdministrationImpl;
import de.worketplace.team06.shared.ReportGenerator;
import de.worketplace.team06.shared.WorketplaceAdministration;
import de.worketplace.team06.shared.bo.Application;
import de.worketplace.team06.shared.bo.Call;
import de.worketplace.team06.shared.bo.Enrollment;
import de.worketplace.team06.shared.bo.LoginInfo;
import de.worketplace.team06.shared.bo.OrgaUnit;
import de.worketplace.team06.shared.bo.Organisation;
import de.worketplace.team06.shared.bo.PartnerProfile;
import de.worketplace.team06.shared.bo.Person;
import de.worketplace.team06.shared.bo.Project;
import de.worketplace.team06.shared.bo.Property;
import de.worketplace.team06.shared.bo.Rating;
import de.worketplace.team06.shared.bo.Team;
import de.worketplace.team06.shared.report.AllApplicationsForCallsOfUserReport;
import de.worketplace.team06.shared.report.AllApplicationsOfApplicantReport;
import de.worketplace.team06.shared.report.AllApplicationsOfUserToCallsReport;
import de.worketplace.team06.shared.report.AllCallsMatchingWithUserReport;
import de.worketplace.team06.shared.report.AllCallsReport;
import de.worketplace.team06.shared.report.AllEnrollmentsOfApplicantReport;
import de.worketplace.team06.shared.report.AllInterrelationsOfAllApplicantsOfUserReport;
import de.worketplace.team06.shared.report.AllInterrelationsOfApplicantReport;
import de.worketplace.team06.shared.report.Column;
import de.worketplace.team06.shared.report.CompositeParagraph;
import de.worketplace.team06.shared.report.FanInFanOutOfUserReport;
import de.worketplace.team06.shared.report.FanInOfApplicationsOfUserReport;
import de.worketplace.team06.shared.report.FanOutOfCallsOfUserReport;
import de.worketplace.team06.shared.report.Report;
import de.worketplace.team06.shared.report.Row;
import de.worketplace.team06.shared.report.SimpleParagraph;

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
		report.setCreated(new Date());
		
		//Hinzufügen der Kopfdaten
		report.setHeaderData(createHeaderData(o, report));
		
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
		report.setCount(calls.size());
		for (Call c : calls){
			Project p = wpadmin.getProjectByID(c.getProjectID());
			Row rowToAdd = new Row();
			rowToAdd.addColumn(new Column(c.getTitle()));
			rowToAdd.addColumn(new Column(c.getDescription()));
			rowToAdd.addColumn(new Column(p.getTitle()));
			rowToAdd.addColumn(new Column(convertDate(c.getDeadline())));
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
		report.setCreated(new Date());
		
		//Hinzufügen der Kopfdaten
		report.setHeaderData(createHeaderData(o, report));
		
		Row headline = new Row();
		
		//Kopfzeile mit den Überschriften der einzelnen Spalten im Report erstellen
		headline.addColumn(new Column("Titel"));
		headline.addColumn(new Column("Beschreibung"));
		headline.addColumn(new Column("Projekt"));
		headline.addColumn(new Column("Deadline"));
		headline.addColumn(new Column("Status"));
		
		//Kopfzeile dem Report hinzufügen
		report.addRow(headline);
		
		int j = 0;
		//Relevanten Daten in den Vektor laden und Zeile für Zeile dem Report hinzufügen
		Vector<Project> projects = wpadmin.getProjectsForLeader(o);
		j = 0;
		for (Project p : projects){
			Vector<Call> calls = wpadmin.getCallsFor(p);
			for (Call c : calls){
				Row rowToAdd = new Row();
				rowToAdd.addColumn(new Column(c.getTitle()));
				rowToAdd.addColumn(new Column(c.getDescription()));
				rowToAdd.addColumn(new Column(p.getTitle()));
				rowToAdd.addColumn(new Column(convertDate(c.getDeadline())));
				rowToAdd.addColumn(new Column(c.getStatusString()));
				report.addRow(rowToAdd);
				j++;
			}
		}
		
		report.setCount(j);
		return report;
	}
	
	/**
	 * Methode zum Generieren eines Reports für alle Ausschreibungen, die zum Partnerprofil der übergebenen Organisations-Einheit passt.
	 * @param o
	 * @return report
	 */
	@Override
	public AllCallsMatchingWithUserReport createAllCallsMatchingWithUserReport(OrgaUnit o) throws IllegalArgumentException {
		
		//Partnerprofil des angemeldeten Users auslesen
		PartnerProfile pp = wpadmin.getPartnerProfileFor(o);
		
		
		//Alle Eigenschaften des angemeldeten Users in einen Vektor einlesen
		Vector<Property> propertiesOfOrgaUnit = wpadmin.getAllPropertiesFor(pp);
		
		
		//Alle Ausschreibungen in einen Vektor einlesen
		Vector<Call> allCalls = wpadmin.getAllCalls();
		
		
		//Vektor für die zutreffenden Ausschreibungen instanziieren
		Vector<Call> matchingCalls = new Vector<Call>();
		
		//Erstellung einer Instanz des Reports
		AllCallsMatchingWithUserReport report = new AllCallsMatchingWithUserReport();
		
		//Setzen des Reporttitels und dem Generierungsdatum
		report.setTitle("Alle interessanten Ausschreibungen für den Benutzer");
		report.setCreated(new Date());
		
		//Hinzufügen der Kopfdaten
		report.setHeaderData(createHeaderData(o, report));
		
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
		
		if (allCalls != null){
		for(Call c : allCalls){
			PartnerProfile tempPartnerProfile = wpadmin.getPartnerProfileFor(c);
			Vector<Property> callProperties = wpadmin.getAllPropertiesFor(tempPartnerProfile);
			
			if (callProperties != null){
			for (Property callProperty : callProperties){
				
				if(propertiesOfOrgaUnit != null){
				for (Property orgaUnitProperty : propertiesOfOrgaUnit){
					if (callProperty.equals(orgaUnitProperty)){
						if(!matchingCalls.contains(c)){
							c.setMatchingCount(1);
							matchingCalls.addElement(c);
						}
						else{
							for(Call c2 : matchingCalls){
								if (c2.equals(c)){
									Integer temp = c2.getMatchingCount();
									temp++;
									c2.setMatchingCount(temp);
								}
							}
						}
					}
				}
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
		report.setCount(matchingCalls.size());
		for (Call call : matchingCalls){
			Project proj = wpadmin.getProjectByID(call.getProjectID());
			Row rowToAdd = new Row();
			rowToAdd.addColumn(new Column(call.getTitle()));
			rowToAdd.addColumn(new Column(convertDate(call.getDeadline())));
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
		report.setTitle("Alle Bewerbungen auf Ausschreibungen");
		report.setCreated(new Date());
		
		//Hinzufügen der Kopfdaten
		report.setHeaderData(createHeaderData(o, report));
		
		Row headline = new Row();
		
		//Kopfzeile mit den Überschriften der einzelnen Spalten im Report erstellen
		headline.addColumn(new Column("Name"));
		headline.addColumn(new Column("Bewerbungstext"));
		headline.addColumn(new Column("Status"));
		headline.addColumn(new Column("Rating"));
		headline.addColumn(new Column("Projekt"));
		
		//Kopfzeile dem Report hinzufügen
		report.addRow(headline);
		
		//Relevanten Daten in den Vektor laden und Zeile für Zeile dem Report hinzufügen
		Vector<Project> projects = wpadmin.getProjectsForLeader(o);
		int j = 0;
		for (Project p : projects){
			Vector<Call> calls = wpadmin.getCallsFor(p);
			for (Call c : calls){
				Vector<Application> applications = wpadmin.getApplicationsFor(c);
				for (Application a : applications){
					Row rowToAdd = new Row();
					OrgaUnit ou = wpadmin.getOrgaUnitById(a.getOrgaUnitID());
					rowToAdd.addColumn(new Column(this.getNameForOrgaUnit(ou)));
					rowToAdd.addColumn(new Column(a.getText()));
					rowToAdd.addColumn(new Column(a.getStatusString()));
					Rating r = wpadmin.getRatingFor(a);
					try {
						rowToAdd.addColumn(new Column(r.getRating().toString()));
					} catch (NullPointerException e) {
						rowToAdd.addColumn(new Column("Kein Rating"));
						//e.printStackTrace();
					}
					
					rowToAdd.addColumn(new Column(p.getTitle()));
					report.addRow(rowToAdd);
					j++;
				}
			}
		}
		report.setCount(j);
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
		report.setTitle("Alle ausgehenden Bewerbungen für");
		report.setCreated(new Date());
		
		//Generierung der Kopfdaten des Reports
		CompositeParagraph headerData = new CompositeParagraph();
		headerData.addSubParagraph(new SimpleParagraph("User: " + getNameForOrgaUnit(o)));
		headerData.addSubParagraph(new SimpleParagraph("Datum: " + getDateForReport(report)));
		headerData.addSubParagraph(new SimpleParagraph("Uhrzeit: " + getTimeForReport(report)));
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
		report.setCount(applications.size());
		for(Application a : applications){
			Call c = wpadmin.getCallByID(a.getCallID());
			Row rowToAdd = new Row();
			rowToAdd.addColumn(new Column(c.getTitle()));
			rowToAdd.addColumn(new Column(convertDate(c.getDeadline())));
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
		report.setTitle("Ausgehende Bewerbungen für " + getNameForOrgaUnit(applicant));
		report.setCreated(new Date());
		
		Row headline = new Row();
		
		//Kopfzeile mit den Überschriften der einzelnen Spalten im Report erstellen
		headline.addColumn(new Column("Projekt"));
		headline.addColumn(new Column("Ausschreibung"));
		headline.addColumn(new Column("Bewerbungstext"));
		headline.addColumn(new Column("Status"));
		
		//Kopfzeile dem Report hinzufügen
		report.addRow(headline);
		
		Vector<Application> applications = wpadmin.getApplicationsFor(applicant);
		report.setCount(applications.size());
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
		report.setTitle("Beteiligungen an Projekten für " + getNameForOrgaUnit(applicant));
		report.setCreated(new Date());
		
		Row headline = new Row();
		
		//Kopfzeile mit den Überschriften der einzelnen Spalten im Report erstellen
		headline.addColumn(new Column("Projekt"));
		headline.addColumn(new Column("Beteiligungsbeginn"));
		headline.addColumn(new Column("Beteiligungsende"));
		
		//Kopfzeile dem Report hinzufügen
		report.addRow(headline);
		
		Vector<Enrollment> enrollments = wpadmin.getEnrollmentFor(applicant);
		report.setCount(enrollments.size());
		for(Enrollment e : enrollments){
			Project p = wpadmin.getProjectByID(e.getProjectID());
			Row rowToAdd = new Row();
			rowToAdd.addColumn(new Column(p.getTitle()));
			rowToAdd.addColumn(new Column(convertDate(e.getStartDate())));
			rowToAdd.addColumn(new Column(convertDate(e.getEndDate())));
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
		report.setTitle("Verflechtungen: " + getNameForOrgaUnit(applicant));
		report.setCreated(new Date());
		
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
		report.setTitle("Alle Verflechtungen meiner Bewerber");
		report.setCreated(new Date());
		
		//Generierung der Kopfdaten des Reports
		CompositeParagraph headerData = new CompositeParagraph();
		headerData.addSubParagraph(new SimpleParagraph("User: " + getNameForOrgaUnit(o)));
		headerData.addSubParagraph(new SimpleParagraph("Datum: " + getDateForReport(report)));
		headerData.addSubParagraph(new SimpleParagraph("Uhrzeit: " + getTimeForReport(report)));
		report.setHeaderData(headerData);
		
		Vector<Project> projects = wpadmin.getProjectsForLeader(o);
		int j = 0;
		for(Project p : projects){
			Vector<Call> calls = wpadmin.getCallsFor(p);
			for(Call c : calls){
				Vector<Application> applications = wpadmin.getApplicationsFor(c);
				for(Application a : applications){
					OrgaUnit applicant = wpadmin.getOrgaUnitById(a.getOrgaUnitID());
					report.addSubReport(createAllInterrelationsOfApplicantReport(applicant));
					j++;
				}
			}
		}
		report.setCount(j);
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
		report.setCreated(new Date());
		
		//Generierung der Kopfdaten des Reports
		CompositeParagraph headerData = new CompositeParagraph();
		headerData.addSubParagraph(new SimpleParagraph("User: " + getNameForOrgaUnit(o)));
		headerData.addSubParagraph(new SimpleParagraph("Datum: " + getDateForReport(report)));
		headerData.addSubParagraph(new SimpleParagraph("Uhrzeit: " + getTimeForReport(report)));
		report.setHeaderData(headerData);
		
		Row headline = new Row();
		
		//Kopfzeile mit den Überschriften der einzelnen Spalten im Report erstellen
		headline.addColumn(new Column("Name"));
		headline.addColumn(new Column("Typ"));
		headline.addColumn(new Column("laufend"));
		headline.addColumn(new Column("angenommen"));
		headline.addColumn(new Column("abgelehnt"));
		
		//Kopfzeile dem Report hinzufügen
		report.addRow(headline);
		
		Integer ongoing = 0;
		Integer assumed = 0;
		Integer rejected = 0;
		
		//Alle Organisations-Einheiten aus der Datenbank auslesen
		Vector<OrgaUnit> allOrgaUnits = new Vector<OrgaUnit>();
		allOrgaUnits = wpadmin.getAllOrgaUnits();
		report.setCount(allOrgaUnits.size());
		
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
		report.setCreated(new Date());
		
		//Generierung der Kopfdaten des Reports
		CompositeParagraph headerData = new CompositeParagraph();
		headerData.addSubParagraph(new SimpleParagraph("User: " + getNameForOrgaUnit(o)));
		headerData.addSubParagraph(new SimpleParagraph("Datum: " + getDateForReport(report)));
		headerData.addSubParagraph(new SimpleParagraph("Uhrzeit: " + getTimeForReport(report)));
		report.setHeaderData(headerData);
		
		Row headline = new Row();
		
		//Kopfzeile mit den Überschriften der einzelnen Spalten im Report erstellen
		headline.addColumn(new Column("Name"));
		headline.addColumn(new Column("Typ"));
		headline.addColumn(new Column("laufend"));
		headline.addColumn(new Column("erfolgreich besetzt"));
		headline.addColumn(new Column("abgebrochen"));
		
		//Kopfzeile dem Report hinzufügen
		report.addRow(headline);
		
		Integer ongoing = 0;
		Integer successful = 0;
		Integer canceled = 0;
		
		//Alle Organisations-Einheiten aus der Datenbank auslesen
		Vector<OrgaUnit> allOrgaUnits = new Vector<OrgaUnit>();
		allOrgaUnits = wpadmin.getAllOrgaUnits();
		report.setCount(allOrgaUnits.size());
		
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
		report.setCreated(new Date());
		
		//Generierung der Kopfdaten des Reports
		CompositeParagraph headerData = new CompositeParagraph();
		headerData.addSubParagraph(new SimpleParagraph("User: " + getNameForOrgaUnit(o)));
		headerData.addSubParagraph(new SimpleParagraph("Datum: " + getDateForReport(report)));
		headerData.addSubParagraph(new SimpleParagraph("Uhrzeit: " + getTimeForReport(report)));
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
	
	/**
	 * Methode zum Auslesen aller Bewerber für alle Ausschreibungen, die der übergebenen Person zugeordnet sind.
	 */
	@Override
	public Vector<OrgaUnit> getAllApplicantsForAllCallsFrom(Person person){
		Vector<OrgaUnit> applicants = new Vector<OrgaUnit>();
		Vector<Project> projects = wpadmin.getAllProjects();
		
		for (Project pro : projects){
			if (pro.getProjectLeaderID() == person.getID()){
				Vector<Call> calls = wpadmin.getCallsFor(pro);
				for (Call c : calls){
					Vector<Application> apps = wpadmin.getApplicationsFor(c);
					for (Application a : apps){
						OrgaUnit o = wpadmin.getOrgaUnitById(a.getOrgaUnitID());
						applicants.addElement(o);
					}
				}
			}
		}
		return applicants;
	}
	
	/**
	 * Methode zum extrahieren des Datums eines Date-Objekts und Rückgabe als String
	 * @param ts
	 * @return date String
	 */
	public String convertDate(Date d){
		String date = "Kein Datum gesetzt!";
		DateFormat df;
		try {
			if(d != null){
				df = DateFormat.getDateInstance(DateFormat.MEDIUM);
				date = df.format(d);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * Methode zum extrahieren des Datums eines Date-Objekts und Rückgabe als String
	 * @param ts
	 * @return date String
	 */
	public String getDateForReport(Report r){
		String date = "Kein Datum gesetzt!";
		DateFormat df;
		try {
			if(r.getCreated() != null){
				df = DateFormat.getDateInstance(DateFormat.FULL);
				date = df.format(r.getCreated());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * Methode zum extrahieren der Uhrzeit eines Date-Objekts und Rückgabe als String
	 * @param ts
	 * @return time String
	 */
	public String getTimeForReport(Report r){
		String time = "Keine Uhrzeit gesetzt!";
		DateFormat df;
		try {
			if (r.getCreated() != null){
				df = DateFormat.getTimeInstance(DateFormat.MEDIUM);
				time = df.format(r.getCreated());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;	
	}
	/**
	 * Einheitliche Methode zur Erstellung der Kopfdaten.
	 * Rückgabe des Zusammengesetzten Paragraphen.
	 * @param o
	 * 
	 * @return CompositeParagraph
	 */
	private CompositeParagraph createHeaderData(OrgaUnit o, Report report){
		//Generierung der Kopfdaten des Reports
		CompositeParagraph headerData = new CompositeParagraph();
		try {
			headerData.addSubParagraph(new SimpleParagraph("User: " + getNameForOrgaUnit(o)));
		} catch (NullPointerException e) {
			headerData.addSubParagraph(new SimpleParagraph("User: Unbekannter Nutzer"));
			e.printStackTrace();
		}
		headerData.addSubParagraph(new SimpleParagraph("Datum: " + getDateForReport(report)));
		headerData.addSubParagraph(new SimpleParagraph("Uhrzeit: " + getTimeForReport(report)));
		
		return headerData;
	}
	
}
