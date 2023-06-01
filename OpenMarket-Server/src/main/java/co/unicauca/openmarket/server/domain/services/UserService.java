package co.unicauca.openmarket.server.domain.services;

import co.unicauca.openmarket.client.domain.User;
import co.unicauca.openmarket.server.access.IUserRepository;
import java.util.List;

/**
 *
 * @author 
 */
public class UserService {
    /**
     * Repositorio de User
     */
     IUserRepository repo;
     /**
     * Constructor parametrizado. Hace inyeccion de dependencias
     *
     * @param repo repositorio de tipo ICategoryRepository
     */
    public UserService(IUserRepository repo) {
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
    public User findById(Long id){
        return repo.findById(id);
    }
    public List<User> findAll(){
        return repo.findAll();
    }
    public User findByUsernameAndPassword(String username, String password){
        return repo.findByUsernameAndPassword(username, password);
    }
}
