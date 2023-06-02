package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.client.domain.Location;
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
public class LocationRepository implements ILocationRepository{
        private Connection conn;
    public LocationRepository() {
        initDatabase();
        insertInit();
    }

    private void insertInit() {
        try {
            String sql = "INSERT INTO location (locationId, latitude, longitude, place) "
                    + "VALUES ( ?, ?, ?, ? )";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 1);
            pstmt.setFloat(2, 12.4581f);
            pstmt.setFloat(3, -66.8698f);
            pstmt.setString(4, "Colombia");
            pstmt.executeUpdate();
            
            sql = "INSERT INTO location (locationId, latitude, longitude, place) "
                    + "VALUES ( ?, ?, ?, ? )";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 2);
            pstmt.setFloat(2, -21.7817f);
            pstmt.setFloat(3, -53.5912f);
            pstmt.setString(4, "Argentina");
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Location> findAll() {
        List<Location> locations = new ArrayList<>();
        try {
            String sql = "SELECT * FROM location";
            //this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Location newLocation = new Location();
                newLocation.setLocationId(rs.getInt("locationId"));
                newLocation.setLatitude(rs.getFloat("latitude"));
                newLocation.setLongitude(rs.getFloat("longitude"));
                newLocation.setPlace(rs.getString("place"));
                locations.add(newLocation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return locations;
    }

    public void initDatabase() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS location (\n"
                + "	locationId integer PRIMARY KEY,\n"
                + "	latitude real NOT NULL,\n"
                + "	longitude real NOT NULL,\n"
                + "     place text NOT NULL\n"
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
    public void cleanDatabase() {
        try {
            String sql = "DELETE FROM location";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

