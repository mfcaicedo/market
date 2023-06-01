/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.serversocket.loadbalancerserver.presentation;

import co.unicauca.serversocket.loadbalancerserver.logic.LoadBalancerHandler;
import co.unicauca.serversocket.loadbalancerserver.logic.servermanagement.HashListServerManagement;
import co.unicauca.serversocket.loadbalancerserver.logic.serversscheduling.IServerScheduler;
import co.unicauca.serversocket.loadbalancerserver.logic.serversscheduling.RoundRobinServerScheduler;
import co.unicauca.strategyserver.infra.ServerSocketMultiThread; 

/**
 *
 * @author ahurtado
 */
public class LoadBalancerServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ServerSocketMultiThread myServer = new ServerSocketMultiThread(5005);
        IServerScheduler scheduler = new RoundRobinServerScheduler(new HashListServerManagement());
        ((RoundRobinServerScheduler)scheduler).addObserver(new LoadBalancerGUI());
        LoadBalancerHandler myHandler = new LoadBalancerHandler();
        myHandler.setServerScheduler(scheduler);
        myServer.setServerHandler(myHandler);  
        myServer.startServer();
    }
    
}
