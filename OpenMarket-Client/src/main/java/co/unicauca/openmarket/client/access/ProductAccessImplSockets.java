package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.client.domain.Category;
import co.unicauca.openmarket.client.domain.Product;
import co.unicauca.openmarket.client.infra.OpenMarketSocket;
import co.unicauca.openmarket.commons.infra.JsonError;
import co.unicauca.openmarket.commons.infra.Protocol;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */

public class ProductAccessImplSockets implements IProductAccess {

    private OpenMarketSocket mySocket;

    public ProductAccessImplSockets() {
       mySocket = new OpenMarketSocket();
    }

    @Override
    public boolean save(Product newProduct)throws Exception {
        boolean bandera=false;
        String jsonResponse = null;
        String requestJson = doSaveProductRequestJson(newProduct);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
         if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {

            if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //return customer.getId();
                bandera=true;
            }

        }
         return bandera;
    }

    @Override
    public boolean edit(Product producto)throws Exception {
        boolean bandera=false;
        String jsonResponse = null;
        String requestJson = doEditproductRequestJson(producto);
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
                //Devolvió algún error
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse+"aqi estoy");
                throw new Exception(extractMessages(jsonResponse));
               
            } else {
                //Encontró el customer
                
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ("+producto.getName());
                bandera=true;
            }
        }
        return bandera;
    }

    @Override
    public boolean delete(Long id) {
        boolean bandera=false;
        String jsonResponse = null;
        String requestJson = doDeleteProductRequestJson(id);
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
                             //Encuentra el producto
                
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: {0}", requestJson);
                bandera=true;
            }
        }
      
       return bandera;
    }
    

    @Override
    public Product findById(Long id)throws Exception {
        String jsonResponse = null;
        String requestJson = doFindProductIdRequestJson(id);
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
                Product product = jsonToProduct(jsonResponse);
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ("+jsonResponse.toString()+ ")");
                return product;
            }
        } 
        
    }

    @Override
    public List<Product> findByName(String pname) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Product> findByCategory(String categoryName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> findAll() {
        String jsonResponse = null;
        String requestJson = doListProductRequestJson();
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
                List<Product> listProduct = null;
                try {
                    listProduct = jsonToListProduct(jsonResponse);
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ({0})", jsonResponse);
                return listProduct;
            }
        }
    }
    
    @Override
    public List<Product> findAllByNameAndDescription(String search) {
        String jsonResponse = null;
        String requestJson = doListProductSearchRequestJson(search);
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
                List<Product> listProduct = null;
                try {
                    listProduct = jsonToListProduct(jsonResponse);
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ({0})", jsonResponse);
                return listProduct;
            }
        }
    }

    
    private String doSaveProductRequestJson(Product newProduct) {
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("post");
        //protocol.addParameter("productId",newProduct.getProductId().toString());
        protocol.addParameter("name",newProduct.getName());
        protocol.addParameter("description", newProduct.getDescription());
        protocol.addParameter("price", newProduct.getPrice().toString());
        protocol.addParameter("state", newProduct.getState());
        protocol.addParameter("stock", newProduct.getStock().toString());
        protocol.addParameter("CategoryId", newProduct.getCategoryId().toString());
        protocol.addParameter("location", newProduct.getLocation().toString());
        protocol.addParameter("userSellerId", newProduct.getUserSellerId().toString());
        

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    
    private String doDeleteProductRequestJson(Long id){
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("delete");
        protocol.addParameter("id", id.toString());
       
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    
    /**
    * Convierte jsonProduct, proveniente del server socket, de json a un  objeto product
    */
    private Product jsonToProduct(String jsonProduct) {

        Gson gson = new Gson();
        Product product = gson.fromJson(jsonProduct, Product.class);
        return product;

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
     * Convierte el jsonError a un array de objetos jsonError
     * @return objeto MyError
     */
    private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }
    /**
     * Crea una solicitud json para ser enviada por el socket
     * @param productId identificación del producto
     * @return solicitud de consulta del producto en formato Json
     */
    private String doFindProductIdRequestJson(Long id) {
       Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("get");
        protocol.addParameter("productId",id.toString());

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }
    
    private String doListProductRequestJson(){
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("findAll");
        
       
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    
    private String doListProductSearchRequestJson(String search){
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("findAllBySearch");
        protocol.addParameter("search", search);
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
        
    }
    
    private String doListProductUserSellerRequestJson(Long id){
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("findAllByUserSeller");
        protocol.addParameter("userSellerId", id.toString());
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    
    private List<Product>  jsonToListProduct(String jsonCustomer) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> listProduct = objectMapper.readValue(jsonCustomer, new TypeReference<List<Product>>(){});

        return listProduct;
    }
    
    private String doEditproductRequestJson(Product producto) {
        Protocol protocol =new Protocol();
        protocol.setResource("product");
        protocol.setAction("edit");
        protocol.addParameter("productId",producto.getProductId().toString());
        protocol.addParameter("name",producto.getName());
        protocol.addParameter("description", producto.getDescription());
        protocol.addParameter("price", producto.getPrice().toString());
        protocol.addParameter("state", producto.getState());
        protocol.addParameter("stock", producto.getStock().toString());
        protocol.addParameter("CategoryId", producto.getCategoryId().toString());
        protocol.addParameter("location", producto.getLocation().toString());
        protocol.addParameter("userSellerId", producto.getUserSellerId().toString());
        
        Gson gson=new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson; 
    }

    @Override
    public List<Product> findByUserSeller(Long id) {
        String jsonResponse = null;
        String requestJson = doListProductUserSellerRequestJson(id);
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
                List<Product> listProduct = null;
                try {
                    listProduct = jsonToListProduct(jsonResponse);
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ({0})", jsonResponse);
                return listProduct;
            }
        }
    }

    

    
}
