package de.worketplace.team06.shared.report;

import java.io.Serializable;
import java.sql.Timestamp;

import de.worketplace.team06.shared.report.Paragraph;

/**
 * <p>
 * Basisklasse aller Reports. Reports sind als <code>Serializable</code>
 * deklariert, damit sie von dem Server an den Client gesendet werden können.
 * Der Zugriff auf Reports erfolgt also nach deren Bereitstellung lokal auf dem
 * Client.
 * </p>
 * <p>
 * Ein Report besitzt eine Reihe von Standardelementen. Sie werden mittels
 * Attributen modelliert und dort dokumentiert.
 * </p>
 * 
 * @see Report
 * @author Thies
 */
public abstract class Report implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  /**
   * Variable für das Impressum
   */
  private Paragraph imprint = null;

  /**
   * Kopfdaten des Berichts
   */
  private Paragraph headerData = null;

  /**
   * Titel des Berichts
   */
  private String title = "Report";

  /**
   * Datum der Erstellung des Berichts
   */
  private Timestamp created;

  /**
   * Auslesen des Impressums
   * 
   * @return imprint
   */
  public Paragraph getImprint() {
    return this.imprint;
  }

  /**
   * Setzen des Impressums
   * 
   * @param imprint
   */
  public void setImprint(Paragraph imprint) {
    this.imprint = imprint;
  }
  
  /**
   * Auslesen der Kopfdaten
   * 
   * @return headerData
   */
  public Paragraph getHeaderData() {
    return this.headerData;
  }

  /**
   * Setzen der Kopfdaten.
   * 
   * @param headerData
   */
  public void setHeaderData(Paragraph headerData) {
    this.headerData = headerData;
  }

  /**
   * Auslesen des Titels des Berichts
   * 
   * @return title
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Setzen des Titels des Berichts
   * 
   * @param title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Auslesen des Erstellungsdatums
   * 
   * @return created
   */
  public Timestamp getCreated() {
    return this.created;
  }

  /**
   * Setzen des Erstellungsdatums
   * 
   * @param created
   */
  public void setCreated(Timestamp created) {
    this.created = created;
  }

}
