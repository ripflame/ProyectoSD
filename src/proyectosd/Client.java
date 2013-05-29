/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosd;

/**
 *
 * @author bzeheatnix
 */
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client implements java.io.Serializable {

    public Client(String ipAddress) {
        
    }
    
    public static void main(String[] args) {

        String ipAddress = "127.0.0.1";
        int port = 1234;
        
        try {
            System.out.println("Connecting to " + ipAddress
                    + " on port " + port);
            Socket client = new Socket(ipAddress, port);
            System.out.println("Just connected to "
                    + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream outData =
                    new DataOutputStream(outToServer);
            outData.writeUTF("Client with address: " + client.getLocalAddress());
            
            ObjectOutputStream oos = new ObjectOutputStream(outToServer);
            
            
            InputStream inFromServer = client.getInputStream();
            DataInputStream in =
                    new DataInputStream(inFromServer);

            System.out.println("Type Exit. Then press Enter to finish.");
            Scanner scan = new Scanner(System.in);
            String type = scan.nextLine();
            if (type.equals("Exit")) {
                System.out.println("Server says " + in.readUTF());
                client.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * String message; Server server; Thread thread;
     *
     * public Client(Server server, String message) { this.server = server;
     * this.message = message; thread = new Thread(this); thread.start(); }
     *
     *
     * @Override public void run() { synchronized (server) { // synchronized
     * block server.call(message); } }
     */
}