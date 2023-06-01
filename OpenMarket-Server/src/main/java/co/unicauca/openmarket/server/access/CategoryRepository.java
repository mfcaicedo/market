package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.client.domain.Category;
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
import javax.swing.JOptionPane;


public class CategoryRepository implements ICategoryRepository {

    private Connection conn;

    public CategoryRepository() {
        initDatabase();
        insertInit();
    }
    
     private void insertInit() {
        try {
            String sql = "INSERT INTO categories (name) "
                    + "VALUES ( ? )";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "Entretenimiento");
            pstmt.executeUpdate();
            
            sql = "INSERT INTO categories (name) "
                    + "VALUES ( ? )";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "Juguetería");
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean save(Category newCategory) {
        try {
            if (newCategory == null || newCategory.getCategoryId()==null) {
                return false;
            }
            String sql = "INSERT INTO categories ( name) "
                    + "VALUES ( ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, newCategory.getCategoryId());
            pstmt.setString(2, newCategory.getName());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void initDatabase() {
        // SQL statement for creating a new table

        String sql = "CREATE TABLE IF NOT EXISTS categories (\n"
                + "      categoryId integer PRIMARY KEY,\n"
                + "      name text NOT NULL\n"
                + ");";
        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            //this.disconnect();

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
    
 public boolean clearCategories() {
    try {
        String sql = "DELETE FROM categories";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();

     
        String resetSql = "UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = 'categories'";
        PreparedStatement pstmtReset = conn.prepareStatement(resetSql);
        pstmtReset.executeUpdate();

        return true;
    } catch (SQLException ex) {
        Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
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
    public boolean edit(Long id, Category category) {
        try {
            if (id <= 0 || category == null) {
                return false;
            }
            String sql = "UPDATE categories "
                    + "SET name=?"
                    + "WHERE categoryId=? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category.getName());
            pstmt.setLong(2, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }   
    return false;
}

    @Override
    public boolean delete(Long id) {
        try {
             if (id <= 0) {
                return false;
            }
             String sql = "DELETE FROM categories "
                    + "WHERE categoryId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
              Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public Category findById(Long id) {
        try {
             JOptionPane.showMessageDialog(null, "soy el ide: "+id);
            String sql = "SELECT * FROM categories "
                    + "WHERE categoryId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Category cat = new Category();
                cat.setCategoryId(res.getLong("categoryId"));
                cat.setName(res.getString("name"));
                  JOptionPane.showMessageDialog(null, "soy el ide: "+cat.getCategoryId());
                  JOptionPane.showMessageDialog(null, "soy el ide: "+cat.getName());
                return cat;
            } else {
                return null;
            }

        } catch (SQLException ex) {
         
             Logger.getLogger(CategoryRepository.class.getName()).log(Level.SEVERE, "Error al consultar Customer de la base de datos", ex);
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try {
            String sql = "SELECT * FROM categories";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Long id = rs.getLong("categoryId");
                String name = rs.getString("name");
                Category category = new Category(id, name);
                categories.add(category);
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, e);
        }
        return categories;
    }
  @Override
    public List<Category> findByName(String name) {
        List<Category> categories = new ArrayList<>();
        try {
            String sql = "SELECT * FROM categories WHERE name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("categoryId");
                String categoryName = rs.getString("name");
                Category category = new Category(id, categoryName);
                categories.add(category);
            }
        } catch (Exception e) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, e);
        }
        return categories;
    }

}
