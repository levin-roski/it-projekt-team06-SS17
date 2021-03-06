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
		simpleReportProcess(r);
	}
	
	@Override
	public void process(AllCallsOfUserReport r) {
		simpleReportProcess(r);
	}
	
	@Override
	public void process(AllCallsMatchingWithUserReport r) {
		simpleReportProcess(r);
	}
	
	@Override
	public void process(AllApplicationsForCallsOfUserReport r) {
		simpleReportProcess(r);
	}
	
	@Override
	public void process(AllApplicationsOfUserToCallsReport r) {
		simpleReportProcess(r);
	}

	@Override
	public void process(AllApplicationsOfApplicantReport r) {
		this.resetReportText();
		
		StringBuffer result = new StringBuffer();
		result.append("Sub-Reporttitel: " + r.getTitle() + "\n");
		
		Vector<Row> allRows = r.getRows();
		
		for (Row rowToAppend : allRows){
			for (int i = 0; i < rowToAppend.getNumColumns(); i++) {
				result.append(rowToAppend.getColumnAt(i) + "\t ; \t");
		    }

			result.append("\n");			
		}
		
		this.reportText = result.toString();
	}

	@Override
	public void process(AllEnrollmentsOfApplicantReport r) {
		this.resetReportText();
		
		StringBuffer result = new StringBuffer();
		result.append("Sub-Reporttitel: " + r.getTitle() + "\n");
		
		Vector<Row> allRows = r.getRows();
		
		for (Row rowToAppend : allRows){
			for (int i = 0; i < rowToAppend.getNumColumns(); i++) {
				result.append(rowToAppend.getColumnAt(i) + "\t ; \t");
		    }

			result.append("\n");			
		}
		
		this.reportText = result.toString();
	}

	@Override
	public void process(AllInterrelationsOfApplicantReport r) {
		this.resetReportText();
		
		StringBuffer result = new StringBuffer();
		result.append("Reporttitel: " + r.getTitle() + "\n");
		
		if (r.getHeaderData() != null){
			result.append("Header: " + r.getHeaderData() + "\n");
		}
		
		result.append("Generiert am: " + r.getCreated() + "\n\n");
		
		for (int i = 0; i < r.getNumSubReports(); i++) {

			if (r.getSubReportAt(i) instanceof AllApplicationsOfApplicantReport){
				AllApplicationsOfApplicantReport subReport = (AllApplicationsOfApplicantReport) r.getSubReportAt(i);
				this.process(subReport);
				
			} else if (r.getSubReportAt(i) instanceof AllEnrollmentsOfApplicantReport){
				AllEnrollmentsOfApplicantReport subReport = (AllEnrollmentsOfApplicantReport) r.getSubReportAt(i);
				this.process(subReport);
			}
			
		    // Ein Form Feed wäre hier statt der 5 Leerzeilen auch denkbar...
		    result.append(this.reportText + "\n\n\n\n\n");

		    /*
		     * Nach jeder Übersetzung eines Teilreports und anschließendem Auslesen
		     * sollte die Ergebnisvariable zurückgesetzt werden.
		     */
		    this.resetReportText();
		}
		this.reportText = result.toString();
		
	}

	@Override
	public void process(AllInterrelationsOfAllApplicantsOfUserReport r) {
		this.resetReportText();
		
		StringBuffer result = new StringBuffer();
		result.append("Reporttitel: " + r.getTitle() + "\n");
		
		if (r.getHeaderData() != null){
			result.append("Header: " + r.getHeaderData() + "\n");
		}
		
		result.append("Generiert am: " + r.getCreated() + "\n\n");
		
		for (int i = 0; i < r.getNumSubReports(); i++) {

			AllInterrelationsOfApplicantReport subReport = (AllInterrelationsOfApplicantReport) r.getSubReportAt(i);
			this.process(subReport);
				
		    // Ein Form Feed wäre hier statt der 5 Leerzeilen auch denkbar...
		    result.append(this.reportText + "\n\n\n\n\n");

		    /*
		     * Nach jeder Übersetzung eines Teilreports und anschließendem Auslesen
		     * sollte die Ergebnisvariable zurückgesetzt werden.
		     */
		    this.resetReportText();
		}
		this.reportText = result.toString();
		
	}
	
	@Override
	public void process(FanInOfApplicationsOfUserReport r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process(FanOutOfCallsOfUserReport r) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void process(FanInFanOutOfUserReport r) {
		// TODO Auto-generated method stub
		
	}
	
	private void simpleReportProcess(SimpleReport r){
		this.resetReportText();
		
		StringBuffer result = new StringBuffer();
		result.append("Reporttitel: " + r.getTitle() + "\n");
		
		if (r.getHeaderData() != null){
			result.append("Header: " + r.getHeaderData() + "\n");
		}
		
		result.append("Generiert am: " + r.getCreated() + "\n\n");
		
		Vector<Row> allRows = r.getRows();
		
		for (Row rowToAppend : allRows){
			for (int i = 0; i < rowToAppend.getNumColumns(); i++) {
				result.append(rowToAppend.getColumnAt(i) + "\t ; \t");
		    }

			result.append("\n");			
		}
		
		this.reportText = result.toString();
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
