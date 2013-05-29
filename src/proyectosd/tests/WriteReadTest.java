package proyectosd.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectosd.controllers.AccountReader;
import proyectosd.models.AccountOperation;
import proyectosd.models.Operations;
import proyectosd.models.User;

/**
 *
 * @author Gilberto Leon <ripflame@gmail.com>
 */
public class WriteReadTest {

    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setName("Gilberto");
        user.setFirstLastName("León");
        user.setSecondLastName("Enríquez");
        AccountOperation ao = new AccountOperation();
        ao.setUser(user);
        ao.setOperation(Operations.DEPOSIT);
        ao.setBalance(100);

        TimeZone utc = TimeZone.getTimeZone("UTC");
        Calendar calendar = Calendar.getInstance(utc);
        ao.setDate(calendar.getTime());

        try {
            ao.saveToFile();
            AccountReader ar = new AccountReader();
            ArrayList<AccountOperation> al = ar.readFile("1.txt");
            
            for (AccountOperation aof : al) {
                System.out.println(aof);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(WriteReadTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
