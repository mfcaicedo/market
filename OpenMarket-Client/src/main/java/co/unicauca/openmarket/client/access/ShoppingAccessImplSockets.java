/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.client.domain.Product;
import co.unicauca.openmarket.client.domain.Shopping;
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
 * @author Personal
 */
public class ShoppingAccessImplSockets implements IShoppingAccess {

    private OpenMarketSocket mySocket;

    public ShoppingAccessImplSockets() {
        mySocket = new OpenMarketSocket();
    }

    @Override
    public boolean save(Shopping shopping) throws Exception {
        boolean bandera = false;
        String jsonResponse = null;
        String requestJson = doSaveShoppingRequestJson(shopping);
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
                bandera = true;
            }

        }
        return bandera;
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
    public List<Shopping> findAll() {
        String jsonResponse = null;
        String requestJson = doListShoppingRequestJson();
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
                //Encuentró el shopping        
                List<Shopping> listShopping = null;
                try {
                    listShopping = jsonToListShopping(jsonResponse);
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ({0})", jsonResponse);
                return listShopping;
            }
        }
    }

    @Override
    public List<Shopping> findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String doSaveShoppingRequestJson(Shopping shopping) {
        Protocol protocol = new Protocol();
        protocol.setResource("shopping");
        protocol.setAction("post");
//        protocol.addParameter("shoppingId",shopping.getShoppingId().toString());
        protocol.addParameter("userBuyerId", shopping.getUserBuyerId().toString());
        protocol.addParameter("productId", shopping.getProductId().toString());

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    private String doListShoppingRequestJson() {
        Protocol protocol = new Protocol();
        protocol.setResource("shopping");
        protocol.setAction("findAllShopping");

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    private List<Shopping> jsonToListShopping(String jsonCustomer) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Shopping> listShopping = objectMapper.readValue(jsonCustomer, new TypeReference<List<Shopping>>() {
        });

        return listShopping;
    }

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

    private String doFindShoppingIdRequestJson(Long productid) {
        Protocol protocol = new Protocol();
        protocol.setResource("shopping");
        protocol.setAction("findByProductId");
        protocol.addParameter("productId", productid.toString());

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }

    @Override
    public Shopping findByproductId(Long productid) throws Exception {
        System.out.println("LLEGO A FINSBYPRUDUCTID");
        String jsonResponse = null;
        String requestJson = doFindShoppingIdRequestJson(productid);
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
                Shopping shopping = jsonToShopping(jsonResponse);
                Logger.getLogger(ShoppingAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return shopping;
            }
        }
    }

    private Shopping jsonToShopping(String jsonShopping) {

        Gson gson = new Gson();
        Shopping shopping = gson.fromJson(jsonShopping, Shopping.class);
        return shopping;

    }

}
