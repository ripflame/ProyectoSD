/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;
import proyectosd.models.User;

/**
 *
 * @author bzeheatnix
 */
public interface Client extends Remote {
    
    public void beginTransaction()
            throws RemoteException;
    
    public void commitTransaction()
            throws RemoteException;
    
    public void deposit(User user, double amount)
            throws RemoteException, IOException, ParseException;
    
    public void withdraw(User user, double amount)
            throws RemoteException, IOException, ParseException;
    
    public void consult(User user)
            throws RemoteException, IOException, ParseException;
    
}
