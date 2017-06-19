package de.worketplace.team06.client.gui;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;

import com.google.gwt.user.client.ui.RootPanel;

public class SearchMarketplace {
	private static class Marketplace {
		private final String title;
		private final String owner;
		private final String description;
		
		public Marketplace(String title, String owner, String description) {
			this.title = title;
			this.owner = owner;
			this.description = description;
		}
	}
	
	private static final List<Marketplace> MARKETPLACE = Arrays.asList(
			new Marketplace("Stuttgart", "Patrick Strepp", "Hier steht eine Beschreibung des Marktplatzes Stuttgart"),
			new Marketplace("Karlsruhe", "Dominik Florschütz", "Hier steht eine Beschreibung des Marktplatzes Karlsruhe"),
			new Marketplace("Heilbronn", "Tobias Müller", "Hier steht eine Beschreibung des Marktplatzes Heilbronn"));
	
	public void load() {
		final CellTable<Marketplace> table = new CellTable<Marketplace>();
		table.setPageSize(3);
		
		TextColumn<Marketplace> titleColumn = new TextColumn<Marketplace>() {
			@Override
			public String getValue(Marketplace object) {
				return object.title;
			}
		};
		table.addColumn(titleColumn, "Name");
		
		TextColumn<Marketplace> ownerColumn = new TextColumn<Marketplace>() {
			@Override
			public String getValue(Marketplace object) {
				return object.owner;
			}
		};
		table.addColumn(titleColumn, "Inhaber");
		
		TextColumn<Marketplace> descriptionColumn = new TextColumn<Marketplace>() {
			@Override
			public String getValue(Marketplace object) {
				return object.description;
			}
		};
		table.addColumn(titleColumn, "Beschreibung");
		
		RootPanel.get("content").add(table);
	}
}