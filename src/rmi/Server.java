/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.Naming;
import proyectosd.controllers.OperationController;

/**
 *
 * @author bzeheatnix
 */
public class Server {
    
    public Server() {
        try {
            Client client = new OperationController();
            Naming.rebind("rmi://localhost:1099/BankSystem", client);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    public static void main(String[] args) {
        new Server();
    }
    
}
