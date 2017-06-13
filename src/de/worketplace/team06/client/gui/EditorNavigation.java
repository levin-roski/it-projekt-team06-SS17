package de.worketplace.team06.client.gui;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EditorNavigation extends VerticalPanel {
	public void loadNavigation() {
		MenuBar menu = new MenuBar();
		menu.setAutoOpen(true);
		menu.setAnimationEnabled(true);
	    final SafeHtml image = new SafeHtml() {
			@Override
			public String asString() {
				// TODO Auto-generated method stub
				return "<img src=\"/logo.png\" style=\"width: 225px;\">";
			}
		};
		
		menu.addItem(new MenuItem(image, new Command() {
			public void execute() {
//				TODO Anzeige generieren
//				RootPanel.get("content").clear();
//				RootPanel.get("content").add(alleNutzerAnzeigen);
//				Window.Location.replace("home.html");
			}
		}));
		menu.addSeparator();		
		menu.addItem(new MenuItem("Mein Bereich", new Command() {
			public void execute() {
//				TODO Anzeige generieren
//				RootPanel.get("content").clear();
//				RootPanel.get("content").add(alleNutzerAnzeigen);
//				Window.Location.replace("home.html");
			}
		}));
		menu.addSeparator();
		menu.addItem(new MenuItem("Marktplätze suchen", new Command() {
			public void execute() {
//				TODO Anzeige generieren
//				RootPanel.get("content").clear();
//				RootPanel.get("content").add(alleNutzerAnzeigen);
//				Window.Location.replace("home.html");
			}
		}));
		menu.addSeparator();
		menu.addItem(new MenuItem("Projekte suchen", new Command() {
			public void execute() {
//				TODO Anzeige generieren
//				RootPanel.get("content").clear();
//				RootPanel.get("content").add(alleNutzerAnzeigen);
//				Window.Location.replace("home.html");
			}
		}));
		menu.addSeparator();
		menu.addItem(new MenuItem("Ausschreibungen suchen", new Command() {
			public void execute() {
//				TODO Anzeige generieren
//				RootPanel.get("content").clear();
//				RootPanel.get("content").add(alleNutzerAnzeigen);
//				Window.Location.replace("home.html");
			}
		}));
		menu.addSeparator();
		menu.addItem(new MenuItem("Ausschreibungen suchen", new Command() {
			public void execute() {
//				TODO Anzeige generieren
//				RootPanel.get("content").clear();
//				RootPanel.get("content").add(alleNutzerAnzeigen);
//				Window.Location.replace("home.html");
			}
		}));
		menu.addSeparator();
		MenuBar optionenMenu = new MenuBar(true);
		optionenMenu.setAnimationEnabled(true);
		optionenMenu.addItem(new MenuItem("Mein Profil", new Command() {
			public void execute() {
//				TODO Anzeige generieren
//				RootPanel.get("content").clear();
//				RootPanel.get("content").add(alleNutzerAnzeigen);
//				Window.Location.replace("home.html");
			}
		}));
		optionenMenu.addItem(new MenuItem("Zum Report Generator", new Command() {
			public void execute() {
//				TODO Anzeige generieren
//				RootPanel.get("content").clear();
//				RootPanel.get("content").add(alleNutzerAnzeigen);
//				Window.Location.replace("home.html");
			}
		}));
		optionenMenu.addItem(new MenuItem("Logout", new Command() {
			public void execute() {
//				TODO Anzeige generieren
//				RootPanel.get("content").clear();
//				RootPanel.get("content").add(alleNutzerAnzeigen);
//				Window.Location.replace("home.html");
			}
		}));
		menu.addItem(new MenuItem("Mehr", optionenMenu));


		RootPanel.get("navigation").add(menu);
	}
}
