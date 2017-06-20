package de.worketplace.team06.client.gui;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

public class SearchMarketplace extends Page {
	
	private static class Marketplace {
		private final String title;
		private final String owner;
		private final String description;
		
		private Marketplace m1 = new Marketplace(title, owner, description);
		
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
	
	
	
	@Override
	protected String getHeadlineText() {
		return "<h1>Alle Marktplätze</h1>";
	}
	
	public void run() {
		
		final CellTable<Marketplace> allmarketplacestable = new CellTable<Marketplace>();
		allmarketplacestable.setPageSize(3);
		
		final SingleSelectionModel<Marketplace> allmarketplacessm = new SingleSelectionModel<Marketplace>();
		
		allmarketplacestable.setSelectionModel(allmarketplacessm);
		
		allmarketplacessm.addSelectionChangeHandler(new Handler() { 
			
		@Override	
		public void onSelectionChange (SelectionChangeEvent event) {
			m1 = allmarketplacessm.getSelectedObject();
			Page page = new SearchProject(m1);
			RootPanel.get("Anzeige").clear();
			RootPanel.get("Anzeige").add(page);
		}
	});
		
		TextColumn<Marketplace> titleColumn = new TextColumn<Marketplace>() {
			@Override
			public String getValue(Marketplace object) {
				return object.title;
			}
		};
		allmarketplacestable.addColumn(titleColumn, "Name");
		
		TextColumn<Marketplace> ownerColumn = new TextColumn<Marketplace>() {
			@Override
			public String getValue(Marketplace object) {
				return object.owner;
			}
		};
		allmarketplacestable.addColumn(titleColumn, "Inhaber");
		
		TextColumn<Marketplace> descriptionColumn = new TextColumn<Marketplace>() {
			@Override
			public String getValue(Marketplace object) {
				return object.description;
			}
		};
		allmarketplacestable.addColumn(titleColumn, "Beschreibung");
		allmarketplacestable.setRowData(MARKETPLACE);
		allmarketplacestable.setWidth("100%", true);
		
		RootPanel.get("content").add(allmarketplacestable);
	}
}