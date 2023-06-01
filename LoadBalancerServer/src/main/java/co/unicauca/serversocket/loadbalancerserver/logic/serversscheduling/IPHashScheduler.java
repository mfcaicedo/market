/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.serversocket.loadbalancerserver.logic.serversscheduling;

import co.unicauca.serversocket.loadbalancerserver.logic.ServerInfo;
import co.unicauca.serversocket.loadbalancerserver.logic.servermanagement.IServerManagement;
import java.net.Socket;

/**
 *
 * @author ahurtado
 */
public class IPHashScheduler implements IServerScheduler{
    private IServerManagement servers;
    private static int requestNumber=-1;
    private ServerInfo actualServer = null;
    
    public IPHashScheduler(IServerManagement servers){
      this.servers = servers; 
    }

    @Override
    public ServerInfo selectServer(Socket socket) {
        int hashcode = socket.getInetAddress().hashCode();
        actualServer = servers.getServer(hashcode % servers.size());
        return actualServer;
    }
    
    @Override
    public ServerInfo getActualServer() {
        return actualServer;
    } 
}
