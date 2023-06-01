package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.client.domain.Product;
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
 * Es una implementación que tiene libertad de hacer una implementación del
 * contrato. Lo puede hacer con Sqlite, postgres, mysql, u otra tecnología
 *
 * @author Libardo, Julio
 */
public class ProductRepository implements IProductRepository {

    private Connection conn;

    public ProductRepository() {
        initDatabase();
    }

    @Override
    public boolean save(Product newProduct) {
        try {
            //Validate product
            if (newProduct == null || newProduct.getName().isBlank()) {
                return false;
            }
            //this.connect();
            String sql = "INSERT INTO products (name, description, price, state, stock, categoryId, locationId, userSellerId) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newProduct.getName());
            pstmt.setString(2, newProduct.getDescription());
            pstmt.setDouble(3, newProduct.getPrice());
            pstmt.setString(4, newProduct.getState());
            pstmt.setInt(5, newProduct.getStock());
            pstmt.setLong(6, newProduct.getCategoryId());
            pstmt.setLong(7, newProduct.getLocation());
            pstmt.setLong(8, newProduct.getUserSellerId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM products";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(rs.getLong("productId"));
                newProduct.setName(rs.getString("name"));
                newProduct.setDescription(rs.getString("description"));
                newProduct.setPrice(rs.getDouble("price"));
                newProduct.setState(rs.getString("state"));
                newProduct.setStock(rs.getInt("stock"));
                newProduct.setCategoryId(rs.getLong("categoryId"));
                newProduct.setLocation(rs.getLong("locationId"));
                newProduct.setUserSellerId(rs.getLong("userSellerId"));
                products.add(newProduct);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public void initDatabase() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS products (\n"
                + "	productId integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	name text NOT NULL,\n"
                + "	description text NULL,\n"
                + "     price real NOT NULL,\n"
                + "     state text NULL,\n"
                + "     stock integer NOT NULL,\n"
                + "     categoryId integer,\n"
                + "     locationId integer NULL,\n"
                + "     userSellerId integer NULL,\n"
                + "     FOREIGN KEY (categoryId) REFERENCES categories(categoryId)\n"
                + "     FOREIGN KEY (locationId) REFERENCES location(locationId)\n"
                + "     FOREIGN KEY (userSellerId) REFERENCES users(userId)\n"
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
        String url = "jdbc:sqlite::memory:market";

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
    public boolean edit(Product product) {
        try {
            //Validate product
            if (product == null) {
                return false;
            }
            //this.connect();

            String sql = "UPDATE  products"
                    + "SET name=?, description=?,price=?, state=?, stock=?, categoryId=?,locationId=?, userSellerId=?"
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setString(4, product.getState());
            pstmt.setLong(5, product.getStock());
            pstmt.setLong(6, product.getCategoryId());
            pstmt.setLong(7, product.getLocation());
            pstmt.setLong(8, product.getUserSellerId());
            pstmt.setLong(9, product.getProductId());
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try {
            //Validate product
            if (id <= 0) {
                return false;
            }
            //this.connect();

            String sql = "DELETE FROM products "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Product findById(Long id) {
        try {
            String sql = "SELECT * FROM products  "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Product prod = new Product();
                prod.setProductId(res.getLong("productId"));
                prod.setName(res.getString("name"));
                prod.setDescription(res.getString("description"));
                prod.setPrice(res.getDouble("price"));
                prod.setStock(res.getInt("stock"));
                prod.setCategoryId(res.getLong("categoryId"));
                prod.setLocation(res.getLong("locationId"));
                prod.setUserSellerId(res.getLong("userSellerId"));
                return prod;
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
    public List<Product> findAllByNameAndDescription(String search) {
          List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM products  "
                    + "WHERE name LIKE '%?%' OR description LIKE %?% ";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, search);
            pstmt.setString(2, search);

            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                Product prod = new Product();
                prod.setProductId(res.getLong("productId"));
                prod.setName(res.getString("name"));
                prod.setDescription(res.getString("description"));
                prod.setPrice(res.getDouble("price"));
                prod.setStock(res.getInt("stock"));
                prod.setCategoryId(res.getLong("categoryId"));
                prod.setLocation(res.getLong("locationId"));
                prod.setUserSellerId(res.getLong("userSellerId"));
                products.add(prod);
            }
            return products;

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Product> findByName(String pname) {
        List<Product> products = new ArrayList<>();
        try {

            String sql = "SELECT * FROM products"
                    + " WHERE name = ?";
            //this.connect();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, pname);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(rs.getLong("productId"));
                newProduct.setName(rs.getString("name"));
                newProduct.setDescription(rs.getString("description"));

                products.add(newProduct);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }

    public void cleanDatabase() {
        try {
            String sql = "DELETE FROM products";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public List<Product> findByCategory(String categoryName) {
        List<Product> products = new ArrayList<>();
        try {
            // Get the categoryId for the given categoryName
            String categorySql = "SELECT categoryId FROM categories WHERE name = ?";
            PreparedStatement categoryStmt = conn.prepareStatement(categorySql);
            categoryStmt.setString(1, categoryName);
            ResultSet categoryRs = categoryStmt.executeQuery();

            if (categoryRs.next()) {
                long categoryId = categoryRs.getLong("categoryId");

                // Find products with the given categoryId
                String productSql = "SELECT * FROM products WHERE categoryId = ?";
                PreparedStatement productStmt = conn.prepareStatement(productSql);
                productStmt.setLong(1, categoryId);
                ResultSet productRs = productStmt.executeQuery();

                while (productRs.next()) {
                    Product newProduct = new Product();
                    newProduct.setProductId(productRs.getLong("productId"));
                    newProduct.setName(productRs.getString("name"));
                    newProduct.setDescription(productRs.getString("description"));
                    newProduct.setPrice(productRs.getDouble("price"));
                    products.add(newProduct);
                }
            } else {
                // No category found with the given categoryName
                return products;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }

}
