package proyectosd.tests;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectosd.models.AccountOperation;
import proyectosd.models.Operations;
import proyectosd.models.User;

/**
 *
 * @author Gilberto Leon <ripflame@gmail.com>
 */
public class ObjectWriteTest {

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
            ao.saveToFile(user.getId() + ".txt");
            ao.readFile(user.getId() + ".txt");
        } catch (IOException ex) {
            Logger.getLogger(ObjectWriteTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
