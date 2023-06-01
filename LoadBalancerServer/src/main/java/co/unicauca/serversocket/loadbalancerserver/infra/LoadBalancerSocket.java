package co.unicauca.serversocket.loadbalancerserver.infra;

import co.unicauca.serversocket.loadbalancerserver.logic.ServerInfo;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Representa el Socket de la aplicación cliente. Su función es enviar una
 * solicitud/respuesta entre el cliente y el servidor.
 *
 * @author Libardo, Julio
 */
public class LoadBalancerSocket implements ILoadBalancerConnection {

    /**
     * Socket de la aplicación cliente
     */
    private java.net.Socket socket = null;
    /**
     * Permite leer la recibir la respuesta del socket
     */
    private Scanner input;
    /**
     * Permite enviar una solicitud por el socket
     */
    private PrintStream output;
    /**
     * Ip del Server Socket
     */
    private String serverIP;
    /**
     * Puerto del server socket
     */
    private int serverPort;

    public LoadBalancerSocket(){
    }
    
    public LoadBalancerSocket(ServerInfo server){
        serverIP = server.getHost();
        serverPort = server.getPort();
    }
    
    
    /**
     * Envia una solicitud desde la aplicación cliente al servidor mediate el
     * socket
     *
     * @param requestJson solicitud en formato json
     * @return respuesta del scoket
     * @throws IOException error de entrada y salida
     */
    
    @Override
    public String sendRequest(String request) throws IOException {
        String response = "";
        input = new Scanner(socket.getInputStream());
        output = new PrintStream(socket.getOutputStream());
        output.flush();
        input.reset();

        Logger.getLogger(LoadBalancerSocket.class.getName()).log(Level.INFO, "Lo que se le envia al: ("+request+")");
           
        // Enviar la solicitud
        output.println(request);

        // Procesa la respuesta
        if (input.hasNextLine()) {
            response = input.nextLine();
        }
        Logger.getLogger(LoadBalancerSocket.class.getName()).log(Level.INFO, "Lo que se lee del servidor: ("+response+")");
                
        return response;
    }

    /**
     * Cierra los flujos input y output
     */
    @Override
    public void closeStream() {
        output.close();
        input.close();
    }

    /**
     * Desconectar el socket
     */
    @Override
    public void disconnect() {
        closeStream();
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(LoadBalancerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Conectar o abrir un socket
     *
     * @throws IOException error de entrada y salida
     */
    @Override
    public void connect() throws IOException {
        socket = new java.net.Socket(serverIP, serverPort);
        Logger.getLogger("LoadBalancerSocket").log(Level.INFO, "Socket establecido");
    }

    @Override
    public void setServer(ServerInfo selectedServer) {
        serverIP = selectedServer.getHost();
        serverPort = selectedServer.getPort();
    }

}
