package de.worketplace.team06.shared.bo;

import java.util.*;

/**
 * 
 */
public class Team extends OrgaUnit {

    /**
	 * ID Zur Serialisierung und Prüfung der Version einer Klasse.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * Deklaration der Attribute.
     */
    private String name;
    
    private int membercount;
    
	/**
     * Default constructor
     */
    public Team() {
    }

	/**
	 * Getter Methoden für die einzelnen Attribute. 
	 */
	public String getName() {
		return name;
	}
	
	public int getMembercount() {
		return membercount;
	}
	
	/**
	 * Setter Methoden für die einzelnen Attribute. 
	 */
	public void setName(String name) {
		this.name = name;
	}

	public void setMembercount(int membercount) {
		this.membercount = membercount;
	}

}