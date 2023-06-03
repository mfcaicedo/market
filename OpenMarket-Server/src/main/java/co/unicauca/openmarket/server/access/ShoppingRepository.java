package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.client.domain.Product;
import co.unicauca.openmarket.client.domain.Shopping;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public class ShoppingRepository implements IShoppingRepository {
    
    private Connection conn;
    
    public ShoppingRepository() {
        initDatabase();
    }

    private void initDatabase() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS shopping (\n"
                + "      shoppingId integer PRIMARY KEY AUTOINCREMENT,\n"
                + "      userBuyerId integer NOT NULL,\n"
                + "      productId integer NOT NULL,\n"
                + "      FOREIGN KEY (userBuyerId) REFERENCES users(userId),\n"
                + "      FOREIGN KEY (productId) REFERENCES products(pruductId)\n"
                + ");";
        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connect() {
        String url = "jdbc:sqlite::memory:";
        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public boolean save(Shopping shopping) {
         try {
            //Validate product
            if (shopping == null) {
                return false;
            }
            //this.connect();
            String sql = "INSERT INTO shopping (userBuyerId, productId) "
                    + "VALUES ( ?, ? )";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, shopping.getUserBuyerId());
            pstmt.setLong(2, shopping.getProductId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean edit(Long id, Shopping shopping) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Shopping findByproductId(Long productid) {
    System.out.println("CONSULTA PELUCHES: "+productid);

        try {
            String sql = "SELECT * FROM shopping  "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, productid);
            

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Shopping shopping = new Shopping();
                System.out.println("CONSULTA PELUCHES: "+res.getLong("shoppingId"));
                shopping.setShoppingId(res.getLong("shoppingId"));
                shopping.setProductId(Long.parseLong(res.getString("productId")));
                shopping.setUserBuyerId(Long.parseLong(res.getString("userBuyerId")));
                
                return shopping;
            } else {
                return null;
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Shopping> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Shopping> findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
