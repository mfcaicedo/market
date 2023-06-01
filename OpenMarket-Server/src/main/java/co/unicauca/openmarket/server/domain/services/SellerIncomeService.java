package co.unicauca.openmarket.server.domain.services;

import co.unicauca.openmarket.client.domain.SellerIncome;
import java.util.List;
import co.unicauca.openmarket.server.access.ISellerIncomeRepository;

/**
 *
 * @author 
 */
public class SellerIncomeService {
      /**
     * Repositorio de Category
     */
     ISellerIncomeRepository repo;
     /**
     * Constructor parametrizado. Hace inyeccion de dependencias
     *
     * @param repo repositorio de tipo ICategoryRepository
     */
     public SellerIncomeService(ISellerIncomeRepository repo) {
        this.repo = repo;
    }
    public boolean save(SellerIncome sellerIncome){
       return repo.save(sellerIncome);
    }
    public List<SellerIncome> findAll(){
        return repo.findAll();
    }
}
