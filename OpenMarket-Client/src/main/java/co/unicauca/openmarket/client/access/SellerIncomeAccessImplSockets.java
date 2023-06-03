/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.client.domain.SellerIncome;
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
public class SellerIncomeAccessImplSockets implements ISellerIncomeAccess{
    
    private OpenMarketSocket mySocket;
    
    public SellerIncomeAccessImplSockets() {
        mySocket = new OpenMarketSocket();
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
    
     private String doListSellerIncomeRequestJson(){
        Protocol protocol = new Protocol();
        protocol.setResource("SellerIncome");
        protocol.setAction("findAllSellerIncome");
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    
    private List<SellerIncome>  jsonToListSellerIncome(String jsonCustomer) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<SellerIncome> listSellerIncome = objectMapper.readValue(jsonCustomer, new TypeReference<List<SellerIncome>>(){});

        return listSellerIncome;
    }
    
    private String doSaveSellerIncomeRequestJson(SellerIncome sellerIncome){
        Protocol protocol = new Protocol();
        protocol.setResource("sellerIncome");
        protocol.setAction("post");
//        protocol.addParameter("SellerIncomeId",sellerIncome.getSellerIncomeId().toString());
        protocol.addParameter("income",sellerIncome.getIncome().toString());
        protocol.addParameter("ShoppingId",sellerIncome.getShoppingId().toString());
        
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    @Override
    public boolean save(SellerIncome sellerIncome) throws Exception{
        boolean bandera=false;
        String jsonResponse = null;
        String requestJson = doSaveSellerIncomeRequestJson(sellerIncome);
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
    public List<SellerIncome> findAll() {
        String jsonResponse = null;
        String requestJson = doListSellerIncomeRequestJson();
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
                List<SellerIncome> listSellerIncome = null;
                try {
                    listSellerIncome = jsonToListSellerIncome(jsonResponse);
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ({0})", jsonResponse);
                return listSellerIncome;
            }
        }
    }
}
