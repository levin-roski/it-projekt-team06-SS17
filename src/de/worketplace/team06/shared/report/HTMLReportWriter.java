package de.worketplace.team06.shared.report;

import java.util.Vector;
//import de.worketplace.team06.shared.report.*;

/**
 * Ein <code>ReportWriter</code>, der Reports mittels HTML formatiert. Das im
 * Zielformat vorliegende Ergebnis wird in der Variable <code>reportText</code>
 * abgelegt und kann nach Aufruf der entsprechenden Prozessierungsmethode mit
 * <code>getReportText()</code> ausgelesen werden.
 * 
 * @author Thies
 */
public class HTMLReportWriter extends ReportWriter {

	/**
	 * Diese Variable wird mit dem Ergebnis einer Umwandlung (vgl.
	 * <code>process...</code>-Methoden) belegt. Format: HTML-Text
	 */
	private String reportText = "";
	
	/**
	 * Zurücksetzen der Variable <code>reportText</code>.
	 */
	public void resetReportText() {
	  this.reportText = "";
	}
	
	/**
	 * Umwandeln eines <code>Paragraph</code>-Objekts in HTML.
	 * 
	 * @param p der Paragraph
	 * @return HTML-Text
	 */
	public String paragraph2HTML(Paragraph p) {
	  if (p instanceof CompositeParagraph) {
	    return this.paragraph2HTML((CompositeParagraph) p);
	  }
	  else {
	    return this.paragraph2HTML((SimpleParagraph) p);
	  }
	}
	
	/**
	 * Umwandeln eines <code>CompositeParagraph</code>-Objekts in HTML.
	 * 
	 * @param p der CompositeParagraph
	 * @return HTML-Text
	 */
	public String paragraph2HTML(CompositeParagraph p) {
	  StringBuffer result = new StringBuffer();
	
	  for (int i = 0; i < p.getNumParagraphs(); i++) {
	    result.append("<p>" + p.getParagraphAt(i) + "</p>");
	  }
	
	  return result.toString();
	}
	
	/**
	 * Umwandeln eines <code>SimpleParagraph</code>-Objekts in HTML.
	 * 
	 * @param p der SimpleParagraph
	 * @return HTML-Text
	 */
	public String paragraph2HTML(SimpleParagraph p) {
	  return "<p>" + p.toString() + "</p>";
	}
	
	/**
	 * HTML-Header-Text produzieren.
	 * 
	 * @return HTML-Text
	 */
	public String getHeader() {
	  StringBuffer result = new StringBuffer();
	
	  result.append("<html><head><title></title></head><body>");
	  return result.toString();
	}
	
	/**
	 * HTML-Trailer-Text produzieren.
	 * 
	 * @return HTML-Text
	 */
	public String getTrailer() {
	  return "</body></html>";
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
		
		result.append("<H2>" + r.getTitle() + "</H2>");
		result.append("<table><tr>");

		Vector<Row> rows = r.getRows();
		result.append("<table>");
		for (int i = 0; i < rows.size(); i++) {
			Row row = rows.elementAt(i);
		    result.append("<tr>");
		    for (int j = 0; j < row.getNumColumns(); j++) {
		    	result.append("<td>" + row.getColumnAt(j) + "</td>");
		    }
		    result.append("</tr>");
		}
		
		result.append("</table>");
		this.reportText = result.toString();
		
	}

	@Override
	public void process(AllEnrollmentsOfApplicantReport r) {
		this.resetReportText();
		
		StringBuffer result = new StringBuffer();
		
		result.append("<H2>" + r.getTitle() + "</H2>");
		result.append("<table><tr>");

		Vector<Row> rows = r.getRows();
		result.append("<table>");
		for (int i = 0; i < rows.size(); i++) {
			Row row = rows.elementAt(i);
		    result.append("<tr>");
		    for (int j = 0; j < row.getNumColumns(); j++) {
		    	result.append("<td>" + row.getColumnAt(j) + "</td>");
		    }
		    result.append("</tr>");
		}
		
		result.append("</table>");
		this.reportText = result.toString();
		
	}

	@Override
	public void process(AllInterrelationsOfApplicantReport r) {
		this.resetReportText();
		
		StringBuffer result = new StringBuffer();
		
		result.append("<H1>" + r.getTitle() + "</H1>");
		result.append("<table><tr>");
		if (r.getHeaderData() != null){
			result.append("<td>" + paragraph2HTML(r.getHeaderData()) + "</td></tr>");
		}
		//result.append("<td valign=\"top\">" + paragraph2HTML(r.getImprint()) + "</td>");
		result.append("<tr><td>" + "Erstellungsdatum: " + r.getCreated().toString() + "</td></tr></table>");

		for (int i = 0; i < r.getNumSubReports(); i++) {

			if (r.getSubReportAt(i) instanceof AllApplicationsOfApplicantReport){
				AllApplicationsOfApplicantReport subReport = (AllApplicationsOfApplicantReport) r.getSubReportAt(i);
				this.process(subReport);
				
			} else if (r.getSubReportAt(i) instanceof AllEnrollmentsOfApplicantReport){
				AllEnrollmentsOfApplicantReport subReport = (AllEnrollmentsOfApplicantReport) r.getSubReportAt(i);
				this.process(subReport);
			}
			
		    result.append(this.reportText + "\n");

		    this.resetReportText();
		}
		this.reportText = result.toString();
		
	}
	
	@Override
	public void process(AllInterrelationsOfAllApplicantsOfUserReport r) {
		this.resetReportText();
		
		StringBuffer result = new StringBuffer();
		
		result.append("<H1>" + r.getTitle() + "</H1>");
		result.append("<table><tr>");
		if (r.getHeaderData() != null){
			result.append("<td>" + paragraph2HTML(r.getHeaderData()) + "</td></tr>");
		}
		//result.append("<td valign=\"top\">" + paragraph2HTML(r.getImprint()) + "</td>");
		result.append("<tr><td>" + "Erstellungsdatum: " + r.getCreated().toString() + "</td></tr></table>");

		for (int i = 0; i < r.getNumSubReports(); i++) {

			AllInterrelationsOfApplicantReport subReport = (AllInterrelationsOfApplicantReport) r.getSubReportAt(i);
			this.process(subReport);
				
		    result.append(this.reportText + "\n");

		    this.resetReportText();
		}
		this.reportText = result.toString();

	}
	
	@Override
	public void process(FanInOfApplicationsOfUserReport r) {
		simpleReportProcess(r);
		
	}

	@Override
	public void process(FanOutOfCallsOfUserReport r) {
		simpleReportProcess(r);
		
	}
	
	@Override
	public void process(FanInFanOutOfUserReport r) {
		this.resetReportText();
		
		StringBuffer result = new StringBuffer();
		
		result.append("<H1>" + r.getTitle() + "</H1>");
		result.append("<table><tr>");
		if (r.getHeaderData() != null){
			result.append("<td>" + paragraph2HTML(r.getHeaderData()) + "</td></tr>");
		}
		//result.append("<td valign=\"top\">" + paragraph2HTML(r.getImprint()) + "</td>");
		result.append("<tr><td>" + "Erstellungsdatum: " + r.getCreated().toString() + "</td></tr></table>");

		for (int i = 0; i < r.getNumSubReports(); i++) {

			if (r.getSubReportAt(i) instanceof FanInOfApplicationsOfUserReport){
				FanInOfApplicationsOfUserReport subReport = (FanInOfApplicationsOfUserReport) r.getSubReportAt(i);
				this.process(subReport);
				
			} else if (r.getSubReportAt(i) instanceof FanOutOfCallsOfUserReport){
				FanOutOfCallsOfUserReport subReport = (FanOutOfCallsOfUserReport) r.getSubReportAt(i);
				this.process(subReport);
			}
			
		    result.append(this.reportText + "\n");

		    this.resetReportText();
		}
		this.reportText = result.toString();
		
	}
	
	/**
	 * Einheitliche Methode zum Erstellen von SimpleReports in HTML-Format.
	 * Da die Logik der Erstellung von SimpleReports und der Aufbaue identisch ist,
	 * genügt jeweils der Aufruf dieser Methode.
	 * @param r
	 */
	private void simpleReportProcess(SimpleReport r){
		this.resetReportText();
		
		StringBuffer result = new StringBuffer();
		
		result.append("<H1>" + r.getTitle() + "</H1>");
		result.append("<table><tr>");
		if (r.getHeaderData() != null){
			result.append("<td>" + paragraph2HTML(r.getHeaderData()) + "</td></tr>");
		}
		//result.append("<td valign=\"top\">" + paragraph2HTML(r.getImprint()) + "</td>");
		result.append("<tr><td>" + "Erstellungsdatum: " + r.getCreated().toString() + "</td></tr></table>");

		Vector<Row> rows = r.getRows();
		result.append("<table>");
		for (int i = 0; i < rows.size(); i++) {
			Row row = rows.elementAt(i);
		    result.append("<tr>");
		    for (int j = 0; j < row.getNumColumns(); j++) {
		    	result.append("<td>" + row.getColumnAt(j) + "</td>");
		    }
		    result.append("</tr>");
		}
		
		result.append("</table>");
		this.reportText = result.toString();
	}
	
	/**
	 * Auslesen des Ergebnisses der zuletzt aufgerufenen Prozessierungsmethode.
	 * 
	 * @return ein String im HTML-Format
	 */
	public String getReportText() {
	  return this.reportText;
	}

}