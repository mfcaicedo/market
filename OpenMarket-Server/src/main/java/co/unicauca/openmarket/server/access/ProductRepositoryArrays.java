/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.client.domain.Product;
import java.util.ArrayList;
import java.util.List;


public class ProductRepositoryArrays implements IProductRepository{
   
     private static List<Product> productos;

    public ProductRepositoryArrays() {
        if (productos== null){
            productos = new ArrayList();
        }
        if (productos.size() == 0){
           inicializar();
        }
    }
        
    public void inicializar(){
//        productos.add(new Product(1L,"Aceite","Producto fritador",10L));
//        productos.add(new Product(2L,"Salchichas","Marca perrito",10L));
//        productos.add(new Product(3L,"Spagueetii","Marca la muñeca",11L));
//        productos.add(new Product(4L,"Bianchi","Caramelo",12L));
    }  
    @Override
    public boolean save(Product newProduct) {
       productos.add(newProduct);
       return true;
    }

    @Override
    public boolean edit(Product newProduct) {
       for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getProductId().equals(newProduct.getProductId())) {
                productos.set(i, newProduct);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product findById(Long id) {
        for (Product OProduct : productos) {
            if (OProduct.getProductId().equals(id)) {
                return OProduct;
            }
        }
        return null;
    }

    @Override
    public List<Product> findByName(String pname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> findByCategory(String categoryName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> findAllByNameAndDescription(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
