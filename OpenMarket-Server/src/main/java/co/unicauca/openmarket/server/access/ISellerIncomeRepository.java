package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.client.domain.SellerIncome;
import java.util.List;

/**
 *
 * @author 
 */
public interface ISellerIncomeRepository {
 
    boolean save(SellerIncome sellerIncome);
    List<SellerIncome> findAll();
}
