
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.serversocket.loadbalancerserver.logic;

import co.unicauca.serversocket.loadbalancerserver.infra.ILoadBalancerConnection;
import co.unicauca.serversocket.loadbalancerserver.infra.LoadBalancerSocket;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import co.unicauca.serversocket.loadbalancerserver.logic.serversscheduling.IServerScheduler;
import co.unicauca.strategyserver.infra.ServerHandler;

/**
 *
 * @author ahurtado, shdorado, wpantoja
 */
public class LoadBalancerHandler extends ServerHandler {

     /**
     * Servicio de clientes
     */
    
    private static IServerScheduler serverScheduler;
    
    public LoadBalancerHandler(){
        super();
    }
    
    
     /**
     * Procesar la solicitud que proviene de la aplicación cliente, esto es,
     * enviar la solicitud al servidor y devolver la respuesta al cliente, 
     * este balanceador trabaja como un intermediario
     * @param requestJson petición que proviene del cliente socket en formato
     * json que viene de esta manera:
     * "{"resource":"customer","action":"get","parameters":[{"name":"id","value":"1"}]}"
     *
     */
   
    
    @Override
    public String processRequest(String requestJson) {
        
        String response="";
        try {
            ILoadBalancerConnection conn = this.getLoadBalancerConnection();
            conn.setServer(serverScheduler.selectServer(this.getSocket()));
            conn.connect();
            response = conn.sendRequest(requestJson);
            // this.respond(conn.sendRequest(requestJson));
            System.out.println("Pasando por el balanceador ...");
            conn.closeStream();
            conn.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(LoadBalancerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    private ILoadBalancerConnection getLoadBalancerConnection(){
        return new LoadBalancerSocket();
    } 

    /**
     * @return the serverScheduler
     */
    public IServerScheduler getServerScheduler() {
        return serverScheduler;
    }

    /**
     * @param serverScheduler the serverScheduler to set
     */
    public void setServerScheduler(IServerScheduler serverScheduler) {
        LoadBalancerHandler.serverScheduler = serverScheduler;
    }
    
}
