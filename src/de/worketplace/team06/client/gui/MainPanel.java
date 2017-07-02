/**
 * 
 */
package de.worketplace.team06.client.gui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.View;

/**
 * 
 * @author Roski
 */
public class MainPanel extends DockLayoutPanel {
	public MainPanel(Unit unit) {
		super(unit);
		this.setStyleName("main-panel");
	}

	public <T extends View> void setView(T viewItem) {
		ScrollPanel sp = new ScrollPanel(viewItem);
		sp.setWidth("100%");
		sp.setHeight("100%");
		try {
			this.getWidget(1).removeFromParent();
		} catch (Exception e) {
		}
		try {
			this.getWidget(0);
			this.getWidget(0).removeFromParent();
			this.addWest(sp, 100);
		} catch (Exception e) {
			this.addWest(new ScrollPanel(viewItem), 100);
		}
		this.getWidget(0).setStyleName("main-panel-overview");
		this.getWidget(0).setHeight("100%");
		ClientsideSettings.setCurrentView(viewItem);
	}

	public <T extends Widget> void setForm(T formItem) {
		ScrollPanel sp = new ScrollPanel(formItem);
		sp.setWidth("100%");
		try {
			this.getWidget(1);
			this.getWidget(1).removeFromParent();
			this.addEast(sp, 25);
		} catch (Exception e) {
			this.addEast(sp, 25);
		}
		this.getWidget(1).setStyleName("main-panel-form");
		this.getWidget(1).setHeight("100%");
		sp.setHeight("100%");
	}

	public void closeForm() {
		this.getWidget(1).removeFromParent();
	}
}