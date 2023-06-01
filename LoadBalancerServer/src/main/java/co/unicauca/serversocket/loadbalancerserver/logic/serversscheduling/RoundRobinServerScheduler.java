/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.serversocket.loadbalancerserver.logic.serversscheduling;

import co.unicauca.serversocket.loadbalancerserver.infra.Subject;
import co.unicauca.serversocket.loadbalancerserver.logic.ServerInfo;
import co.unicauca.serversocket.loadbalancerserver.logic.servermanagement.IServerManagement;
import java.net.Socket;
import java.util.HashMap;

/**
 *
 * @author ahurtado
 */
public class RoundRobinServerScheduler extends Subject implements IServerScheduler {
    private IServerManagement servers;
    private static int requestNumber=-1;
    private ServerInfo actualServer = null;
    
    public RoundRobinServerScheduler(IServerManagement servers){
      this.servers = servers; 
    }

    @Override
    public ServerInfo selectServer(Socket socket) {
        // int hashcode = socket.getInetAddress().hashCode();
        // return this.serverList.get( hashcode % serverList.size() );
        requestNumber++;
        actualServer = servers.getServer(requestNumber % servers.size());
        this.notifyAllObserves();
        return actualServer;
    }

    @Override
    public ServerInfo getActualServer() {
        return actualServer;
    } 
}
