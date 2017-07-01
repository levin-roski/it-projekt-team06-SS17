package de.worketplace.team06.client.gui.report;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.client.gui.MainPanel;
import de.worketplace.team06.shared.report.*;

@SuppressWarnings("unused")
public class ReportNavigation extends VerticalPanel {
	MainPanel mainPanel = ClientsideSettings.getMainPanel();
	
	public ReportNavigation() {
		MenuBar menu = new MenuBar();
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
				History.newItem("Startseite");
			}
		}));
		menu.addSeparator();
		menu.addItem(new MenuItem("Startseite", new Command() {
			public void execute() {
				History.newItem("Startseite");
			}
		}));
		menu.addSeparator();
		menu.addItem(new MenuItem("Alle Ausschreibungen", new Command() {
			public void execute() {
				History.newItem("Alle-Ausschreibungen");
			}
		}));
		menu.addSeparator();
		menu.addItem(new MenuItem("Fan-In-Fan-Out-Analyse", new Command() {
			public void execute() {
				History.newItem("Fan-In-Fan-Out-Analyse");
			}
		}));
		menu.addSeparator();
		
		
		
		
		MenuBar applicantMenu = new MenuBar(true);
		applicantMenu.setAnimationEnabled(true);
		applicantMenu.addItem(new MenuItem("Passende Ausschreibungen zu meinem Partnerprofil", new Command() {
			public void execute() {
				History.newItem("Passende-Ausschreibungen-zu-meinem-Partnerprofil");
			}
		}));
		applicantMenu.addItem(new MenuItem("Anzahl meiner Bewerbungen nach Status (Fan-In)", new Command() {
			public void execute() {
				History.newItem("Anzahl-meiner-Bewerbungen-nach-Status-(Fan-In)");
			}
		}));		
		menu.addItem(new MenuItem("Berichte für Bewerber", applicantMenu));
		menu.addSeparator();

		
		
		MenuBar leaderMenu = new MenuBar(true);
		leaderMenu.setAnimationEnabled(true);
		leaderMenu.addItem(new MenuItem("Meine Ausschreibungen", new Command() {
			public void execute() {
				History.newItem("Meine-Ausschreibungen");
			}
		}));
		leaderMenu.addItem(new MenuItem("Anzahl meiner Ausschreibungen nach Status (Fan-Out)", new Command() {
			public void execute() {
				History.newItem("Anzahl-meiner-Ausschreibungen-nach-Status-(Fan-Out)");
			}
		}));
		leaderMenu.addItem(new MenuItem("Bewerbungen auf meine Ausschreibungen", new Command() {
			public void execute() {
				History.newItem("Bewerbungen-auf-meine-Ausschreibungen");
			}
		}));
		leaderMenu.addItem(new MenuItem("Bewerbungen meines Bewerbers", new Command() {
			public void execute() {
				History.newItem("Bewerbungen-meines-Bewerbers");
			}
		}));
		leaderMenu.addItem(new MenuItem("Beteiligungen meines Bewerbers", new Command() {
			public void execute() {
				History.newItem("Beteiligungen-meines-Bewerbers");
			}
		}));
		leaderMenu.addItem(new MenuItem("Verflechtungen meines Bewerbers", new Command() {
			public void execute() {
				History.newItem("Verflechtungen-meines-Bewerbers");
			}
		}));
		leaderMenu.addItem(new MenuItem("Verflechtungen sämtlicher meiner Bewerber", new Command() {
			public void execute() {
				History.newItem("Verflechtungen-saemtlicher-meiner-Bewerber");
			}
		}));
		menu.addItem(new MenuItem("Berichte für Projektleiter", leaderMenu));
		menu.addSeparator();
		
		
		
		
		MenuBar optionenMenu = new MenuBar(true);
		optionenMenu.setAnimationEnabled(true);
		optionenMenu.addItem(new MenuItem("Mein Nutzer", new Command() {
			public void execute() {
				History.newItem("Mein-Nutzer");
			}
		}));
		optionenMenu.addItem(new MenuItem("Zum Worketplace Editor", new Command() {
			public void execute() {
				 Window.Location.replace("worketplace.html");
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
