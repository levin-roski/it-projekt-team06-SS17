package de.worketplace.team06.client;

import java.util.logging.Logger;

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
	private static EditorAsync editor = null;
	
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
	private static final Logger Log = Logger.getLogger(LOGGER_NAME);
	
	/**
	 * 
	 */
	public static Logger getLogger() {
		return Log;
	}
	
	/**
	 * 
	 * @return
	 */
	public static EditorAsync getEditor() {
		// TODO getEditor schreiben
	}
	
	/**
	 * 
	 * @return
	 */
	public static ReportGeneratorAsync getReportGenerator() {
		// TODO getReportGenerator schreiben
	}
}
