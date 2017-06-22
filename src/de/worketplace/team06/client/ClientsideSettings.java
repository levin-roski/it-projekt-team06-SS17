package de.worketplace.team06.client;

import com.google.gwt.core.client.GWT;

import de.worketplace.team06.shared.*;
import de.worketplace.team06.shared.bo.OrgaUnit;

/**
 * Klasse mit Eigenschaften und Diensten, die f�r alle Client-seitigen Klassen relevant sind
 *
 * @author Roski
 * @version 1.0
 * @since 08.05.2017
 * 
 * TODO extends CommonSettings hinzuf�gen?
 *
 */
public class ClientsideSettings {
	/**
	 * 
	 */
	private static WorketplaceAdministrationAsync worketplaceAdministration = null;
	private static OrgaUnit currentUser = null;
	
	/**
	 * 
	 */
//	private static ReportGeneratorAsync reportGenerator = null;
	
	/**
	 * 
	 * @return
	 */
	public static WorketplaceAdministrationAsync getWorketplaceAdministration() {
		if (worketplaceAdministration == null) {
			worketplaceAdministration = GWT.create(WorketplaceAdministration.class);
		}
		
		return worketplaceAdministration;
	}
	
	public static OrgaUnit getCurrentUser() {
		return currentUser;
	}
	
	public static void setCurrentUser(OrgaUnit pCurrentUser) {
		currentUser = pCurrentUser;
	}
	
	/**
	 * 
	 * @return
	 */
//	public static ReportGeneratorAsync getReportGenerator() {
//		if (reportGenerator == null) {
//			reportGenerator= GWT.create(ReportGenerator.class);
//		}
//		
//		return reportGenerator;
//	}
}
