package de.worketplace.team06.client;

import com.google.gwt.core.client.GWT;

import de.worketplace.team06.shared.*;

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
	
	/**
	 * 
	 */
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
