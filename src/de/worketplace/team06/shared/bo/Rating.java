package de.worketplace.team06.shared.bo;

/**
 * 
 */
public class Rating extends BusinessObject {

    /**
	 * ID Zur Serialisierung und Prüfung der Version einer Klasse.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public Rating() {
    }

    /**
     * Variable für den Wert der Bewertung
     */
    private Float rating;

    /**
     * Variable für das Statement der Bewertung
     */
    private String ratingStatement;

    /**
     * Auslesen des Wertes der Bewertung
     * @return
     */
    public Float getRating() {
        return this.rating;
    }

    /**
     * Auslesen des Statements der Bewertung
     * @return ratingStatement
     */
    public String getRatingStatement() {
        return ratingStatement;
    }

    /**
     * Setzen des Wertes der Bewertung
     * @param rating
     */
    public void setRating(Float value) {
        this.rating = value;
    }

    /**
     * Setzen des Statements der Bewertung
     * @param ratingStatement
     */
    public void setRatingStatement(String statement) {
        this.ratingStatement = statement;
    }

}