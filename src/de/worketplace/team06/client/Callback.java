package de.worketplace.team06.client;

/**
 * Ein Callback kann in eine Methode übergeben werden, dass die gefüllte Methode
 * des Callbacks nach einer Kernaktion der aufgerufenen Methode ausgeführt wird.
 * 
 * @author Roski
 */
public interface Callback {
	public void run();

	public <T> void runOnePar(T parameter);
}