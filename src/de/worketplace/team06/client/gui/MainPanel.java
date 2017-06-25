/**
 * 
 */
package de.worketplace.team06.client.gui;

import com.google.cloud.sql.jdbc.internal.ClientSideBlob;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.DataLoading;

/**
 * 
 * @author Roski
 */
public class MainPanel extends DockLayoutPanel {
	public MainPanel(Unit unit) {
		super(unit);
		this.setStyleName("main-panel");
	}

	public <T extends Widget & DataLoading> void setView(T viewItem) {
		ScrollPanel sp = new ScrollPanel(viewItem);
		sp.setWidth("100%");
		try {
			this.getWidget(0);
			try {
				Widget item = this.getWidget(1);
				this.getWidget(0).removeFromParent();
				this.insertWest(sp, 80, item);
			} catch (Exception e) {
				this.getWidget(0).removeFromParent();
				this.addWest(sp, 100);
			}
		} catch (Exception e) {
			this.addWest(new ScrollPanel(viewItem), 100);
		}
		this.getWidget(0).setStyleName("main-panel-overview");
		this.getWidget(0).setHeight("100%");
		sp.setHeight("100%");
		ClientsideSettings.setCurrentView(viewItem);
	}

	public <T extends Widget> void setForm(T formItem) {
		ScrollPanel sp = new ScrollPanel(formItem);
		sp.setWidth("100%");
		try {
			this.getWidget(1);
			this.getWidget(1).removeFromParent();
			this.addEast(sp, 20);
		} catch (Exception e) {
			try {
				Widget overviewWidget = this.getWidget(0);
				this.getWidget(0).removeFromParent();
				this.addWest(overviewWidget, 80);
			} catch (Exception e1) {
			}
			this.addEast(sp, 20);
		}
		this.getWidget(1).setStyleName("main-panel-item");
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