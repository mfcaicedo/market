package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.client.domain.Product;
import co.unicauca.openmarket.client.domain.User;
import co.unicauca.openmarket.client.domain.UserRole;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class UserRepository implements IUserRepository{
    
     private Connection conn;

    public UserRepository() {
        initDatabase();
        insertInit();
    }
    private void initDatabase() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	userId integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	name text NOT NULL,\n"
                + "	lastname text NULL,\n"
                + "     username text NOT NULL,\n"
                + "     password text NOT NULL,\n"
                + "     email text NULL,\n"
                + "     cellphone integer NULL,\n"
                + "     role text NOT NULL,\n"
                + "     billingType text NULL,\n"
                + "     birthdate text NULL,\n"
                + "     score real NULL,\n"
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
    
     private void insertInit() {
        try {
            String sql = "INSERT INTO users (name, lastname, username, password, email, cellphone, role, billingType, birthdate, score) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "Nicolas");
            pstmt.setString(2, "Lopez");
            pstmt.setString(3, "nlopez");
            pstmt.setString(4, "1234");
            pstmt.setString(5, "nlopez@gmail.com");
            pstmt.setInt(6, 32345);
            pstmt.setString(7, UserRole.VENDEDOR.toString());
            pstmt.setString(8, "Factura electrónica");
            pstmt.setString(9, "2002-03-20");
            pstmt.setFloat(10, 0.0f);
            pstmt.executeUpdate();
            
            sql = "INSERT INTO users (name, lastname, username, password, email, cellphone, role, birthdate) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "Theo");
            pstmt.setString(2, "Muñoz");
            pstmt.setString(3, "tmunoz");
            pstmt.setString(4, "1234");
            pstmt.setString(5, "tmunoz@gmail.com");
            pstmt.setInt(6, 32345);
            pstmt.setString(7, UserRole.COMPRADOR.toString());
            pstmt.setString(9, "2002-01-20");
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean save(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean edit(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User findById(Long id) {
        try {
            String sql = "SELECT * FROM user "
                    + "WHERE userId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                User user = new User();
                
                user.setUserId(res.getLong("userId"));
                user.setName(res.getString("name"));
                user.setLastName(res.getString("lastname"));
                user.setUsername(res.getString("username"));
                user.setPassword(res.getString("password"));
                user.setEmail(res.getString("email"));
                user.setCellphone(res.getInt("cellphone"));
                if(res.getString("role").equals("COMPRADOR")){
                    user.setRole(UserRole.COMPRADOR);
                }else{
                    user.setRole(UserRole.VENDEDOR);
                    user.setBillingType(res.getString("billingType"));
                    user.setScore(res.getFloat("score"));
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(res.getString("birthdate"));
                user.setBirthdate(date);
                return user;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
             Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
         }
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
         try {
            String sql = "SELECT * FROM user "
                    + "WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                User user = new User();
                user.setUserId(res.getLong("userId"));
                user.setName(res.getString("name"));
                user.setLastName(res.getString("lastname"));
                user.setUsername(res.getString("username"));
                user.setPassword(res.getString("password"));
                user.setEmail(res.getString("email"));
                user.setCellphone(res.getInt("cellphone"));
                if(res.getString("role").equals("COMPRADOR")){
                    user.setRole(UserRole.COMPRADOR);
                }else{
                    user.setRole(UserRole.VENDEDOR);
                    user.setBillingType(res.getString("billingType"));
                    user.setScore(res.getFloat("score"));
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(res.getString("birthdate"));
                user.setBirthdate(date);
                return user;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
             Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
         }
        return null;
    }
    
}
