package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.client.domain.Location;

import java.util.List;

/**
 *
 * @author 
 */
public interface ILocationRepository {
    
    List<Location> findAll();
    
}
