/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.serversocket.loadbalancerserver.logic.servermanagement;

import co.unicauca.serversocket.loadbalancerserver.logic.ServerInfo;
import java.util.HashMap;

/**
 *
 * @author ahurtado, shdorado,wpantoja
 */
public class HashListServerManagement implements IServerManagement {
    
    private final HashMap<Integer, ServerInfo> serverList;
    
    public HashListServerManagement(){
        serverList = new HashMap<>();
        serverList.put(0, new ServerInfo("localhost",5003));
        serverList.put(1, new ServerInfo("localhost",5004)); 
        serverList.put(2, new ServerInfo("localhost",5006)); 
    }
    
    @Override
    public ServerInfo getServer(int index){
        return serverList.get(index);
    }
    
    @Override
    public void addServer(ServerInfo newServer){
        serverList.put(serverList.size(), newServer);
    }
    
    @Override
    public void deleteServer(int serverCode){
        if (serverList.containsKey(serverCode)){
            serverList.remove(serverCode);
        }
    }

    @Override
    public int size() {
        return serverList.size();
    }
 }
