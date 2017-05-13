package de.worketplace.team06.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;

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
	private static WorketplaceAdministrationAsync editor = null;
	
	/**
	 * 
	 */
	private static ReportGeneratorAsync reportGenerator = null;
	
	/**
	 * 
	 */
	private static final String LOGGER_NAME = "worketplace Web Client";
	
	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger(LOGGER_NAME);
	
	/**
	 * 
	 */
	public static Logger getLogger() {
		return log;
	}
	
	/**
	 * 
	 * @return
	 */
	public static WorketplaceAdministrationAsync getEditor() {
		if (editor == null) {
			editor = GWT.create(Editor.class);
		}
		
		return editor;
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
