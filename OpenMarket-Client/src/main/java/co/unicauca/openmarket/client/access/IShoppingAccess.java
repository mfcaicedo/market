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
   
    public Shopping findByproductId(Long productid) throws Exception;
   
    List<Shopping> findAll();
   
    List<Shopping> findByName(String name);
}
