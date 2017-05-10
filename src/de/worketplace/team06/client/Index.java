package de.worketplace.team06.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Index implements EntryPoint {
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

		// RootPanel.get("sayHello").add();

		final Button sendButton = new Button("Send");
		// We can add style names to widgets
		sendButton.addStyleName("sendButton");
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("editorButton").add(sendButton);
	}
}