package de.worketplace.team06.server.db;

import java.sql.*;
import java.util.Vector;

import de.worketplace.team06.shared.bo.*;

public class MarketplaceMapper {
	
	private static MarketplaceMapper marketplaceMapper = null;
	
	protected MarketplaceMapper() {
		
	}

	public static MarketplaceMapper marketplaceMapper() {
		if (marketplaceMapper == null) {
			marketplaceMapper = new MarketplaceMapper();
		}
		
		return marketplaceMapper;
	}
/*	
 * 
 * 
 * PATRICK
 * 
	public Marketplace findByKey(int id)
	
	public Marketplace findBy schauen 
	
	public Marketplace insert(Marketplace a)
	
	public Marketplace update(Marketplace a)
	
	public void delete(Marketplace a)
*/

	public Vector<Marketplace> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<Marketplace> findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//AutoGeneratedBy Johannes
	public Marketplace insert(Marketplace m) {
		// TODO Auto-generated method stub
		return null;
	}

	//AutoGeneratedBy Johannes
	public void update(Marketplace marketplace) {
		// TODO Auto-generated method stub
		
	}
	
	//AutoGeneratedBy Johannes
	public Vector<Marketplace> findByOrgaUnitID(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
