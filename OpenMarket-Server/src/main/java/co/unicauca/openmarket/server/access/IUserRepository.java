package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.client.domain.User;
import java.util.List;

/**
 *
 * @author 
 */
public interface IUserRepository {
    boolean save(User user);
    boolean edit(User user);
    boolean delete(Long id);
    User findById(Long id);
    List<User> findAll();
    User findByUsernameAndPassword(String username, String password);
}
