package de.worketplace.team06.client.gui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SearchMarketplace implements EntryPoint {
	
	 public void onModuleLoad() {
		 
	 final Label searchMarketplace = new Label("Alle Marktplätze");
		 
	      // Create a Flex Table
	      final FlexTable flexTable = new FlexTable();
	      FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
	      flexTable.addStyleName("flexTable");
	      flexTable.setWidth("100%");
	      flexTable.setCellSpacing(5);
	      flexTable.setCellPadding(3);
	      
	      // Header-Zeile der Tabelle festlegen.
			flexTable.setText(0, 0, "Name");
			flexTable.setText(0, 1, "Inhaber");
			flexTable.setText(0, 2, "Beschreibung");
	 }

}
