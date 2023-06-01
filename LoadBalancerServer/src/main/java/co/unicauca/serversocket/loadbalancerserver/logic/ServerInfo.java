/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.serversocket.loadbalancerserver.logic;

/**
 *
 * @author ahurtado
 */
public class ServerInfo {
    private String host;
    private int port;

    public ServerInfo(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * @return the Host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param Host the Host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }
    
}
