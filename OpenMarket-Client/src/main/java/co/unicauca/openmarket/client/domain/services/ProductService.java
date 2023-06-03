package co.unicauca.openmarket.client.domain.services;



import java.util.ArrayList;
import java.util.List;
import co.unicauca.openmarket.client.access.IProductAccess;
import co.unicauca.openmarket.client.domain.Product;

/**
 *
 * @author Libardo, Julio
 */
public class ProductService  {
      
    // Ahora hay una dependencia de una abstracción, no es algo concreto,
    // no sabe cómo está implementado.
   public ProductService(){
    
   }
   
   
    private IProductAccess repository;

    /**
     * Inyección de dependencias en el constructor. Ya no conviene que el mismo
     * servicio cree un repositorio concreto
     *
     * @param repository una clase hija de IProductAccess
     */
    public ProductService(IProductAccess repository) {
        this.repository = repository;
    }
    

    public boolean saveProduct(Product product)throws Exception {
     
        return repository.save(product);

    }

    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        products = repository.findAll();

        return products;
    }
    
    public Product findProductById(Long id)throws Exception{
        return repository.findById(id);
    }
    public List<Product> findProductsByName(String name) {
        List<Product> products = new ArrayList<>();
        products = repository.findByName(name);

        return products;
    }
    
    public List<Product> findAllByNameAndDescription(String search){
        List<Product> products = new ArrayList<>();
        products = repository.findAllByNameAndDescription(search);
        return products;
    }
    
    public List<Product> findProductsByCategory(String categoryName) {
        List<Product> products = new ArrayList<>();
        products = repository.findByCategory(categoryName);

        return products;
    }
    public boolean deleteProduct(Long id){
        
        return repository.delete(id);
       
    }
    
    public List<Product> findByUserSeller(Long id){
        return repository.findByUserSeller(id);
    }

    public boolean editProduct(Product producto) throws Exception{
          
        Product productoActualizado = new Product();
        productoActualizado.setProductId(producto.getProductId());
        productoActualizado.setName(producto.getName());
        productoActualizado.setDescription(producto.getDescription());
        productoActualizado.setPrice(producto.getPrice());
        productoActualizado.setState(producto.getState());
        productoActualizado.setStock(producto.getStock());
        productoActualizado.setCategoryId(producto.getCategoryId());
        productoActualizado.setLocation(producto.getLocation());
        productoActualizado.setUserSellerId(producto.getUserSellerId());
        
        //Validate product
        if (producto.getProductId() == null ||producto.getName().isEmpty()) {
            return false;
        }
        return repository.edit(producto);

    }

}