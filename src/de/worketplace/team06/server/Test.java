package de.worketplace.team06.server;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import de.worketplace.team06.shared.bo.Marketplace;
import de.worketplace.team06.shared.bo.OrgaUnit;
import de.worketplace.team06.shared.bo.Person;

public class Test {

	public static void main(String[] args) {
		

		
		WorketplaceAdministrationImpl admin = new WorketplaceAdministrationImpl();
		admin.init();
	
		testOfGetPerson(admin);
//		testOfCreateOrganisation(admin);
//		testOfCreateTeam(admin);
//		testOfCreatePerson(admin);
//		testOfCreateMarketplace(admin);
//		testOfGetAllMarketplaces(admin);
		
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
	
	public static void testOfGetPerson(WorketplaceAdministrationImpl admin) {
		Person p = admin.getPersonByGoogleID("G1337");
		
		System.out.println("ID: " + p.getID());
		System.out.println("Vorname: " + p.getFirstName());
		System.out.println("Nachname: " + p.getLastName());
		System.out.println("Straße: " + p.getStreet());
		System.out.println("PLZ: " + p.getZipcode());
		System.out.println("Stadt: " + p.getCity());
	}
	
	public static void testOfCreatePerson(WorketplaceAdministrationImpl admin) {
	
		admin.createPerson("Hans", "Mayer", "lagistraße 6", 86637, "Augsburg", "Ein Mensch", "G1337");
		//admin.createPerson("Thomas", "Mueller", "schuttstr 6", 86637, "Langweid", "Auch Ein Mensch", "G256060");
		
	}
	
	public static void testOfCreateTeam(WorketplaceAdministrationImpl admin) {
		//admin.createTeam("Ist wohl ein Team", "G2349jf", "Spermateam", 1000);
		admin.createTeam("ahjooo", "Gagasdg234", "SSchluckteam", 500);
		
	}
	
	public static void testOfCreateOrganisation(WorketplaceAdministrationImpl admin) {
		admin.createOrganisation("1 Nice Unternehmen vong Niceigkeit her", "rofliksdeh1337lol", "1Unternehmen", "Stadtstrand 1", 13337, "Stutututtgart");	
	}

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
		Person p = new Person();
		p.setID(1);
		
		Marketplace m = null;
		m = admin.createMarketplace("Metallmarkt", "Alles mit Metall", p);
		
		Marketplace n = null;
		n = admin.createMarketplace("Gartenmarkt", "Alles im Garten", p);
		
		Marketplace t = null;
		
		t = admin.createMarketplace("Baumarkt", "Alles mit Bauen", p);
		}

}
