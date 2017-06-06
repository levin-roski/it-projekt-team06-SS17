package de.worketplace.team06.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Home implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Image logo = new Image("logo.png");
		RootPanel.get("logo").add(logo);
		
		final Button editorButton = new Button("Editor");
		editorButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.Location.assign("Worketplace.html");
			}
		});
		RootPanel.get("editorButton").add(editorButton);

		final Button reportGeneratorButton = new Button("ReportGenerator");
		reportGeneratorButton.addClickHandler(new ClickHandler() {
		  public void onClick(ClickEvent event) {
		    Window.Location.assign("ReportGenerator.html");
		  }
		});
		RootPanel.get("reportGeneratorButton").add(reportGeneratorButton);
	}
}