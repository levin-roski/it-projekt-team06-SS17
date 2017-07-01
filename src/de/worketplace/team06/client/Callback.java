package de.worketplace.team06.client;

public interface Callback {
	public void run();
	
	public <T> void runOnePar(T parameter);
}