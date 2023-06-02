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

    public synchronized boolean save(Shopping shopping) {
        return repo.save(shopping);
    }

    public synchronized boolean edit(Long id, Shopping shopping) {
        return repo.edit(id, shopping);
    }

    public synchronized boolean delete(Long id) {
        return repo.delete(id);
    }

    public synchronized Shopping findByProductId(Long productId) {
        return repo.findByproductId(productId);
    }

    public synchronized List<Shopping> findAll() {
        return repo.findAll();
    }

    public synchronized List<Shopping> findByName(String name) {
        return repo.findByName(name);
    }
}
