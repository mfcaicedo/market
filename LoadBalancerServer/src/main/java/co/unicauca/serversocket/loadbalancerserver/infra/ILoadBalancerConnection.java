/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.serversocket.loadbalancerserver.infra;

import co.unicauca.serversocket.loadbalancerserver.logic.ServerInfo;
import java.io.IOException;

/**
 *
 * @author ahurtado
 */
public interface ILoadBalancerConnection {

    /**
     * Cierra los flujos input y output
     */
    void closeStream();

    /**
     * Conectar o abrir un socket
     *
     * @throws IOException error de entrada y salida
     */
    void connect() throws IOException;

    /**
     * Desconectar el socket
     */
    void disconnect();

    /**
     * Envia una solicitud desde la aplicación cliente al servidor mediate el
     * socket
     *
     * @param request solicitud en formato string
     * @return respuesta del scoket
     * @throws IOException error de entrada y salida
     */
    String sendRequest(String request) throws IOException;

    public void setServer(ServerInfo selectedServer);
    
}
