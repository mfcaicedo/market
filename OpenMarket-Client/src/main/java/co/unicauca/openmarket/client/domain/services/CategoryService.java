
package co.unicauca.openmarket.client.domain.services;

import co.unicauca.openmarket.client.access.ICategoryAccess;
import co.unicauca.openmarket.client.domain.Category;

import java.util.List;


public class CategoryService {
    
    
    public CategoryService(){
        
    }
    private ICategoryAccess repository;
   
  
    public CategoryService(ICategoryAccess repository){
        this.repository=repository;
    }
    public boolean saveCategory (Long id,String name)throws Exception{
        Category newCategory=new Category();
        newCategory.setCategoryId(id);
        newCategory.setName(name);
        if(newCategory.getName().isEmpty()){
            return false;
        }
        return repository.save(newCategory);
    }
    public boolean editCategory(Long categoryId,Category cat) {
        
        
        if(cat==null || cat.getName().isEmpty()){
            return false;
        }
      
       
        return repository.edit(categoryId,cat);
    }
    
   public boolean deleteCategory(Long id){
        return repository.delete(id);
    }  
    public Category findCategoryById(Long id)throws Exception{
        return repository.findById(id);
    }
       public List<Category> findAllCategories(){
        return repository.findAll();
    }
       
       public List<Category> findCategoriesByName(String name)throws Exception{
        return repository.findByName(name);
    }
}  
        
