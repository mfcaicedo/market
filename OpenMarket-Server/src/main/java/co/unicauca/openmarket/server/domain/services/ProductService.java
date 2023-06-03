/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.server.domain.services;

import co.unicauca.openmarket.server.access.IProductRepository;
import co.unicauca.openmarket.client.domain.Product;
import java.util.List;


public class ProductService {
   /**
     * Repositorio de Productos
     */
     IProductRepository repo;

     /**
     * Constructor parametrizado. Hace inyeccion de dependencias
     *
     * @param repo repositorio de tipo IProductRepository
     */ 
    public ProductService(IProductRepository repo) {
        this.repo = repo;
    }
     
      public synchronized boolean save(Product newProduct) {
        return repo.save(newProduct);
    }
    
    public synchronized boolean edit(Product product){
        return repo.edit(product);
    }
    
    public synchronized boolean delete(Long id){
        return repo.delete(id);
    }
    public synchronized Product findById(Long id){
        return repo.findById(id);
    };
    public synchronized List<Product> findAll(){
        return repo.findAll();
    };
    public synchronized List<Product> findByName(String name){
        return repo.findByName(name);
    };
    public synchronized List<Product> findAllByNameAndDescription(String search){
        return repo.findAllByNameAndDescription(search);
    }
    public synchronized List<Product> findByCategory(String categoryName){
        return repo.findByCategory(categoryName);
    }
    
    public synchronized List<Product> findByUserSeller(Long id){
        return repo.findByUserSeller(id);
    }
}
