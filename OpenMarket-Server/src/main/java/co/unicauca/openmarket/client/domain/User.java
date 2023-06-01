package co.unicauca.openmarket.client.domain;

import java.util.Date;

/**
 *
 * @author Libardo Pantoja, Julio A. Hurtado
 */
public class User {
    private Long userId;
    private String name;
    private String lastName;
    private String username; 
    private String password;
    private String email; 
    private Integer cellphone;
    private UserRole role; 
    private String billingType; 
    private Date birthdate;
    private float score; 

    public User() {
    }

    public User(Long userId, String name, String lastName, String username, String password, String email, Integer cellphone, UserRole role, String billingType, Date birthdate, float score) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.cellphone = cellphone;
        this.role = role;
        this.billingType = billingType;
        this.birthdate = birthdate;
        this.score = score;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCellphone() {
        return cellphone;
    }

    public void setCellphone(Integer cellphone) {
        this.cellphone = cellphone;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getBillingType() {
        return billingType;
    }

    public void setBillingType(String billingType) {
        this.billingType = billingType;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
    
}
