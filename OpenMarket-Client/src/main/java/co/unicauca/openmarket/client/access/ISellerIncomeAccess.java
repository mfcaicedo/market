package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.client.domain.SellerIncome;
import java.util.List;

/**
 *
 * @author 
 */
public interface ISellerIncomeAccess {
 
    boolean save(SellerIncome sellerIncome) throws Exception;
    List<SellerIncome> findAll();
}
