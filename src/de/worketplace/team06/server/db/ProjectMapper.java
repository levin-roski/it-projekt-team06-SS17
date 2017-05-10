package de.worketplace.team06.server.db;

import java.util.*;

/**
 * 
 */
public class ProjectMapper {

	private static ProjectMapper projectMapper = null;
	 /**
	   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected ProjectMapper(){
		
	}
	
	public static ProjectMapper projecteMapper(){
		if (projectMapper == null){
			projectMapper = new ProjectMapper();
		}
		return projectMapper; 
	}

    /**
     * @param project 
     * @return
     */
    public Project insert(Project project) {
        // TODO implement here
        return null;
    }

    /**
     * @param project 
     * @return
     */
    public Project update(Project project) {
        // TODO implement here
        return null;
    }

    /**
     * @param project
     */
    public void delete(Project project) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Vector<Project> findAll() {
        // TODO implement here
        return null;
    }

	
}
