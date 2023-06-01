package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.client.domain.Category;
import java.util.List;

public interface ICategoryRepository {
    
   boolean save(Category newCategory);
   
   boolean edit(Long id, Category category);
   
   boolean delete(Long id);
   
   Category findById(Long id);
   
   List<Category> findAll();
   
   List<Category> findByName(String name);
  

}
