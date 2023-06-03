package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.client.domain.User;
import java.util.List;

/**
 *
 * @author 
 */
public interface IUserAccess {
    boolean save(User user);
    boolean edit(User user);
    boolean delete(Long id);
    User findById(Long id) throws Exception;
    List<User> findAll();
    User findByUsernameAndPassword(String username, String password) throws Exception;
}
