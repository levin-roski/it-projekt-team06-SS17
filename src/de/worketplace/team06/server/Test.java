package de.worketplace.team06.server;

import java.util.Date;
import java.util.Vector;

import de.worketplace.team06.shared.bo.Marketplace;

public class Test {

	public static void main(String[] args) {
		

		
		WorketplaceAdministrationImpl admin = new WorketplaceAdministrationImpl();
		admin.init();
		
//		testOfCreateMarketplace(admin);
		testOfGetAllMarketplaces(admin);
		
		
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
		
		Marketplace m = null;
		m = admin.createMarketplace("Metallmarkt", "Alles mit Metall");
		
		Marketplace n = null;
		n = admin.createMarketplace("Gartenmarkt", "Alles im Garten");
		
		Marketplace t = null;
		
		t = admin.createMarketplace("Baumarkt", "Alles mit Bauen");
		}

}
