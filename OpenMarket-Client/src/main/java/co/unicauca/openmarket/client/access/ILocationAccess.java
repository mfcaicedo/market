package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.client.domain.Location;

import java.util.List;

/**
 *
 * @author 
 */
public interface ILocationAccess {
    
    List<Location> findAll();
    
}
