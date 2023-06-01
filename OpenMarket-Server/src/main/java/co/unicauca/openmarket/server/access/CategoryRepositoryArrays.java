package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.client.domain.Category;
import java.util.ArrayList;
import java.util.List;



public class CategoryRepositoryArrays implements ICategoryRepository{
        private static List<Category> category;
    public CategoryRepositoryArrays() {
        if (category == null){
            category = new ArrayList();
        }
        
        if (category.size() == 0){
           inicializar();
        }
    }
   
    public void inicializar() {
        category.add(new Category(10L, "lacteos"));
        category.add(new Category(11L, "granos"));
        category.add(new Category(12L, "mecato"));
        category.add(new Category(13L, "peluchitos"));
        

    }
    
    @Override
    public boolean save(Category newCategory) {
        category.add(newCategory);
        return true;
    }

    @Override
    public boolean edit(Long id, Category prmCategory) {
       for (int i = 0; i < category.size(); i++) {
            if (category.get(i).getCategoryId().equals(id)) {
                category.set(i, prmCategory);
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean delete(Long id) {
       for (int i = 0; i < category.size(); i++) {
            if (category.get(i).getCategoryId().equals(id)) {
                category.remove(i);
                return true;
            }
        }
        return false;
    }


    @Override
    public Category findById(Long id) {
       for (Category OCategory : category) {
            if (OCategory.getCategoryId().equals(id)) {
                return OCategory;
            }
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        if (category.isEmpty())
            return null;      
        return category;
    }

    @Override
    public List<Category> findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
