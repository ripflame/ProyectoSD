/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosd.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import proyectosd.models.AccountOperation;
import proyectosd.models.OperationType;
import proyectosd.models.User;

/**
 *
 * @author bzeheatnix
 */
public class OperationController {

    AccountOperation ao = new AccountOperation();
    double currentBalance;

    public OperationController() {
    }

    public void beginTransaction() {
        UserController userController = new UserController("127.0.0.1", 1234);
    }

    public void commitTransaction() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void deposit(User user, double amount) throws IOException, ParseException {
        AccountReader ar = new AccountReader();
        ArrayList<AccountOperation> list = ar.readFile(user.getId() + ".txt");
        AccountOperation lastOperation = list.get(list.size() - 1);
        currentBalance = lastOperation.getBalance();
        ao = lastOperation;
        ao.setBalance(currentBalance + amount);
        ao.saveToFile();
    }

    public void withdraw(User user, double amount) throws IOException, ParseException {
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

    public void consult(User user) throws IOException, ParseException {
        AccountReader ar = new AccountReader();
        ArrayList<AccountOperation> list = ar.readFile(user.getId() + ".txt");
        AccountOperation lastOperation = list.get(list.size() - 1);
        currentBalance = lastOperation.getBalance();
        ao = lastOperation;
        ao.setOperation(OperationType.CONSULT);
        ao.saveToFile();
    }
}
