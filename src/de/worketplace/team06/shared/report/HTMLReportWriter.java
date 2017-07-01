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
		simpleReportProcessWithoutHeader(r);
	}

	@Override
	public void process(AllEnrollmentsOfApplicantReport r) {
		simpleReportProcessWithoutHeader(r);
	}

	@Override
	public void process(AllInterrelationsOfApplicantReport r) {
		this.resetReportText();
		
		//Ein Stringbuffer, an den alle folgenden HTML Inhalte angehängt werden
		StringBuffer result = new StringBuffer();
		
		/*
		 * Hinzufügen der Überschrift und den Kopfdaten in HTML-Form.
		 * Die Methode paragraph2HTML übersetzte die Kopfdaten des Reports r in ein korrektes HTML-Format.
		 */
		result.append("<h1 class=\"report\">" + r.getTitle() + "</h1>");
		result.append("<table class=\"headerdata\"><tr>");
		if (r.getHeaderData() != null){
			result.append("<td class=\"tdheaderdata\">" + paragraph2HTML(r.getHeaderData()) + "</td></tr>");
		}
		//result.append("<td valign=\"top\">" + paragraph2HTML(r.getImprint()) + "</td>");
		result.append("</table>");

		/*
		 * Innerhalb der Schleife wird jeder SubReport je nach Typ aus dem CompositeReport gelesen und erstellt.
		 * Dadurch, dass innerhalb der process-Methode für den jeweiligen SubReport der aktuelle Report-Text dem StringBuffer hinzugefügt wird,
		 * sind auch die einzelnen SubReports im StringBuffer angehängt.
		 */
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
		
		//Ein Stringbuffer, an den alle folgenden HTML Inhalte angehängt werden
		StringBuffer result = new StringBuffer();
		
		/*
		 * Hinzufügen der Überschrift und den Kopfdaten in HTML-Form.
		 * Die Methode paragraph2HTML übersetzte die Kopfdaten des Reports r in ein korrektes HTML-Format.
		 */
		result.append("<h1 class=\"report\">" + r.getTitle() + "</h1>");
		result.append("<table class=\"headerdata\"><tr>");
		if (r.getHeaderData() != null){
			result.append("<td class=\"tdheaderdata\">" + paragraph2HTML(r.getHeaderData()) + "</td></tr>");
		}
		//result.append("<td valign=\"top\">" + paragraph2HTML(r.getImprint()) + "</td>");
		result.append("</table>");
		
		/*
		 * Innerhalb der Schleife wird jeder SubReport aus dem CompositeReport gelesen und erstellt.
		 * Dadurch, dass innerhalb der process-Methode für den jeweiligen SubReport der aktuelle Report-Text dem StringBuffer hinzugefügt wird,
		 * sind auch die einzelnen SubReports im StringBuffer angehängt.
		 */
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
		simpleReportProcessWithoutHeader(r);
	}

	@Override
	public void process(FanOutOfCallsOfUserReport r) {
		simpleReportProcessWithoutHeader(r);
	}
	
	@Override
	public void process(FanInFanOutOfUserReport r) {
		this.resetReportText();
		
		StringBuffer result = new StringBuffer();
		
		/*
		 * Hinzufügen der Überschrift und den Kopfdaten in HTML-Form.
		 * Die Methode paragraph2HTML übersetzte die Kopfdaten des Reports r in ein korrektes HTML-Format.
		 */
		result.append("<h1 class=\"report\">" + r.getTitle() + "</h1>");
		result.append("<table class=\"headerdata\"><tr>");
		if (r.getHeaderData() != null){
			result.append("<td class=\"tdheaderdata\">" + paragraph2HTML(r.getHeaderData()) + "</td></tr>");
		}
		//result.append("<td valign=\"top\">" + paragraph2HTML(r.getImprint()) + "</td>");
		result.append("</table>");
		
		/*
		 * Innerhalb der Schleife wird jeder SubReport je nach Typ aus dem CompositeReport gelesen und erstellt.
		 * Dadurch, dass innerhalb der process-Methode für den jeweiligen SubReport der aktuelle Report-Text dem StringBuffer hinzugefügt wird,
		 * sind auch die einzelnen SubReports im StringBuffer angehängt.
		 */
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
	 * Da die Logik der Erstellung von SimpleReports und der Aufbau identisch ist,
	 * genügt jeweils der Aufruf dieser Methode.
	 * @param r
	 */
	private void simpleReportProcess(SimpleReport r){
		this.resetReportText();
		
		//Ein Stringbuffer, an den alle folgenden HTML Inhalte angehängt werden
		StringBuffer result = new StringBuffer();
		
		/*
		 * Hinzufügen der Überschrift und den Kopfdaten in HTML-Form.
		 * Die Methode paragraph2HTML übersetzte die Kopfdaten des Reports r in ein korrektes HTML-Format.
		 */
		result.append("<h1 class=\"report\">" + r.getTitle() + "</h1>");
		result.append("<table class=\"headerdata\"><tr>");
		if (r.getHeaderData() != null){
			result.append("<td class=\"tdheaderdata\">" + paragraph2HTML(r.getHeaderData()) + "</td></tr>");
		}
		result.append("</table>");

		//Hinzufügen der einzelnen Zeilen aus dem Report in HTML-Form
		Vector<Row> rows = r.getRows();
		result.append("<table class=\"content\">");
		for (int i = 0; i < rows.size(); i++) {
			Row row = rows.elementAt(i);
			
			//Die erste Zeile der Tabelle wird ein TableHeader, ansonsten eine einfache Zeile
			if(i == 0){
				result.append("<tr class=\"theader\">");
			} else {
				result.append("<tr class=\"rcontent\">");
			}
			
		    for (int j = 0; j < row.getNumColumns(); j++) {
		    	result.append("<td class=\"rcontent\">" + row.getColumnAt(j) + "</td>");
		    }
			result.append("</tr>");
		}
		result.append("</table>");
		
		//Setzen des Report Textes auf den Inhalt des StringBuffers
		this.reportText = result.toString();
	}
	
	/**
	 * Einheitliche Methode zum Erstellen von SimpleReports ohne Kopfdaten in HTML-Format.
	 * Da die Logik der Erstellung von SimpleReports und der Aufbau identisch ist,
	 * genügt jeweils der Aufruf dieser Methode.
	 * @param r
	 */
	private void simpleReportProcessWithoutHeader(SimpleReport r){
		this.resetReportText();
		
		//Ein Stringbuffer, an den alle folgenden HTML Inhalte angehängt werden
		StringBuffer result = new StringBuffer();
		
		result.append("<h2 class=\"report\">" + r.getTitle() + "</h2>");

		//Hinzufügen der einzelnen Zeilen aus dem Report in HTML-Form
		Vector<Row> rows = r.getRows();
		result.append("<table class=\"content\">");
		for (int i = 0; i < rows.size(); i++) {
			Row row = rows.elementAt(i);
			
			//Die erste Zeile der Tabelle wird ein TableHeader, ansonsten eine einfache Zeile
			if(i == 0){
				result.append("<tr class=\"theader\">");
			} else {
				result.append("<tr class=\"rcontent\">");
			}
			
		    for (int j = 0; j < row.getNumColumns(); j++) {
		    	result.append("<td class=\"rcontent\">" + row.getColumnAt(j) + "</td>");
		    }
			result.append("</tr>");
		}
		result.append("</table>");
		result.append("<br />");
		
		//Setzen des Report Textes auf den Inhalt des StringBuffers
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