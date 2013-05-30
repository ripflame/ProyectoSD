/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosd.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectosd.controllers.AccountReader;
import proyectosd.controllers.OperationController;
import proyectosd.models.AccountOperation;
import proyectosd.models.User;
import rmi.Client;

/**
 *
 * @author bzeheatnix
 */
public class BankTest {

    public static void main(String[] args) {

        try {
            User user = new User();
            user.setId(1);
            user.setName("Gilberto");
            user.setFirstLastName("León");
            user.setSecondLastName("Enríquez");
            AccountOperation ao = new AccountOperation();
            ao.setUser(user);
            ao.setBalance(100);
            Client occ;
            
            occ = (Client) Naming.lookup("//localhost:1099/BankSystem");
            occ = new OperationController();
    //        OperationController oc = new OperationController();

    //        try {
    //            oc.deposit(user, 80);
    //        } catch (IOException ex) {
    //            Logger.getLogger(WriteReadTest.class.getName()).log(Level.SEVERE, "IO Exception", ex);
    //        } catch (ParseException ex) {
    //            Logger.getLogger(WriteReadTest.class.getName()).log(Level.SEVERE, "Parse Exception", ex);
    //        }

    //        try {
    //            oc.withdraw(user, 1380);
    //        } catch (IOException ex) {
    //            Logger.getLogger(WriteReadTest.class.getName()).log(Level.SEVERE, "IO Exception", ex);
    //        } catch (ParseException ex) {
    //            Logger.getLogger(WriteReadTest.class.getName()).log(Level.SEVERE, "Parse Exception", ex);
    //        }

            try {
                occ.consult(user);
            } catch (IOException ex) {
                Logger.getLogger(WriteReadTest.class.getName()).log(Level.SEVERE, "IO Exception", ex);
            } catch (ParseException ex) {
                Logger.getLogger(WriteReadTest.class.getName()).log(Level.SEVERE, "Parse Exception", ex);
            }

            TimeZone utc = TimeZone.getTimeZone("UTC");
            Calendar calendar = Calendar.getInstance(utc);
            ao.setDate(calendar.getTime());

            try {
                //ao.saveToFile();
                AccountReader ar = new AccountReader();
                ArrayList<AccountOperation> al = null;
                al = ar.readFile("1.txt");

                for (AccountOperation aof : al) {
                    System.out.println("AR: " + aof);
                }

            } catch (IOException ex) {
                Logger.getLogger(WriteReadTest.class.getName()).log(Level.SEVERE, "IO Exception", ex);
            } catch (ParseException ex) {
                Logger.getLogger(WriteReadTest.class.getName()).log(Level.SEVERE, "Couldn't parse line", ex);
            }
        } catch (NotBoundException ex) {
            Logger.getLogger(BankTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(BankTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(BankTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
