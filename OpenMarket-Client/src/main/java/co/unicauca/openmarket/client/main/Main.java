package co.unicauca.openmarket.client.main; 

import co.unicauca.openmarket.client.access.Factory;
import co.unicauca.openmarket.client.access.ICategoryAccess;
import co.unicauca.openmarket.client.access.IProductAccess;
import co.unicauca.openmarket.client.domain.services.CategoryService;
import co.unicauca.openmarket.client.domain.services.ProductService;
import co.unicauca.openmarket.client.presentation.GUICategory;
import co.unicauca.openmarket.client.presentation.GUIProducts;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       IProductAccess repository = Factory.getInstance().getRepository("default");
        ICategoryAccess repository2 =  Factory.getInstance().getCatRepository("default");
        ProductService productService = new ProductService(repository);
        CategoryService categoryService=new CategoryService(repository2);
        
        

        GUICategory instance1=new GUICategory(categoryService);
        instance1.setVisible(true);
        instance1.setSize(596, 380);
        instance1.setLocation(0,0);
        GUIProducts instance2 = new GUIProducts(productService);
        instance2.setVisible(true);
        instance2.setSize(596, 380);
        instance2.setLocation(590, 0);
    }
    
}