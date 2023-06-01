package co.unicauca.openmarket.server.domain.services;

import co.unicauca.openmarket.client.domain.Location;
import co.unicauca.openmarket.server.access.ILocationRepository;
import java.util.List;

/**
 *
 * @author 
 */
public class LocationService {

    /**
     * Repositorio de shopping
     */
    ILocationRepository repo;

    /**
     * Constructor parametrizado. Hace inyeccion de dependencias
     *
     * @param repo repositorio de tipo ILocationRepository
     */
    public LocationService(ILocationRepository repo) {
        this.repo = repo;
    }

    public List<Location> findAll() {
        return repo.findAll();
    }

}
