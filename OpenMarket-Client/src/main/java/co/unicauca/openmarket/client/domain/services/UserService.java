package co.unicauca.openmarket.client.domain.services;

import co.unicauca.openmarket.client.access.IUserAccess;
import co.unicauca.openmarket.client.domain.User;
import java.util.List;

/**
 *
 * @author 
 */
public class UserService {
    /**
     * Repositorio de User
     */
     IUserAccess repo;
     /**
     * Constructor parametrizado. Hace inyeccion de dependencias
     *
     * @param repo repositorio de tipo ICategoryRepository
     */
    public UserService(IUserAccess repo) {
        this.repo = repo;
    }
    public boolean save(User user){
        return repo.save(user);
    }
    public boolean edit(User user){
        return repo.edit(user);
    }
    public boolean delete(Long id){
        return repo.delete(id);
    }
    public User findById(Long id) throws Exception{
        return repo.findById(id);
    }
    public List<User> findAll(){
        return repo.findAll();
    }
    public User findByUsernameAndPassword(String username, String password) throws Exception{
        return repo.findByUsernameAndPassword(username, password);
    }
}
