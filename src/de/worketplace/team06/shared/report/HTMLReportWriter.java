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
	
		this.resetReportText();
	
		StringBuffer result = new StringBuffer();
		
		result.append("<H1>" + r.getTitle() + "</H1>");
		result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		result.append("<td valign=\"top\"><b>" + paragraph2HTML(r.getHeaderData()) + "</b></td>");
		//result.append("<td valign=\"top\">" + paragraph2HTML(r.getImprint()) + "</td>");
		result.append("</tr><tr><td></td><td>" + r.getCreated().toString() + "</td></tr></table>");

		Vector<Row> rows = r.getRows();
		result.append("<table style=\"width:400px\">");

		for (int i = 0; i < rows.size(); i++) {
			Row row = rows.elementAt(i);
		    result.append("<tr>");
		    for (int j = 0; j < row.getNumColumns(); j++) {
		    	if (i == 0) {
		    		result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnAt(j) + "</td>");
		    	}
		    	else {
		        	if (i > 1) {
		            result.append("<td style=\"border-top:1px solid silver\">" + row.getColumnAt(j) + "</td>");
		        	}
		        	else {
		        		result.append("<td valign=\"top\">" + row.getColumnAt(j) + "</td>");
		        	}
		        }
		    }
		    result.append("</tr>");
		}
		
		result.append("</table>");
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
	 * @return ein String im HTML-Format
	 */
	public String getReportText() {
	  return this.getHeader() + this.reportText + this.getTrailer();
	}
}