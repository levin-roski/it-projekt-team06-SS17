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
}
