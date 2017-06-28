package de.worketplace.team06.client.gui;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.worketplace.team06.client.ClientsideSettings;

public class EditorNavigation extends VerticalPanel {
	MainPanel mainPanel = ClientsideSettings.getMainPanel();
	
	public EditorNavigation() {
		MenuBar menu = new MenuBar();
		ClientsideSettings.setCurrentNavigation(menu);
		menu.setAutoOpen(true);
		menu.setAnimationEnabled(true);
		this.setWidth("100%");
		this.add(menu);
		final SafeHtml logo = new SafeHtml() {
			private static final long serialVersionUID = 1L;
			@Override
			public String asString() {
				return "<img src=\"/logo.png\" style=\"width: 225px;\">";
			}
		};

		menu.addItem(new MenuItem(logo, new Command() {
			public void execute() {
				mainPanel.setView(new MyOverView());
			}
		}));
		menu.addSeparator();
		menu.addItem(new MenuItem("Mein Bereich", new Command() {
			public void execute() {
				mainPanel.setView(new MyOverView());
			}
		}));
		menu.addSeparator();
		menu.addItem(new MenuItem("Marktplätze", new Command() {
			public void execute() {
				mainPanel.setView(new MarketplaceOverView());
			}
		}));
		menu.addSeparator();
		MenuBar optionenMenu = new MenuBar(true);
		optionenMenu.setAnimationEnabled(true);
		optionenMenu.addItem(new MenuItem("Mein Nutzer", new Command() {
			public void execute() {
				mainPanel.setView(new OrgaUnitFormView(null));
			}
		}));
		optionenMenu.addItem(new MenuItem("Mein Partnerprofil", new Command() {
			public void execute() {
				mainPanel.setView(new OrgaUnitPartnerProfileView());
			}
		}));
		optionenMenu.addItem(new MenuItem("Zum Report Generator", new Command() {
			public void execute() {
				 Window.Location.replace("report.html");
			}
		}));
		optionenMenu.addItem(new MenuItem("Logout", new Command() {
			public void execute() {
				Window.Location.replace(ClientsideSettings.getLoginInfo().getLogoutUrl());
			}
		}));
		menu.addItem(new MenuItem("Mehr", optionenMenu));
		menu.addSeparator();
	}
}
