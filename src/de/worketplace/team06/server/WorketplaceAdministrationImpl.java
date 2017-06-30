package de.worketplace.team06.server;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.worketplace.team06.server.db.ApplicationMapper;
import de.worketplace.team06.server.db.CallMapper;
import de.worketplace.team06.server.db.EnrollmentMapper;
import de.worketplace.team06.server.db.MarketplaceMapper;
import de.worketplace.team06.server.db.OrgaUnitMapper;
import de.worketplace.team06.server.db.OrganisationMapper;
import de.worketplace.team06.server.db.PartnerProfileMapper;
import de.worketplace.team06.server.db.PersonMapper;
import de.worketplace.team06.server.db.ProjectMapper;
import de.worketplace.team06.server.db.PropertyMapper;
import de.worketplace.team06.server.db.RatingMapper;
import de.worketplace.team06.server.db.TeamMapper;
import de.worketplace.team06.shared.WorketplaceAdministration;
import de.worketplace.team06.shared.bo.Application;
import de.worketplace.team06.shared.bo.Call;
import de.worketplace.team06.shared.bo.Enrollment;
import de.worketplace.team06.shared.bo.LoginInfo;
import de.worketplace.team06.shared.bo.Marketplace;
import de.worketplace.team06.shared.bo.OrgaUnit;
import de.worketplace.team06.shared.bo.Organisation;
import de.worketplace.team06.shared.bo.PartnerProfile;
import de.worketplace.team06.shared.bo.Person;
import de.worketplace.team06.shared.bo.Project;
import de.worketplace.team06.shared.bo.Property;
import de.worketplace.team06.shared.bo.Rating;
import de.worketplace.team06.shared.bo.Team;

public class WorketplaceAdministrationImpl extends RemoteServiceServlet implements WorketplaceAdministration{

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	
//	Wird wahrscheinlich nicht benötigt	
//	/**
//	 * Referenz auf die zugehörige Organisationseinheit
//	 */
//	private OrgaUnit orgaUnit = null;
	
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Referenzen der Datenbank Mapper
	 * ***************************************************************************
	 */
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObjekt "Application" mit der Datenbank
	 * abgleicht.
	 */
	private ApplicationMapper appMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Call" mit der Datenbank
	 * abgleicht.
	 */
	private CallMapper callMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Enrollment" mit der Datenbank
	 * abgleicht.
	 */
	private EnrollmentMapper enrollMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Marketplace" mit der Datenbank
	 * abgleicht.
	 */
	private MarketplaceMapper marketMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Organisation" mit der Datenbank
	 * abgleicht.
	 */
	private OrganisationMapper orgaMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "OrganisationUnit" mit der Datenbank
	 * abgleicht.
	 */
	private OrgaUnitMapper orgaUnitMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Partner" mit der Datenbank
	 * abgleicht.
	 */
	private PartnerProfileMapper partnerMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Person" mit der Datenbank
	 * abgleicht.
	 */
	private PersonMapper personMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Project" mit der Datenbank
	 * abgleicht.
	 */
	private ProjectMapper projectMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Property" mit der Datenbank
	 * abgleicht.
	 */
	private PropertyMapper propertyMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Rating" mit der Datenbank
	 * abgleicht.
	 */
	private RatingMapper ratingMapper = null;
	
	/**
	 * Referenz auf den DatenbankMapper, der das BusinessObject "Team" mit der Datenbank
	 * abgleicht.
	 */
	private TeamMapper teamMapper = null;
	
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Referenzen der Datenbank Mapper
	 * ***************************************************************************
	 */

	

	public void init() throws IllegalArgumentException{

		//DB Mapper initialisieren
		this.appMapper = ApplicationMapper.applicationMapper();
		this.callMapper = CallMapper.callMapper();
		this.enrollMapper = EnrollmentMapper.enrollmentMapper();
		this.marketMapper = MarketplaceMapper.marketplaceMapper();
		this.orgaMapper = OrganisationMapper.organisationMapper();
		this.orgaUnitMapper = OrgaUnitMapper.orgaUnitMapper();
		this.partnerMapper = PartnerProfileMapper.partnerProfileMapper();
		this.personMapper = PersonMapper.personMapper();
		this.projectMapper = ProjectMapper.projectMapper();
		this.propertyMapper = PropertyMapper.propertyMapper();
		this.ratingMapper = RatingMapper.ratingMapper();
		this.teamMapper = TeamMapper.teamMapper();
		
		
	}
	
	public Person getTestUnit() throws IllegalArgumentException {
		Person test = new Person();
		test.setFirstName("Hans");
		return test;
	}

//	/** 
//	 *  Wird nicht benötigt ! GetOrgaUnitFor(LoginInfo logininfo) ist ausreichend ! 
//	 */
//	@Override
//	public boolean checkExistence(String googleID) throws IllegalArgumentException {
//		Integer temp = null;
//		if (orgaUnitMapper.findID(googleID) != 0){
//			temp = orgaUnitMapper.findID(googleID);
//			return true;
//		}
//		else{
//			temp = null;
//			return false;
//		}
//	}

	
	
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für BusinessObjekte
	 * ***************************************************************************
	 */

	
	/*
	 * ------------------------------
	 * -- METHODEN für APPLICATION --
	 * ------------------------------
	 */
	
	/**
	 * Erstellen einer Bewerbung für eine Ausschreibung
	 */
	@Override
	public Application applyFor(Call call, OrgaUnit orgaUnit, String applicationText)
			throws IllegalArgumentException {
		Application a = new Application();
		
		a.setCallID(call.getID());
		a.setOrgaUnitID(orgaUnit.getID());
		a.setText(applicationText);
		
		//Erzeugen eines Objekts vom Typ Date um das Erstellungsdatum zu setzen.
		Timestamp created = new Timestamp(System.currentTimeMillis());
		a.setCreated(created);
		
		//Setzen einer vorlaueufigen ID
		a.setID(1);
		
		//Speichern einer ausgehenden Bewerbung in der Datenbank.
		return this.appMapper.insert(a);
	}
	
	/**
	 * Speichern von Änderungen einer Bewerbung
	 */
	@Override
	public void saveApplication(Application application) throws IllegalArgumentException {
		this.appMapper.update(application);
		
	}

	/**
	 * Löschen einer Bewerbung
	 */
	@Override
	public void deleteApplication(Application application) throws IllegalArgumentException {
		
		/*
		 * Die zugehörige Bewertung zur Bewerbung muss am Anfang initialisiert werden
		 * nach Löschen der Bewerbung kann die Beziehung nicht wieder rekonstruiert werden.
		 */
		Rating r = this.ratingMapper.findById(application.getRatingID());
		
		//Löschen der Bewerbung
		this.appMapper.delete(application);
		
		/*
		 * Nachdem die Bewerbung gelöscht wurde kann auch die Bewertung gelöscht werden
		 * Voraussetzung: Es besteht keine Beziehung von dieser Ratinginstanz zu einer
		 * Enrollmentinstanz.
		 */
		if (r != null && this.enrollMapper.findByRatingID(r.getID()) == null){
			this.ratingMapper.delete(r);
		}	
	}
	
	/**
	 * Auslesen aller Bewerbungen für eine Organisationseinheit
	 */
	@Override
	public Vector<Application> getApplicationsFor(OrgaUnit orgaUnit) throws IllegalArgumentException {
		//***WICHTIG*** @DB-Team: Methode muss noch deklariert werden.
		//Auslesen aller Bewerbungen für eine OrganisationsEinheit aus der DB
//		/**
//		 * Variable für die OrgaUnit ID
//		 */
//		Integer findID = orgaUnit.getID();
//		return this.appMapper.findByOrgaUnitID(findID);
		
		return this.appMapper.findByOrgaUnitID(orgaUnit.getID());
	}

	/**
	 * Auslesen aller Bewerbungen für ein Projekt
	 */
	@Override
	public Vector<Application> getApplicationsFor(Call call) throws IllegalArgumentException {
		//***WICHTIG*** @DB-Team: Methode muss noch deklariert werden.
		//Auslesen aller Bewerbungen für eine Ausschreibung aus der DB
		return this.appMapper.findByCallID(call.getID());
	}
	
	   /**
		 * Auslesen aller eingehender Bewerbungen für die Projekte, in denen
		 * die Person Projektleiter ist.
		 * @param Person projectleader
		 * @return Vector<Application> result
		 */
		@Override
		public Vector<Application> getApplicationsFor(Person projectleader){
			Vector<Application> result = new Vector<Application>();
			Vector<Project> projects = getAllProjects();
			
			/*
			 * In den verschachtelten Schleifen werden die Bewerbungen ermittelt, die indirekt
			 * an den Projektleiter gerichtet sind und dem Vector result hinzugefügt. 
			 */
			for(Project p : projects){
				if (p.getProjectLeaderID() == projectleader.getID()){
					Vector<Call> calls = getCallsFor(p);
					for (Call c : calls){
						Vector<Application> apps = getApplicationsFor(c);
						for (Application a : apps){
							result.addElement(a);
						}
					}
				}
			}
			return result;
		}
	    
	/**
	 * 
	 */
	@Override
	public Integer getApplicationCountFor(Call c){
		Vector<Application> allApps = this.getApplicationsFor(c);
		return allApps.size();
	}
	
	/*
	 * -----------------------
	 * -- METHODEN für CALL --
	 * -----------------------
	 */
	
	/**
	 * Erstellen einer Ausschreibung
	 */
	@Override
	public Call createCall(Project project, Person callerPerson, String title,
			String description, Date deadline) throws IllegalArgumentException {
		Timestamp created = new Timestamp(System.currentTimeMillis());
		Call c = new Call();
		c.setCreated(created);
		c.setTitle(title);
		c.setDescription(description);
		c.setDeadline(deadline);
		c.setProjectID(project.getID());
		c.setCallerID(callerPerson.getID());
		
		c = this.callMapper.insert(c);
		PartnerProfile p = createPartnerProfileFor(c);
		c.setPartnerProfileID(p.getID());
		
		this.callMapper.update(c);
		return c;
		
		
	}

	/**
	 * Speichern von Änderungen einer Ausschreibung
	 */
	@Override
	public void saveCall(Call call) throws IllegalArgumentException {
		this.callMapper.update(call);
		
	}

	/**
	 * Löschen einer Ausschreibung
	 */
	@Override
	public void deleteCall(Call call) throws IllegalArgumentException {
		
		//Für jede Ausschreibung werden alle Bewerbungen gelöscht. 
		Vector<Application> allApps = appMapper.findByCallID(call.getID());
		if (allApps != null){
			for (Application a : allApps){
				deleteApplication(a);							
			}
		}
		
		PartnerProfile pp = getPartnerProfileFor(call);
			
		//Löschen der jeweiligen Ausschreibung
		this.callMapper.delete(call);
		
		//Löschen des zugehörigen PartnerProfils
		if (pp != null){
			deletePartnerProfile(pp);
		} 
	}

	/**
	 * Auslesen aller Ausschreibungen
	 */
	@Override
	public Vector<Call> getAllCalls() throws IllegalArgumentException {
		//***WICHTIG*** @DB-Team: Methode muss noch deklariert werden.
		//Auslesen aller Calls aus der DB für ein Projekt
		return this.callMapper.findAll();
	}
	
	/**
	 * Auslesen aller Ausschreibungen für ein Projekt
	 */
	@Override
	public Vector<Call> getCallsFor(Project project) throws IllegalArgumentException {
		//***WICHTIG*** @DB-Team: Methode muss noch deklariert werden.
		//Auslesen aller Calls aus der DB für ein Projekt
		return this.callMapper.findByProjectID(project.getID());
	}
	
	   /**
		 * Auslesen aller Ausschreibungen die eine Person in der Rolle 
		 * des Projektleiters erstellt halt. 
		 * @param Person projectleader
		 * @return Vector<Call> result
		 */
		@Override
		public Vector<Call> getCallsFor(Person projectleader){
			Vector<Call> result = new Vector<Call>();
			Vector<Project> projects = getAllProjects();
			
			/*
			 * Die verschachtelte For-Schleife wird verwendet um alle Ausschreibungen
			 * eines Projektleiters in den Vektor result zu schreiben. 
			 */
			for (Project proj : projects){
				if (proj.getProjectLeaderID() == projectleader.getID()){
					Vector<Call> calls = getCallsFor(proj);
					for (Call c : calls){
						if (!result.contains(c)){
							result.addElement(c);
						}
					}
				}
			}
			return result;
		}
	    
	/**
	 * Auslesen einer Ausschreibung mit einer CallID
	 */
	@Override
	public Call getCallByID(Integer callID) throws IllegalArgumentException {
		return this.callMapper.findByID(callID);
	}
	
	/**
	 * Auslesen der Anzahl offener Ausschreibungen eines Projektes
	 */
	@Override
	public Integer getOpenCallCountFor(Project p){
	
		Vector<Call> allCalls = this.getCallsFor(p);
		Integer count = 0;
		
		
		for(Call c : allCalls){
			if (c.getStatus() == 0){
				count++;
			}
		}
		return count; 
	}
	
	
	/*
	 * -----------------------------
	 * -- METHODEN für ENROLLMENT --
	 * -----------------------------
	 */
	
	/**
	 * Erstellen einer Beteiligung
	 */
	@Override
	public Enrollment createEnrollment(Application app, Project project, OrgaUnit orgaUnit, Rating rating, Date startDate, Date endDate, Integer workload) throws IllegalArgumentException {
		
		
		Enrollment e = new Enrollment();
		
		e.setRatingID(rating.getID());
		e.setProjectID(project.getID());
		e.setOrgaUnitID(orgaUnit.getID());
		
		//Erzeugen eines Objekts vom Typ Date um das Erstellungsdatum zu setzen.
		Timestamp created = new Timestamp(System.currentTimeMillis());
		e.setCreated(created);
		
		e.setStartDate(startDate);
		e.setEndDate(endDate);
		e.setWorkload(workload);
		
		
		//Setzen einer vorlauefigen ID
		e.setID(1);
		
		//***WICHTIG*** @ DB-Team: Methode muss noch deklariert werden.
		e = this.enrollMapper.insert(e);
		
		if (e != null){
			
			//Den Bewerbungsstatus auf angenommen setzen 
			app.setStatus(1);
			saveApplication(app);
			
			//Den Ausschreibunsstatus auf erfolgreich besetzt setzen
			Call c = this.callMapper.findByID(app.getCallID());
			c.setStatus(1);
			
			//Den Status der übrigen Bewerbungen auf abgelehnt setzen
			Vector<Application> allAppsForCall = this.getApplicationsFor(c);
			for (Application a : allAppsForCall){
				
				if (!a.equals(app)){
					a.setStatus(2);
					saveApplication(a);
				}
			}
		}
		
		return e;
	}
	
	/**
	 * Erstellen einer automatischen Beteiligung
	 */
	//Kein Override wird nur auf ServerEbene angesprochen
	public Enrollment createAutomaticEnrollment(Project project, OrgaUnit orgaUnit, Rating rating) throws IllegalArgumentException {
		Enrollment e = new Enrollment();
		
		e.setRatingID(rating.getID());
		e.setProjectID(project.getID());
		e.setOrgaUnitID(orgaUnit.getID());
		
		//Erzeugen eines Objekts vom Typ Date um das Erstellungsdatum zu setzen.
		Timestamp created = new Timestamp(System.currentTimeMillis());
		e.setCreated(created);
		
		//Setzen einer vorlauefigen ID
		e.setID(1);
		
		//***WICHTIG*** @ DB-Team: Methode muss noch deklariert werden.
		return this.enrollMapper.insert(e);
	}

	/**
	 * Speichern von Änderungen einer Beteiligung
	 */
	@Override
	public void saveEnrollment(Enrollment enrollment) throws IllegalArgumentException {
		this.enrollMapper.update(enrollment);
				
	}

	/**
	 * Löschen einer Beteiligung
	 */
	@Override
	public void deleteEnrollment(Enrollment enrollment) throws IllegalArgumentException {
		
		/*
		 * Die zugehörige Bewertung zur Beteiligung muss am Anfang initialisiert werden
		 * nach Löschen der Bewerbung kann die Beziehung nicht wieder rekonstruiert werden.
		 */
		Rating r = this.ratingMapper.findById(enrollment.getRatingID());
		
		//Löschen der Beteiligung
		this.enrollMapper.delete(enrollment);
		
		/*
		 * Nachdem die Beteiligung gelöscht wurde kann auch die Bewertung gelöscht werden
		 * Voraussetzung: Es besteht keine Beziehung von dieser Ratinginstanz zu einer
		 * Bewerbungsintinstanz.
		 */
		if (r != null && this.appMapper.findByRatingID(r.getID()) == null){
			this.ratingMapper.delete(r);
		}
		
	}
	
	/**
	 * Auslesen aller Beteiligungen für ein Projekt
	 */
	@Override
	public Vector<Enrollment> getEnrollmentFor(Project project) throws IllegalArgumentException {
		//***WICHTIG*** @DB-Team: Methode muss noch deklariert werden.
		return this.enrollMapper.findByProjectID(project.getID());
	}
	
	/**
	 * Auslesen aller Beteiligungen für eine OrganisationsEinheit
	 */
	@Override
	public Vector<Enrollment> getEnrollmentFor(OrgaUnit orgaUnit) throws IllegalArgumentException {
		//***WICHTIG*** @DB-Team: Methode muss noch deklariert werden.
		return this.enrollMapper.findByOrgaUnitID(orgaUnit.getID());
	}
	
	/**
	 * Auslesen der Beteiligungsobjekte von allen Projekten, in denen die jeweilige Person
	 * Projektleiter ist.
	 * @param Person projectleader
	 * @return Vector<Enrollment> result
	 */
	@Override
	public Vector<Enrollment> getEnrollmentsFor(Person projectleader){
		Vector<Enrollment> result = new Vector<Enrollment>();
		Vector<Project> projects = getAllProjects();
		/*
		 * Mit der verschachtelten for Schleife schreiben wir alle Beteiligungen
		 * unserer Projekte (Projektleiterfunktion) in den result Vektor.
		 */
		for (Project p : projects){
			if (p.getProjectLeaderID() == projectleader.getID()){
			Vector<Enrollment> enrollments = getEnrollmentFor(p);
			for (Enrollment e : enrollments){
				result.addElement(e);
			}
			}
		}
		return result;
	}
    
	
	
	
	/*
	 * ------------------------------
	 * -- METHODEN für MARKETPLACE --
	 * ------------------------------
	 */
	
	/**
	 * Erstellen eines Marktplatzes
	 */
	@Override
	public Marketplace createMarketplace(String title, String description) throws IllegalArgumentException {
		Marketplace m = new Marketplace();
		m.setTitle(title);
		m.setDescription(description);
		
		//Erzeugen eines Objekts vom Typ Date um das Erstellungsdatum zu setzen.
		Timestamp created = new Timestamp(System.currentTimeMillis());
		m.setCreated(created);
		
		
		//Setzen einer vorlaueufigen ID
		m.setID(1);
		
		//Objekt in der DB speichern
		return this.marketMapper.insert(m);
	}

	/**
	 * Speichern von Änderungen eines Marktplatzes
	 */
	@Override
	public void saveMarketplace(Marketplace m) throws IllegalArgumentException {
		this.marketMapper.update(m);
	}

	/**
	 * Löschen eines Marktplatzes
	 */
	@Override
	public void deleteMarketplace(Marketplace m) throws IllegalArgumentException {
		
		/*
		 * Alle Projekte eines Marktplatzes werden gelöscht
		 */
		Vector<Project> allProjects = projectMapper.findByMarketplaceID(m.getID());
		
		if (allProjects != null){
			//Für Jeden Marktplatz werden die zugehörigen Projekte gelöscht 
			for(Project p : allProjects){
				deleteProject(p);
			}
		}
		
		//Löschen des Marktplatzes
		this.marketMapper.delete(m);
		
		
		
		/*ALT!!!!
		 * Vector<Application> allApps = appMapper.findByCallID(call.getID());
		if (allApps != null){
			for (Application a : allApps){
				
				//Für jede Ausschreibung wird die dazugehörige Bewertung ausgelesen
				Rating rating = ratingMapper.findRatingByApplicationID(a.getID());
					
				if (rating != null){
				//Es wird überprüft ob die Bewertung auch mit einer Enrollment Instanz verbunden ist
				//Wenn ja wird der Rating Mapper nicht gelöscht, Wenn nein wird er gelöscht. 
					if (this.enrollMapper.findByRatingID(rating.getID()) == null){
						this.ratingMapper.delete(rating);
					}
					
					
				}	
				else {
					//TO Do: Fehlermeldung
				}
				this.appMapper.delete(a);
											
			}
		}
		
		PartnerProfile pp = getPartnerProfileFor(call);
		if (pp != null){
			
			this.propertyMapper.deleteByPartnerProfileID(pp.getID());
			this.partnerMapper.delete(pp);
		} else {
			//TODO: Fehlermeldung
		}
			
		//Löschen der jeweiligen Ausschreibung
		this.callMapper.delete(call);
		 */
	}
	
	/**
	 * Auslesen aller Marktplätze
	 */
	@Override
	public Vector<Marketplace> getAllMarketplaces() throws IllegalArgumentException {
		//***WICHTIG*** @DB-Team: Methode muss noch deklariert werden.
		//Auslesen aller Marktpl�tze aus der DB
		return this.marketMapper.findAll();
	}


	/**
	 * Auslesen aller Marktplätze einer Organisations-Einheit
	 *//*
	@Override
	public Vector<Marketplace> getMarketplacesFor(OrgaUnit orgaUnit) throws IllegalArgumentException {
		return this.marketMapper.findByOrgaUnitID(orgaUnit.getID());
	}
*/	
	/**
	 * Auslesen eines Marktplatzes anhand der ID
	 */
	@Override
	public Marketplace getMarketplaceByID(Integer marketplaceID) throws IllegalArgumentException {
		return this.marketMapper.findByID(marketplaceID);
	}

	
	
	/*
	 * -------------------------------
	 * -- METHODEN für ORGANISATION --
	 * -------------------------------
	 */
	
	/**
	 * Erstellen einer Organisation
	 * 
	 * @param name
	 * @param description
	 * @param street
	 * @param zipcode
	 * @param city
	 * @param googleID
	 * @return
	 * @throws IllegalArgumentException
	 */
	@Override
	public Organisation createOrganisation(String name, String description, String street, Integer zipcode, String city, String googleID) throws IllegalArgumentException {
		
		Organisation o = new Organisation();
		Timestamp created = new Timestamp(System.currentTimeMillis());
		
		o.setCreated(created);
		o.setType("Organisation");
		
		o.setName(name);
		o.setDescription(description);
		o.setStreet(street);
		o.setZipcode(zipcode);
		o.setCity(city);
		o.setGoogleID(googleID);
		
		/**
		 * Siehe createPerson
		 */
		o.setID(1);
		
		o = this.orgaMapper.insert(o);
		
		PartnerProfile pp = createPartnerProfileFor(o);
		o.setPartnerProfileID(pp.getID());
		
		this.orgaMapper.update(o);
		
		return o;
	}
	
	/**
	 * Speichern von Änderungen einer Organisation
	 */
	@Override
	public void saveOrganisation(Organisation organisation) throws IllegalArgumentException {
		this.orgaMapper.update(organisation);
		
	}
	
	/**
	 * Löschen einer Organisation aus der Datenbank 
	 */
	@Override
	public void deleteOrganisation(Organisation organisation) throws IllegalArgumentException {
//		Vector<Marketplace> markets = getMarketplacesFor(organisation);
		Vector<Enrollment> enrollments = getEnrollmentFor(organisation);
		Vector<Application> applications = getApplicationsFor(organisation);
	
		/*
		 * Marktplätze werden nicht automatisch mitgelöscht. Es wird eine WindowAlertException ausgegeben,
		 * dass die Marktplätze vorher manuell gelöscht werden sollen.
		 *//*
		if (markets != null){
			throw new WindowAlertException("Bitte Löschen Sie zuerst Ihre Marktplätze oder ernennen"
					+ "Sie neue Besitzer für Ihre Marktplätze");
		}*/
	
		
			
			/*
			 * Das zugehörige Partnerprofil muss zu Beginn instanziiert werden,
			 * da nach löschen der Organisation keine Verbindung mehr zum 
			 * Partnerprofil besteht.  
			 */
			PartnerProfile part = getPartnerProfileFor(organisation);
			
			/*
			 * Alle Beteiligungen dieser Organisation löschen
			 */
			if (enrollments != null){
				for (Enrollment e : enrollments){
					deleteEnrollment(e);
				}
			}
			
			/*
			 * Alle Bewerbungen dieser Organisation löschen
			 */
			if (applications != null){
				for (Application a : applications){
					deleteApplication(a);
				}	
			}	
			
			// Löschen der Organisation
			this.orgaMapper.delete(organisation);
			
			/*
			 * Das PartnerProfil muss zueletzt gelöscht werden,
			 * da ein Fremdschlüssel(partnerprofile_id) in der OrgaUnitInstanz 
			 * vorhanden ist. 
			 */
			this.partnerMapper.delete(part);
		
	}
	
	/**
	 * Auslesen einer Organisation aus der Datenbank
	 * @param googleID
	 * @return
	 */
	@Override
	public Organisation getOrganisationByGoogleID(String googleID){

		return this.orgaMapper.findByGoogleID(googleID);
	}
	
	@Override
	public Vector<Organisation> getAllOrganisations(){
		return this.orgaMapper.findAll();
	}
	
	
	
	/*
	 * ---------------------------------
	 * -- METHODEN für OrgaUnit --
	 * ---------------------------------
	 */
	
	
	/**
	 * Auslesen eines Vectors mit allen OrgaUnits
	 * @param void
	 * @return allOrgaUnits
	 */
	@Override
	public Vector<OrgaUnit> getAllOrgaUnits() {
		Vector<OrgaUnit> allOrgaUnits = new Vector<OrgaUnit>();
		Vector<Person> allPersons = getAllPersons();
		Vector<Team> allTeams = getAllTeams();
		Vector<Organisation> allOrganisations = getAllOrganisations();
		
		
		for(Person p : allPersons){
			allOrgaUnits.addElement(p);
		}
		
		
		for(Team t : allTeams){
			allOrgaUnits.addElement(t);
		}
		
		
		for(Organisation o : allOrganisations){
			allOrgaUnits.add(o);
		}
		
		return allOrgaUnits;
	}
	
	@Override
	public OrgaUnit getOrgaUnitFor(LoginInfo loginInfo) throws IllegalArgumentException {
		
		String type = orgaUnitMapper.findTypeByGoogleID(loginInfo.getGoogleId());
        
		if (type instanceof String) {
			switch(type){ 
	        case "Person": 
	        	Person p = this.personMapper.findByGoogleID(loginInfo.getGoogleId());
	        	return p;
			case "Team": 
	        	Team t = this.teamMapper.findByGoogleID(loginInfo.getGoogleId());
	            return t;
			case "Organisation": 
	        	Organisation o = this.orgaMapper.findByGoogleID(loginInfo.getGoogleId());
	        	return o; 
	        }
		}
		return null;
	}
	
	@Override
	public OrgaUnit getOrgaUnitById(Integer ouid) throws IllegalArgumentException {
		
		String type = orgaUnitMapper.findTypeByID(ouid);
        
        switch(type){ 
        case "Person": 
        	Person p = this.personMapper.findByID(ouid);
        	return p;
		case "Team": 
        	Team t = this.teamMapper.findByID(ouid);
            return t;
		case "Organisation": 
        	Organisation o = this.orgaMapper.findByID(ouid);
        	return o; 
        }
		return null;
		//return this.orgaUnitMapper.findByGoogleID(loginInfo.getGoogleId());
	}
	
	
	
	/*
	 * ---------------------------------
	 * -- METHODEN für PARTNERPROFILE --
	 * ---------------------------------
	 */
	
	/**
	 * Erstellen eines PartnerProfils für einen Call
	 */
	@Override
	public PartnerProfile createPartnerProfileFor(Call call)
			throws IllegalArgumentException {
		
		PartnerProfile profile = new PartnerProfile();
		Timestamp created = new Timestamp(System.currentTimeMillis());
		
		profile.setCreated(created);
		profile.setLastEdit(created);
		profile.setID(1);
		
		profile = this.partnerMapper.insert(profile);
		call.setPartnerProfileID(profile.getID());
		this.callMapper.update(call);
		
		return profile;
	}

	/**
	 * Erstellen eines PartnerProfils für eine Organisation
	 */
	@Override
	public PartnerProfile createPartnerProfileFor(OrgaUnit orgaunit)
			throws IllegalArgumentException {
		
		PartnerProfile profile = new PartnerProfile();
		Timestamp created = new Timestamp(System.currentTimeMillis());
		
		profile.setCreated(created);
		profile.setLastEdit(created);
		profile.setID(1);
		
		profile = this.partnerMapper.insert(profile);
		orgaunit.setPartnerProfileID(profile.getID()); 
		this.orgaUnitMapper.update(orgaunit);
		
		return profile;
	}

	/**
	 * Speichern von Änderungen für ein PartnerProfil 
	 */
	@Override
	public void savePartnerProfileFor(PartnerProfile partnerProfile) throws IllegalArgumentException {
		this.partnerMapper.update(partnerProfile);
		
	}
	
	/**
	 * Auslesen eines PartnerProfils für eine Ausschreibung
	 */
	@Override
	public PartnerProfile getPartnerProfileFor(Call call) throws IllegalArgumentException {
		return this.partnerMapper.findById(call.getPartnerProfileID());
	}

	/**
	 * Auslesen eines PartnerProfils für eine Organisations-Einheit.
	 */
	@Override
	public PartnerProfile getPartnerProfileFor(OrgaUnit orgaunit) throws IllegalArgumentException {
		return this.partnerMapper.findById(orgaunit.getPartnerProfileID());
	}
	
	/**
	 * Löschen eines ParnterProfils
	 */
	@Override
	public void deletePartnerProfile(PartnerProfile p) throws IllegalArgumentException {
		
		/*
		 * Es werden alle zugehörigen Properties zum PartnerProfile gelöscht.
		 */
		Vector<Property> allproperties = this.getAllPropertiesFor(p);
		for(Property prop : allproperties){
			deleteProperty(prop);
		}
		// Das PartnerProfile wird gelöscht.
		this.partnerMapper.delete(p);
	}
	
	
	
	/*
	 * -------------------------
	 * -- METHODEN für PERSON --
	 * -------------------------
	 */
	
	
	/**
	 * Methode zum erstellen einer Person. Es werden alle Attribute bis auf die partnerprofileID 
	 * gesetzt. Die Partnerprofile id kann zu einem spätzeren Zeitpunkt über die savePerson Methode
	 * gespeichert werden. 
	 *  
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param zipcode
	 * @param city
	 * @param description
	 * @param googleID
	 * @return
	 * @throws IllegalArgumentException
	 */
	@Override
	public Person createPerson(String firstName, String lastName, String street, Integer zipcode, String city, String description, String googleID) throws IllegalArgumentException {
		Person p = new Person();
		Timestamp created = new Timestamp(System.currentTimeMillis());
		
		p.setCreated(created);
		p.setType("Person");
		
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setStreet(street);
		p.setZipcode(zipcode);
		p.setCity(city);
		p.setGoogleID(googleID);
		p.setDescription(description);
		
		
		/**
		 * Setzen einer vorläufigen ID. 
		 * Der korrekte bzw vortlaufende Primärschlüssel (=id) wird über eine Datenbankabfrage in der
		 * personMapper.insert Methode generiert. (Die Id muss mit den Datensätzen in der Datenbank
		 * konsistent sein) 
		 */
		p.setID(1);
		
		p = this.personMapper.insert(p);
		
		PartnerProfile pp = createPartnerProfileFor(p);
		p.setPartnerProfileID(pp.getID());
		
		this.personMapper.update(p);
		
		return p;
	}
	
	/**
	 * Speichern von Änderungen einer Person
	 */
	@Override
	public void savePerson(Person person) throws IllegalArgumentException {
		this.personMapper.update(person);
	}
	
	/**
	 * Löschen einer Person aus der Datenbank 
	 */
	@Override
	public void deletePerson(Person person) throws IllegalArgumentException {
		
//		Vector<Marketplace> markets = getMarketplacesFor(person);
		
		Vector<Enrollment> enrollments = getEnrollmentFor(person);
		Vector<Application> applications = getApplicationsFor(person);
		
		
		//Löschen aller Projekte in denen die OrgaUnit Projektleiter ist
		Vector<Project> projects = getProjectsForLeader(person); 
		for (Project p : projects){
			deleteProject(p);
		}
			
		PartnerProfile part = getPartnerProfileFor(person);	
		
		if (enrollments != null){
			for (Enrollment e : enrollments){
				deleteEnrollment(e);
			}
		}
		
		
		if (applications != null){
			for (Application a : applications){
				deleteApplication(a);
			}	
		}	

		this.personMapper.delete(person);
		
		this.partnerMapper.delete(part);
		

	}
	
	/**
	 * Auslesen einer Person aus der Datenbank
	 * @param googleID
	 * @return
	 */
	@Override
	public Person getPersonByGoogleID(String googleID){

		return this.personMapper.findByGoogleID(googleID);
	}
	
	/**
	 * Auslesen einer Person aus der Datenbank
	 */
	@Override
	public Person getPersonByID(Integer personid){
		return this.personMapper.findByID(personid);
	}
	
	@Override
	public Vector<Person> getAllPersons(){
		return this.personMapper.findAll();
	}
	
	
	/*
	 * --------------------------
	 * -- METHODEN für PROJECT --
	 * --------------------------
	 */
	
	/**
	 * Erstellen eines Projekts auf einem Marktplatz.
	 * 
	 * ProjectOwnerID ausgeklammert. Wird nicht benutzt.
	 */
	@Override
	public Project createProject(Marketplace marketplace, String title, String description, Person projectLeaderPerson,
			Date startDate, Date endDate) throws IllegalArgumentException {
		Timestamp created = new Timestamp(System.currentTimeMillis());
		
		Project p = new Project();
		p.setTitle(title);
		p.setCreated(created);
		p.setDescription(description);
		p.setProjectLeaderID(projectLeaderPerson.getID());
//		p.setProjectOwnerID(projectOwnerOrgaUnit.getID());
		p.setStartDate(startDate);
		p.setEndDate(endDate);
		p.setMarketplaceID(marketplace.getID());
		
		//Setzen einer vorlaueufigen ID
		p.setID(1);
		
		//Objekt in der DB speichern
		return this.projectMapper.insert(p);
	}

	/**
	 * Speichern von Änderungen eines Projekts.
	 */
	@Override
	public void saveProject(Project project) throws IllegalArgumentException {
		this.projectMapper.update(project);
		
	}

	/**
	 * Löschen eines Projekts
	 */
	@Override
	public void deleteProject(Project project) throws IllegalArgumentException {
		//TODO: Die Funkion muss definitiv nochmals überprüft werden.
		
		/*
		 * Wir deklarieren die Variable pID und setzen die ProjectID als Value.
		 * So muss die Methode getID nicht mehrfach aufgerufen werden.
		 */
		Integer pID = project.getID();
		
		
		/*
		 * Löschen aller Beteiligungen anhand der Projekt ID.
		 */
		Vector<Enrollment> allEnrolls = enrollMapper.findByProjectID(pID);
		if (allEnrolls != null){
			for (Enrollment e : allEnrolls){
				deleteEnrollment(e);
			}
		}
		
		
		/*
		 * Löschen aller Ausschreibungen anhand der Projekt ID
		 */
		Vector<Call> allCalls = callMapper.findByProjectID(pID);
		if (allCalls != null){
			for (Call c : allCalls){
				deleteCall(c);
			}
		}
		
		//Löschen des Projekts
		this.projectMapper.delete(project);	
		
		
//		/*
//		 * ALTE VARIANTE
//		 */
//		//Auslesen aller Ausschreibungen in einen Vektor anhand der ProjectID
//		Vector<Call> allCalls = callMapper.findByProjectID(pID);
//		if (allCalls != null){
//			for (Integer i = 0; allCalls.capacity()>i; i++){
//				
//				//Für jede Ausschreibung werden alle Bewerbungen in einen Vektor ausgelesen
//				Vector<Application> allApps = appMapper.findByCallID(allCalls.get(i).getID());
//				if (allApps != null){
//					for (Integer j = 0; allApps.capacity()>j; j++){
//						
//						//Für jede Ausschreibung wird die dazugehörige Bewertung ausgelesen
//						Rating rating = ratingMapper.findRatingByApplicationID(allApps.get(i).getID());
//						if (rating != null){
//							
//							//Löschen der Bewertung
//							this.ratingMapper.delete(rating);
//						}
//						
//						//Löschen der jeweiligen Bewerbung
//						this.appMapper.delete(allApps.get(j));
//													
//					}
//				}
//		
//				PartnerProfile pp = getPartnerProfileFor(call);
//				if (pp != null){
//					this.partnerMapper.delete(pp);
//				} else {
//					//TODO: Fehlermeldung
//				}
//				
//				//Löschen der jeweiligen Ausschreibung
//				this.callMapper.delete(allCalls.get(i));
//				
//			}
//		}
//		
//		//Löschen des Projekts
//		this.projectMapper.delete(project);		
		
	}

	/**
	 * Auslesen aller Projekte.
	 */
	@Override
	public Vector<Project> getAllProjects() throws IllegalArgumentException {
		//Auslesen aller Projekte aus der DB
		return this.projectMapper.findAll();
	}

	/**
	 * Auslesen aller Projekte, die eine bestimmte Organisationseinheit leitet. 
	 */
	@Override
	public Vector<Project> getProjectsForLeader(OrgaUnit orgaUnit) throws IllegalArgumentException {
		//***WICHTIG*** Nochmals pr�fen...
		//Auslesen aller Projekte f�r eine OrgaUnit aus der DB
	
		return this.projectMapper.findByProjectLeaderID(orgaUnit.getID());
	}
	
	/**
	 * Auslesen aller Projekte, die eine bestimmte Organisationseinheit besitzt.
	 * Ausgeklammert. Wird nicht benutzt.
	 */
	/*
	@Override
	public Vector<Project> getProjectsForOwner(OrgaUnit orgaUnit) throws IllegalArgumentException {
		//***WICHTIG*** Nochmals pr�fen...
		//Auslesen aller Projekte f�r eine OrgaUnit aus der DB
	
		return this.projectMapper.findByProjectOwnerID(orgaUnit.getID());
	}
	*/
	/**
	 * Auslesen eines Projektes mit einer projectID
	 */
	@Override
	public Project getProjectByID(Integer projectID) throws IllegalArgumentException{
	return this.projectMapper.findByID(projectID);	
	}
	
	/**
	 * Auslesen aller Projekte für einen Marktplatz
	 */
	@Override
	public Vector<Project> getProjectsFor(Marketplace marketplace) throws IllegalArgumentException {
		return this.projectMapper.findByMarketplaceID(marketplace.getID());
	}
	

	
	/*
	 * ---------------------------
	 * -- METHODEN für PROPERTY --
	 * ---------------------------
	 */
	
	/**
	 * Erstellen von Eigenschaften für ein PartnerProfil
	 */
	@Override
	public Property createProperty(PartnerProfile partnerProfile, String name, String value)
			throws IllegalArgumentException {
		
		Timestamp created = new Timestamp(System.currentTimeMillis());
		Property p = new Property();
		p.setName(name);
		p.setValue(value);
		p.setCreated(created);
		p.setPartnerProfileID(partnerProfile.getID());
		
		//Setzen einer vorläufigen ID
		p.setID(1);
	
		//Objekt in der Datenbank speichern
		p = this.propertyMapper.insert(p);
		
		/*
		 * Wenn ein Objekt vom Typ Property erzeugt wird muss auch der Timestamp lastEdit
		 * im zugehörigen PartnerProfile aktualisiert werden. Da Partnerprofile aus mehreren 
		 * Property Objekten besteht und sich somit auch der Zustand des Partnerprofils 
		 * ändert. 
		 */
		Timestamp lastEdit = new Timestamp(System.currentTimeMillis());
		partnerProfile.setLastEdit(lastEdit);
		this.partnerMapper.update(partnerProfile);
		return p;
	}

	/**
	 * Speichern von Änderungen einer Eigenschaft
	 */
	@Override
	public void saveProperty(Property property) throws IllegalArgumentException {
		this.propertyMapper.update(property);
		
		/*
		 * Wenn ein Objekt vom Typ Property geändert wird muss auch der Timestamp lastEdit
		 * im zugehörigen PartnerProfile aktualisiert werden. Da Partnerprofile aus mehreren 
		 * Property Objekten besteht und sich somit auch der Zustand des Partnerprofils
		 * ändert. 
		 */
		Timestamp lastEdit = new Timestamp(System.currentTimeMillis());
		PartnerProfile p = partnerMapper.findById(property.getPartnerProfileID());
		p.setLastEdit(lastEdit);
		this.partnerMapper.update(p);
		
	}

	/**
	 * Auslesen aller Eigenschaften für ein PartnerProfil
	 */
	@Override
	public Vector<Property> getAllPropertiesFor(PartnerProfile partnerprofile) throws IllegalArgumentException {
		//***WICHTIG*** Methode muss im Mapper angelegt werden!
		return this.propertyMapper.findByPartnerProfileID(partnerprofile.getID());
	}
	
	/**
	 * Löschen einer Eigenschaft 
	 */
	@Override
	public void deleteProperty(Property p) throws IllegalArgumentException {
		this.propertyMapper.delete(p);
	}

	
	
	/*
	 * -------------------------
	 * -- METHODEN für RATING --
	 * -------------------------
	 */
	
	/**
	 * Erstellen einer Bewertung für eine Bewerbung
	 */
	@Override
	public Rating rateApplication(Application application, Float rating, String ratingStatement)
			throws IllegalArgumentException {
		
		Timestamp created = new Timestamp(System.currentTimeMillis());
		
		Rating r = new Rating();
		r.setRating(rating);
		r.setRatingStatement(ratingStatement);
		r.setCreated(created);
		
		//***WICHTIG*** Hier fehlt noch die Zuweisung zur Application. Wie realisieren wir das genau?
		
		//Setzen einer vorläufigen ID
		r.setID(1);
		
		//Speichern des Objekts in der Datenbank
		r = this.ratingMapper.insert(r);
		
		application.setRatingID(r.getID());
		
		saveApplication(application);
		
		Call c = this.getCallByID(application.getCallID());
		Project p = this.getProjectByID(c.getProjectID());
		OrgaUnit ou = this.getOrgaUnitById(application.getOrgaUnitID());
		
		if (r.getRating() == 1){
			this.createAutomaticEnrollment(p, ou, r);
		}
		
		return r;
	}
	
	/**
	 * Auslesen einer Bewertung für eine Bewerbung
	 * @param application
	 * @return
	 */
	@Override
	public Rating getRatingFor(Application application) throws IllegalArgumentException {
		return this.ratingMapper.findById(application.getRatingID());
	}
	
	/**
	 * Auslesen einer Bewertung für eine Beteiligung
	 * @param enrollment
	 * @return
	 */
	@Override
	public Rating getRatingFor(Enrollment enrollment) throws IllegalArgumentException {
		return this.ratingMapper.findById(enrollment.getRatingID());
	}

	/**
	 * Speichern von Änderungen einer Bewertung
	 */
	@Override
	public void saveRating(Rating rating) throws IllegalArgumentException {
		this.ratingMapper.update(rating);
		
	}

	/**
	 * Löschen einer Bewertung
	 */
	@Override
	public void deleteRating(Rating rating) throws IllegalArgumentException {
		Application a = appMapper.findByRatingID(rating.getID());
		Enrollment e = enrollMapper.findByRatingID(rating.getID());
		
		if (a != null){
		a.setRatingID(null);
		this.appMapper.update(a);
		}
		if (e != null){
		e.setRatingID(null);
		this.enrollMapper.update(e);
		}
		this.ratingMapper.delete(rating);
	}
	
	
	
	/*
	 * -----------------------
	 * -- METHODEN für TEAM --
	 * -----------------------
	 */
	
	/**
	 * Erstellen eines Teams
	 * 
	 * @param name
	 * @param description
	 * @param membercount
	 * @param googleID
	 * @return
	 * @throws IllegalArgumentException
	 */
	@Override
	public Team createTeam(String name, String description, Integer membercount, String googleID) throws IllegalArgumentException {
		
		Team t = new Team();
		Timestamp created = new Timestamp(System.currentTimeMillis());
		
		t.setCreated(created);
		t.setType("Team");
		
		t.setName(name);
		t.setDescription(description);
		t.setMembercount(membercount);
		t.setGoogleID(googleID);
		
		/**
		 * Siehe createPerson
		 */
		t.setID(1);
		
		t = this.teamMapper.insert(t);
		
		PartnerProfile pp = createPartnerProfileFor(t);
		t.setPartnerProfileID(pp.getID());
		
		this.teamMapper.update(t);
		
		return t;
	}
		
	/**
	 * Speichern von Änderungen an einem Team.
	 */
	@Override
	public void saveTeam(Team team) throws IllegalArgumentException {
		this.teamMapper.update(team);
	}
	
	/**
	 * Löschen eines Teams aus der Datenbank 
	 */
	
	
	@Override
	public void deleteTeam(Team team) throws IllegalArgumentException {		
		
//		Vector<Marketplace> markets = getMarketplacesFor(team);
		Vector<Enrollment> enrollments = getEnrollmentFor(team);
		Vector<Application> applications = getApplicationsFor(team);
		
		// nur bei Person, da nur Person Projektleiter: Vector<Project> projects = getProjectsForLeader(team); 
	

			PartnerProfile part = getPartnerProfileFor(team);
						
			if (enrollments != null){
				for (Enrollment e : enrollments){
					deleteEnrollment(e);
				}
			}
			
			if (applications != null){
				for (Application a : applications){
					deleteApplication(a);
				}	
			}	

			this.teamMapper.delete(team);
			
			this.partnerMapper.delete(part);
		
	}
	
	/**
	 * Auslesen eines Teams aus der Datenbank
	 * @param googleID
	 * @return
	 */
	@Override
	public Team getTeamByGoogleID(String googleID){

		return this.teamMapper.findByGoogleID(googleID);
	}
	
	@Override
	public Vector<Team> getAllTeams(){
		return this.teamMapper.findAll();
	}
	

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Business Objekte
	 * ***************************************************************************
	 */
	
	/*
	 * ---------------------------
	 * -- USER LOGIN Überprüfen --
	 * ---------------------------
	 */
		
	private User getUser() {
	    UserService userService = UserServiceFactory.getUserService();
	    return userService.getCurrentUser();
	  }
	
	/*
	 * ***************************************************************************
	 * ABSCHNITT, BEGINN: Verschiedenes
	 * ***************************************************************************
	 */

//	Wird wahrscheinlich nicht benötigt
//	/**
//	 * Auslesen der Organisationseinheit, die aktuell für die Verwaltung zuständig ist.
//	 */
//	@Override
//	public OrgaUnit getOrgaUnit() throws IllegalArgumentException {
//		return this.orgaUnit;
//	}
//	
//	Wird wahrscheinlich nicht benötigt
//	/**
//	 * Setzen der Organisationseinheit, die aktuell für die Verwaltung zuständig ist.
//	 */
//	@Override
//	public void setOrgaUnit(OrgaUnit ou) throws IllegalArgumentException {
//		this.orgaUnit = ou;
//	}
	
	/*
	 * ***************************************************************************
	 * ABSCHNITT, ENDE: Verschiedenes
	 * ***************************************************************************
	 */
	
}
