package de.worketplace.team06.shared.report;

import java.util.Vector;

/**
 * Ein <code>ReportWriter</code>, der Reports mittels Plain Text formatiert. Das
 * im Zielformat vorliegende Ergebnis wird in der Variable
 * <code>reportText</code> abgelegt und kann nach Aufruf der entsprechenden
 * Prozessierungsmethode mit <code>getReportText()</code> ausgelesen werden.
 * 
 * @author Thies
 */
public class PlainTextReportWriter extends ReportWriter {

	/**
	 * Diese Variable wird mit dem Ergebnis einer Umwandlung (vgl.
	 * <code>process...</code>-Methoden) belegt. Format: Text
	 */
	private String reportText = "";
	
	 /**
	 * Zurücksetzen der Variable <code>reportText</code>.
	 */
	public void resetReportText() {
	  this.reportText = "";
	}
	
	 /**
	 * Header-Text produzieren.
	 * 
	 * @return Text
	 */
	public String getHeader() {
	  // Wir benötigen für Demozwecke keinen Header.
	  return "";
	}
	
	 /**
	 * Trailer-Text produzieren.
	 * 
	 * @return Text
	 */
	public String getTrailer() {
	  // Wir verwenden eine einfache Trennlinie, um das Report-Ende zu markieren.
	  return "___________________________________________";
	}
	
	@Override
	public void process(AllCallsReport r) {
		this.resetReportText();
		
		StringBuffer result = new StringBuffer();
		result.append("Title: " + r.getTitle());
		
		if (r.getHeaderData() != null){
			result.append("Header: " + r.getHeaderData());
		}
		
		result.append("Generiert am: " + r.getCreated());
		
		Vector<Row> allRows = r.getRows();
		
		for (Row rowToAppend : allRows){
			result.append(rowToAppend);
		}
		
		this.reportText = result.toString();
			
	}
	
	@Override
	public void process(AllCallsMatchingWithUserReport r) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void process(AllApplicationsForCallsOfUserReport r) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void process(AllApplicationsOfUserToCallsReport r) {
		// TODO Auto-generated method stub
		
	}
	
	  /**
	   * Auslesen des Ergebnisses der zuletzt aufgerufenen Prozessierungsmethode.
	   * 
	   * @return ein String bestehend aus einfachem Text
	   */
	  public String getReportText() {
	    return this.getHeader() + this.reportText + this.getTrailer();
	  }
 
  
}
