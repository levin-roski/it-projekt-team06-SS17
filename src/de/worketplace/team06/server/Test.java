package de.worketplace.team06.server;

import java.util.Date;

import de.worketplace.team06.shared.bo.Marketplace;

public class Test {

	public static void main(String[] args) {
		

		
		WorketplaceAdministrationImpl admin = new WorketplaceAdministrationImpl();
		admin.init();
		
		Marketplace m = null;
		m = admin.createMarketplace("Metallmarkt", "Alles mit Metall");
		
		System.out.println(m.getTitle() + m.getDescription());
		

	}

}
