package co.unicauca.openmarket.client.domain.services;

import co.unicauca.openmarket.client.access.ISellerIncomeAccess;
import co.unicauca.openmarket.client.domain.SellerIncome;
import java.util.List;

/**
 *
 * @author 
 */
public class SellerIncomeService {
      /**
     * Repositorio de Category
     */
     ISellerIncomeAccess repo;
     /**
     * Constructor parametrizado. Hace inyeccion de dependencias
     *
     * @param repo repositorio de tipo ICategoryRepository
     */
     public SellerIncomeService(ISellerIncomeAccess repo) {
        this.repo = repo;
    }
    public boolean save(SellerIncome sellerIncome) throws Exception{
       return repo.save(sellerIncome);
    }
    public List<SellerIncome> findAll(){
        return repo.findAll();
    }
}
