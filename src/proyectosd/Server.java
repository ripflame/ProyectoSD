/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosd;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bzeheatnix
 */
public class Server extends Thread {

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        // serverSocket.setSoTimeout(20000);
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Welcome.");
                System.out.println("Waiting for client on port "
                        + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("Succesful connection. Just connected to "
                        + server.getRemoteSocketAddress());
                DataInputStream in =
                        new DataInputStream(server.getInputStream());
                System.out.println(in.readUTF());


                ObjectInputStream in2 = 
                        new ObjectInputStream(server.getInputStream());
                try {
                    Client client = (Client) in2.readObject();
                    call(client);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                

                DataOutputStream out =
                        new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting to "
                        + server.getLocalSocketAddress() + "\nGoodbye!");
                server.close();

            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void call(Client client) {
        System.out.println("Client connected.");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("Client disconnected.");
    }

    /*
     * public void call(String message) { System.out.println(message); try {
     * Thread.sleep(10000); } catch (InterruptedException e) {
     * System.out.println("Interrupted"); } System.out.println("Client
     * disconnected.");
    }
     */
    public static void main(String[] args) {
        int port = 1234;
        try {
            Thread thread = new Server(port);

            synchronized (thread) {
                thread.start();
            }// synchronized block
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private ServerSocket serverSocket;
}