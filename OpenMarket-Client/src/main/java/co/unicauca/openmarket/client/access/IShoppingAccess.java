package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.client.domain.Shopping;
import java.util.List;

/**
 *
 * @author 
 */
public interface IShoppingAccess {
    boolean save(Shopping shopping) throws Exception;
   
    boolean edit(Long id, Shopping shopping);
   
    boolean delete(Long id);
   
    Shopping findById(Long id);
   
    List<Shopping> findAll();
   
    List<Shopping> findByName(String name);
}
