package de.worketplace.team06.client;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.worketplace.team06.client.gui.MainPanel;
import de.worketplace.team06.shared.bo.OrgaUnit;

/**
 * Superklasse von Form und View.
 * 
 * @author Roski
 */
public abstract class Page extends VerticalPanel {
	protected OrgaUnit currentUser = ClientsideSettings.getCurrentUser();
	protected MainPanel mainPanel = ClientsideSettings.getMainPanel();
	protected final DateTimeFormat simpleDateFormat = DateTimeFormat.getFormat("dd.MM.yyyy");

	/**
	 * Gibt eine h1 Überschrift als HorizontalPanel zurück.
	 * 
	 * @param text
	 *            Der Text, der in der h1 Überschrift erscheinen soll.
	 * @param isFirstPageElement
	 *            Falls true wird der Abstand der Überschrift oben verringert.
	 * @return h1 Überschrift als HorizontalPanel
	 */
	protected HorizontalPanel createHeadline(final String text, boolean isFirstPageElement) {
		HorizontalPanel hp = new HorizontalPanel();
		HTML sh = new HTML();
		if (isFirstPageElement) {
			sh.setHTML("<h1 class=\"first-element\">" + text + "</h1>");
		} else {
			sh.setHTML("<h1>" + text + "</h1>");
		}
		hp.add(sh);
		return hp;
	}

	/**
	 * Gibt eine h2 Überschrift als HorizontalPanel zurück.
	 * 
	 * @param text
	 *            Der Text, der in der h2 Überschrift erscheinen soll.
	 * @return h2 Überschrift als HorizontalPanel
	 */
	protected HorizontalPanel createSecondHeadline(final String text) {
		HorizontalPanel hp = new HorizontalPanel();
		HTML sh = new HTML();
		sh.setHTML("<h2>" + text + "</h2>");
		hp.add(sh);
		return hp;
	}
}