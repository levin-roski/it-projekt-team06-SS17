package de.worketplace.team06.client;

public abstract class View extends Page {
	public View() {
		this.setBreadcrumb();
	}
	public abstract void setBreadcrumb();
	public abstract void loadData();
}