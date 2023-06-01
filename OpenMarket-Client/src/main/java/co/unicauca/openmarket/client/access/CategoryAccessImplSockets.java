package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.client.domain.Category;
import co.unicauca.openmarket.client.infra.OpenMarketSocket;
import co.unicauca.openmarket.commons.infra.JsonError;
import co.unicauca.openmarket.commons.infra.Protocol;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import java.io.IOException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivonne, Camilo
 */
public class CategoryAccessImplSockets implements ICategoryAccess {

     private OpenMarketSocket mySocket;
     public CategoryAccessImplSockets() {
        mySocket = new OpenMarketSocket();
    }
                                        //private Connection conn;

          
    @Override
    
    public boolean save(Category newCategory) throws Exception  {
        boolean bandera=false;
        String jsonResponse = null;
        String requestJson = doSaveCategoryRequestJson(newCategory);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

            
        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
         if (jsonResponse == null) {
            throw new Exception("Oye No se pudo conectar con el servidor, por favor revisa la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse+"Aqui estoy");
                throw new Exception(extractMessages(jsonResponse));
               
            } else {
                                             //Encontró el customer
                
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ("+newCategory.getName());
                bandera=true;
            }
        }
      
       return bandera;
    }

    @Override
    
    public boolean edit(Long id, Category newCategory) {
         boolean bandera=false;
        String jsonResponse = null;
        String requestJson = doEditCategoryRequestJson(id,newCategory);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
         if (jsonResponse == null) {
             try {
                 throw new Exception("Oye No se pudo conectar con el servidor, por favor revisa la red o que el servidor esté escuchando. ");
             } catch (Exception ex) {
                 Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
             }
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse+"aqi estoy");
                try {
                    throw new Exception(extractMessages(jsonResponse));
                } catch (Exception ex) {
                    Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            } else {
                //Encontró la categoria
                
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ("+newCategory.getName());
                bandera=true;
            }
        }
      
       return bandera;
    }

    @Override
    public boolean delete(Long id) {
        boolean bandera=false;
        String jsonResponse = null;
        String requestJson = doDeleteCategoryRequestJson(id);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
         if (jsonResponse == null) {
             try {
                 throw new Exception("Oye No se pudo conectar con el servidor, por favor revisa la red o que el servidor esté escuchando. ");
             } catch (Exception ex) {
                 Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
             }
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse+"aqi estoy");
                try {
                    throw new Exception(extractMessages(jsonResponse));
                } catch (Exception ex) {
                    Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            } else {
                             //Encuentra la categoria
                
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: {0}", requestJson);
                bandera=true;
            }
        }
      
       return bandera;
    }

    @Override
    public Category findById(Long id) {
        String jsonResponse = null;
        String requestJson = doFindCategoyIdRequestJson(id);
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            return null;


        } else {
            if (jsonResponse.contains("error")) {
                
//Devolvió algún error
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
               return null;
            } else {
                //Encuentra el category
                Category category = jsonToCategory(jsonResponse);
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ("+jsonResponse.toString()+ ")");
                return category;
            }
        }     
    }

    @Override
    public List<Category> findAll() {
        String jsonResponse = null;
        String requestJson = doListCategoryRequestJson();
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            return null;
           // throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
               return null;
            } else {
                //Encuentró el category           
                List<Category> listCategory = null;
                try {
                    listCategory = jsonToListCategory(jsonResponse);
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ({0})", jsonResponse);
                return listCategory;
            }
        }     
    }

    @Override
    public List<Category> findByName(String name){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    private String extractMessages(String jsonResponse) {
        JsonError[] errors = jsonToErrors(jsonResponse);
        String msjs = "";
        for (JsonError error : errors) {
            msjs += error.getMessage();
        }
        return msjs;
    }

    /**
     * 
     * @param jsonError
     * @return objeto MyError
     */
    private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }
    /**
     * 
     *
     *
     * @param categoryId identificación del cliente
     * @return solicitud de consulta del cliente en formato Json, algo como:
     * {"resource":"category","action":"get","parameters":[{"name":"id","value":"1"}]}
     */
    private String doFindCategoyIdRequestJson(Long categoryId) {

        Protocol protocol = new Protocol();
        protocol.setResource("category");
        protocol.setAction("get");
        protocol.addParameter("categoryId", categoryId.toString());

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }
    /**
     * Crea la solicitud json de creación de la Category para ser enviado por el
     * socket
     *
     * @param customer objeto customer
     * @return devulve algo como:
     * {"resource":"category","action":"post","parameters":[{"name":"id","value":"1"},{"name":"name","value":"lacteos"},...}]}
     */
    private String doSaveCategoryRequestJson(Category category) {

        Protocol protocol = new Protocol();
        protocol.setResource("category");
        protocol.setAction("post");
        protocol.addParameter("id", category.getCategoryId().toString());
        protocol.addParameter("name", category.getName());
       
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    
     private String doEditCategoryRequestJson(Long id,Category newCategory) {

        Protocol protocol = new Protocol();
        protocol.setResource("category");
        protocol.setAction("edit");
        protocol.addParameter("id", id.toString());
        protocol.addParameter("name", newCategory.getName());
       
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
     
    private String doDeleteCategoryRequestJson(Long id){
        Protocol protocol = new Protocol();
        protocol.setResource("category");
        protocol.setAction("delete");
        protocol.addParameter("id", id.toString());
       
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    
     private String doListCategoryRequestJson(){
        Protocol protocol = new Protocol();
        protocol.setResource("category");
        protocol.setAction("listCategory");
       
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    
    /**
    * Convierte jsonCategory, proveniente del server socket, de json a un
    * objeto Category
    *
    * @param jsonCustomer objeto cliente en formato json
    */
    private Category jsonToCategory(String jsonCustomer) {

        Gson gson = new Gson();
        Category category = gson.fromJson(jsonCustomer, Category.class);
        return category;
    }
    private List<Category>  jsonToListCategory(String jsonCustomer) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Category> listCategory = objectMapper.readValue(jsonCustomer, new TypeReference<List<Category>>(){});

        return listCategory;
    }

}
