package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.client.domain.Product;
import co.unicauca.openmarket.client.domain.SellerIncome;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class SellerIncomeRepository implements ISellerIncomeRepository{
    
     private Connection conn;

    public SellerIncomeRepository() {
        initDatabase();
    }
    
    public void initDatabase() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS sellerIncome (\n"
                + "	sellerIncomeId integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	income real NOT NULL,\n"
                + "	shoppingId integer NOT NULL,\n"
                + "     FOREIGN KEY (shoppingId) REFERENCES shopping(shoppingId)\n"
                + ");"; 

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            //sentencia para crear la tabla categoria

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void connect() {
        // SQLite connection string
        //String url = "jdbc:sqlite:./myDatabase.db"; //Para Linux/Mac
        //String url = "jdbc:sqlite:C:/sqlite/db/myDatabase.db"; //Para Windows
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
    public boolean save(SellerIncome sellerIncome) {
    
         try {
            //Validate sellerIncome
            if (sellerIncome == null || sellerIncome.getIncome() != 0) {
                return false;
            }
            //this.connect();
            String sql = "INSERT INTO sellerIncome (income, shoppingId) "
                    + "VALUES ( ?, ? )";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, sellerIncome.getIncome());
            pstmt.setLong(2, sellerIncome.getShoppingId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<SellerIncome> findAll() {
         List<SellerIncome> sellersIncome = new ArrayList<>();
        try {
            String sql = "SELECT * FROM sellerIncome";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                SellerIncome sellerIncomeAux = new SellerIncome();
                sellerIncomeAux.setSellerIncomeId(rs.getLong("sellerIncomeId"));
                sellerIncomeAux.setIncome(rs.getDouble("income"));
                sellerIncomeAux.setShoppingId(rs.getLong("shoppingId"));
                sellersIncome.add(sellerIncomeAux);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sellersIncome;
    }
}
