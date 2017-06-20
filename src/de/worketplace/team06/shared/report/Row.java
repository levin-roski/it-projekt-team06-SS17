package de.worketplace.team06.shared.report;

import java.io.Serializable;
import java.util.Vector;

/**
 * Zeile einer Tabelle eines <code>SimpleReport</code>-Objekts. <code>Row</code>
 * -Objekte implementieren das <code>Serializable</code>-Interface und können
 * daher als Kopie z.B. vom Server an den Client übertragen werden.
 * 
 * @see SimpleReport
 * @see Column
 * @author Thies
 */
public class Row implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  /**
   * Vektor vom Typ Column zum Speichern der Spalten einer Zeile
   */
  private Vector<Column> columns = new Vector<Column>();

  /**
   * Hinzufügen einer Spalte
   * 
   * @param c
   */
  public void addColumn(Column c) {
    this.columns.addElement(c);
  }

  /**
   * Entfernen einer Spalte
   * 
   * @param c
   */
  public void removeColumn(Column c) {
    this.columns.removeElement(c);
  }

  /**
   * Auslesen aller Spalten
   * 
   * @return Vektor<Column>
   */
  public Vector<Column> getColumns() {
    return this.columns;
  }

  /**
   * Auslesen der Anzahl der Spalten
   * 
   * @return int
   */
  public int getColumnCount() {
    return this.columns.size();
  }

  /**
   * Auslesen einer Spalte
   * 
   * @param c
   * @return Spalte
   */
  public Column getColumnAt(int c) {
    return this.columns.elementAt(c);
  }
}
