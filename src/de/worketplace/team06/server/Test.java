package de.worketplace.team06.server;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;


import de.worketplace.team06.shared.bo.*;

public class Test {

	public static void main(String[] args) {
		
		
		
		WorketplaceAdministrationImpl admin = new WorketplaceAdministrationImpl();
		admin.init();
		
		//testreportblub(admin);
		
		Person projectleader = admin.getPersonByID(10);
		Vector<Application> allApplications = admin.getApplicationsForProjectLeader(projectleader);
		
		for (Application a : allApplications){
			System.out.println("Bewerbungstext: "+ a.getText() + "Created :" + a.getCreated());
		}
		
		
//		Person pers1 = admin.getPersonByID(7);
//		
//		PartnerProfile pp1 = admin.getPartnerProfileFor(pers1);
//		
//		System.out.println("Datum PartnerProfile Person:" +  pp1.getCreated());
//		admin.createProperty(pp1, "Berufserfahrung", "1 Jahr");
//		admin.createProperty(pp1, "Alter", "22");
//		
//		
//		Call call1 = admin.getCallByID(1);
//		Call call2 = admin.getCallByID(2);
//		
//		PartnerProfile pp2 = admin.getPartnerProfileFor(call1);
//		PartnerProfile pp3 = admin.getPartnerProfileFor(call2);
//		
//		System.out.println("Datum PartnerProfile Call:" +  pp2.getCreated());
//		System.out.println("Datum PartnerProfile Call2:" +  pp3.getCreated());
//		
//		admin.createProperty(pp2, "Berufserfahrung", "1 Jahr");
//		admin.createProperty(pp2, "Alter", "22");
//		
//		admin.createProperty(pp3, "Berufserfahrung", "2 Jahre");
//		admin.createProperty(pp3, "Alter", "22");
		
		
		
		
		

		
		
		
//		fillDatabaseWithExamples(admin);
		
//		testOfFindByProjectID(admin);
		
//		testOfGetProjectsFor(admin);
		
//		testOfDeleteRating(admin);
//		testOfDeleteApplication(admin);
//		testOfDeleteEnrollment(admin);
// 		testOfDeleteCall(admin);
// 		testOfDeleteProperty(admin);
//		testOfDeleteProject(admin);
//		testOfDeletePerson(admin);
		
		
//		testOfCreateOrganisation(admin);
//		testOfCreateTeam(admin);
//		testOfCreatePerson(admin);	
//		testOfCreateMarketplace(admin);
		
//      testOfSaveOrganisation(admin);		
//		testOfSaveTeam(admin);
//		testOfSavePerson(admin);
//		testOfDeletePerson(admin);
//		testOfGetPerson(admin);
		
//		testOfGetAllMarketplaces(admin);
//		testOfGetMarketplaceFor(admin);
//		testOfSaveMarketplace(admin);
		
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		
//		java.util.Date date = getItSomehow();
//		Timestamp timestamp = new Timestamp(date.getTime());
//		preparedStatement = connection.prepareStatement("SELECT * FROM tbl WHERE ts > ?");
//		preparedStatement.setTimestamp(1, timestamp);
//		
//		
//		
//		Timestamp timestamp = resultSet.getTimestamp("ts");
//		java.util.Date date = timestamp; // You can just upcast.
//		
//		System.out.println(timestamp);
//      private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String currentTime = sdf.format(m.getCreated());	
	}
	
	
	public static void testreportblub(WorketplaceAdministrationImpl wpadmin){
		
		
		
		
		//Alle Ausschreibungen ausgeben
		Vector<Call> allCalls = wpadmin.getAllCalls();
		System.out.println("Alle Calls ausgeben! ");
		for (Call c : allCalls){
			System.out.println("Titel : " + c.getTitle() + " | PartnerProfile ID: " +  c.getPartnerProfileID());
		}
		System.out.println("___________________________________");
		System.out.println("");
		
		
		//Person abrufen und ausgeben
		Person pers1 = wpadmin.getPersonByID(7);
		System.out.println("Informationen zum Personenobjekt ausgeben");
		System.out.println("Vorname : " + pers1.getFirstName() + " | PartnerProfile ID der Person : " +pers1.getPartnerProfileID());
		System.out.println("___________________________________");
		System.out.println("");
		
		
		//Partnerprofil der Person ausgeben
		PartnerProfile pp1 = wpadmin.getPartnerProfileFor(pers1);
		System.out.println("Informationen zum Partnerprofil der Person");
		System.out.println("ID-Partnerprofil: " + pp1.getID() + " | Erstellungsdatum : " + pp1.getCreated());
		System.out.println("___________________________________");
		System.out.println("");
		
		
		//Alle Properties der Person ausgeben
		Vector<Property> allPropsOfOu = wpadmin.getAllPropertiesFor(pp1);
		System.out.println("Properties der Person");
		for (Property p : allPropsOfOu){
			System.out.println("Name : " + p.getName() + " | Wert :" + p.getValue());
		}
		System.out.println("___________________________________");
		System.out.println("");
		
		
		//Vector für die personalisierten Ausschreibungen für den Benutzer erstellen
		Vector<Call> matchingCalls = new Vector<Call>();
		
		
	
		//Alle Ausschreibungen durchlaufen 
		System.out.println("Verschachtelete Schleifen durchlaufen");
		for (Call c : allCalls){
			//System.out.print("Ausschreibungstitel : " + c.getTitle());
			PartnerProfile callPartnerProfile = wpadmin.getPartnerProfileFor(c);
			//System.out.print(" | mit partnerProfileId : " + callPartnerProfile.getID());
			Vector<Property> callProperties = wpadmin.getAllPropertiesFor(callPartnerProfile);
			 for (Property callProperty : callProperties){
				 for (Property orgaUnitProperty : allPropsOfOu ){
					 if (callProperty.equals(orgaUnitProperty)){
						 System.out.println("Matching Call : " + c.getTitle());
					 }
				 }
			 }
		}
		
		

//		
//		for(Call c : allCalls){
//			System.out.println("Callname: " + c.getTitle());
//			PartnerProfile tempPartnerProfile = wpadmin.getPartnerProfileFor(c);
//			Vector<Property> tempProperties = wpadmin.getAllPropertiesFor(tempPartnerProfile);
//			System.out.println("temp Properties index 0" + tempProperties.get(0).getValue());
//			
//			
//			for (Property prop : tempProperties){
//				for (Property prop2 : allPropsOfOu){
//					System.out.println("Property Call "+ prop.getValue());
//					System.out.println("Property OU " + prop.getValue());
//					if (prop.getValue().equals(prop2)){
//						System.out.println(prop.getValue() + prop2.getValue());
//						if(!matchingCalls.contains(c)){
//							matchingCalls.addElement(c);
//							c.setMatchingCount(1);
//						}
//						else{
//							int temp = c.getMatchingCount();
//							temp++;
//							c.setMatchingCount(temp);
//						}
//					}
//				}
//			}
//			
//			System.out.println("Ausschreibung gematched: " + matchingCalls.get(0).getTitle());
//			System.out.println("2. Ausschreibung gematched: " + matchingCalls.get(1).getTitle());
//		}
	}
	
	
	public static void fillDatabaseWithExamples (WorketplaceAdministrationImpl admin) {
		
		/*
		 * Unternehmen generieren
		 * Name, Beschreibung, Straße, PLZ, Stadt, GoogleID
		 */
		Organisation o1 = admin.createOrganisation("1Unternehmen", "1 Nice Unternehmen vong Niceigkeit her", "Stadtstrand 1", 13337, "Stutututtgart", "G1000");

		
		PartnerProfile part1 = admin.createPartnerProfileFor(o1);
		
		
		System.out.println(part1.getCreated());
		System.out.println(part1.getLastEdit());

		
		admin.createProperty(part1, "Organisationsbezeichnung", "Lechwerke AG");
		
		System.out.println(part1.getCreated());
		System.out.println(part1.getLastEdit());
		
		
		Organisation o2 = admin.createOrganisation("CoolStrich", "Malerei", "Hansestraße 4", 87765, "Hamburg", "G1001");
		PartnerProfile part2 = admin.createPartnerProfileFor(o2);
		admin.createProperty(part2, "Entwickleranzahl", "5");
		
		Organisation o3 = admin.createOrganisation("Mediakram", "Medienagentur", "Friedrich-Schiller-Straße 34a", 70174, "Stuttgart", "G1003");
		PartnerProfile part3 = admin.createPartnerProfileFor(o3);
			
		/*
		 * Teams generieren
		 * Name, Beschreibung, Mitgliederzahl, GoogleID
		 */
		Team t1 = admin.createTeam("Superknechte", "Ist wohl ein Team", 1000, "G2000");
		PartnerProfile part4 = admin.createPartnerProfileFor(t1);
		admin.createProperty(part4, "Teammitglieder", "50");
		
		Team t2 = admin.createTeam("Glücksspechte", "Mehrfaches Glück bedeutet Können", 500, "G2001");
		PartnerProfile part5 = admin.createPartnerProfileFor(t2);
		
		Team t3 = admin.createTeam("Team Alpha", "Alphasaurier", 30, "G2002");
		PartnerProfile part6 = admin.createPartnerProfileFor(t3);
		
		/*
		 * Personen generieren
		 * Vorname, Nachname, Straße, PLZ, Stadt, Beschreibung, GoogleID
		 */
		Person p1 = admin.createPerson("Hans", "Mayer", "Lagistraße 5", 86637, "Augsburg", "Ein Mensch", "G3000");
		PartnerProfile part7 = admin.createPartnerProfileFor(p1);
		admin.createProperty(part7, "Berufserfahrung", "4 Jahre");
		
		Person p2 = admin.createPerson("Thomas", "Mueller", "Schuttstr 6", 38299, "Langweid", "Auch ein Mensch", "G3001");
		PartnerProfile part8 = admin.createPartnerProfileFor(p2);
		
		Person p3 = admin.createPerson("Jesus", "Christus", "Wüstenweg 4", 12345, "Jerusalem", "Kein Mensch, Gott.", "G3002");
		PartnerProfile part9 = admin.createPartnerProfileFor(p3);
		
		
		// 3 Sekunden warten, um sicherzustellen, dass die Daten in der DB gespeichert wurden
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Person person1 = admin.getPersonByGoogleID("G3000");
		System.out.println("Person, GoogleID: " + person1.getGoogleID());
		
		Team team1 = admin.getTeamByGoogleID("G2000");
		System.out.println("Team, GoogleID: " + team1.getGoogleID());
		
		Organisation orga1 = admin.getOrganisationByGoogleID("G1000");
		System.out.println("Organisation, GoogleID: " + orga1.getGoogleID());
		
		
		
		admin.createMarketplace("Marktplatz Blau", "Dieser Marktplatz ist für Blaue", person1);
		
		
		admin.createMarketplace("Marktplatz Rot", "Dieser Marktplatz ist für Rote", orga1);
		
		
		admin.createMarketplace("Marktplatz Pink", "Dieser Marktplatz ist für Pinke", team1);
		
		// 3 Sekunden warten, um sicherzustellen, dass die Daten in der DB gespeichert wurden
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Marketplace m1 = admin.getMarketplaceByID(1);
		
		Vector<Marketplace> Vm2 = admin.getMarketplacesFor(orga1);
		
		
		/*
		 * Projekte generieren
		 */
		Date startdate = new Date();
		Date enddate = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
		startdate = sdf.parse("2017-06-11");
		enddate = sdf.parse("2017-08-02");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		admin.createProject(Vm2.firstElement(), "Orgaprojekt", "Ein Projekt für Organisationen", person1, startdate, enddate);
		admin.createProject(m1, "Testprojekt", "Ein Projekt zu Testzwecken", p2, startdate, enddate);
		admin.createProject(m1, "Testprojekt 2", "Ein neues Projekt", p2, startdate, enddate);
		
//		// 3 Sekunden warten, um sicherzustellen, dass die Daten in der DB gespeichert wurden
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		
		/*
		 * Calls generieren
		 */
		
		Project pro1 = admin.getProjectByID(1);
		Project pro2 = admin.getProjectByID(2);
		Project pro3 = admin.getProjectByID(3);
		Date deadline = new Date();
		

		
		//TODO: PartnerProfile Mapper funktioniert nicht. Fehler @ SQL Syntax
		//admin.createPartnerProfileFor(p2);
		//pp1 = admin.getPartnerProfileFor(p2);
		
		try {
			deadline = sdf.parse("2017-04-11");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Call c1 = admin.createCall(pro1, p2, "Testcall 1", "test", deadline);
		Call c2 = admin.createCall(pro2, p3, "Testcall 2", "test2", deadline);
		Call c3 = admin.createCall(pro3, p1, "Testcall 3", "test3", deadline);
		
		System.out.println("Deadline: "+ c1.getDeadline());
		System.out.println("Timestamp: "+ c1.getCreated());
		
		
		Application a = admin.applyFor(c1, p1, "Ich bewerbe mich mit ganz viel Freude und so geblubber! ");
		Application a2 = admin.applyFor(c2, p2, "Hab halt mal ne Bewerbung geschickt, nehmt mich oder nicht");
		
		Rating r = admin.rateApplication(a, (float) 0.6, "Ahjoo hört si doch ganz vielversprechend a");
		Rating r2 = admin.rateApplication(a2, (float) 1, "Du bisch mir so ein Schlingel");
		
		
		try {
			startdate = sdf.parse("2017-08-15");
			enddate = sdf.parse("2017-10-16");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		admin.createEnrollment(pro1, p1, r, startdate, enddate, 5);
		
		
		
			
	}
	
	
	
	
	
	
	
	
	
	
	
	// DELETE Methods
	
	public static void testOfDeleteRating(WorketplaceAdministrationImpl admin){
		
		Person p1 = admin.getPersonByGoogleID("G3001");
		
		Vector<Project> myprojects = admin.getProjectsForLeader(p1);
		
		Vector<Call> mycalls = admin.getCallsFor(myprojects.firstElement());
		
		Vector<Application> apps = admin.getApplicationsFor(mycalls.firstElement());
		
		System.out.println("Applications' RatingID" + apps.firstElement().getRatingID());
		System.out.println("Applications' RatingID" + apps.firstElement().getText());
		
		Rating r = admin.getRatingFor(apps.firstElement());
		
		System.out.println("Rating: " + r.getRatingStatement());
		
		admin.deleteRating(r);
		
	}
	
	
public static void testOfDeleteApplication(WorketplaceAdministrationImpl admin){
		
		Person p1 = admin.getPersonByGoogleID("G3001");
		
		Vector<Project> myprojects = admin.getProjectsForLeader(p1);
		
		Vector<Call> mycalls = admin.getCallsFor(myprojects.firstElement());
		
		Vector<Application> apps = admin.getApplicationsFor(mycalls.firstElement());
		
		System.out.println("Applications' Bewerbungstext" + apps.firstElement().getText());
		
		admin.deleteApplication(apps.firstElement());
		
	}

public static void testOfDeleteEnrollment(WorketplaceAdministrationImpl admin){
	
	Person p1 = admin.getPersonByGoogleID("G3001");
	
	Vector<Project> myprojects = admin.getProjectsForLeader(p1);
	
	Vector<Enrollment> enrollments = admin.getEnrollmentFor(myprojects.firstElement());
	
	System.out.println("Enrollment Startdatum: " + enrollments.firstElement().getStartDate());
	
	admin.deleteEnrollment(enrollments.firstElement());
	
}

public static void testOfDeleteCall(WorketplaceAdministrationImpl admin){
Person p1 = admin.getPersonByGoogleID("G3001");
	
	Vector<Project> myprojects = admin.getProjectsForLeader(p1);
	
	Vector<Call> mycalls = admin.getCallsFor(myprojects.firstElement());
	
	System.out.println("Call der gelöscht wird: " + mycalls.firstElement().getDescription());
	
	admin.deleteCall(mycalls.firstElement());
	
	
}	

public static void testOfDeleteProperty(WorketplaceAdministrationImpl admin){
	
Person p1 = admin.getPersonByGoogleID("G3000");
	
	PartnerProfile part1 = admin.getPartnerProfileFor(p1);
	
	System.out.println("partnerprofile: " + part1.getCreated());
	
	Vector<Property> allprops = admin.getAllPropertiesFor(part1);
	
	System.out.println("Property: " + allprops.firstElement().getName());
	
	admin.deleteProperty(allprops.firstElement());
	
}	

public static void testOfDeleteProject(WorketplaceAdministrationImpl admin){
	Person p1 = admin.getPersonByGoogleID("G3001");
	Vector<Project> allProjects = admin.getProjectsForLeader(p1);
	
	System.out.println("Projekt wird gelöscht: "+ allProjects.firstElement().getDescription());
	
	admin.deleteProject(allProjects.firstElement());
	
}


	
public static void testOfGetProjectsFor(WorketplaceAdministrationImpl admin){
	Marketplace m = admin.getMarketplaceByID(1);
	
	Vector<Project> allps = admin.getProjectsFor(m);
	
	for(Project p : allps){
		System.out.println("Projektmarktplatz Titel: "+p.getTitle());
	}
}
	
//	public static void testDeleteAll(WorketplaceAdministrationImpl admin){
//		
//		
//		
//		
//		Vector<Project> allProjects = admin.getAllProjects();
//		
//		if (allProjects != null){
//			for (Project p : allProjects){
//				admin.deleteProject(p);
//			}
//		}
//		
//		Vector<Person> allPersons = new Vector<Person>();
//		allPersons.add(admin.getPersonByGoogleID("G3000"));
//		allPersons.add(admin.getPersonByGoogleID("G3001"));
//		allPersons.add(admin.getPersonByGoogleID("G3002"));
//		
//		if (allPersons != null){
//			for (Person per : allPersons){
//				admin.deletePerson(per);
//			}
//		}
//		
//		Vector<Team> allTeams = new Vector<Team>();
//		allTeams.add(admin.getTeamByGoogleID("G2000"));
//		allTeams.add(admin.getTeamByGoogleID("G2001"));
//		allTeams.add(admin.getTeamByGoogleID("G2002"));
//		
//		if (allTeams != null){
//			for (Team t : allTeams){
//				admin.deleteTeam(t);
//			}
//		}
//		
//		Vector<Organisation> allOrganisations = new Vector<Organisation>();
//		allOrganisations.add(admin.getOrganisationByGoogleID("G2000"));
//		allOrganisations.add(admin.getOrganisationByGoogleID("G2001"));
//		allOrganisations.add(admin.getOrganisationByGoogleID("G2002"));
//		
//		if (allOrganisations != null){
//			for (Organisation o : allOrganisations){
//				admin.deleteOrganisation(o);
//			}
//		}
//		
//	}
	
/*
 * Call Methoden
 */


public static void testOfFindByProjectID(WorketplaceAdministrationImpl admin){
	
	Project pro = admin.getProjectByID(2);
	Vector<Enrollment> allenrollsforpro = admin.getEnrollmentFor(pro);
	
	for (Enrollment e : allenrollsforpro){
		System.out.println("Startdatum: " + e.getStartDate());
	}
}

	

	/*
	 * Organisation
	 *
	 */
	
	public static void testOfCreateOrganisation(WorketplaceAdministrationImpl admin) {
		admin.createOrganisation("1Unternehmen", "1 Nice Unternehmen vong Niceigkeit her", "Stadtstrand 1", 13337, "Stutututtgart", "rofliksdeh1337lol");
		admin.createOrganisation("CoolStrich", "Malerei", "Hansestraße 4", 87765, "Hamburg", "Gmal234");
	}
	
	private static void testOfSaveOrganisation(WorketplaceAdministrationImpl admin) {
		Organisation o = admin.getOrganisationByGoogleID("Gmal234");
		System.out.println("Hier kommt der Organisationsname: " + o.getName());
		
		o.setDescription("Blub");
		o.setPartnerProfileID(999);
		
		o.setName("Blub");
		o.setStreet("Blub");
		o.setCity("Blub");
		o.setZipcode(999);
		
		admin.saveOrganisation(o);
		
	}
	
	
	/*
	 * TEAM
	 * 
	 */
	
	public static void testOfCreateTeam(WorketplaceAdministrationImpl admin) {
		admin.createTeam("Superknechte", "Ist wohl ein Team", 1000, "G2349jf");
		admin.createTeam("Glücksspechte", "ahjooo", 500, "Gagasdg234");
		
	}

	private static void testOfSaveTeam(WorketplaceAdministrationImpl admin) {
		Team t = admin.getTeamByGoogleID("G2349jf");
		System.out.println("Hier kommt der Teamname: " + t.getName());
		
		t.setDescription("Blub");
		t.setPartnerProfileID(999);
		t.setMembercount(999);
		t.setName("Blub");
		
		admin.saveTeam(t);
		
	}

	
	/*
	 * 
	 * Person
	 * 
	 */

	private static void testOfSavePerson(WorketplaceAdministrationImpl admin) {
		Person p = admin.getPersonByGoogleID("G1337");
		p.setCity("Blub");
		p.setDescription("Blub");
		p.setFirstName("Blub");
		p.setLastName("Blub");
		p.setStreet("Blub");
		p.setPartnerProfileID(999);
		p.setZipcode(97898);
		
		admin.savePerson(p);
		
	}


	public static void testOfDeletePerson(WorketplaceAdministrationImpl admin) {
		Person p = admin.getPersonByGoogleID("G3001");
		
		System.out.println("Die folgende Person wird gelöscht: " + p.getFirstName());
		try {
			admin.deletePerson(p);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WindowAlertException e) {
			
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	public static void testOfGetPerson(WorketplaceAdministrationImpl admin) {
		Person p = admin.getPersonByGoogleID("G1337");
		
		System.out.println("ID: " + p.getID());
		System.out.println("Created: " + p.getCreated());
		System.out.println("GoogleID: " + p.getGoogleID());
		System.out.println("Description: " + p.getDescription());
		System.out.println("PartnerProfileID: " + p.getPartnerProfileID());
		System.out.println("Type: " + p.getType());
		
		System.out.println("Vorname: " + p.getFirstName());
		System.out.println("Nachname: " + p.getLastName());
		System.out.println("Straße: " + p.getStreet());
		System.out.println("PLZ: " + p.getZipcode());
		System.out.println("Stadt: " + p.getCity());
	}
	
	public static void testOfCreatePerson(WorketplaceAdministrationImpl admin) {
	
		admin.createPerson("Hans", "Mayer", "lagistraße 6", 86637, "Augsburg", "Ein Mensch", "G1337");
		admin.createPerson("Thomas", "Mueller", "schuttstr 6", 86637, "Langweid", "Auch Ein Mensch", "G256060");
		
	}
	
	
	
	

	
	/*
	 * Marketplacemethoden
	 */
	
	
	public static void testOfGetAllMarketplaces(WorketplaceAdministrationImpl admin) {
		
		Vector<Marketplace> myVector = admin.getAllMarketplaces();
		
		for (Marketplace laufvariable : myVector)
		{
		    System.out.println(laufvariable.getTitle());
		    System.out.println(laufvariable.getDescription());
		    System.out.println(laufvariable.getID());
		    System.out.println(laufvariable.getCreated());
		    System.out.println();
		}
		
	}
	
	
	public static void testOfCreateMarketplace(WorketplaceAdministrationImpl admin) {
		Person p = admin.getPersonByGoogleID("G3000");
		System.out.println("Vorname"+ p.getFirstName());
	
		
		
		
		Marketplace m = null;
		m = admin.createMarketplace("Metallmarkt", "Alles mit Metall", p);
		
		Marketplace n = null;
		n = admin.createMarketplace("Gartenmarkt", "Alles im Garten", p);
		
		Marketplace t = null;
		
		t = admin.createMarketplace("Baumarkt", "Alles mit Bauen", p);
	}
	
	private static void testOfGetMarketplaceFor(WorketplaceAdministrationImpl admin) {
		Person p = admin.getPersonByGoogleID("G1337");
		
		System.out.println("Hier kommt die Person für welche die Marktplätze ermittelt werden sollen ! ");
		System.out.println("ID: " + p.getID());
		System.out.println("Created: " + p.getCreated());
		System.out.println("GoogleID: " + p.getGoogleID());
		System.out.println("Description: " + p.getDescription());
		System.out.println("PartnerProfileID: " + p.getPartnerProfileID());
		System.out.println("Type: " + p.getType());
		
		System.out.println("Vorname: " + p.getFirstName());
		System.out.println("Nachname: " + p.getLastName());
		System.out.println("Straße: " + p.getStreet());
		System.out.println("PLZ: " + p.getZipcode());
		System.out.println("Stadt: " + p.getCity());
		
		Vector<Marketplace> myVector = admin.getMarketplacesFor(p);
		
		for (Marketplace laufvariable : myVector)
		{
		    System.out.println("Titel: " + laufvariable.getTitle());
		    System.out.println("Beschreibung : " + laufvariable.getDescription());
		    System.out.println("ID : " + laufvariable.getID());
		    System.out.println("Erstellungszeitstempel: " + laufvariable.getCreated());
		    System.out.println("OrgaUnitID: " + laufvariable.getOrgaUnitID());
		    System.out.println();
		}
		
	}
	
	private static void testOfSaveMarketplace(WorketplaceAdministrationImpl admin) {
Person p = admin.getPersonByGoogleID("G1337");
		
		System.out.println("Hier kommt die Person für welche die Marktplätze ermittelt werden sollen ! ");
		System.out.println("ID: " + p.getID());
		System.out.println("Created: " + p.getCreated());
		System.out.println("GoogleID: " + p.getGoogleID());
		System.out.println("Description: " + p.getDescription());
		System.out.println("PartnerProfileID: " + p.getPartnerProfileID());
		System.out.println("Type: " + p.getType());
		
		System.out.println("Vorname: " + p.getFirstName());
		System.out.println("Nachname: " + p.getLastName());
		System.out.println("Straße: " + p.getStreet());
		System.out.println("PLZ: " + p.getZipcode());
		System.out.println("Stadt: " + p.getCity());
		
		Vector<Marketplace> myVector = admin.getMarketplacesFor(p);
		Marketplace m = myVector.elementAt(0);
		
		m.setDescription("Ich habe ein Update verführt.");
		m.setTitle("Ich auch ändern Titel");
		admin.saveMarketplace(m);
		
	}
	
	

}
