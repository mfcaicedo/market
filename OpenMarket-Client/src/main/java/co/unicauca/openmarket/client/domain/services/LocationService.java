package co.unicauca.openmarket.client.domain.services;

import co.unicauca.openmarket.client.access.ILocationAccess;
import co.unicauca.openmarket.client.domain.Location;

import java.util.List;

/**
 *
 * @author 
 */
public class LocationService {

    /**
     * Repositorio de shopping
     */
    ILocationAccess repo;

    /**
     * Constructor parametrizado. Hace inyeccion de dependencias
     *
     * @param repo repositorio de tipo ILocationRepository
     */
    public LocationService(ILocationAccess repo) {
        this.repo = repo;
    }

    public List<Location> findAll() {
        return repo.findAll();
    }

}
