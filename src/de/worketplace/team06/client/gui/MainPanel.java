/**
 * 
 */
package de.worketplace.team06.client.gui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.History;
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
			this.getWidget(0);
			try {
				Widget item = this.getWidget(1);
				this.getWidget(0).removeFromParent();
				this.insertWest(sp, 75, item);
			} catch (Exception e) {
				this.getWidget(0).removeFromParent();
				this.addWest(sp, 100);
			}
		} catch (Exception e) {
			this.addWest(new ScrollPanel(viewItem), 100);
		}
		this.getWidget(0).setStyleName("main-panel-overview");
		this.getWidget(0).setHeight("100%");
		ClientsideSettings.setCurrentView(viewItem);
		if (viewItem instanceof MarketplaceOverView) {
			History.newItem("Marktplaetze");
		} else if (viewItem instanceof MyOverView) {
			History.newItem("");
		} else if (viewItem instanceof MarketplaceView) {
			History.newItem("Marktplatz-Details" + ClientsideSettings.getCurrentMarketplaceId());
		} else if (viewItem instanceof ProjectView) {
			History.newItem("Projekt-Details" + ClientsideSettings.getCurrentProjectId() + "-"
					+ ClientsideSettings.getCurrentMarketplaceId());
		}
		// TODO Auch tiefere Strukturen wie ProjectView hinzufügen!
	}

	public <T extends Widget> void setForm(T formItem) {
		ScrollPanel sp = new ScrollPanel(formItem);
		sp.setWidth("100%");
		try {
			this.getWidget(1);
			this.getWidget(1).removeFromParent();
			this.addEast(sp, 25);
		} catch (Exception e) {
			try {
				Widget overviewWidget = this.getWidget(0);
				this.getWidget(0).removeFromParent();
				this.addWest(overviewWidget, 75);
			} catch (Exception e1) {
			}
			this.addEast(sp, 25);
		}
		this.getWidget(1).setStyleName("main-panel-form");
		this.getWidget(1).setHeight("100%");
		sp.setHeight("100%");
	}

	public void closeForm() {
		try {
			this.getWidget(1).removeFromParent();
			try {
				Widget overviewWidget = this.getWidget(0);
				this.getWidget(0).removeFromParent();
				this.addWest(overviewWidget, 100);
			} catch (Exception e1) {
			}
		} catch (Exception e) {
		}
	}
}