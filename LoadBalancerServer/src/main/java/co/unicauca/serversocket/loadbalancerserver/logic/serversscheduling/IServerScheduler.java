/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.serversocket.loadbalancerserver.logic.serversscheduling;

import co.unicauca.serversocket.loadbalancerserver.logic.ServerInfo;
import java.net.Socket;

/**
 *
 * @author ahurtado
 */
public interface IServerScheduler {
    public ServerInfo selectServer(Socket socket);
    public ServerInfo getActualServer();
}
