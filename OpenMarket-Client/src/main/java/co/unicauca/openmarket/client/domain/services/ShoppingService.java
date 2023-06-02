package co.unicauca.openmarket.client.domain.services;

import co.unicauca.openmarket.client.access.IShoppingAccess;
import co.unicauca.openmarket.client.domain.Shopping;

import java.util.List;

/**
 *
 * @author
 */
public class ShoppingService {

    /**
     * Repositorio de shopping
     */
    IShoppingAccess repo;

    /**
     * Constructor parametrizado. Hace inyeccion de dependencias
     *
     * @param repo repositorio de tipo IShoppingRepository
     */
    public ShoppingService(IShoppingAccess repo) {
        this.repo = repo;
    }

    public boolean save(Shopping shopping) throws Exception {
        return repo.save(shopping);
    }

    public boolean edit(Long id, Shopping shopping) {
        return repo.edit(id, shopping);
    }

    public boolean delete(Long id) {
        return repo.delete(id);
    }

    public Shopping findByProductId(Long productId) throws Exception{
        return repo.findByproductId(productId) ;
    }

    public List<Shopping> findAll() {
        return repo.findAll();
    }

    public List<Shopping> findByName(String name) {
        return repo.findByName(name);
    }
}
