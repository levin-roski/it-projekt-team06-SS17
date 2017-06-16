package de.worketplace.team06.server;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;


import de.worketplace.team06.shared.bo.*;

public class Test {

	public static void main(String[] args) {
		

		
		WorketplaceAdministrationImpl admin = new WorketplaceAdministrationImpl();
		admin.init();
	
		fillDatabaseWithExamples(admin);
		
		

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
	
	
	public static void fillDatabaseWithExamples (WorketplaceAdministrationImpl admin) {
		
		/*
		 * Unternehmen generieren
		 * Name, Beschreibung, Straße, PLZ, Stadt, GoogleID
		 */
		admin.createOrganisation("1Unternehmen", "1 Nice Unternehmen vong Niceigkeit her", "Stadtstrand 1", 13337, "Stutututtgart", "G1000");
		admin.createOrganisation("CoolStrich", "Malerei", "Hansestraße 4", 87765, "Hamburg", "G1001");
		admin.createOrganisation("Mediakram", "Medienagentur", "Friedrich-Schiller-Straße 34a", 70174, "Stuttgart", "G1003");
		
		
		
		/*
		 * Teams generieren
		 * Name, Beschreibung, Mitgliederzahl, GoogleID
		 */
		admin.createTeam("Superknechte", "Ist wohl ein Team", 1000, "G2000");
		admin.createTeam("Glücksspechte", "Mehrfaches Glück bedeutet Können", 500, "G2001");
		admin.createTeam("Team Alpha", "Alphasaurier", 30, "G2002");
		
		/*
		 * Personen generieren
		 * Vorname, Nachname, Straße, PLZ, Stadt, Beschreibung, GoogleID
		 */
		admin.createPerson("Hans", "Mayer", "Lagistraße 5", 86637, "Augsburg", "Ein Mensch", "G3000");
		admin.createPerson("Thomas", "Mueller", "Schuttstr 6", 38299, "Langweid", "Auch ein Mensch", "G3001");
		admin.createPerson("Jesus", "Christus", "Wüstenweg 4", 12345, "Jerusalem", "Kein Mensch, Gott.", "G3002");
		
//		// 3 Sekunden warten, um sicherzustellen, dass die Daten in der DB gespeichert wurden
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		Person p1 = admin.getPersonByGoogleID("G3000");
		System.out.println(p1.getGoogleID());
		
		admin.createMarketplace("Marktplatz Blau", "Dieser Marktplatz ist für Blaue", p1);
		
		OrgaUnit u1 = admin.getOrganisationByGoogleID("G1001");
		admin.createMarketplace("Marktplatz Rot", "Dieser Marktplatz ist für Rote", u1);
		
//		// 3 Sekunden warten, um sicherzustellen, dass die Daten in der DB gespeichert wurden
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
//		Marketplace m1 = admin.getMarketplaceByID(1);
//		Person p2 = admin.getPersonByGoogleID("G3001");
//		Date startdate = new Date();
//		Date enddate = new Date();
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String date = sdf.format("yyyy-MM-dd");
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//		startdate = sdf.parse("2017-06-11");
//		enddate = sdf.parse("2017-08-02");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		/*
//		 * Projekte generieren
//		 */
//		admin.createProject(m1, "Testprojekt", "Ein Projekt zu Testzwecken", p2, u1, startdate, enddate);
//		admin.createProject(m1, "Testprojekt 2", "Ein neues Projekt", p2, u1, startdate, enddate);
//		
//		// 3 Sekunden warten, um sicherzustellen, dass die Daten in der DB gespeichert wurden
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		Project pro1 = admin.getProjectByID(1);
//		Project pro2 = admin.getProjectByID(2);
//		Date deadline = new Date();
//		PartnerProfile pp1 = new PartnerProfile();
//		admin.createPartnerProfileFor(p2);
//		pp1 = admin.getPartnerProfileFor(p2);
//		try {
//			deadline = sdf.parse("2017-04-11");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		admin.createCall(pro1, p2, pp1, "Testcall 1", "", deadline);
		
		
		
		
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
		Person p = admin.getPersonByGoogleID("G256061");
		admin.deletePerson(p);
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
