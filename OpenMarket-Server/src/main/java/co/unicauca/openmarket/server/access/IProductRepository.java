package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.client.domain.Category;
import co.unicauca.openmarket.client.domain.Product;
import java.util.List;

/**
 *
 * @author Libardo, Julio
 */
public interface IProductRepository {

    boolean save(Product newProduct);

    boolean edit(Product newProduct);

    boolean delete(Long id);

    Product findById(Long id);

    List<Product> findAllByNameAndDescription(String search);

    List<Product> findByName(String pname);

    List<Product> findByCategory(String categoryName);

    List<Product> findAll();

}
