package co.unicauca.openmarket.commons.domain;

/**
 *
 * @author 
 */
public class SellerIncome {
    private Long sellerIncomeId;
    private Double income;
    private Long shoppingId;

    public SellerIncome() {
    }

    public SellerIncome(Long sellerIncomeId,Double income, Long shoppingId) {
        this.sellerIncomeId = sellerIncomeId;
        this.income = income;
        this.shoppingId = shoppingId;
    }

    public Long getSellerIncomeId() {
        return sellerIncomeId;
    }

    public void setSellerIncomeId(Long sellerIncomeId) {
        this.sellerIncomeId = sellerIncomeId;
    }

    public Long getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(Long shoppingId) {
        this.shoppingId = shoppingId;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }
    
}