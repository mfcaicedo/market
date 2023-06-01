package co.unicauca.openmarket.commons.domain;

/**
 *
 * @author 
 */
public class Shopping {
    private Long shoppingId;
    private Long userBuyerId;
    private Long productId;

    public Shopping() {
    }

    public Shopping(Long shoppingId, Long userBuyerId, Long productId) {
        this.shoppingId = shoppingId;
        this.userBuyerId = userBuyerId;
        this.productId = productId;
    }

    public Long getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(Long shoppingId) {
        this.shoppingId = shoppingId;
    }

    public Long getUserBuyerId() {
        return userBuyerId;
    }

    public void setUserBuyerId(Long userBuyerId) {
        this.userBuyerId = userBuyerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
    
    
}
