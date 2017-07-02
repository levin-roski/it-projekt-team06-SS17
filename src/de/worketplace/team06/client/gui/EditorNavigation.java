package de.worketplace.team06.client.gui;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.worketplace.team06.client.ClientsideSettings;

/**
 * Editor Navigation, welche bei einem Klick die Token-Navigation ändert, oder
 * die gesamte URL.
 * 
 * @author Roski
 */
public class EditorNavigation extends VerticalPanel {
	MainPanel mainPanel = ClientsideSettings.getMainPanel();

	public EditorNavigation() {
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
		menu.addItem(new MenuItem("Mein Bereich", new Command() {
			public void execute() {
				History.newItem("Startseite");
			}
		}));
		menu.addSeparator();
		menu.addItem(new MenuItem("Marktplätze", new Command() {
			public void execute() {
				History.newItem("Marktplaetze");
			}
		}));
		menu.addSeparator();
		menu.addItem(new MenuItem("Projekte", new Command() {
			public void execute() {
				History.newItem("Projekte");
			}
		}));
		menu.addSeparator();
		menu.addItem(new MenuItem("Ausschreibungen", new Command() {
			public void execute() {
				History.newItem("Ausschreibungen");
			}
		}));
	}
}
