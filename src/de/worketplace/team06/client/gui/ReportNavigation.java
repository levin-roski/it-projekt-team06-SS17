package de.worketplace.team06.client.gui;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.worketplace.team06.client.ClientsideSettings;

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
				mainPanel.setView(new HomeReportView());
			}
		}));
		menu.addSeparator();
		menu.addItem(new MenuItem("Startseite", new Command() {
			public void execute() {
				mainPanel.setView(new HomeReportView());
			}
		}));
		menu.addSeparator();
		menu.addItem(new MenuItem("AllCallsReportView", new Command() {
			public void execute() {
				mainPanel.setView(new AllCallsReportView());
			}
		}));
		menu.addSeparator();
		MenuBar optionenMenu = new MenuBar(true);
		optionenMenu.setAnimationEnabled(true);
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
