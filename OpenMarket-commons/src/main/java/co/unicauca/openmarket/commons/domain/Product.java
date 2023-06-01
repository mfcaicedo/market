
package co.unicauca.openmarket.commons.domain;

/**
 * EVIDENCIAMOS EL PRODUCTO 
 */
public class Product {
  
    private Long productId;
    private String name;
    private String description;
    private Double price;
    private String state;
    private Integer stock;
    private Long categoryId;
    private Long location;
    private Long userSellerId;

    public Product() {
    }

    public Product(Long productId, String name, String description, Double price, String state, Integer stock, Long categoryId, Long location, Long userSellerId) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.state = state;
        this.stock = stock;
        this.categoryId = categoryId;
        this.location = location;
        this.userSellerId = userSellerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getLocation() {
        return location;
    }

    public void setLocation(Long location) {
        this.location = location;
    }

    public Long getUserSellerId() {
        return userSellerId;
    }

    public void setUserSellerId(Long userSellerId) {
        this.userSellerId = userSellerId;
    }

}
