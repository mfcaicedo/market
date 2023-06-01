package co.unicauca.openmarket.server.domain.services;

import co.unicauca.openmarket.client.domain.Shopping;
import co.unicauca.openmarket.server.access.IShoppingRepository;
import java.util.List;

/**
 *
 * @author
 */
public class ShoppingService {

    /**
     * Repositorio de shopping
     */
    IShoppingRepository repo;

    /**
     * Constructor parametrizado. Hace inyeccion de dependencias
     *
     * @param repo repositorio de tipo IShoppingRepository
     */
    public ShoppingService(IShoppingRepository repo) {
        this.repo = repo;
    }

    public boolean save(Shopping shopping) {
        return repo.save(shopping);
    }

    public boolean edit(Long id, Shopping shopping) {
        return repo.edit(id, shopping);
    }

    public boolean delete(Long id) {
        return repo.delete(id);
    }

    public Shopping findById(Long id) {
        return repo.findById(id);
    }

    public List<Shopping> findAll() {
        return repo.findAll();
    }

    public List<Shopping> findByName(String name) {
        return repo.findByName(name);
    }
}
