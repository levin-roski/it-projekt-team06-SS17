package de.worketplace.team06.client.gui;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.worketplace.team06.client.ClientsideSettings;

public class EditorNavigation extends VerticalPanel {
	MainPanel mainPanel = ClientsideSettings.getMainPanel();
	
	public EditorNavigation() {
		MenuBar menu = new MenuBar();
		menu.setAutoOpen(true);
		menu.setAnimationEnabled(true);
		this.setWidth("100%");
		this.add(menu);
		final SafeHtml image = new SafeHtml() {
			private static final long serialVersionUID = 1L;
			@Override
			public String asString() {
				return "<img src=\"/logo.png\" style=\"width: 225px;\">";
			}
		};

		menu.addItem(new MenuItem(image, new Command() {
			public void execute() {
				mainPanel.setView(new MyOverview());
			}
		}));
		menu.addSeparator();
		menu.addItem(new MenuItem("Mein Bereich", new Command() {
			public void execute() {
				mainPanel.setView(new MyOverview());
			}
		}));
		menu.addSeparator();
		menu.addItem(new MenuItem("Marktplätze", new Command() {
			public void execute() {
				mainPanel.setView(new MarketplaceOverview());
			}
		}));
		menu.addSeparator();
		MenuBar optionenMenu = new MenuBar(true);
		optionenMenu.setAnimationEnabled(true);
		optionenMenu.addItem(new MenuItem("Mein Profil", new Command() {
			public void execute() {
				// TODO Anzeige generieren
				// RootPanel.get("content").clear();
				// RootPanel.get("content").add(alleNutzerAnzeigen);
				// Window.Location.replace("home.html");
			}
		}));
		optionenMenu.addItem(new MenuItem("Zum Report Generator", new Command() {
			public void execute() {
				// TODO Anzeige generieren
				// RootPanel.get("content").clear();
				// RootPanel.get("content").add(alleNutzerAnzeigen);
				// Window.Location.replace("home.html");
			}
		}));
		optionenMenu.addItem(new MenuItem("Logout", new Command() {
			public void execute() {
				// TODO Anzeige generieren
				// RootPanel.get("content").clear();
				// RootPanel.get("content").add(alleNutzerAnzeigen);
				// Window.Location.replace("home.html");
			}
		}));
		menu.addItem(new MenuItem("Mehr", optionenMenu));
		menu.addSeparator();
	}
}
