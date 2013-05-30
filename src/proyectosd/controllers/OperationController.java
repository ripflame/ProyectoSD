/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosd.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectosd.models.AccountOperation;
import proyectosd.models.OperationType;
import proyectosd.models.User;
import rmi.Client;

/**
 *
 * @author bzeheatnix
 */
public class OperationController extends UnicastRemoteObject implements Client {

    AccountOperation ao = new AccountOperation();
    double currentBalance;

    public OperationController() throws RemoteException {
        super();
    }

    @Override
    public void beginTransaction() throws RemoteException {
        
    }

    @Override
    public void commitTransaction() throws RemoteException {
        
    }

    @Override
    public void deposit(User user, double amount) throws RemoteException, IOException, ParseException {
        AccountReader ar = new AccountReader();
        ArrayList<AccountOperation> list = ar.readFile(user.getId() + ".txt");
        AccountOperation lastOperation = list.get(list.size() - 1);
        currentBalance = lastOperation.getBalance();
        ao = lastOperation;
        ao.setBalance(currentBalance + amount);
        ao.saveToFile();
    }

    @Override
    public void withdraw(User user, double amount) throws RemoteException, IOException, ParseException {
        AccountReader ar = new AccountReader();
        ArrayList<AccountOperation> list = ar.readFile(user.getId() + ".txt");
        AccountOperation lastOperation = list.get(list.size() - 1);
        currentBalance = lastOperation.getBalance();
        ao = lastOperation;
        ao.setOperation(OperationType.WITHDRAW);
        if (currentBalance < amount) {
            System.out.println("Not enough funds");
        } else {
            ao.setBalance(currentBalance - amount);
            ao.saveToFile();
        }

    }

    @Override
    public void consult(User user) throws RemoteException, IOException, ParseException {
        AccountReader ar = new AccountReader();
        ArrayList<AccountOperation> list = ar.readFile(user.getId() + ".txt");
        AccountOperation lastOperation = list.get(list.size() - 1);
        currentBalance = lastOperation.getBalance();
        ao = lastOperation;
        ao.setOperation(OperationType.CONSULT);
        ao.saveToFile();
    }
}
