package de.worketplace.team06.client.gui;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.worketplace.team06.client.ClientsideSettings;
import de.worketplace.team06.shared.bo.OrgaUnit;
import de.worketplace.team06.shared.bo.Organisation;
import de.worketplace.team06.shared.bo.Person;
import de.worketplace.team06.shared.bo.Team;

/**
 * Navigation, die rechts neben der Report und Editornavigation angezeigt wird.
 * 
 * @author Tobias Müller
 */
public class UserNavigationReport extends VerticalPanel {
	MainPanel mainPanel = ClientsideSettings.getMainPanel();

	public UserNavigationReport() {
		MenuBar menu = new MenuBar();
		menu.setAutoOpen(true);
		menu.setAnimationEnabled(true);
		this.setWidth("100%");
		this.add(menu);
		final SafeHtml usericon = new SafeHtml() {
			private static final long serialVersionUID = 1L;
			@Override
			public String asString() {
				return "<img src=\"/usericon.png\">";
			}
		};

		menu.addItem(new MenuItem(usericon, new Command() {
			public void execute() {
				History.newItem("Mein-Nutzer");
			}
		}));
		MenuBar optionenMenu = new MenuBar(true);
		optionenMenu.setAnimationEnabled(true);
		optionenMenu.addItem(new MenuItem("User editieren", new Command() {
			public void execute() {
				History.newItem("Mein-Nutzer");
			}
		}));
		optionenMenu.addItem(new MenuItem("Worketplace Editor", new Command() {
			public void execute() {
				 Window.Location.replace("worketplace.html");
			}
		}));
		optionenMenu.addItem(new MenuItem("Logout", new Command() {
			public void execute() {
				Window.Location.replace(ClientsideSettings.getLoginInfo().getLogoutUrl());
			}
		}));
		
		OrgaUnit user = ClientsideSettings.getCurrentUser();
		String name = "Unknown User";
		if (user instanceof Person) {
			Person p = (Person) user;
			name = p.getFirstName() + " " + p.getLastName();
		} else if (user instanceof Team) {
			Team t = (Team) user;
			name = t.getName();
		} else if (user instanceof Organisation) {
			Organisation org = (Organisation) user;
			name = org.getName();
		}
		menu.addItem(new MenuItem(name, optionenMenu));
		
	}
}
