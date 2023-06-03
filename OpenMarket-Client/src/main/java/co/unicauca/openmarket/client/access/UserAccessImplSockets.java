package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.client.domain.Product;
import co.unicauca.openmarket.client.domain.User;
import co.unicauca.openmarket.client.infra.OpenMarketSocket;
import co.unicauca.openmarket.commons.infra.JsonError;
import co.unicauca.openmarket.commons.infra.Protocol;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Personal
 */
public class UserAccessImplSockets implements IUserAccess{
    
    private OpenMarketSocket mySocket;
    
    public UserAccessImplSockets() {
        mySocket = new OpenMarketSocket();
    }

    @Override
    public boolean save(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean edit(User user) {
        boolean bandera = false;
        String jsonResponse = null;
        String requestJson = doEdituserRequestJson(user);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
         if (jsonResponse == null) {
            try {
                throw new Exception("Oye No se pudo conectar con el servidor, por favor revisa la red o que el servidor esté escuchando.  ");
            } catch (Exception ex) {
                Logger.getLogger(UserAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (jsonResponse.contains(" Hay error")) {
                //Devolvió algún error
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse+"aqi estoy");
                try {
                    throw new Exception(extractMessages(jsonResponse));
                } catch (Exception ex) {
                    Logger.getLogger(UserAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            } else {
                //Encontró el customer
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ("+user.getName());
                bandera=true;
            }
        }
        return bandera;
    }

    @Override
    public boolean delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User findById(Long id) throws Exception {
        String jsonResponse = null;
        String requestJson = doFindUserIdRequestJson(id);
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
           
            
                throw new Exception("Oye No se pudo conectar con el servidor, por favor revisa la red o que el servidor esté escuchando.  ");
            

        } else {
            if (jsonResponse.contains(" Hay error")) {
               
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                
                    throw new Exception(extractMessages(jsonResponse));
                
               

            } else {
                //Encontró el category
                User user = jsonToUser(jsonResponse);
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ("+jsonResponse.toString()+ ")");
                return user;
            }
        } 
    }
    
    private User jsonToUser(String jsonUser) {

        Gson gson = new Gson();
        User user = gson.fromJson(jsonUser, User.class);
        return user;

    }
    
    private String doFindUserIdRequestJson(Long id) {
       Protocol protocol = new Protocol();
        protocol.setResource("user");
        protocol.setAction("findById");
        protocol.addParameter("UserId",id.toString());

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws Exception{
        String jsonResponse = null;
        String requestJson = doFindUserUsernameAndPasswordRequestJson(username,password);
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
           
            throw new Exception("Oye No se pudo conectar con el servidor, por favor revisa la red o que el servidor esté escuchando.  ");
//retura un valor null;
        } else {
            if (jsonResponse.contains(" Hay error")) {
               
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
               throw new Exception(extractMessages(jsonResponse));
               
//retura un valor null;
            } else {
                //Encontró el category
                User user = jsonToUser(jsonResponse);
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ("+jsonResponse.toString()+ ")");
                return user;
            }
        }
        
    
    }
    
    
    private String doFindUserUsernameAndPasswordRequestJson(String username, String password) {
        Protocol protocol = new Protocol();
        protocol.setResource("user");
        protocol.setAction("login");
        protocol.addParameter("username",username);
        protocol.addParameter("password",password);

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }
    private String doEdituserRequestJson(User user){
        Protocol protocol = new Protocol();
        protocol.setResource("user");
        protocol.setAction("editScore");
        protocol.addParameter("userId", user.getUserId().toString());
        protocol.addParameter("score", String.valueOf(user.getScore()));

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }
    
//    private User jsonToUser(String jsonUser) {
//
//        Gson gson = new Gson();
//        User user = gson.fromJson(jsonUser, User.class);
//        return user;
//
//    }
    
    private String extractMessages(String jsonResponse) {
        JsonError[] errors = jsonToErrors(jsonResponse);
        String msjs = "";
        for (JsonError error : errors) {
            msjs += error.getMessage();
        }
        return msjs;
    }
    
    private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }
    
}
