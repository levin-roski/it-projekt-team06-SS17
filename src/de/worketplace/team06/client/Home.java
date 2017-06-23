package de.worketplace.team06.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry-Point-Klasse des Projekts <b>Worketplace</b>. Diese stellt eine
 * Startseite dar, die eine Navigation zwischen dem Editor oder dem Report
 * Generator bietet.
 */
public class Home implements EntryPoint {
	/**
	 * Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	 * zusichert, benötigen wir eine Methode
	 * <code>public void onModuleLoad()</code>. Diese ist das GWT-Pendant der
	 * <code>main()</code>-Methode normaler Java-Applikationen.
	 */
	public void onModuleLoad() {
		final Image logo = new Image("logo.png");
		RootPanel.get("logo").add(logo);

		final Button editorButton = new Button("Editor");
		editorButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.Location.assign("worketplace.html");
			}
		});
		RootPanel.get("editorButton").add(editorButton);

		final Button reportGeneratorButton = new Button("ReportGenerator");
		reportGeneratorButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.Location.assign("reportGenerator.html");
			}
		});
		RootPanel.get("reportGeneratorButton").add(reportGeneratorButton);
	}
}