package de.worketplace.team06.server.report;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.worketplace.team06.shared.*;
import de.worketplace.team06.server.WorketplaceAdministrationImpl;

import de.worketplace.team06.shared.bo.*;
import de.worketplace.team06.shared.report.*;
import de.worketplace.team06.server.db.*;
import de.worketplace.team06.client.NotLoggedInException;
import de.worketplace.team06.client.UserChangedException;
import de.worketplace.team06.client.WindowAlertException;


/**
 * @author Toby
 *
 */
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private WorketplaceAdministration wpadmin = null;
	
	public ReportGeneratorImpl() throws IllegalArgumentException {
	
	}
	
	/**
	 * Methode zur Initalisierung der WorketplaceAdministration
	 */
	@Override
	public void init() throws IllegalArgumentException {
	    /*
	     * Intantiierung von einer WorketplaceAdministrationImpl zum internen Gebrauch
	     */
	    WorketplaceAdministrationImpl impl = new WorketplaceAdministrationImpl();
	    impl.init();
	    this.wpadmin = impl;
	}
	
	protected WorketplaceAdministration getWorketplaceAdministration() {
		return this.wpadmin;
	}
	
	/**
	 * Methode zum Hinzufügen eines Impressums zum entsprechenden Report
	 * @param r
	 */
	
	/*
	 * Impressum hinzufügen (optional)
	protected void addImprint (Report r){
		
		CompositeParagraph imprint = new CompositeParagraph();
		
		User user = getUser();

        switch(orgaUnit.getType()){ 
        case "Person": 
        	Person p = wpadmin.getPersonByGoogleID(orgaUnit.getGoogleID());
        	imprint.addSubParagraph(new SimpleParagraph(p.getFirstName() + " " + p.getLastName()));
        	imprint.addSubParagraph(new SimpleParagraph(p.getStreet()));
        	imprint.addSubParagraph(new SimpleParagraph(p.getZipcode() + " " + p.getCity()));
        	break;
		case "Team": 
			Team t = wpadmin.getTeamByGoogleID(orgaUnit.getGoogleID());
        	imprint.addSubParagraph(new SimpleParagraph(t.getName()));
            break;
		case "Organisation": 
			Organisation o = wpadmin.getOrganisationByGoogleID(orgaUnit.getGoogleID());
        	imprint.addSubParagraph(new SimpleParagraph(o.getName()));
        	imprint.addSubParagraph(new SimpleParagraph(o.getStreet()));
        	imprint.addSubParagraph(new SimpleParagraph(o.getZipcode() + " " + o.getCity()));
        	break;
        }
		
		r.setImprint(imprint);
		
	}
	*/
	
	@Override
	public AllCallsReport createAllCallsReport(OrgaUnit o) throws IllegalArgumentException {
		
		AllCallsReport report = new AllCallsReport();
		
		report.setTitle("Alle Ausschreibungen");
		
		report.setCreated(new Timestamp(System.currentTimeMillis()));
		
		Row headline = new Row();
		
		headline.addColumn(new Column("Titel"));
		headline.addColumn(new Column("Beschreibung"));
		headline.addColumn(new Column("Projekt"));
		headline.addColumn(new Column("Deadline"));
		headline.addColumn(new Column("Status"));
		report.addRow(headline);
		
		
		Vector<Call> calls = wpadmin.getAllCalls();
		
		for (Call c : calls){
			Project p = wpadmin.getProjectByID(c.getProjectID());
			Row rowToAdd = new Row();
			rowToAdd.addColumn(new Column(c.getTitle()));
			rowToAdd.addColumn(new Column(c.getDescription()));
			rowToAdd.addColumn(new Column(p.getTitle()));
			rowToAdd.addColumn(new Column(c.getDeadline().toString()));
			rowToAdd.addColumn(new Column(c.getStatusString()));
			report.addRow(rowToAdd);
		}
		
		return report;
	}
	
	
	@Override
	public AllCallsMatchingWithUserReport createAllCallsMatchingWithUserReport(OrgaUnit o) throws IllegalArgumentException {
		//TODO: Überlegung, wie die Partnerprofile verglichen werden. Ggf. DB "like"
		return null;
	}
	
	@Override
	public AllApplicationsForCallsOfUserReport createAllApplicationsForCallsOfUserReport(OrgaUnit o) throws IllegalArgumentException {
		
		return null;
	}
	
	
}
