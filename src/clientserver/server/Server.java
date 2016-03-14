/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hai
 */
public class Server {
    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());
    public static void main(String[] args) throws IOException {
        boolean listening = true;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("Server is Listening to port   " + 4444);
            int i = 1;
            while (listening) {
                Socket clientSocket = serverSocket.accept();
                Thread serveClient = new ServerHandler(clientSocket, i);
                serveClient.start();
                System.out.println("Server ready to serve Client " + i);
                i++;
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Exception occur, Could not listen on port: 4444", e);
            System.exit(1);
        } finally {
            if(serverSocket != null)
                serverSocket.close();
        }

    }

}
