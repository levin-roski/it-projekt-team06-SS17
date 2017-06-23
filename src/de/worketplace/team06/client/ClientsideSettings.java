package de.worketplace.team06.client;

import com.google.gwt.core.client.GWT;

import de.worketplace.team06.client.gui.MainPanel;
import de.worketplace.team06.shared.*;
import de.worketplace.team06.shared.bo.OrgaUnit;

/**
 * Klasse mit Eigenschaften und Diensten, die für alle Client-seitigen Klassen relevant sind
 *
 * @author Roski
 * @version 1.0
 * @since 08.05.2017
 * 
 * TODO extends CommonSettings hinzufügen?
 *
 */
public class ClientsideSettings {
	/**
	 * 
	 */
	private static WorketplaceAdministrationAsync worketplaceAdministration = null;
	private static OrgaUnit currentUser = null;
	private static MainPanel mainPanel = null;
	private static ReportGeneratorAsync reportGenerator = null;
	
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
	
	public static void setMainPanel(MainPanel pMainPanel) {
		mainPanel = pMainPanel;
	}
	
	public static MainPanel getMainPanel() {
		return mainPanel;
	}
	
	/**
	 * 
	 * @return
	 */
	public static ReportGeneratorAsync getReportGenerator() {
		if (reportGenerator == null) {
			reportGenerator= GWT.create(ReportGenerator.class);
		}
		
		return reportGenerator;
	}
}
