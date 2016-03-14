/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserver.server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hai
 */
public class ServerHandler extends Thread {

    private static final Logger LOGGER = Logger.getLogger(ServerHandler.class.getName());
    private static final String File_Line_Header = "#HEADER_V_1#";
    private static final boolean DEBUG = false;
    private Socket socket = null;
    private int clientNumber = 0;
    private BufferedReader in;
    private PrintWriter out;
    
    public ServerHandler(Socket clientSocket, int i) {
        socket = clientSocket;
        this.clientNumber = i;
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        }catch(IOException e){
            LOGGER.log(Level.SEVERE, "IO Exception occur", e);
        }       
    }

    //This method is called when the thread runs
    public void run() {
        try {
            // read the file name from the socket connection
            String incomingMsgFromClient = null;
            while ((incomingMsgFromClient = in.readLine()) != null) {
                if(DEBUG)System.out.println("Msg recieved from the client  " + clientNumber + " : " + incomingMsgFromClient);

                if (incomingMsgFromClient.equals("#SendFile#")) {
                    FileInputStream fis = new FileInputStream("islands_in_the_stream.txt");
					BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String strLine;
                    while ((strLine = br.readLine()) != null) {
                        if(DEBUG)System.out.println(strLine);
                        out.println(File_Line_Header + strLine);
                    }
                    br.close();
                }
            }
            out.flush();
            if(DEBUG)System.out.println("Client  Terminated....");                      
        }catch(FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "File Not Found Exception occur", e);
        }catch (IOException e){
            LOGGER.log(Level.SEVERE, "IO Exception occured while reading from socket: ", e);
        }catch (Exception e) {
            LOGGER.log(Level.SEVERE, "General Exception occur", e);
        }finally{
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "IO Exception occur while closing connection: ", e);
            }
        }
    }

}
