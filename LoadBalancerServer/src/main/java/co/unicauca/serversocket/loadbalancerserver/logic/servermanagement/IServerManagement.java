/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.serversocket.loadbalancerserver.logic.servermanagement;

import co.unicauca.serversocket.loadbalancerserver.logic.ServerInfo;

/**
 *
 * @author ahurtado, shdorado, wpantoja
 */
public interface IServerManagement {

    void addServer(ServerInfo newServer);

    void deleteServer(int serverCode);

    ServerInfo getServer(int index);

    public int size();
    
}
